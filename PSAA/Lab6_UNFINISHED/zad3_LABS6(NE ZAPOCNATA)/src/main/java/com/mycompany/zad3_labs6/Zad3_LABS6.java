

package com.mycompany.zad3_labs6;

import java.util.LinkedList;

class Edge {
public int from, to;

    public Edge(int from, int to) {
        this.from = from;
        this.to = to;
    }
}

class GNeighbour<E extends Comparable<E>> {
    public GNode<E> node;

    public GNeighbour(GNode<E> node) {
        this.node = node;
    }
}

class GNode<E extends Comparable<E>> {
    public int num;
    public E info;
    public LinkedList<GNeighbour<E>> list;

    public GNode(int num, E info) {
        this.num = num;
        this.info = info;
        list = new LinkedList<>();
    }

    public void addNeighbour(GNode<E> node) {
        list.add(new GNeighbour<>(node));
    }

    public void deleteNeighbour(GNode<E> node) {
        list.removeIf(neighbour -> neighbour.node.info.equals(node.info));
    }

    public boolean hasNeighbour(GNode<E> node) {
        for (GNeighbour<E> neighbour : list) {
            if (neighbour.node.info.equals(node.info)) {
                return true;
            }
        }
        return false;
    }
}

class Graph<E extends Comparable<E>> {
    int n;
    GNode<E>[] graph;

    
    public Graph(int n) {
        this.n = n;
        graph = new GNode[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new GNode<>(i, null);
        }
    }

    
    public Graph(int n, E[] infos) {
        this.n = n;
        graph = new GNode[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new GNode<>(i, infos[i]);
        }
    }

    boolean hasNeighbour(int x, int y) {
        return graph[x].hasNeighbour(graph[y]);
    }

    void addEdge(int x, int y) {
        if (!graph[x].hasNeighbour(graph[y])) {
            graph[x].addNeighbour(graph[y]);
        }
        if (!graph[y].hasNeighbour(graph[x])) {
            graph[y].addNeighbour(graph[x]); // Ensure undirected connection
        }
    }

    void deleteEdge(int x, int y) {
        graph[x].deleteNeighbour(graph[y]);
        graph[y].deleteNeighbour(graph[x]); // Remove reverse connection
    }

    public Edge[] getAllEdges() {
        LinkedList<Edge> edgeList = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (GNeighbour<E> neighbour : graph[i].list) {
                int from = graph[i].num;
                int to = neighbour.node.num;
                if (!visited[from][to] && !visited[to][from]) {
                    edgeList.add(new Edge(from, to));
                    visited[from][to] = visited[to][from] = true;
                }
            }
        }

        return edgeList.toArray(new Edge[0]);
    }
}



public class Zad3_LABS6 {

    public static void main(String[] args) {
        Integer[] jazli=new Integer[]{0,1,2,3,4};
        
        Graph g=new Graph(5,jazli);
        g.addEdge(1, 2);
        
        
    }
}
