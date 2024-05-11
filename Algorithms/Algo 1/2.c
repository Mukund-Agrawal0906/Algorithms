#include<stdio.h>
#include<stdlib.h>
#include<math.h>

double logarithm(double n)
    {
        return log(n)/log(2);
    }

int min_nodes(int h)
{
    if (h==0)
        return 1;
    if (h==1)
        return 2;
    return 1+ min_nodes(h-1)+min_nodes(h-2);
}
int h1=0;
int max(int n)
{
    if(n>min_nodes(h1))
    {
        h1++;
        max(n);
    }
    else{
        return h1;
    }
}

int main()
{
    int n;
    printf("Enter the Number of Nodes \n");
    scanf("%d",&n);

    if (n==1)
    {
        printf("Min. height , Max. height = %d , %d \n",0,0);
    }

    if (n==0)
    {
        printf("AVL not possible\n");
    }
    
    else
    {
        double a;
        
        a= logarithm(n+1)-1;
        int maxheight=max(n);
        int k=0;
        if(!(a/1))
        {
            k=a/1;
        }
        else{
            k=(a/1)+1;
        }
        printf("Min. height , Max. height = %d , %d \n",k,maxheight);
    }


    return 0;

}