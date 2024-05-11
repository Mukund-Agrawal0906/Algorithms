#include<stdio.h>
#include<stdlib.h>

int main()
{
    int n;
    printf("Enter the number of elements \n");
    scanf("%d",&n);
    int Arr[n];
    printf("Enter the elements in either ascending or descending\n");
    for(int i=0;i<n;i++)
    {
        scanf("%d",&Arr[i]);
    }
    if(Arr[0]>Arr[n-1])
    {
        printf("Maximum is %d \n",Arr[0]);
        printf("Minimum is %d \n",Arr[n-1]);
    }
    else
    {
        printf("Maximum is %d \n",Arr[n-1]);
        printf("Minimum is %d \n",Arr[0]);
    }
    return 0;
}