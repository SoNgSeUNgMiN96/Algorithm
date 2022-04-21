import java.util.Comparator;
import java.util.PriorityQueue;

public class commuRunningNumberGame {

    public static int solution(int[] A, int[] B) {
        int answer = A.length;

        PriorityQueue<Integer> priorityQueueA = new PriorityQueue<>();
        PriorityQueue<Integer> priorityQueueB = new PriorityQueue<>();

        for(int i: A){
            priorityQueueA.add(i);
        }

        for(int i: B){
            priorityQueueB.add(i);
        }

        while (priorityQueueB.size()>0){

            if(priorityQueueA.peek()<priorityQueueB.peek()){
                priorityQueueA.poll();

            }else if (priorityQueueA.peek()>priorityQueueB.peek()){
                answer--;
            }else {     //
                priorityQueueA.poll();
                answer--;
            }
            priorityQueueB.poll();
        }

        return answer;
    }

    public static void main(String[] args) {
        int a[] = {5,1,3,7};
        int b[] = {2,2,6,7};

        System.out.println(solution(a,b));


    }
}
