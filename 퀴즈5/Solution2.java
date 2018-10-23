package sol;

class Solution2 {
  public long solution(int n) {
      long answer = 1;
      long before=1;
      long mid=1;
      for(int i=2;i<=n;i++){
          answer=((mid+before)%1234567);
          mid=before;
          before=answer;
          }
       return answer;
  }
}