import java.util.*;

class edge {
    int src, dest, weight;

    public edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

public class graph {
    private int V, E;
    private List<edge> edges;

    public graph(int V, int E) {
        this.V = V;
        this.E = E;
        edges = new ArrayList<>();
    }

    public void addEdge(int src, int dest, int weight) {
        edges.add(new edge(src, dest, weight));
    }

    // Function to detect a cycle in the graph
    private boolean isCyclic() {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        for (int i = 0; i < V - 1; i++) {
            for (edge edge : edges) {
                if (dist[edge.src] != Integer.MAX_VALUE && dist[edge.dest] > dist[edge.src] + edge.weight) {
                    dist[edge.dest] = dist[edge.src] + edge.weight;
                }
            }
        }

        // Check for negative weight cycles
        for (edge edge : edges) {
            if (dist[edge.src] != Integer.MAX_VALUE && dist[edge.dest] > dist[edge.src] + edge.weight) {
                return true; // Negative weight cycle detected
            }
        }

        return false;
    }

    // Function to detect a negative weight cycle reachable from a given source vertex
    private boolean isNegativeCycleReachable(int source) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        for (int i = 0; i < V - 1; i++) {
            for (edge edge : edges) {
                if (dist[edge.src] != Integer.MAX_VALUE && dist[edge.dest] > dist[edge.src] + edge.weight) {
                    dist[edge.dest] = dist[edge.src] + edge.weight;
                }
            }
        }

        // Check for negative weight cycles
        for (edge edge : edges) {
            if (dist[edge.src] != Integer.MAX_VALUE && dist[edge.dest] > dist[edge.src] + edge.weight) {
                return true; // Negative weight cycle reachable from the source
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();

        System.out.print("Enter the number of edges: ");
        int E = scanner.nextInt();

        graph graph = new graph(V, E);

        for (int i = 0; i < E; i++) {
            System.out.println("Enter details for edge " + (i + 1) + ":");
            System.out.print("Source: ");
            int src = scanner.nextInt();

            System.out.print("Destination: ");
            int dest = scanner.nextInt();

            System.out.print("Weight: ");
            int weight = scanner.nextInt();

            graph.addEdge(src, dest, weight);
        }

        if (graph.isCyclic()) {
            System.out.println("The graph contains a cycle.");
        } else {
            System.out.println("The graph does not contain a cycle.");
        }

        if (graph.isNegativeCycleReachable(0)) {
            System.out.println("There is a negative weight cycle reachable from the source.");
        } else {
            System.out.println("There is no negative weight cycle reachable from the source.");
        }
    }
}
