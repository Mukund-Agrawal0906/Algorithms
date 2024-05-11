#include <stdio.h>
#include <stdlib.h>

int bruteforce(int n)
{
    if (n >= 0)
    {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        else
        {
            return bruteforce(n - 1) + bruteforce(n - 2);
        }
    }
    else
        return -1;
}

int recursion(int n, int arr[])
{
    if (arr[n])
    {
        return arr[n];
    }
    else
    {
        int value = 0;
        if (n == 0 || n == 1)
        {
            value = n;
            arr[n] = value;
            return n;
        }
        else
        {
            value = recursion(n - 1, arr) + recursion(n - 2, arr);
            arr[n] = value;
            return value;
        }
    }
}

void bottom_up(int n, int arr[])
{
    if (n >=0)
    {
        arr[0] = 0;
        arr[1] = 1;
        for (int i = 2; i <=n; i++)
        {
            int value = arr[i - 1] + arr[i - 2];
            arr[i] = value;
        }
        printf("bottom up :%d\n", arr[n]);
    }
    else
        printf("bottom up :%d\n", -1);
}

void bottom_up_constant(int n)
{
    if (n >=0)
    {
        if (n == 0)
        {
            printf("bottom up const :%d\n", 0);
        }
        if (n == 1)
        {
            printf("bottom up const :%d\n", 1);
        }
        else
        {
            int k = 0;
            int l = 1;
            int fibo = 0;
            for (int i = 2; i <= n; i++)
            {
                fibo = k + l;
                k = l;
                l = fibo;
            }
            printf("bottom up const :%d\n", fibo);
        }
    }
    else
        printf("bottom up const : -1");
}

void top_down(int n)
{
    if (n >=0)
    {
        int arr[n+1];
        for (int i = 0; i <=n; i++)
        {
            arr[i] = 0;
        }
        int fibo = recursion(n, arr);
        printf("top down: %d\n", fibo);
    }
    else
        printf("top down : -1\n");
}
int main()
{
    int n;
    printf("Enter the number \n");
    scanf("%d", &n);

    int fibo = bruteforce(n);
    printf("Brute force: %d\n", fibo);

    top_down(n);
    int arr[n+1];
    for (int i = 0; i <=n; i++)
    {
        arr[i] = 0;
    }
    bottom_up(n, arr);

    bottom_up_constant(n);

    return 0;
}