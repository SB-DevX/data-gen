package com.sb.datagen.service.impl;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sb.datagen.configs.Configs;
import com.sb.datagen.gen.GeneratorFactory;
import com.sb.datagen.model.GenRequest;
import com.sb.datagen.service.GeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.LongStream;

@Service
@Slf4j
public class FileGeneratorService extends GeneratorService<Boolean> {
    
    @Autowired
    private Configs configs;
    
    public Boolean generate(GenRequest genRequest)  {
        int batchSize = configs.getFileGenBatchSize();
        try {
            Thread.startVirtualThread(() -> Optional.ofNullable(genRequest.getOutputFilePath()).ifPresent(filePath -> {
                final String dynamicFilePath = createDynamicDirectory(filePath);
                int noOfTimes = Optional.ofNullable(genRequest.getNoOfTimes()).orElse(1);
                Pair<Long, Long> batches = calculateBatches(noOfTimes, batchSize);
                long noOfBatches = batches.getLeft();
                long noOfTimesInLastBatch = batches.getRight();
                log.info("NoOfBatches :: " + noOfBatches + " noOfTimesInLastBatch " + noOfTimesInLastBatch);
                try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
                    LongStream.range(0, noOfBatches).forEach(_$ -> executor.execute(getTask(genRequest, batchSize, dynamicFilePath)));
                }
                process(genRequest, noOfTimesInLastBatch, dynamicFilePath);
            }));
        }catch (Exception e) {
            return false;
        }
        return true;
    }
    
    private Pair<Long, Long> calculateBatches(int noOfTimes, int batchSize) {
        long noOfBatches = noOfTimes / batchSize;
        long noOfTimesInLastBatch = noOfTimes % batchSize;
        if(noOfTimesInLastBatch == 0) {
            noOfBatches--;
            noOfTimesInLastBatch = batchSize;
        }
        return Pair.of(noOfBatches, noOfTimesInLastBatch);
    }
    
    private String createDynamicDirectory(String filePath) {
        Path path = Paths.get(filePath);
        String fileName = path.getFileName().toString();
        String directory = Paths.get(path.getParent().toString() ,String.valueOf((new Date()).getTime())).toString();
        File theDir = new File(directory);
        if (!theDir.exists()){
            theDir.mkdirs();
        }
        return Paths.get(directory, fileName).toString();
    }
    
    private void storeIntoFile(JsonNode data, String filePath) throws IOException {
        ObjectWriter writer = this.mapper.writer(new DefaultPrettyPrinter());
        String[] filePathPartsByDots = filePath.split("[.]");
        String actualFilePath = filePath.substring(0, filePath.lastIndexOf('.')) + "_" + Thread.currentThread().threadId() + "." + filePathPartsByDots[filePathPartsByDots.length -1];
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(actualFilePath, true)));
        writer.writeValue(out, data);
    }
    
    private void process(GenRequest genRequest, long noOfTimes, String filePath) {
        log.info("Processing in Thread :: " + Thread.currentThread() + " at :: " + new Date());
        
        List<?> generatedData = LongStream.range(0, noOfTimes).mapToObj(_$ -> GeneratorFactory.getGenerator(genRequest.getSchema()).generate()).toList();
        try {
            storeIntoFile(this.mapper.convertValue(generatedData, JsonNode.class), filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    private Runnable getTask(GenRequest genRequest, long noOfTimes, String filePath) {
        return () -> process(genRequest, noOfTimes, filePath);
    }
}
