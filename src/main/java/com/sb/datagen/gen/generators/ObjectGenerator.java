package com.sb.datagen.gen.generators;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sb.datagen.gen.Generator;
import com.sb.datagen.gen.GeneratorFactory;
import org.everit.json.schema.ObjectSchema;
import org.everit.json.schema.Schema;

import java.util.Map;

public class ObjectGenerator extends Generator<JsonNode> {
    public ObjectGenerator(Schema schema) {
        super(schema);
    }
    @Override
    public JsonNode generate() {
        if (schema instanceof ObjectSchema objectSchema) {
            Map<String, Schema> map = objectSchema.getPropertySchemas();
            ObjectNode object = mapper.createObjectNode();
            for (Map.Entry<String, Schema> entry : map.entrySet()) {
                String key = entry.getKey();
                object.put(key,  mapper.convertValue(GeneratorFactory.getGenerator(entry.getValue()).generate(), JsonNode.class));
            }
            return mapper.convertValue(object, JsonNode.class);
        }
        return null;
    }
}
