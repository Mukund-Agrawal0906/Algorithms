#include <stdio.h>
#include <stdlib.h>

int n = 15;
int counter = 0;

int main()
{
    int arg;
    printf("Enter the Argument \n");
    scanf("%d", &arg);

    if (arg == 0)
    {
        for (int j = 1; j <= n; j++)
        {
            for (int i = 1; i <= n; i++)
            {
                printf("* ");
            }
            printf("\n");
        }
    }

    else
    {
        int ar = 0;

        for (int j = 1; j <= n;)
        {
            if (ar == arg || j == 1)
            {
                counter++;
                for (int i = 1; i <= n; i++)
                {
                    printf("* ");
                }
                printf("\n");
                j++;
                ar = 0;
            }

            else
            {
                if (counter % 2 == 1)
                {
                    for (int i = 1; i < n; i++)
                    {
                        printf("  ");
                    }
                    printf("*");
                }

                else
                {
                    printf("* ");
                    for (int i = 1; i < n; i++)
                    {
                        printf("  ");
                    }
                }
                ar++;
                j++;
                printf("\n");
            }
        }
    }
    return 0;
}