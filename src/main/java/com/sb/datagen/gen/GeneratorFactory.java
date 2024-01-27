package com.sb.datagen.gen;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sb.datagen.gen.generators.*;
import org.everit.json.schema.*;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Optional;

public class GeneratorFactory {
    private static final ObjectMapper mapper = new ObjectMapper();
    
    public static Generator<?> getGenerator(Schema schema) {
        Generator<?> generator = null;
        
        if (schema instanceof StringSchema) {
            generator = new StringGenerator(schema);
        } else if (schema instanceof NumberSchema) {
            generator = new NumberGenerator(schema);
        } else if (schema instanceof BooleanSchema) {
            generator = new BooleanGenerator(schema);
        } else if (schema instanceof ObjectSchema) {
            generator = new ObjectGenerator(schema);
        } else if (schema instanceof ArraySchema) {
            generator = new ArrayGenerator(schema);
        } else if (schema instanceof EnumSchema) {
            generator = new EnumGenerator(schema);
        } else if (schema instanceof ReferenceSchema) {
            generator = new ReferenceGenerator(schema);
        }
        
        return generator;
    }
    
    public static Generator<?> getGenerator(JsonNode schemaNode) {
        Schema schema = getSchema(schemaNode);
        return getGenerator(schema);
    }
    
    private static Schema getSchema(JsonNode schemaNode) {
        InputStream jsonSchemaStream = Optional.ofNullable(schemaNode).map(jsonNode -> {
            InputStream inputStream;
            try {
                byte[] bytes = mapper.writeValueAsBytes(jsonNode);
                inputStream = new ByteArrayInputStream(bytes);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return inputStream;
        }).orElseThrow(() -> new RuntimeException("Json schema is not configured"));
        JSONObject rawSchema = new JSONObject(new JSONTokener(jsonSchemaStream));
        return SchemaLoader.load(rawSchema);
    }
}
