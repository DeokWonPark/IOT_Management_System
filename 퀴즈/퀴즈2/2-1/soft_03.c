#include<stdio.h>

int main(void)
{
	int count,num;
	int i,j;

	scanf("%d", &count);

	for (i = 0; i < count; i++)
	{
		scanf("%d", &num);
		j = 0;
		while (num != 0)
		{
			if (num % 2 == 1)
				printf("%d ", j);
			num /= 2;
			j++;
		}
		printf("\n");
		
	}
	return 0;
}