
import java.util.ArrayList;
import java.util.Scanner;
class NODES{
    int data;
    ArrayList<NODES> links;
    int colour;
    int distance;
    int key;
    NODES parent;
    int startTime;
    int finishTime;
    NODES(int n){
        data = n;
    }
    NODES(){}
}
class burnstatus{
    Boolean status;
    int time;
    burnstatus(Boolean stat, int t){
        status = stat;
        time = t;
    }
    burnstatus(){}
}
class edge{
    int wieght;
    boolean directed; // true is A to B . in (A,B)
    NODES A;
    NODES B;
    int key;
    NODES parent;
}
public class Mukund_Agrawal_2201126_Ques_2 {
    static NODES[] graphPoints;
    static ArrayList<edge> edgeList = new ArrayList<>();
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        int[][] mtx= { {0,1,1,1,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,1,0},{0,0,0,0,0,1,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,1,1,1,0}};
        graphPoints = matrixToList(mtx);
        burnstatus[] status = statusArray();
        fillEdges(mtx);
        NODES[] temp1 = graphPoints;
        System.out.println("Enter the source1 : ");
        int data1 = scan.nextInt();
        NODES source = new NODES();
        for(NODES i: graphPoints){
            if(data1 == i.data){
                source = i;
            }
        }
        status[source.data-1].status = true;
        status[source.data-1].time = 0;
        djikstra(temp1, source, status, 0);
        burnstatus[] status1 = statusArray();
        System.out.println("Enter the source2 : ");
        int data2 = scan.nextInt();
        NODES source1 = new NODES();
        for(NODES i: graphPoints){
            if(data2 == i.data){
                source1 = i;
            }
        }
        status1[source1.data-1].status = true;
        status1[source1.data-1].time = 0;
        djikstra(temp1, source1, status1, 0);
        findNode(status, status1);
        //NODES[] temp1 = graphPoints;     
    }
    static void findNode(burnstatus[] status, burnstatus[] status1){
        burnstatus min = new burnstatus(false, Integer.MAX_VALUE );
        int min_idx = Integer.MAX_VALUE;
        for(int i =0; i<status.length; ++i){
            if(status[i].time == status1[i].time && status[i].time != 0 && status[i].time < min.time){
                min = status[i];
                min_idx = i;
            }
        }
        if(min.time == Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println("They will meet at : " + (min_idx+1));
        }
    }
    static burnstatus[] statusArray(){
        burnstatus[] status = new burnstatus[graphPoints.length];
        for(int i=0; i<status.length; ++i){
            status[i] = new burnstatus();
        }
        for(int i=0; i<status.length; ++i){
            status[i].status = false;
            status[i].time = -1;
        }
        return status;
    }
    static void fillEdges(int[][] mtx) {
        //int weight = 5;
        for (int i = 0; i < mtx.length; i++) {
            for (int j = 0; j < mtx.length; j++) {
                if (mtx[i][j] == 1) {
                    edge temp = new edge();
                    temp.directed = true;
                    temp.A = graphPoints[i];
                    temp.B = graphPoints[j];
                    System.out.println("Enter weight for edge "+temp.A.data+" to "+temp.B.data+": ");
                    temp.wieght = scan.nextInt();
                    /*temp.wieght = weight;
                    weight += 2;*/
                    edgeList.add(temp);
                }
            }
        }
    }
    static NODES[] matrixToList(int[][] adj_mtx) {
        NODES[] adj_list = new NODES[adj_mtx.length];
        for (int i = 0; i < adj_list.length; ++i) {
            adj_list[i] = new NODES(i + 1);
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
    static int findWieght(NODES n1, NODES n2) {
        for (edge e : edgeList) {
            if (e.A.data == n1.data && e.B.data == n2.data) {
                return e.wieght;
            }
        }
        return -1;
    }
    static void djikstra(NODES[] adjList, NODES source, burnstatus[] status, int time){
        for(int i = 0; i<source.links.size(); ++i){
            if(!status[source.links.get(i).data - 1].status){
                int weight = findWieght(source, source.links.get(i));
                time = status[source.data-1].time + weight;
                status[source.links.get(i).data -1].status = true;
                status[source.links.get(i).data -1].time = time;
                djikstra(adjList, source.links.get(i), status, time );
            }
        }
    }
}
