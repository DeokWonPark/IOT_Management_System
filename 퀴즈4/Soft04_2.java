package soft4;
class Soft_4 {
  public int solution(int n) {
      int answer = n;
      int count=0;
      int temp=n;
      int count1=0;
      while(n>1){
          if(n%2==0)
              n/=2;
          else{
              n/=2;
              count++;
          }
      }
      
      while(true){
          if(count==count1)
              break;
          count1=0;
          answer++;
          temp=answer;
          while(temp>1){
            if(temp%2==0)
                temp/=2;
            else{
                temp/=2;
                count1++;
          }
         }
      }
      
      return answer;
  }
}
