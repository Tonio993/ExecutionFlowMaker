package com.trainingground.efm.test;

import java.util.HashMap;
import java.util.Map;

import com.trainingground.efm.block.CodeBlock;
import com.trainingground.efm.block.CodeBlockPrint;
import com.trainingground.efm.datastruct.SafeMap;
import com.trainingground.efm.datastruct.graph.Graph;
import com.trainingground.efm.flow.CodeFlow;

public class TestFlow {

	public static void main(String[] args) throws InterruptedException {
		Graph<CodeBlock> flow = new Graph<>();
		
		CodeBlock cb1 = new CodeBlockPrint("A");
		cb1.setDelay(1000);
		CodeBlock cb2 = new CodeBlockPrint("B");
		cb2.setDelay(1000);
		CodeBlock cb3 = new CodeBlockPrint("C");
		cb3.setDelay(1000);
		CodeBlock cb4 = new CodeBlockPrint("D");
		cb4.setDelay(1000);
		CodeBlock cb5 = new CodeBlockPrint("E");
		cb5.setDelay(1000);
		CodeBlock cb6 = new CodeBlockPrint("F");
		cb6.setDelay(1000);
		CodeBlock cb7 = new CodeBlockPrint("G");
		cb7.setDelay(1000);
		
		flow.addNode(cb1);
		flow.addNode(cb2);
		flow.addNode(cb3);
		flow.addNode(cb4);
		flow.addNode(cb5);
		flow.addNode(cb6);
		flow.addNode(cb7);
		flow.addArch(cb1, cb2);
		flow.addArch(cb2, cb7);
		flow.addArch(cb1, cb3);
		flow.addArch(cb3, cb4);
		flow.addArch(cb3, cb5);
		flow.addArch(cb4, cb6);
		flow.addArch(cb5, cb6);
		flow.addArch(cb6, cb2);
		
		CodeFlow cf = new CodeFlow();
		cf.setFlow(flow);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("VALUE_TO_DOUBLE", 1);

		cf.setParameters(new SafeMap<String, Object>(parameters));

		cf.launch();
	}

}
