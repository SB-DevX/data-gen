package com.sb.datagen.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sb.datagen.model.GenRequest;


public abstract class GeneratorService<T> {
    protected final ObjectMapper mapper = new ObjectMapper();
    public abstract T generate(GenRequest genRequest);
}
