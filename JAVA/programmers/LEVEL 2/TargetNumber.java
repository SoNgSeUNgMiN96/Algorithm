public class TargetNumber {
    static int answer=0;
    public int solution(int[] numbers, int target) {
        int length = numbers.length;
        getTarget(numbers,0,length,0,target);
        return answer;
    }
    public static void getTarget(int[] numbers, int depth,int length,int sum,int target){
        if(depth==length){
            if(target==sum) answer++;
            return;
        }
        getTarget(numbers,depth+1,length,sum+numbers[depth],target);
        getTarget(numbers,depth+1,length,sum-numbers[depth],target);
    }
}
