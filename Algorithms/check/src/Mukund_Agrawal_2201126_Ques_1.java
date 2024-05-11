import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

class NODES {
    int data;
    ArrayList<NODES> links;
    int colour;
    int distance;
    int key;
    NODES parent;
    int startTime;
    int finishTime;

    NODES(int n) {
        data = n;
    }

    NODES() {
    }
}

public class Mukund_Agrawal_2201126_Ques_1 {
    static NODES[] graphPoints;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of nodes: ");
        int n = sc.nextInt();
        System.out.print("Enter the number of edges: ");
        int m = sc.nextInt();
        int[][] mtx = new int[n][n];
        for (int i = 0; i < m; i++) {
            System.out.print("Enter the source node and destination node of edge " + (i + 1) + " separated by a space: ");
            int s = sc.nextInt();
            int d = sc.nextInt();
            mtx[s][d] = 1;
        }
        graphPoints = matrixToList(mtx);
        NODES[] temp1 = graphPoints;
        ArrayList<NODES> dfsForest = dfSearch(temp1);
        System.out.println("The size of the set is " + dfsForest.size());
        System.out.println("The set is: ");
        for (NODES i : dfsForest) {
            System.out.println(i.data - 1);
        }
    }

    static NODES[] matrixToList(int[][] adj_mtx) {
        NODES[] adj_list = new NODES[adj_mtx.length];
        for (int i = 0; i < adj_list.length; ++i) {
            adj_list[i] = new NODES(i+1);
            adj_list[i].links = new ArrayList<>();
        }
        for (int i = 0; i < adj_mtx.length; ++i) {
            for (int j = 0; j < adj_list.length; ++j) {
                if (adj_mtx[i][j] == 1) {
                    adj_list[i].links.add(adj_list[j]);
                }
            }
        }
        return adj_list;
    }

    static ArrayList<NODES> dfSearch(NODES[] adjList) {
        for (NODES i : adjList) {
            i.colour = 0;
            i.parent = null;
        }
        int time = 0;
        ArrayList<NODES> dfsForest = new ArrayList<>();
        LinkedList<NODES> finishing_order = new LinkedList<>();
        for (NODES j : adjList) {
            if (j.colour == 0 && !j.links.isEmpty()) {
                dfsVisit(adjList, j, time, finishing_order);
                time = j.finishTime;
                dfsForest.add(j);
                System.out.println("A tree with " + j.data + " as root(source) is added to the forest.");
            }
        }
        return dfsForest;
    }

    static void dfsVisit(NODES[] adjList, NODES start, int time, LinkedList<NODES> order) {
        time = time + 1;
        start.startTime = time;
        start.colour = 1;
        for (NODES i : adjList[start.data - 1].links) {
            if (i.colour == 0) {
                i.parent = start;
                dfsVisit(adjList, i, time, order);
                time = i.finishTime;
            }
        }
        time = time + 1;
        start.finishTime = time;
        order.addFirst(start);
        start.colour = 2;

    }
}
