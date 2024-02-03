package com.sb.datagen.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.sb.datagen.model.GenRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GeneratorServiceFacadeTest {
    private static final ObjectMapper mapper = new ObjectMapper();
    
    @Autowired
    private GeneratorServiceFacade generatorServiceFacade;
    private GenRequest genRequest = GenRequest.builder().build();
    
    @BeforeEach
    public void init() throws IOException {
        InputStream schemaStream =GeneratorServiceFacade.class.getClassLoader().getResourceAsStream("__request__/request1.json");
        genRequest = mapper.readValue(schemaStream, GenRequest.class);
    }
    
    @Test
    void testGenerateJsonDataBasedOnSchema() {
        ArrayNode generatedData  = (ArrayNode) this.generatorServiceFacade.generate(genRequest);
        assertEquals(5, generatedData.size(), "Length of the given generated data does not match.");
    }
}
