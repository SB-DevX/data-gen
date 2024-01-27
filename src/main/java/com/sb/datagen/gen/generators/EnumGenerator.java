package com.sb.datagen.gen.generators;

import com.sb.datagen.gen.Generator;
import org.everit.json.schema.EnumSchema;
import org.everit.json.schema.Schema;

import java.util.List;

public class EnumGenerator extends Generator<Object> {

    public EnumGenerator(Schema schema) {
        super(schema);

    }

    @Override
    public Object generate() {
        List<Object> possibleValuesAsList = ((EnumSchema) schema).getPossibleValuesAsList();
        return possibleValuesAsList.get(random.nextInt(possibleValuesAsList.size()));
    }
}
