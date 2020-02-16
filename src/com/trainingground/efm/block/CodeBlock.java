package com.trainingground.efm.block;

import java.util.Map;

import com.trainingground.efm.datastruct.SafeEntry;
import com.trainingground.efm.flow.CodeFlowInstance;

public abstract class CodeBlock implements Runnable {

	private CodeFlowInstance codeFlowInstance;
	protected Map<String, SafeEntry<String, Object>> parameters;
	protected Map<String, String> mapping;
	
	private long delay;

	protected abstract void execute() throws InterruptedException;

	@Override
	public void run() {
		try {
			if (delay >= 0) {
				Thread.sleep(delay);
			}
			execute();
			codeFlowInstance.finish(this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public CodeFlowInstance getCodeFlow() {
		return codeFlowInstance;
	}

	public void setCodeFlowInstance(CodeFlowInstance codeFlowInstance) {
		this.codeFlowInstance = codeFlowInstance;
	}

	public Map<String, SafeEntry<String, Object>> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, SafeEntry<String, Object>> parameters) {
		this.parameters = parameters;
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public Map<String, String> getMapping() {
		return mapping;
	}

	public void setMapping(Map<String, String> mapping) {
		this.mapping = mapping;
	}

}
