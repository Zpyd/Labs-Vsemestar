

package com.mycompany.zad1_labs6;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Map;
import java.util.HashMap;

class GNode<E> {
    public int num;
    public E info;
    public LinkedList<GNode<E>> list;
    
    
    public GNode(int num, E info) {
        this.num = num;
        this.info = info;
        list = new LinkedList();
    }
    
    void addNeighbour(GNode<E> node) {
        if (!list.contains(node)) {
            list.add(node);
        }
    }
    
    void deleteNeighbour(GNode<E> node) {
        if (list.contains(node)) {
            list.remove(node);
        }
    }
    
    boolean hasNeighbour(GNode<E> node) {
        return list.contains(node);
    }
    
  
public String toString() {
    return info.toString();
}

}

class Graph<E> {
    public int n;
    public GNode<E> graph[];
    
    public Graph(int n, E[] infos) {
        this.n = n;
        graph = new GNode[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new GNode(i, infos[i]);
        }
    }
    
    boolean neighbours(int x, int y) {
        return graph[x].hasNeighbour(graph[y]);
    }
    
    void addEdge(int x, int y) {
        graph[x].addNeighbour(graph[y]);
    }
    
    void deleteEdge(int x, int y) {
        graph[x].deleteNeighbour(graph[y]);
    }
    
     
    GNode getNode(E info) {
        for (int i = 0; i < n; i++) {
            if (graph[i].info == info) {
                return graph[i];
            }
        }
        
        return null;
    }
    
    void addNode(E info) {
        ++n;
        
        GNode<E> [] graphpom = new GNode[n];
        
        for (int i = 0; i < n; i++) {
            graphpom[i] = graph[i];
        }
        graphpom[n - 1] = new GNode(n - 1, info);
        
        graph = graphpom;
    }
    
    void deleteNode(E info) {
        GNode node = getNode(info);
        
        for (int i = 0; i < n; i++) {
            if (graph[i].hasNeighbour(node)) {
                graph[i].deleteNeighbour(node);
            }
        }
        
        for (int i = node.num; i < n - 1; i++) {
            graph[i] = graph[i + 1];
            graph[i].num = i;
        }
        
        n--;
    } 
   
    void printGraph() {
    System.out.println("Graph Structure:");
    for (int i = 0; i < n; i++) {
        System.out.print("Node " + graph[i].info + " -> ");
        for (GNode<E> neighbor : graph[i].list) {
            System.out.print(neighbor.info + " ");
        }
        System.out.println();
        }
    }
    
    
    
    void dfs(int visited[], int start) {
        visited[start] = 1;
        System.out.println("Node: " + graph[start].info);
        
        GNode<E> pom = graph[start];
        GNode<E> next;
        
        for (int i = 0; i < pom.list.size(); i++) {
            next = pom.list.get(i);
            
            if (visited[next.num] == 0) {
                dfs(visited, next.num);
            }
        }
    }
    
}

public class Zad1_LABS6 {

    
    
    public static Graph<Integer> duplicate(GNode<Integer> start) {
    LinkedList<GNode<Integer>> siteNodes = new LinkedList<>();
    Queue<GNode<Integer>> q = new LinkedList<>();
    q.add(start);
    siteNodes.add(start);
    
    Map<GNode<Integer>, GNode<Integer>> cloneMap = new HashMap<>();
    cloneMap.put(start, new GNode<>(start.num, start.info));
    
    while (!q.isEmpty()) {
        GNode<Integer> cur = q.poll();
        
        for (GNode<Integer> neighbor : cur.list) {
            if (!cloneMap.containsKey(neighbor)) {
                GNode<Integer> clonedNeighbor = new GNode<>(neighbor.num, neighbor.info);
                cloneMap.put(neighbor, clonedNeighbor);
                siteNodes.add(neighbor);
                q.add(neighbor);
            }
            
            cloneMap.get(cur).addNeighbour(cloneMap.get(neighbor));
        }
    }
    
    return new Graph<>(siteNodes.size(), cloneMap.values().toArray(new GNode[0]));
}

        
    public static void main(String[] args) {
    
    Integer[] nodeInfos = {1, 2, 3, 4, 5}; // Node information
    Graph<Integer> originalen = new Graph<>(5, nodeInfos);
    
    // Step 2: Add edges to the graph
    originalen.addEdge(0, 1);
    originalen.addEdge(0, 2);
    originalen.addEdge(1, 3);
    originalen.addEdge(2, 4);
    originalen.addEdge(3, 4);
    
    System.out.println("Original Graph (DFS traversal):");
    int[] visited = new int[originalen.n];
    originalen.dfs(visited, 0);
    
    originalen.printGraph();
    
    Graph<Integer> duplikat = duplicate(originalen.graph[0]);
    
    duplikat.printGraph();
    
        
    }
}
