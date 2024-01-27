package com.sb.datagen.gen.generators;

import com.sb.datagen.gen.Generator;
import com.sb.datagen.gen.GeneratorFactory;
import org.everit.json.schema.ReferenceSchema;
import org.everit.json.schema.Schema;

public class ReferenceGenerator extends Generator<Object> {

    public ReferenceGenerator(Schema schema) {
        super(schema);
    }

    @Override
    public Object generate() {
        Schema referredSchema = ((ReferenceSchema) schema).getReferredSchema();
        return GeneratorFactory.getGenerator(referredSchema).generate();
    }
}
