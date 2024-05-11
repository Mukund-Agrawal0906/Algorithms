#include <stdio.h>
#include <stdlib.h>

struct Edge
{
    int source;
    int destination;
    int weight;
};

struct DisjointSetNode
{
    int parent;
    int rank;
};

struct DisjointSetNode* createSetNode(int v)
{
    struct DisjointSetNode* node = (struct DisjointSetNode*) malloc (sizeof(struct DisjointSetNode));
    node.parent = v;
    node.rank=0;
    return node;
}

int findSet(struct DisjointSetNode** sets, int v)
{
    if(sets[v].parent!=v)
    {
        sets[v].parent = findSet(sets,sets[v].parent);
    }
    return sets[v].parent;
}

void unionSets(struct DisjointSetNode** sets, int v1, int v2 )
{
    int root1 = findSet(sets, v1);
    int root2 = findSet(sets, v2);

    if(root1 != root2)
    {
        if(sets[root1].rank<sets[root2].rank)
        {
            sets[root1].parent = root2;
        }
        else if(sets[root1].rank>sets[root2].rank)
        {
            sets[root2].parent = root1;
        }
        else
        {
            sets[root2].parent = root1;
            sets[root1].rank++;
        }
    }
}

int compareEdges(const void* a, const void*b)
{
    return ((struct Edge*)a
}

       void KruskalMST(struct Edge* edges, int numEdges, int numVertices)
{
    struct Edge resultMST[numVertices];
    struct DisjointSetNode* sets[numVertices];

    for(int i=0; i< numVertices; i++)
    {
        sets[i] = createSetNode(i);
    }
    qsort(edges,numEdges,)
}
