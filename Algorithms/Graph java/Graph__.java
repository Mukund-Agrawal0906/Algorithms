import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
class NODES{
    int data;
    ArrayList<NODES> links;
    int colour; // White : 0 ; Grey : 1 ; Black : 2 . 
    int distance;
    NODES parent;
    int startTime;
    int finishTime;
    NODES(int n){
        data = n; 
    }
    NODES(){}
}
public class Graph__{
    static Scanner scan = new Scanner(System.in);
//    static LinkedList<NODES> order = new LinkedList<>();
    static ArrayList<NODES> graphPoints = new ArrayList<>();
    public static void main(String[] args){
        int[][]  mtx = {{0,1,0,0,0,0,0,0},{0,0,1,0,1,0,0,0},{0,0,0,1,0,0,1,0},{0,0,1,0,0,0,0,1},{1,0,0,0,0,1,0,0},{0,0,0,0,0,0,1,0},{0,0,0,0,0,1,0,1},{0,0,0,0,0,0,0,1}};
        NODES[] list = matrixToList(mtx);
        ArrayList<NODES> SCC = stronglyConnectedComponent(list);
    } 
    static void printMatrix(int[][] mtx){
        for(int[] i : mtx){
            for(int j :i){
                System.out.print(j+" ");
            }
            System.out.println();
        } 
    }
    static void fillMatrix(int[][] arr){
        for(int i=0; i<arr.length;++i){
            for(int j = 0; j<arr[0].length;++j){
                arr[i][j] = scan.nextInt();
            }
        }
    }
    static NODES[] fillList(){
        System.out.println("Enter the no. of vertices in graph : ");
        int n = scan.nextInt();
        NODES[] list = new NODES[n]; 
        int data = 0;
        for(int i=0; i<list.length;i++){
            data++;
            list[i] = new NODES(data);
        }
        for(int i=0; i<list.length; ++i){
            System.out.println("Enter the nodes connected to node"+(i+1));
            int choice=0;
            while(choice>=0 && choice<list.length){
                list[i].links.add(list[choice-1]);
            }
        }
        return list;
    }
    static void printList(NODES[] list){
         for(NODES i:list ){
            System.out.print(i.data);
            for(NODES j:i.links){
                System.out.print("->"+j.data);
            }
            System.out.println();
        }
    }
    static int[][] listToMatrix(NODES[] adj_list){
        int[][] adj_mtx = new int[adj_list.length][adj_list.length];
        for(NODES i : adj_list){
            for(NODES j:i.links){
                adj_mtx[i.data-1][j.data-1] = 1;
            }
        }
        return adj_mtx;
    }
    static NODES[] matrixToList(int[][] adj_mtx){
        NODES[] adj_list = new NODES[adj_mtx.length];
        for(int i=0; i<adj_list.length;++i){
            adj_list[i] = new NODES(i+1);
            adj_list[i].links = new ArrayList<>();
        }
        for(int i=0; i<adj_mtx.length; ++i){
            for(int j=0; j<adj_list.length; ++j){
                if(adj_mtx[i][j]==1){
                    adj_list[i].links.add(adj_list[j]); 
                }
            }
        }
        return adj_list;
    }
    static void directedDegree(NODES[] adjList){
        int[][] Degree = new int[adjList.length][2];
        for(int i=0; i<Degree.length; ++i){
            for(int j=0; j<Degree[0].length; ++j){
                Degree[i][j] = 0;
            }
        }
        for(int i =0; i<adjList.length ;++i){
            for(NODES j :  adjList[i].links){
                Degree[i][1]++;
                Degree[j.data-1][0]++;
            }
        }
        int count =1;
        for(int[] i: Degree){
            System.out.println("for the node "+count+" in degree "+i[0]+" Out degree "+i[1]);
            count++;
        }
    }
    static void directedDegree(int[][] adjMtx){
        int[][] Degree = new int[adjMtx.length][2];
         for(int i=0; i<Degree.length; ++i){
            for(int j=0; j<Degree[0].length; ++j){
                Degree[i][j] = 0;
            }
        }
        for(int i=0; i<adjMtx.length; ++i){
            for(int j=0;j<adjMtx.length;++j){
                if(adjMtx[i][j] == 1){
                    Degree[i][0]++;
                    Degree[j][1]++;
                }
            }
        }
        int count =1;
        for(int[] i: Degree){
            System.out.println("for the node "+count+" in degree "+i[0]+" Out degree "+i[1]);
            count++;
        }
    }
    static void degree(NODES[] adjList){
        int[] Degree = new int[adjList.length];
        for(int i=0; i<Degree.length; ++i){
            Degree[i] = 0;
        }
        for(int i =0; i<adjList.length ;++i){
            for(NODES j :  adjList[i].links){
                Degree[i]++;
            }
        }
        int count =1;
        for(int i: Degree){
            System.out.println("for the node "+count+" the  degree will be "+i);
            count++;
        }
    }
    static void degree(int[][] adjMtx){
        int[] Degree = new int[adjMtx.length];
         for(int i=0; i<Degree.length; ++i){
            Degree[i] = 0;
        }
        for(int i=0; i<adjMtx.length; ++i){
            for(int j=0;j<adjMtx.length;++j){
                if(adjMtx[i][j] == 1){
                    Degree[i]++;
                }
            }
        }
        int count =1;
        for(int i: Degree){
            System.out.println("for the node "+count+" degree is "+i);
            count++;
        }
    }
    static NODES[] transpose(NODES[] adjList){
        NODES[] transposeList = new NODES[adjList.length];
        for(int i=0; i<transposeList.length;++i){
            transposeList[i] = new NODES();
            transposeList[i].links = new ArrayList<>();
        }
        for(int i=0; i<adjList.length; i++){
            transposeList[i].data = adjList[i].data;
            for(NODES p:adjList[i].links){
                transposeList[p.data-1].links.add(transposeList[i]);
            }
        }
        return transposeList;
    }
    static int[][] transpose(int[][] adjMtx){
        int[][] transpose = new int[adjMtx.length][adjMtx.length];
        for(int i=0;i<adjMtx.length; ++i){
            for(int j=0; j<adjMtx.length; ++j){
                if(adjMtx[i][j] == 1)
                    transpose[j][i] = 1;
            }
        }
        return transpose;
    }
    static Boolean udConnected(NODES[] adjList){
        NODES source = adjList[0];
        source = bfSearch(adjList, source);
        for(NODES j:adjList){
            if(j.colour !=2){
                return false;
            }
        }
        return true;
    }
    static NODES bfSearch(NODES[] adjList, NODES source){
        NODES bfstree = new NODES(); 
        for(NODES j : adjList){
            j.colour = 0;
            j.distance = Integer.MAX_VALUE;
            j.parent = null;
        }
        bfstree = adjList[source.data-1];
        bfstree.distance =0;
        Queue<NODES> queue = new LinkedList<>();
        queue.add(bfstree);
        while(!queue.isEmpty()){
            NODES u = queue.remove();
           // System.out.println("Started processing "+ u.data);
            for(NODES v : adjList[u.data-1].links){
                if(v.colour == 0){
                //    System.out.println("Discovered :" + v.data);
                    v.colour = 1;
                    v.distance = u.distance +1;
                    v.parent = u;
                    queue.add(v);
                }                
            }
            //System.out.println("Finshed processing " + u.data);
            u.colour = 2;
        }
        return bfstree;
    } 
    static ArrayList<NODES> dfSearch(NODES[] adjList){
        for(NODES i : adjList){
            i.colour = 0;
            i.parent = null;
        }
        int time = 0;
        ArrayList<NODES> dfsForest = new ArrayList<>();
        LinkedList<NODES> finishing_order = new LinkedList<>();
        for(NODES j : adjList){
            if(j.colour == 0){
            //    System.out.println(j.data+" is discovered");
                dfsVisit(adjList, j, time, finishing_order);
                time = j.finishTime;
                dfsForest.add(j);
                System.out.println("A tree with "+j.data+" as root(source) is added to the forest.");
            }
        }
        return dfsForest;
    }
    static void dfsVisit(NODES[] adjList, NODES start, int time, LinkedList<NODES> order){
        time = time+1;
        start.startTime = time;
        start.colour = 1;
    //    System.out.println("Started processing "+start.data+" at "+start.startTime);
        for(NODES i:adjList[start.data-1].links){
            if(i.colour == 0){
    //            System.out.println(i.data + " is discovered. ");
                i.parent = start;
                dfsVisit(adjList, i, time, order);
                time = i.finishTime;
            }
        }
        time = time+1;
        start.finishTime = time;
        order.addFirst(start);
        start.colour = 2;
    //    System.out.println("finished processing "+start.data+" at "+start.finishTime);
    }
    static int[][] undirectedGraph(int[][] adjMtx){
        int[][] simpleGraph = new int[adjMtx.length][adjMtx.length];
        for(int i=0; i<adjMtx.length; ++i){
            for(int j=0; j<adjMtx.length;++j){
                if(adjMtx[i][j] ==1){
                    simpleGraph[i][j] = 1;
                    simpleGraph[j][i] = 1;
                }else{
                    simpleGraph[i][j] = 0;
                }
            }
        }
        return simpleGraph;
    }
    static Boolean weaklyConnected(int[][] adjMtx){
        int[][] ud = undirectedGraph(adjMtx);
        NODES[] list = matrixToList(ud);
        bfSearch(list, list[0]);
        for(NODES j: list){
            if(j.colour !=2){
                return false;
            }
        }
        return true;
    }
    static LinkedList<NODES> topologicalSort(NODES[] adjList){
        LinkedList<NODES> order = new LinkedList<>();
        for(NODES i : adjList){
            i.colour = 0;
            i.parent = null;
        }
        int time = 0;
        for(NODES j : adjList){
            if(j.colour == 0){
                //System.out.println(j.data+" is discovered");
                dfsVisit(adjList, j, time, order);
                time = j.finishTime;
            }
        }
        return order;
    }
    static ArrayList<NODES> stronglyConnectedComponent(NODES[] adjList){
        NODES[] G = adjList.clone();
        LinkedList<NODES> finishingOrder = topologicalSort(G);
        System.out.println(G[0].finishTime);
        NODES[] gTrans = adjList.clone();
        gTrans = transpose(gTrans);
        for(NODES i : gTrans){
            i.colour = 0;
            i.parent = null;
        }
        int time = 0;
        ArrayList<NODES> dfsForest = new ArrayList<>();
        LinkedList<NODES> finishing_order = new LinkedList<>();
        for(NODES j : gTrans){
            if(j.colour == 0){
            //    System.out.println(j.data+" is discovered");
                dfsVisit(gTrans, j, time, finishing_order);
                time = j.finishTime;
                dfsForest.add(j);
                System.out.println("A SCC with "+j.data+" is added to the forest.");
            }
        }
        System.out.println();
        return dfsForest;
    }
}
