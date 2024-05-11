#include <stdio.h>
#include <stdlib.h>

struct Edge
{
    int src, dest, weight;
};

struct Node
{
    int data;
    struct Node* next;
    struct Node* prev;
};

struct SetNode
{
    struct Node* head;
    struct Node* tail;
    int representative;
};

struct Node* createNode(int data)
{
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->data = data;
    newNode->next = NULL;
    newNode->prev = NULL;
    return newNode;
}

struct SetNode* createSetNode(int data)
{
    struct SetNode* setNode = (struct SetNode*)malloc(sizeof(struct SetNode));
    setNode->head = createNode(data);
    setNode->tail = setNode->head;
    setNode->representative = data;
    return setNode;
}

int findSetRepresentative(struct SetNode* set, int data)
{
    struct Node* currentNode = set[data].head;
    while (currentNode != NULL)
    {
        if (currentNode->data == data)
        {
            return set[currentNode->data].representative;
        }
        currentNode = currentNode->next;
    }
    return -1;
}

void mergeSets(struct SetNode* set, int data1, int data2)
{
    int rep1 = findSetRepresentative(set, data1);
    int rep2 = findSetRepresentative(set, data2);
    if (rep1 != rep2)
    {
        set[rep1].tail->next = set[rep2].head;
        set[rep2].head->prev = set[rep1].tail;
        set[rep1].tail = set[rep2].tail;

        struct Node* currentNode = set[rep2].head;
        while (currentNode != NULL)
        {
            set[currentNode->data].representative = rep1;
            currentNode = currentNode->next;
        }
    }
}

int compareEdges(const void* a, const void* b)
{
    return ((struct Edge*)a)->weight - ((struct Edge*)b)->weight;
}

void kruskalMST(struct Edge* edges, int numEdges, int numVertices)
{
    qsort(edges, numEdges, sizeof(struct Edge), compareEdges);

    struct SetNode* sets = (struct SetNode*)malloc(numVertices * sizeof(struct SetNode));
    for (int i = 0; i < numVertices; i++)
    {
        sets[i] = *createSetNode(i);
    }

    int numMSTEdges = 0;
    int mstWeight = 0;

    for (int i = 0; i < numEdges; i++)
    {
        int src = edges[i].src;
        int dest = edges[i].dest;
        int srcRep = findSetRepresentative(sets, src);
        int destRep = findSetRepresentative(sets, dest);

        if (srcRep != destRep)
        {
            printf("Edge %d-%d with weight %d added to MST\n", src, dest, edges[i].weight);
            mstWeight += edges[i].weight;
            mergeSets(sets, src, dest);
            numMSTEdges++;
        }

        if (numMSTEdges == numVertices - 1)
        {
            break;
        }
    }

    if (numMSTEdges < numVertices - 1)
    {
        printf("The graph is not connected.\n");
        return;
    }

    printf("Total weight of MST: %d\n", mstWeight);

    free(sets);
}

int main()
{
    int numVertices, numEdges;
    printf("Enter the number of vertices and edges: ");
    scanf("%d %d", &numVertices, &numEdges);

    struct Edge* edges = (struct Edge*)malloc(numEdges * sizeof(struct Edge));
    printf("Enter edges (source destination weight):\n");
    for (int i = 0; i < numEdges; i++)
    {
        scanf("%d %d %d", &edges[i].src, &edges[i].dest, &edges[i].weight);
    }

    kruskalMST(edges, numEdges, numVertices);

    free(edges);
    return 0;
}
