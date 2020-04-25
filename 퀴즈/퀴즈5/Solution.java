package sol;

class Solution {
  public int solution(int n) {
      int answer = 0;
      int sum=0;
      for(int i=0;i<n;i++){
          sum=0;
          for(int j=0;j<n;j++){
              sum+=i+j+1;
              if(sum==n){
                  answer++;
                  break;
              }
              else if(sum>n){
                  break;
              }
                  
          }
      }
          
      return answer;
  }
}