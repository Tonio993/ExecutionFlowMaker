package com.trainingground.efm.block;

public class CodeBlockPrint extends CodeBlock {
	
	private String print;
	
	public CodeBlockPrint(String print) {
		this.print = print;
	}

	@Override
	protected void execute() throws InterruptedException {
		System.out.println(print);
	}
	
	@Override
	public String toString() {
		return "Print: " + print;
	}

}
