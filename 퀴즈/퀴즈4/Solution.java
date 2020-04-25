class Solution {
  public int solution(long num) {
      int count=0;
      while(num!=1){
          if(count==500){
              return -1;
          }
          if(num%2==0){
              num/=2;
              count++;
          }
          else{
            num*=3;
            num+=1;
            count++;
        }
          if(num==1){
              return count;
          }
          if(count==500){
              return -1;
          }
      }
      
      int answer = count;
      return answer;
  }
}