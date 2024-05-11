import java.util.LinkedList;
import java.util.Scanner;

class Edges1 {
    int src, dest, weight;

    public Edges1(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

class Graph1 {
    int V, E;
    LinkedList<Edges1>[] adj;

    public Graph1(int V, int E) {
        this.V = V;
        this.E = E;
        adj = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int src, int dest, int weight) {
        Edges1 edge = new Edges1(src, dest, weight);
        adj[src].add(edge);
        adj[dest].add(edge);
    }
}

public class PrimMST {
    public static void primMST(Graph1 graph) {
        int V = graph.V;
        Edges1[] parent = new Edges1[V];
        int[] key = new int[V];
        boolean[] inMST = new boolean[V];

        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            inMST[i] = false;
        }

        key[0] = 0;
        parent[0] = null;

        for (int count = 0; count < V - 1; count++) {
            int u = -1;
            for (int v = 0; v < V; v++) {
                if (!inMST[v] && (u == -1 || key[v] < key[u])) {
                    u = v;
                }
            }

            inMST[u] = true;

            for (Edges1 edge : graph.adj[u]) {
                int v = (edge.src == u) ? edge.dest : edge.src;
                if (!inMST[v] && edge.weight < key[v]) {
                    key[v] = edge.weight;
                    parent[v] = edge;
                }
            }
        }

        System.out.println("Edges in MST:");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i].src + " - " + parent[i].dest);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();
        System.out.print("Enter the number of edges: ");
        int E = scanner.nextInt();

        Graph1 graph = new Graph1(V, E);

        for (int i = 0; i < E; i++) {
            System.out.print("Enter edge " + (i + 1) + " (source destination weight): ");
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.addEdge(src, dest, weight);
        }

        primMST(graph);
    }
}