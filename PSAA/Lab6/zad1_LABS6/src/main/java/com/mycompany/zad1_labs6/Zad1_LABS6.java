

package com.mycompany.zad1_labs6;
import java.util.HashMap;
import java.util.LinkedList;

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
public class Main {

    public static <E> Graph<E> copyGraph(Graph<E> original) {

        HashMap<GNode<E>, GNode<E>> nodeMapping = new HashMap<>();


        for (GNode<E> node : original.graph) {
            nodeMapping.put(node, new GNode<>(node.num, node.info));
        }


        for (GNode<E> oldNode : original.graph) {
            GNode<E> newNode = nodeMapping.get(oldNode);
            for (GNode<E> neighbour : oldNode.list) {
                newNode.addNeighbour(nodeMapping.get(neighbour));
            }
        }


        Graph<E> copiedGraph = new Graph<>(original.n, (E[]) new Object[original.n]);
        for (int i = 0; i < original.n; i++) {
            copiedGraph.graph[i] = nodeMapping.get(original.graph[i]);
        }

        return copiedGraph;
    }

    public static void main(String[] args) {

        String[] infos = {"A", "B", "C", "D"};
        Graph<String> originalGraph = new Graph<>(4, infos);

        originalGraph.addEdge(0, 1);
        originalGraph.addEdge(1, 2);
        originalGraph.addEdge(2, 3);
        originalGraph.addEdge(0, 3);

        System.out.println("Originalen Graf:");
        for (GNode<String> node : originalGraph.graph) {
            System.out.print(node.info + " -> ");
            for (GNode<String> neighbour : node.list) {
                System.out.print(neighbour.info + " ");
            }
            System.out.println();
        }


        Graph<String> copiedGraph = copyGraph(originalGraph);

        System.out.println("\nKopiran Graf:");
        for (GNode<String> node : copiedGraph.graph) {
            System.out.print(node.info + " -> ");
            for (GNode<String> neighbour : node.list) {
                System.out.print(neighbour.info + " ");
            }
            System.out.println();
        }
    }
    }

