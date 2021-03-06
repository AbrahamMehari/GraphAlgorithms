package graph;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Simpler light weight graph
 * 
 * @author Er. Abraham Bisrat https://github.com/abrahammehari
 *
 */

public class GraphX {
	public static void p(Object line) { System.out.println(line); }
	private List<Integer> adjLists[];
	private boolean visited[];
	
	// Creation
	public GraphX(int vertices) {
		adjLists = new LinkedList[vertices];
		visited = new boolean[vertices];
		
		// Initialize adjacency list
		for(int i = 0; i < adjLists.length; i++)
			adjLists[i] = new LinkedList<Integer>();
	}
	void addEdge(int src, int dst) { adjLists[src].add(dst); adjLists[dst].add(src); }
	public void addEdgeDirected(int src, int dst) { adjLists[src].add(dst); }
	
	// Depth first search
	void dfsX(int vertex) {
		visited[vertex] = true;
		p(vertex);
		
		Iterator<Integer> iter = adjLists[vertex].listIterator();
		while(iter.hasNext()) {
			int adj = iter.next();
			if(!visited[adj])
				dfsX(adj);
		}
	}
	void dfs(int vertex) {
		visited[vertex] = true;
		p(vertex);
		for(int adj : adjLists[vertex])
			if(!visited[adj])
				dfs(adj);
	}
	void dfsCompCount() {
		Set<Integer> visited = new HashSet<>();
		for(List<Integer> adjList : adjLists) {
			p(adjList);
		}
	}
	// working with it's own hash-set
	Set<Integer> visitedSet = new HashSet<>();
	void dfs(int vertex, HashSet<Integer> visited) {
		visited.add(vertex);
		p(vertex);
		for(int adj : adjLists[vertex])
			if(!visited.contains(adj))
				dfs(adj, visited);
	}
	public int V() { return adjLists.length; }
	public List<Integer> adj(int vertex) { return adjLists[vertex]; }
	public List<Integer>[] adjLists() { return adjLists; }
	@Override public String toString() {
		String output = "";
		for(int i = 0; i < adjLists.length; i++)
			output += i + " : " + adjLists[i].toString() + "\n";
		return output;
	}
	public static void main(String[] args) {
		GraphX g = new GraphX(13);
		init(g);
		g.dfs(1);
		g.dfsX(1);
		g.dfs(1, new HashSet<Integer>());
		g.dfsCompCount();
	}
	private static void init(GraphX G) {
		G.addEdge(0, 5);
		G.addEdge(4, 3);
		G.addEdge(0, 1);
		G.addEdge(9, 12);
		G.addEdge(6, 4);
		G.addEdge(5, 4);
		G.addEdge(0, 2);
		G.addEdge(11, 12);
		G.addEdge(9, 10);
		G.addEdge(0, 6);
		G.addEdge(7, 8);
		G.addEdge(9, 11);
		G.addEdge(5, 3);
	}
}
