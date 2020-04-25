package back01;
import java.util.Scanner;

public class msum {
	public static void main(String [] args) {
		int num,mod,sum=0,result=1000001;
		int a,b,c;
		
		Scanner s= new Scanner(System.in);
		num=s.nextInt();
		mod=1;
		for(int i=0;i<num;i++) {
			if(i>=mod*10) {
				mod*=10;
			}
			int j=i;
			int mir=mod;
			while(j!=0) {
				a=j/mir;
				sum+=a;
				j=j-(a*mir);
				if(mir>1)
					mir/=10;
			}
			if(((sum+i)==num) && ((sum+i)<=result)) {
				result=i;
			}
			sum=0;
		}
		if(result!=1000001) {
			System.out.println(result);
		}
		else
			System.out.println(0);
		return;
	}

}
