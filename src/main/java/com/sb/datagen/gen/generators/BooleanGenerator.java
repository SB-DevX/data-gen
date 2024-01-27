package com.sb.datagen.gen.generators;

import com.sb.datagen.gen.Generator;
import org.everit.json.schema.Schema;

public class BooleanGenerator extends Generator<Boolean> {

	public BooleanGenerator(Schema schema) {
		super(schema);
	}

	@Override
	public Boolean generate() {
		return random.nextBoolean();
	}
}