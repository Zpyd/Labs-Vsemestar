

package com.mycompany.zad3_labs6;

import java.util.Arrays;
import java.util.Scanner;

class GraphInfo<E extends Comparable<E>> {
    E info;
    
    public GraphInfo(E info) {
        this.info = info;
    }
}

class Graph<E extends Comparable<E>> {
    private GraphInfo<E> infos[];
    public int n;
    public int mtx[][];
    
    public Graph(int n) {
        this.n = n;
        infos = new GraphInfo[n];
        mtx = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mtx[i][j] = 0;
            }
        }
    }
    
    void addEdge(int x, int y) {
        mtx[x][y] = 1;
        mtx[y][x] = 1;
    }
    
    void deleteEdge(int x, int y) {
        mtx[x][y] = 0;
        mtx[y][x] = 0;
    }
    
    void setInfo(int pos, E info) {
        infos[pos] = new GraphInfo(info);
    }
    
    E getInfo(int pos) {
        return infos[pos].info;
    }
    
    int getIndex(E info) {
        for (int i = 0; i < n; i++) {
            if (infos[i].info == info) {
                return i;
            }
        }
        
        return -1;
    }
    
    boolean neighbours(int x, int y) {
        if (mtx[x][y] == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    void addNode(E info) {
        ++n;
        
        GraphInfo[] infospom = new GraphInfo[n];
        for (int i = 0; i < n - 1; i++) {
            infospom[i] = infos[i];
        }
        infospom[n - 1] = new GraphInfo(info);
        
        int[][] mtxpom = new int[n][n];
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                mtxpom[i][j] = mtx[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            mtxpom[n - 1][i] = 0;
            mtxpom[i][n - 1] = 0;
        }
        
        infos = infospom;
        mtx = mtxpom;
    }
    
    void deleteNode(E info) {
        int ind = getIndex(info);
        
        if (ind != n - 1) {
            for (int i = ind; i < n - 1; i++) {
                for (int j = 0; j < n; j++) {
                    mtx[i][j] = mtx[i + 1][j];
                }
            }
            for (int j = ind; j < n - 1; j++) {
                for (int i = 0; i < n; i++) {
                    mtx[i][j] = mtx[i][j + 1];
                }
            }
            
            for (int i = ind; i < n - 1; i++) {
                infos[i] = infos[i + 1];
            }
        }
        
        n--;
    }
    
    public void printMatrix() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(mtx[i][j] + ", ");
            }
            System.out.println();
        }
    }
    
    void dfs(int visited[], int start) {
        visited[start] = 1;
        System.out.println("Node: " + infos[start].info);
        
        for (int i = 0; i < n; i++) {
            if (i != start) {
                if (mtx[start][i] > 0 && visited[i] == 0) {
                    dfs(visited, i);
                }
            }
        }
    }
    
    public boolean detectIfCycleExists(int[] visited, int start, int parent) {
        visited[start] = 1;
        System.out.println(infos[start].info);
        
        for (int i = 0; i < n; i++) {
            if (mtx[start][i] > 0) {
                if (visited[i] == 0) {
                    if (detectIfCycleExists(visited, i, start)) {
                        return true;
                    }
                } else if(visited[i] == 1 && i != parent) {
                    return true;
                } 
            }
        }
        
        return false;
    }
}

public class Zad3_LABS6 {
    
      public static int num=0;
      public static int lenCycles;

      public static int detectNumOfNCycles(int lenCycles,Graph g){
        int[] visited= new int[g.n];
        Arrays.fill(visited,0);
        
        presmetajCiklusi(visited,0,-1,lenCycles,g);
        
        return num;
      }
      
      public static boolean presmetajCiklusi(int[] visited, int start, int parent,int lenCycle,Graph g) {
        visited[start] = 1;
//        System.out.println(g.getInfo(start));
        
        for (int i = 0; i < g.n; i++) {//n broj na jazli
            if (g.mtx[start][i]>0) {
                if (visited[i] == 0) {
                    if (presmetajCiklusi(visited, i, start, --lenCycle,g)) {
                        if(lenCycle==0){
                        num++;
                    }else{
                        lenCycle=lenCycles;
                    }
                        return true;
                    }
                } else if(visited[i] == 1 && i != parent) {
                    if(lenCycle==0){
                        num++;
                    }else{
                        lenCycle=lenCycles;
                    }
                    
                    return true;
                } 
            }
        }
        
        return false;
    }
      
      //ai friends to the rescue :')
    public static int findCycles(Graph graph, int N) {
        boolean[] visited = new boolean[graph.n];
        int[] cycleCount = {0};

        for (int i = 0; i < graph.n; i++) {
            dfsCycle(graph, i, i, visited, N, 0, cycleCount);
            visited[i] = true;
        }

        return cycleCount[0] / 2;
    }
 private static void dfsCycle(Graph<Integer> graph, int start, int current, boolean[] visited, int N, int depth, int[] cycleCount) {
        if (depth == N) {
            if (current == start) {
                cycleCount[0]++;
            }
            return;
        }

        visited[current] = true;

        for (int i = 0; i < graph.n; i++) {
            if (graph.neighbours(current, i)) {
                if (!visited[i] || (i == start && depth == N - 1)) {
                    dfsCycle(graph, start, i, visited, N, depth + 1, cycleCount);
                }
            }
        }

        visited[current] = false;
    }

    
    public static void main(String[] args) {
        
        int num_nodes = 5;
        Graph<String> g = new Graph(num_nodes);
        
        for (int i = 0; i < num_nodes; i++) {
            g.setInfo(i, "N" + i);
        }
        
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 4);
        
        
        lenCycles=4;
        System.out.println("num of cycles of len "
                +lenCycles+ " is " + findCycles(g,lenCycles));
        
        
    }
}
