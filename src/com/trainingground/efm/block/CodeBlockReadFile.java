package com.trainingground.efm.block;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.trainingground.efm.datastruct.SafeEntry;

public class CodeBlockReadFile extends CodeBlock {
	
	private static final String FILE_PATH = "FILE_PATH";
	private static final String FILE_READER = "FILE_READER";

	public void execute() throws InterruptedException {
		SafeEntry<String, Object> var = parameters.get(FILE_PATH);
		if (var.get() != null) {
			File file = new File(var.get().toString());
			if (file.exists()) {
				SafeEntry<String, Object> fileEntry = parameters.get(FILE_READER);
				try {
					fileEntry.set(new Scanner(file));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				fileEntry.release();
			}
		}
		var.release();
	}
	
}
