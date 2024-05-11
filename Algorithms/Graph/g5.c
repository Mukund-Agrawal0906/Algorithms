#include <stdio.h>
#include <stdlib.h>

struct Edge {
    int src, dest, weight;
    struct Edge* next;
};

struct Graph {
    int V, E;
    struct Edge* edge;
};

struct Graph* createGraph(int V, int E) {
    struct Graph* graph = (struct Graph*)malloc(sizeof(struct Graph));
    graph->V = V;
    graph->E = E;
    graph->edge = NULL;
    return graph;
}

void addEdge(struct Graph* graph, int src, int dest, int weight) {
    struct Edge* newEdge = (struct Edge*)malloc(sizeof(struct Edge));
    newEdge->src = src;
    newEdge->dest = dest;
    newEdge->weight = weight;
    newEdge->next = graph->edge;
    graph->edge = newEdge;
}

void primMST(struct Graph* graph) {
    int V = graph->V;
    struct Edge* MST[V];
    int key[V];
    int inMST[V];

    for (int i = 0; i < V; i++) {
        key[i] = INT_MAX;
        inMST[i] = 0;
        MST[i] = NULL;
    }

    key[0] = 0;

    for (int count = 0; count < V - 1; count++) {
        int minKey = INT_MAX;
        int u;

        for (int v = 0; v < V; v++) {
            if (!inMST[v] && key[v] < minKey) {
                minKey = key[v];
                u = v;
            }
        }

        inMST[u] = 1;

        struct Edge* edge = graph->edge;
        while (edge != NULL) {
            int src = edge->src;
            int dest = edge->dest;
            int weight = edge->weight;
            edge = edge->next;

            if ((inMST[src] || inMST[dest]) && (key[src] + key[dest] - weight < key[u])) {
                key[u] = key[src] + key[dest] - weight;
                MST[u] = edge;
            }
        }
    }

    printf("Edges in MST:\n");
    for (int i = 1; i < V; i++) {
        printf("%d - %d\n", MST[i]->src, MST[i]->dest);
    }
}

int main() {
    int V, E;
    printf("Enter the number of vertices: ");
    scanf("%d", &V);
    printf("Enter the number of edges: ");
    scanf("%d", &E);

    struct Graph* graph = createGraph(V, E);

    for (int i = 0; i < E; i++) {
        int src, dest, weight;
        printf("Enter edge %d (source destination weight): ", i + 1);
        scanf("%d %d %d", &src, &dest, &weight);
        addEdge(graph, src, dest, weight);
    }

    primMST(graph);

    return 0;
}
