#include<stdio.h>
int main(void)
{
	int i,j=0;
	int count;
	int start, end=0,result=0;
	int load[1002] = { 0 };
	int check = 0;

	scanf("%d", &count);
	for (i = 0; i < count; i++)
	{
		scanf("%d", &load[i]);
	}
	while (load[j] != 0)
	{
		check = 0;
		start = load[j];
		 i = 1;
		while (load[j+i-1] < load[j + i])
		{
			end = load[j+i];
			if (load[j + i + 1] == 0)
			{
				end = load[j+i];
				break;
			}
			if (load[j + i + 1] < load[j + i])
				break;
			check++;
			i++;
		}
		if (result < (end - start))
		{
			result = (end - start);
		}
		if (check != 0)
		{
			j = j + i+1;
		}
		else
			j++;
	}
	if (end == 0)
		printf("0 \n");
	else
	{
		printf("%d \n", result);
	}
		
	return 0;
}