#include<stdio.h>
#include<stdlib.h>
#include<limits.h>



int MCM(int A[], int n) {
    int matrix[n][n], s[n][n];
    int i, j, k, d, res, min = INT_MAX;

    for (i = 0; i < n; i++) {
        for (j = 0; j < n; j++) {
            matrix[i][j] = 0;
            s[i][j] = 0;
        }
    }

    for (d = 1; d < n - 1; d++) {
        for (i = 1; i < n - d; i++) {
            j = i + d;
            min = INT_MAX;
            for (k = i; k < j; k++) {
                res = matrix[i][k] + matrix[k + 1][j] + A[i - 1] * A[k] * A[j];
                if (res < min) {
                    min = res;
                    s[i][j] = k;
                }
            }
            matrix[i][j] = min;
        }
    }

    return matrix[1][n - 1];
}


int main(){
    int n;
    printf("Enter the Number of Matrix\n");
    scanf("%d",&n);

    int dimension[n+1];
    printf("Enter the Dimension \n");

    for(int i=0;i<n+1;i++)
    {
        scanf("%d",&dimension[i]);
    }

    printf("The Least Cost is : %d\n",MCM(dimension,n+1));
    return 0;
   

}