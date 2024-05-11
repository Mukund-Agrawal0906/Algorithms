#include <stdio.h>
#include <stdlib.h>

int search_decide(int Arr[], int l, int r, int choice, int key)
{
    while (l <= r)
    {
        int m;
        if (choice == 1)
        {
            m = l + (r - l) / 2;
        }
        if (choice == 2)
        {
            m = l + 2 * (r - l) / 3;
        }
        if (choice == 3)
        {
            m = l + (r - l) / 4;
        }

        
        if (Arr[m] == key)
            return m;
        else if (Arr[m] < key)
            l = m + 1;
        else
            r = m - 1;
    }
    return -1;
}

int main()
{
    int n;
    printf("Enter the number of elements in array \n");
    scanf("%d", &n);
    int Arr[n];
    printf("Enter the elements \n");
    for (int i = 0; i < n; i++)
    {
        scanf("%d", &Arr[i]);
    }

    int choice;
    printf("Enter the Preference \n");
    printf("1) Binary Search \n");
    printf("2) Alpha Search \n");
    printf("3) Beta Search \n");
    printf("Enter the Choice \n");
    scanf("%d", &choice);

    int key;
    printf("Enter the elements you want to search \n");
    scanf("%d", &key);

    int found=search_decide(Arr, 0, n - 1, choice, key);
    if(found==-1)
        printf("Not Found\n");

    else if(found==-2)
        printf("Wrong Choice\n");
    else
        printf("The Index is %d\n",found);
    return 0;
}