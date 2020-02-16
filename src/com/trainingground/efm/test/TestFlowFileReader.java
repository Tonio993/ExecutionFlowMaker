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
		Graph<CodeBlock> flow = new Graph<>();

		CodeBlock cb1 = new CodeBlockReadFile();
		Map<String, String> mapping1 = new HashMap<String, String>();
		mapping1.put("FILE_PATH", "FILE_PATH");
		mapping1.put("FILE_READER", "FILE_READER");
		cb1.setMapping(mapping1);
		flow.addNode(cb1);
		
		CodeBlock cb2 = new CodeBlockPrintFile();
		Map<String, String> mapping2 = new HashMap<String, String>();
		mapping2.put("FILE_READER", "FILE_READER");
		cb2.setMapping(mapping2);
		flow.addNode(cb2);

		CodeFlow cf = new CodeFlow();
		cf.setFlow(flow);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("FILE_PATH", "C:/Users/Antonio/Desktop/prj-forms-final/tslint.json");

		cf.setParameters(new SafeMap<String, Object>(parameters));

		cf.launch();
	}
}
