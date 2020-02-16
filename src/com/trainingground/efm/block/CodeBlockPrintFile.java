package com.trainingground.efm.block;

import java.util.Scanner;

import com.trainingground.efm.datastruct.SafeEntry;

public class CodeBlockPrintFile extends CodeBlock {
	
	private static final String FILE_READER = "FILE_READER";

	public void execute() throws InterruptedException {
		SafeEntry<String, Object> var = parameters.get(FILE_READER);
		if (var.get() != null) {
			Scanner scanner = (Scanner) var.get();
			while(scanner.hasNext()) {
				System.out.println(scanner.hasNextLine());
			}
		}
		var.release();
	}
	
}
