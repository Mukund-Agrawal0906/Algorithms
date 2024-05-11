import java.util.LinkedList;
import java.util.Scanner;

class Edge {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

class Graph {
    int V, E;
    LinkedList<Edge>[] adj;

    public Graph(int V, int E) {
        this.V = V;
        this.E = E;
        adj = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int src, int dest, int weight) {
        Edge edge = new Edge(src, dest, weight);
        adj[src].add(edge);
        adj[dest].add(edge);
    }
}

public class PrimMST {
    public static void primMST(Graph graph) {
        int V = graph.V;
        Edge[] parent = new Edge[V];
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

            for (Edge edge : graph.adj[u]) {
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

        Graph graph = new Graph(V, E);

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