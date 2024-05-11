#include <stdio.h>
#include <stdlib.h>

struct Edge
{
    int src, dest, weight;
};

struct DisjointSet
{
    int *parent;
    int *rank;
    int n;
};

struct DisjointSet* createSet(int n)
{
    struct DisjointSet* ds = (struct DisjointSet*)malloc(sizeof(struct DisjointSet));
    ds->n = n;
    ds->parent = (int*)malloc(n * sizeof(int));
    ds->rank = (int*)malloc(n * sizeof(int));

    for (int i = 0; i < n; i++)
    {
        ds->parent[i] = i;
        ds->rank[i] = 0;
    }

    return ds;
}

int find(struct DisjointSet* ds, int i)
{
    if (i != ds->parent[i])
        ds->parent[i] = find(ds, ds->parent[i]); // Path compression
    return ds->parent[i];
}

void unionSets(struct DisjointSet* ds, int x, int y)
{
    int xRoot = find(ds, x);
    int yRoot = find(ds, y);

    if (xRoot == yRoot) return;

    if (ds->rank[xRoot] < ds->rank[yRoot])
    {
        ds->parent[xRoot] = yRoot;
    }
    else if (ds->rank[xRoot] > ds->rank[yRoot])
    {
        ds->parent[yRoot] = xRoot;
    }
    else
    {
        ds->parent[yRoot] = xRoot;
        ds->rank[xRoot]++;
    }
}

int compareEdges(const void* a, const void* b)
{
    return ((struct Edge*)a)->weight - ((struct Edge*)b)->weight;
}

void kruskalMST(struct Edge* edges, int numEdges, int numVertices)
{
    struct Edge resultMST[numVertices];
    int mstSize = 0;
    int edgeIndex = 0;

    qsort(edges, numEdges, sizeof(struct Edge), compareEdges);

    struct DisjointSet* ds = createSet(numVertices);

    while (mstSize < numVertices - 1)
    {
        struct Edge nextEdge = edges[edgeIndex++];

        int x = find(ds, nextEdge.src);
        int y = find(ds, nextEdge.dest);

        if (x != y)
        {
            resultMST[mstSize++] = nextEdge;
            unionSets(ds, x, y);
        }
    }

    printf("Minimum Spanning Tree Edges:\n");
    for (int i = 0; i < mstSize; i++)
    {
        printf("%d - %d : %d\n", resultMST[i].src, resultMST[i].dest, resultMST[i].weight);
    }

    free(ds->parent);
    free(ds->rank);
    free(ds);
}

int main()
{
    int numVertices, numEdges;

    printf("Enter the number of vertices: ");
    scanf("%d", &numVertices);
    printf("Enter the number of edges: ");
    scanf("%d", &numEdges);

    struct Edge edges[numEdges];

    for (int i = 0; i < numEdges; i++)
    {
        printf("Enter source, destination, and weight for edge %d: ", i + 1);
        scanf("%d %d %d", &edges[i].src, &edges[i].dest, &edges[i].weight);
    }

    kruskalMST(edges, numEdges, numVertices);

    return 0;
}

