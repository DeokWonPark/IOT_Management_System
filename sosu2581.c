#include<stdio.h>

int main(void)
{
	int m, n;
	int gb = 0;
	int count = 0, sum = 0;
	int first = 0;
	int i, j;
	while (1)
	{
		scanf("%d", &m);
		if ((m > 10000) || (m <= 0))
			continue;
		scanf("%d", &n);
		if ((n > 10000) || (n <= 0))
			continue;
		if (m > n)
			continue;
		if ((m <= 10000 && n <= 10000) && (m <= n))
			break;
	}


	gb = m;

	for (i = 0; i < (n - m + 1); i++)
	{
		if (m == 1 && gb == 1)
		{
			gb++;
			continue;
		}
		if (m == 1 && n == 1)
		{
			break;
		}
		count = 0;
		for (j = 2; j < gb; j++)
		{
			if (gb%j == 0)
				count++;
		}
		if (count == 0)
			sum += gb;

		if (first == 0 && count == 0)
		{

			first = gb;
		}

		gb++;
	}
	if (sum == 0 && first == 0)
	{
		printf("%d \n", -1);
	}
	else
	{
		printf("%d \n", sum);
		printf("%d \n", first);
	}

	return 0;

}