package com.sb.datagen.configs;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Configs {
    
    @Value("${data.file.gen.batch.size:10000}")
    private int fileGenBatchSize;
}
