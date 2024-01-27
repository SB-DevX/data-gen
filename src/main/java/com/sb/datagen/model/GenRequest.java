package com.sb.datagen.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenRequest {
    private Integer noOfTimes;
    private JsonNode schema;
    private String outputFilePath;
    @Builder.Default
    private GenType genType = GenType.defaultType();
}
