package com.trainingground.efm.flow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

import com.trainingground.efm.block.CodeBlock;
import com.trainingground.efm.datastruct.SafeEntry;
import com.trainingground.efm.datastruct.SafeMap;
import com.trainingground.efm.datastruct.graph.Graph;

public class CodeFlowInstance {
	
	private Semaphore semaphore;
	private Graph<CodeBlock> flow;
	private SafeMap<String, Object> parameters;
	private Set<CodeBlock> executed;
	
	public CodeFlowInstance(CodeFlow codeFlow) {
		this.semaphore = new Semaphore(1);
		this.flow = new Graph<>(codeFlow.getFlow());
		this.parameters = new SafeMap<>(codeFlow.getParameters());
		this.executed = new HashSet<>();
	}
	
	public void start() throws InterruptedException {
		semaphore.acquire();
		for (CodeBlock block : flow.noArchToNodeSet()) {
			execute(block);
		}
		semaphore.release();
	}
	
	public void execute(CodeBlock block) throws InterruptedException {
		executed.add(block);
		block.setCodeFlowInstance(this);
		HashMap<String, SafeEntry<String, Object>> blockParameters = new HashMap<>();
		if (block.getMapping() != null) {
			for (String parameter : block.getMapping().keySet()) {
				blockParameters.put(parameter, parameters.acquire(block.getMapping().get(parameter)));
			}
		}
		block.setParameters(blockParameters);
		new Thread(block).start();
	}
	
	public void finish(CodeBlock block) throws InterruptedException {
		semaphore.acquire();
		flow.removeNode(block);
		Set<CodeBlock> toExecuteSet = flow.noArchToNodeSet().stream().filter(n->!executed.contains(n)).collect(Collectors.toSet());
		for (CodeBlock toExecute : toExecuteSet) {
			execute(toExecute);
		}
		semaphore.release();
	}
	
}
