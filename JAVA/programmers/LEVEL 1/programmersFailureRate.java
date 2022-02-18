import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class programmersFailureRate {

    public static void main(String[] args) {
        int stages [] = {4,4,4,4,4};
        System.out.println(solution(5,stages));
    }

    static public int[] solution(int N, int[] stages) {
        int []answer = new int[N];
        double [][] failure = new double[N+1][2];
        int presum [] = new int[N+1];
        for (int i = 0; i < stages.length; i++)
            failure[stages[i]-1][0]++;

        presum[N]= (int)failure[N][0];


        for (int i = N-1; i >=0; i--) {
            presum[i] = presum[i+1] + (int)failure[i][0];
        }

        for (int i = 0; i <N; i++) {
            System.out.print(presum[i]+" ");
            if(presum[i]==0)
                break;
            failure[i][1] = (double)(presum[i]-presum[i+1])/presum[i];
            failure[i][0] = i+1;
        }
        failure[N][0] = N+1;
        failure[N][1]  = 0;

        System.out.println();
        for (int i = 0; i <N; i++) {
            System.out.print(failure[i][1]+" "+failure[i][0]+" ");
        }

        Arrays.sort(failure,new Comparator<double[]>(){
            @Override
            public int compare(double[] o1, double[] o2) {
                return ((o1[1] - o2[1])>0?-1:1);
            }
        });

        System.out.println();
        for (int i = 0; i <N; i++) {
            answer[i] = (int)failure[i][0];
        }



        return answer;
    }
}
