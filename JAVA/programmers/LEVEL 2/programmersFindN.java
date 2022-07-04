public class programmersFindN {
    class Solution {
        public int solution(int n) {
            int answer = 0, left=1, right=1;
            int[] numArr = new int[n+1];
            numArr[1] = 1;
            for(int i=2;i<=n;i++){        //자연수의 누적합으로 초기화
                numArr[i] = i + numArr[i-1];
            }

            while(right<=n){
                if(numArr[right]-numArr[left]+left==n){  //찾는 수일경우
                    answer++;
                    left++;
                    right++;
                }else if(numArr[right]-numArr[left]+left<n){
                    right++;
                }else left++;
            }

            return answer;
        }
    }
}
