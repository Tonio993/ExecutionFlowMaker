package com.trainingground.efm.block;

import com.trainingground.efm.datastruct.SafeEntry;

public class CodeBlockDouble extends CodeBlock {
	
	private static final String VALUE_TO_DOUBLE_FIELD = "VALUE_TO_DOUBLE";

	public void execute() throws InterruptedException {
		SafeEntry<String, Object> var = parameters.get(VALUE_TO_DOUBLE_FIELD);
		if (var.get() != null) {
			var.set(Double.parseDouble(var.get().toString()) * 2);
			System.out.println(getClass().getName() + ": parameter name = " + mapping.get(VALUE_TO_DOUBLE_FIELD) + ", new value = " + var.get());
		}
		var.release();
	}
	
}
