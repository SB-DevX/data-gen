package com.sb.datagen.gen;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.everit.json.schema.Schema;

import java.util.Random;

public abstract class Generator<T> {
    protected final Schema schema;
    protected  final Random random = new Random();
    protected  final ObjectMapper mapper = new ObjectMapper();
    
    public Generator(Schema schema) {
        this.schema = schema;
    }
    public abstract T generate();
}
