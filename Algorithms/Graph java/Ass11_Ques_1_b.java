import java.util.*;

class Edge3 {
    int source, destination, weight;

    public Edge3(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}

public class Ass11_Ques_1_b {
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int numVertices = scanner.nextInt();
        System.out.print("Enter the number of edges: ");
        int numEdges = scanner.nextInt();

        List<Edge3> edges = new ArrayList<>();
        for (int i = 0; i < numEdges; i++) {
            System.out.print("Enter source, destination, and weight for edge " + (i + 1) + ": ");
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            int weight = scanner.nextInt();
            edges.add(new Edge3(src, dest, weight));
        }

        int[] distance = new int[numVertices];
        Arrays.fill(distance, INF);
        distance[0] = 0;

        for (int i = 0; i < numVertices - 1; i++) {
            for (Edge3 edge : edges) {
                if (distance[edge.source] != INF && distance[edge.source] + edge.weight < distance[edge.destination]) {
                    distance[edge.destination] = distance[edge.source] + edge.weight;
                }
            }
        }

        boolean hasNegativeCycle = false;
        for (Edge3 edge : edges) {
            if (distance[edge.source] != INF && distance[edge.source] + edge.weight < distance[edge.destination]) {
                hasNegativeCycle = true;
                break;
            }
        }

        if (hasNegativeCycle) {
            System.out.println("Negative weight cycle detected in the graph.");
        } else {
            System.out.println("No negative weight cycle found in the graph.");
        }

        scanner.close();
    }
}
