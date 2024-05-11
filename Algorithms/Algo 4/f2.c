#include <stdio.h>
#include <stdlib.h>

int combine(int arr[], int leftMajor, int rightMajor, int l, int r)
{
    if (leftMajor == rightMajor)
        return leftMajor;

    int countL = 0, countR = 0;

    for (int i = l; i <= r; i++)
    {
        if (arr[i] == leftMajor)
            countL++;

        else if (arr[i] == rightMajor)
            countR++;
    }

    if (countL > (r - l + 1) / 2)
        return leftMajor;

    else if (countR > (r - l + 1) / 2)
        return rightMajor;
    else
        return -1;
}

int findMajority(int *arr, int l, int r)
{
    if (l == r)
        return arr[l];

    int mid = l + (r - l) / 2;
    int leftMajor = findMajority(arr, l, mid);
    int rightMajor = findMajority(arr, mid + 1, r);

    return combine(arr, leftMajor, rightMajor, l, r);
}

int main()
{
    int n;
    printf("Enter the number of elements \n");
    scanf("%d", &n);
    int arr[n];
    printf("Enter the Elements \n");
    for (int i = 0; i < n; i++)
    {
        scanf("%d", &arr[i]);
    }
    printf("The Majority Element is %d\n", findMajority(arr, 0, n - 1));
    return 0;
}