package com.trainingground.efm.datastruct.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * 
 * @author Antonio
 *
 * @param <N> The node type class of the graph
 */
public class Graph<N> {
	
	private Set<N> nodes;
	private Map<N, Map<N, Object>> archFrom;
	private Map<N, Map<N, Object>> archTo;
	
	private Set<N> noArchFromNodes;
	private Set<N> noArchToNodes;
	
	public Graph() {
		this.nodes = new HashSet<>();
		this.archFrom = new HashMap<>();
		this.archTo = new HashMap<>();
		this.noArchFromNodes = new HashSet<>();
		this.noArchToNodes = new HashSet<>();
	}
	
	public Graph(Graph<N> graph) {
		this.nodes = new HashSet<>(graph.nodes);
		this.archFrom = new HashMap<>(graph.archFrom);
		this.archTo = new HashMap<>(graph.archTo);
		this.noArchFromNodes = new HashSet<>(graph.noArchFromNodes);
		this.noArchToNodes = new HashSet<>(graph.noArchToNodes);
	}
	
	public boolean addNode(N node) {
		if (!nodes.contains(node)) {
			nodes.add(node);
			noArchFromNodes.add(node);
			noArchToNodes.add(node);
			archFrom.put(node, new HashMap<>());
			archTo.put(node, new HashMap<>());
			return true;
		}
		return false;
	}
	
	public boolean removeNode(N node) {
		if (!nodes.contains(node)) {
			return false;
		}
		nodes.remove(node);
		noArchFromNodes.remove(node);
		noArchToNodes.remove(node);
		for (N arch : archFrom.get(node).keySet()) {
			archTo.get(arch).remove(node);
			if (archTo.get(arch).isEmpty()) {
				noArchToNodes.add(arch);
			}
		}
		for (N arch : archTo.get(node).keySet()) {
			archFrom.get(arch).remove(node);
			if (archFrom.get(arch).isEmpty()) {
				noArchFromNodes.add(arch);
			}
		}
		archFrom.remove(node);
		archTo.remove(node);
		return true;
	}
	
	public void addArch(N nodeA, N nodeB) {
		addArch(nodeA, nodeB, null);
	}
	
	public void addArch(N nodeA, N nodeB, Object weight) {
		// Check if nodes exist
		List<N> missingNodes = new ArrayList<>(Arrays.asList(nodeA, nodeB));
		missingNodes.removeAll(nodes);		
		if (!missingNodes.isEmpty()) {
			throw new IllegalArgumentException(String.format("At least one of the nodes are not part of the graph. Missing node/s: %s", missingNodes));
		}

		if (archFrom.get(nodeA).isEmpty()) {
			noArchFromNodes.remove(nodeA);
		}
		archFrom.get(nodeA).put(nodeB, weight);

		if (archTo.get(nodeB).isEmpty()) {
			noArchToNodes.remove(nodeB);
		}
		archTo.get(nodeB).put(nodeA, weight);
	}
	
	public Set<N> nodeSet() {
		return new HashSet<>(nodes);
	}
	
	public Set<N> noArchFromNodeSet() {
		return new HashSet<>(noArchFromNodes);
	}
	
	public Set<N> noArchToNodeSet() {
		return new HashSet<>(noArchToNodes);
	}
	
	public Set<N> archFrom(N node) {
		if (!nodes.contains(node)) {
			throw new IllegalArgumentException("Graph does not contain the passed node");
		}
		return new HashSet<>(archFrom.get(node).keySet());
	}
	
	public Set<N> archTo(N node) {
		if (!nodes.contains(node)) {
			throw new IllegalArgumentException("Graph does not contain the passed node");
		}
		return new HashSet<>(archTo.get(node).keySet());
	}

}
