package com.sb.datagen.gen.generators;

import com.sb.datagen.gen.Generator;
import org.everit.json.schema.Schema;

public class StringGenerator extends Generator<String> {

	String subset = "abcdefghijklmnopqrstuvwxyz";

	public StringGenerator(Schema schema) {
		super(schema);
	}

	@Override
	public String generate() {
		int length = 7;
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < length; i++) {
			int index = random.nextInt(subset.length());
			char c = subset.charAt(index);
			sb.append(c);
		}
		return sb.toString();
	}
}