package com.mycompany.zad2_labs6;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;


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





public class Zad2_LABS6 {

    public static void main(String[] args) {
        Scanner scan= new Scanner(System.in);
        int n;
        System.out.println("vnesi broj na gradovi");
        n=scan.nextInt();
//        System.out.println("n:" + n);
//        
        String[] gradovi=new String[n];
        System.out.println("Vnesi ime na grad: (ime ime ime ) - name separated by white space");
        for (int i = 0; i < n; i++) {
            gradovi[i]=scan.next();
        }
        
        for (String grad:gradovi){
            System.out.println(grad);
        }
        
        Graph<String> mapa=new Graph(n,gradovi);
//        
//        System.out.println(mapa.graph[1].info);
//        
        System.out.println("Vnesi broj na patista:");
        int npat=scan.nextInt();
        for (int i = 0; i < npat; i++) {
            System.out.println("Napisi od deka do deka (Grad Grad )");
            String gr1=scan.next(); int grad1=-1;
            String gr2=scan.next(); int grad2=-1;
            for (int j = 0; j < n; j++) {
                if(mapa.graph[j].info.equals(gr1)){
                    grad1=j;
                }
                if(mapa.graph[j].info.equals(gr2)){
                    grad2=j;
                }
                
                
            }
            
            if(grad1==-1 || grad2==-1){
                    System.out.println("Netocno vnesen grad");
                      }
            
            mapa.addEdge(grad1, grad2);
            
        }
//        
//        System.out.println(mapa.graph[0].list.get(0).node.info);
//        System.out.println(mapa.graph[1].list.get(0).node.info);
        
        System.out.println("Napisi starting point grad: ");
        
        String startGrad=scan.next(); int start=-1;
        for (int j = 0; j < n; j++) {
                if(mapa.graph[j].info.equals(startGrad)){
                    start=j;
                }
        }
        
        List<GNode<String>> mozniGradovi=mozniGradovi(start,mapa);
        
        if(mozniGradovi.isEmpty()){
            System.out.println("nemozeme nikade da otideme od vaj grad"
                    + "");
        }
        for (GNode<String> grad: mozniGradovi){
            System.out.println(grad.info);
        }
        
    }

    private static List<GNode<String>> mozniGradovi(int start, Graph mapata) {
        List<GNode<String>> lista=new LinkedList<>();
        HashMap<String,GNode<String>> visited= new HashMap();
        
        GNode<String> grad=mapata.graph[start];
        visited.put(grad.info, grad);
        
        
        Queue<GNode<String>> q= new LinkedList<>();
        q.add(grad);
        
        while(!q.isEmpty()){
            grad=q.poll();
            lista.add(grad);
            for (int i = 0; i < grad.list.size(); i++) {
                    if(!visited.containsKey(grad.list.get(i).node.info)){
                        visited.put(grad.list.get(i).node.info,grad.list.get(i).node);
                        q.add(grad.list.get(i).node);
                    }
            }
        }
        
        
        return lista;
    }
}
