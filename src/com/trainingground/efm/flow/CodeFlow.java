package com.trainingground.efm.flow;

import com.trainingground.efm.block.CodeBlock;
import com.trainingground.efm.datastruct.SafeMap;
import com.trainingground.efm.datastruct.graph.Graph;

public class CodeFlow {

	private SafeMap<String, Object> parameters;

	private Graph<CodeBlock> flow;

	public void launch() throws InterruptedException {
		CodeFlowInstance instance = new CodeFlowInstance(this);
		instance.start();
	}

	public SafeMap<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(SafeMap<String, Object> parameters) {
		this.parameters = parameters;
	}

	public Graph<CodeBlock> getFlow() {
		return flow;
	}

	public void setFlow(Graph<CodeBlock> flow) {
		this.flow = flow;
	}

}
