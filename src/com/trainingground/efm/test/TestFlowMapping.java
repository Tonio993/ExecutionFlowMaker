package com.trainingground.efm.test;

import java.util.HashMap;
import java.util.Map;

import com.trainingground.efm.block.CodeBlock;
import com.trainingground.efm.block.CodeBlockDouble;
import com.trainingground.efm.datastruct.SafeMap;
import com.trainingground.efm.datastruct.graph.Graph;
import com.trainingground.efm.flow.CodeFlow;

public class TestFlowMapping {

	public static void main(String[] args) throws InterruptedException {

		Graph<CodeBlock> flow = new Graph<>();
		
		Map<String, String> mapping1 = new HashMap<String, String>();
		mapping1.put("VALUE_TO_DOUBLE", "GNEE");
		Map<String, String> mapping2 = new HashMap<String, String>();
		mapping2.put("VALUE_TO_DOUBLE", "GNEE");
		
		CodeBlock last = null;
		for (int i = 0; i < 5; i++) {
			CodeBlock cb = new CodeBlockDouble();
			cb.setDelay(500);
			cb.setMapping(mapping1);
			flow.addNode(cb);
			if (last != null) {
				flow.addArch(last, cb);
			}
			last = cb;
		}
		
		last = null;
		for (int i = 0; i < 5; i++) {
			CodeBlock cb = new CodeBlockDouble();
			cb.setDelay(500);
			cb.setMapping(mapping2);
			flow.addNode(cb);			
			if (last != null) {
				flow.addArch(last, cb);
			}
			last = cb;
		}
		
		CodeFlow cf = new CodeFlow();
		cf.setFlow(flow);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("GNII", 1);
		parameters.put("GNEE", 10);

		cf.setParameters(new SafeMap<String, Object>(parameters));

		cf.launch();
	}

}
