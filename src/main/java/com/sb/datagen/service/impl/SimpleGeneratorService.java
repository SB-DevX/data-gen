package com.sb.datagen.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.sb.datagen.gen.GeneratorFactory;
import com.sb.datagen.model.GenRequest;
import com.sb.datagen.service.GeneratorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class SimpleGeneratorService extends GeneratorService<JsonNode> {
    
    public JsonNode generate(GenRequest genRequest) {
        List<?> generatedData = IntStream.range(0, Optional.ofNullable(genRequest.getNoOfTimes()).orElse(1)).mapToObj(_$ ->  GeneratorFactory.getGenerator(genRequest.getSchema()).generate()).toList();
        return this.mapper.convertValue(generatedData, JsonNode.class);
    }
}
