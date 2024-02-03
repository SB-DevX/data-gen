package com.sb.datagen.model;

import com.sb.datagen.service.impl.FileGeneratorService;
import com.sb.datagen.service.impl.SimpleGeneratorService;

public enum GenType {
    SIMPLE_GEN(SimpleGeneratorService.class), FILE_GEN(FileGeneratorService.class);
    
    private final Class<?> genClass;
    GenType(Class<?> genClass) {
        this.genClass = genClass;
    }
    
    Class<?> getGenClass() {
        return this.genClass;
    }
    
    static GenType defaultType() {
        return SIMPLE_GEN;
    }
}
