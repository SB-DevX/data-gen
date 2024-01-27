package com.sb.datagen.gen.generators;

import com.sb.datagen.gen.Generator;
import org.everit.json.schema.Schema;

public class NumberGenerator extends Generator<Number> {

	public NumberGenerator(Schema schema) {
		super(schema);
	}

	@Override
	public Number generate() {
		return random.nextInt();
	}
}