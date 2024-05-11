import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Edge implements Comparable<Edges> {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edges other) {
        return this.weight - other.weight;
    }
}

class DoublyLinkedListNode {
    int data;
    DoublyLinkedListNode next, prev;

    public DoublyLinkedListNode(int data) {
        this.data = data;
        next = prev = null;
    }
}

class DisjointSet {
    private final DoublyLinkedListNode[] sets;

    public DisjointSet(int n) {
        sets = new DoublyLinkedListNode[n];
        for (int i = 0; i < n; i++) {
            makeSet(i);
        }
    }

    public void makeSet(int data) {
        sets[data] = new DoublyLinkedListNode(data);
    }

    public DoublyLinkedListNode find(int data) {
        return sets[data];
    }

    public void union(int data1, int data2) {
        DoublyLinkedListNode node1 = find(data1);
        DoublyLinkedListNode node2 = find(data2);
        if (node1 != node2) {
            DoublyLinkedListNode tail1 = node1;
            while (tail1.next != null) {
                tail1 = tail1.next;
            }
            tail1.next = node2;
            node2.prev = tail1;
            sets[data2] = tail1;
        }
    }
}

public class KruskalMST {
    public static List<Edges> kruskalMST(List<Edges> edges, int numVertices) {
        List<Edges> mst = new ArrayList<>();
        Collections.sort(edges);
        DisjointSets disjointSet = new DisjointSets(numVertices);

        for (Edges edge : edges) {
            int srcRoot = disjointSet.find(edge.src);
            int destRoot = disjointSet.find(edge.dest);

            if (srcRoot != destRoot) {
                mst.add(edge);
                disjointSet.union(edge.src, edge.dest);
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
