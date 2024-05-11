#include <stdio.h>
#include <stdlib.h>

int Ternary_Search(int Arr[], int l, int r, int key)
{
    while (l <= r)
    {
        int m = l + (r - l) / 3;
        int n = l + 2 * (r - l) / 3;
        if (Arr[m] == key)
            return m;
        if (Arr[n] == key)
            return n;
        if (Arr[m] > key)
            r = m - 1;
        if (Arr[m] < key && Arr[n] > key)
        {
            l = m + 1;
            r = n - 1;
        }
        else
            l = n + 1;
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

    int key;
    printf("Enter the elements you want to search \n");
    scanf("%d", &key);

    int found = Ternary_Search(Arr, 0, n - 1, key);
    if (found == -1)
        printf("Not Found\n");
    else
        printf("The Key is %d \n", found);
}