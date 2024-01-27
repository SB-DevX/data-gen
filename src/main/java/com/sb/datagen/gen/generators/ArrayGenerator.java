package com.sb.datagen.gen.generators;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.sb.datagen.gen.Generator;
import com.sb.datagen.gen.GeneratorFactory;
import org.everit.json.schema.ArraySchema;
import org.everit.json.schema.Schema;

public class ArrayGenerator extends Generator<JsonNode> {
	
	public ArrayGenerator(Schema schema) {
		super(schema);
	}
	
	@Override
	public JsonNode generate() {
		if (schema instanceof ArraySchema arraySchema) {
            Schema allItemSchema = arraySchema.getAllItemSchema();
			ArrayNode jsonArray = mapper.createArrayNode();
			jsonArray.add(mapper.convertValue(GeneratorFactory.getGenerator(allItemSchema).generate(), JsonNode.class));
			return mapper.convertValue(jsonArray, JsonNode.class);
		}
		return null;
	}
}