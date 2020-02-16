package com.trainingground.efm.test;

import java.util.HashMap;
import java.util.Map;

import com.trainingground.efm.block.CodeBlock;
import com.trainingground.efm.block.CodeBlockPrintFile;
import com.trainingground.efm.block.CodeBlockReadFile;
import com.trainingground.efm.datastruct.SafeMap;
import com.trainingground.efm.datastruct.graph.Graph;
import com.trainingground.efm.flow.CodeFlow;

public class TestFlowFileReader {
	public static void main(String[] args) throws InterruptedException {
		System.out.println(System.getProperty("user.dir"));

		Graph<CodeBlock> flow = new Graph<>();

		CodeBlock cb1 = new CodeBlockReadFile("C:/Users/Antonio/Desktop/prj-forms-final/tslint.json");
		flow.addNode(cb1);
		
		CodeBlock cb2 = new CodeBlockPrintFile();
		flow.addNode(cb2);

		CodeFlow cf = new CodeFlow();
		cf.setFlow(flow);

		Map<String, Object> parameters = new HashMap<>();

		cf.setParameters(new SafeMap<String, Object>(parameters));

		cf.launch();
	}
}
