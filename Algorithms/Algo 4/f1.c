#include <stdio.h>
#include <stdlib.h>

int peakelement(int Arr[], int l, int h)
{
    if (l == h)
    {
        return Arr[l];
    }
    int mid = l + (h - l) / 2;
    if (mid > l && mid < h)
    {
        if ((Arr[mid] >= Arr[mid - 1]) && (Arr[mid] >= Arr[mid + 1]))
            return Arr[mid];
        else
        {
            if (Arr[mid - 1] >= Arr[mid + 1])
            {
                peakelement(Arr, l, mid - 1);
            }
            else
            {
                peakelement(Arr, mid + 1, h);
            }
        }
    }
    else if (mid == l)
    {
        if (Arr[mid] >= Arr[mid + 1])
            return Arr[mid];
    }
    else if (mid == h)
    {
        if (Arr[mid] >= Arr[mid - 1])
            return Arr[mid];
    }
    else
        return -1;
}

int main()
{
    int n;
    printf("Enter the number of elements \n");
    scanf("%d", &n);
    int Arr[n];
    printf("Enter the elements\n");
    for (int i = 0; i < n; i++)
    {
        scanf("%d", &Arr[i]);
    }
    int peak = peakelement(Arr, 0, n - 1);
    printf("The peak Element is %d\n", peak);
    return 0;
}