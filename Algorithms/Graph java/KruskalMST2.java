import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Edges implements Comparable<Edges> {
    int src, dest, weight;

    public Edges(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edges other) {
        return this.weight - other.weight;
    }
}

class DisjointSets {
    private final int[] parent;
    private final int[] rank;

    public DisjointSets(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int i) {
        if (i != parent[i]) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }

    public void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);

        if (xRoot == yRoot) return;

        if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;
        } else if (rank[xRoot] > rank[yRoot]) {
            parent[yRoot] = xRoot;
        } else {
            parent[yRoot] = xRoot;
            rank[xRoot]++;
        }
    }
}

public class KruskalMST2 {
    public static List<Edges> kruskalMST(List<Edges> edges, int numVertices) {
        List<Edges> mst = new ArrayList<>();
        Collections.sort(edges);
        DisjointSets disjointSet = new DisjointSets(numVertices);

        for (Edges edge : edges) {
            int srcRoot = disjointSet.find(edge.src);
            int destRoot = disjointSet.find(edge.dest);

            if (srcRoot != destRoot) {
                mst.add(edge);
                disjointSet.union(srcRoot, destRoot);
            }
        }

        return mst;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int numVertices = scanner.nextInt();
        System.out.print("Enter the number of edges: ");
        int numEdges = scanner.nextInt();

        List<Edges> edges = new ArrayList<>();
        for (int i = 0; i < numEdges; i++) {
            System.out.print("Enter source, destination, and weight for edge " + (i + 1) + ": ");
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            int weight = scanner.nextInt();
            edges.add(new Edges(src, dest, weight));
        }

        List<Edges> mst = kruskalMST(edges, numVertices);

        System.out.println("Minimum Spanning Tree edges:");
        for (Edges edge : mst) {
            System.out.println(edge.src + " - " + edge.dest + " : " + edge.weight);
        }

        scanner.close();
    }
}
