package com.sb.datagen.controller;

import com.sb.datagen.model.GenRequest;
import com.sb.datagen.service.GeneratorServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("json/data")
public class GeneratorController {
    @Autowired
    private GeneratorServiceFacade generatorServiceFacade;
    
    @PostMapping("generate")
    public ResponseEntity<Object> generate(@RequestBody GenRequest genRequest) {
        Object result = this.generatorServiceFacade.generate(genRequest);
        return switch (genRequest.getGenType()) {
            case FILE_GEN -> ResponseEntity.accepted().body(result);
            default -> ResponseEntity.ok(result);
        };
    }
}
