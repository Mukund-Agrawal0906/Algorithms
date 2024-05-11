import java.util.*;

class Graph2{
    private final int V;
    private final List<List<int []>> adj;

    public Graph2(int V){
        this.V = V;
        adj = new ArrayList<>(V);
        for(int i=0;i<V;i++){
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v, int weight) {
        adj.get(u).add(new int[]{v,weight});
    }

    private boolean isCyclicUtil(int v, boolean[] visited, boolean[] recStack){
        visited[v]=true;
        recStack[v]= true;

        for(int[] neighbor : adj.get(v)){
            int nextVertex = neighbor[0];
            if (!visited[nextVertex]){
                if(isCyclicUtil(nextVertex,visited,recStack)){
                    return true;
                }
            }
            else if(recStack[nextVertex]){
                return true;
            }
        }
        recStack[v]=false;
        return false;
    }

    public boolean isCyclic() {
        boolean [] visited = new boolean[V];
        boolean [] recStack = new boolean[V];

        for (int i = 0; i<V;i++){
            if(!visited[i]){
                if(isCyclicUtil(i,visited,recStack)){
                    return true;
                }
            }
        }
        return false;
    }
}

public class Ass11_Ques_1_a {
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of vertices : ");
        int V = scanner.nextInt();
        Graph2 graph = new Graph2(V);

        System.out.print("Enter the number of edges : ");
        int E = scanner.nextInt();

        for (int i = 0; i<E;i++){
            System.out.println("Enter edge " + (i+1) + " (u v weight) : ");
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.addEdge(u, v, weight);
        }
        if (graph.isCyclic()){
            System.out.println("The Graph contains a cycle.");
        }
        else{
            System.out.println("The Graph does not contain a cycle. ");
        }

        scanner.close();
    }
}