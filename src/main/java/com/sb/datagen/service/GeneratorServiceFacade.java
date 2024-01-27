package com.sb.datagen.service;

import com.sb.datagen.model.GenRequest;
import com.sb.datagen.service.impl.FileGeneratorService;
import com.sb.datagen.service.impl.SimpleGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneratorServiceFacade {
    @Autowired
    private SimpleGeneratorService simpleGeneratorService;
    @Autowired
    private FileGeneratorService fileGeneratorService;
    
    public Object generate(GenRequest genRequest) {
       return switch (genRequest.getGenType()) {
            case FILE_GEN -> this.fileGeneratorService.generate(genRequest);
            default ->  this.simpleGeneratorService.generate(genRequest);
       };
    }
}
