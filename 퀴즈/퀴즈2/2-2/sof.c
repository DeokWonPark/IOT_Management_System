#include<stdio.h>

int main(void)
{
	char str[80];
	int count, sum;
	int i, index, plus;

	scanf("%d", &count);

	for (i = 0; i < count; i++)
	{
		sum = 0;
		scanf("%s", str);
		plus = 1;
		index = 0;
		while (str[index] != NULL)
		{
			if (str[index] == 'O')
			{
				sum += plus;
				plus++;

			}
			if (str[index] == 'X')
			{
				plus = 1;
				index++;
				continue;
			}
			index++;
		}
		printf("%d \n", sum);
	}
	return 0;
}
