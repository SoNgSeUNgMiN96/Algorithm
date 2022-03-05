import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class programmersFailureRate {

    public static void main(String[] args) {
        int stages [] = {4, 4, 4, 4, 4,7};
        int []answer = solution(6,stages);
        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i]+" ");
        }
    }

    static public int[] solution(int N, int[] stages) {
        int []answer = new int[N];
        double [][] failure = new double[N+2][3];

        /*
            fairlure[0] = 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수
            fairlure[1] = 스테이지 실패율
            fairlure[2] = 스테이지 넘버
            presum = 스테이지를 클리어한 플레이어 수
        */
        int presum [] = new int[N+2];

        for (int i = 0; i < stages.length; i++)
            failure[stages[i]-1][0]++;

        for (int i = N-1; i >=0; i--)
            presum[i] = presum[i+1] + (int)failure[i+1][0];

        for (int i = 0; i <=N; i++)
            failure[i][2] = i+1;

        //나중에 스테이지를 기준으로 정렬할 때 잔여 데이터의 스테이지를 최대갑이상으로 잡아둔다.
        failure[N][2] = 600;
        failure[N+1][2] = 600;

        for (int i = 0; i <N; i++) {
            if(presum[i]+failure[i][0]==0) break;
            failure[i][1] = (double)(failure[i][0])/(presum[i]+failure[i][0]);
        }

        Arrays.sort(failure,new Comparator<double[]>(){
            @Override
            public int compare(double[] o1, double[] o2) {
                if(o1[1]==o2[1]) return (o1[2] - o2[2])>0? 1 : -1;
                return ((o1[1] - o2[1])>0?-1:1);
            }
        });

        for (int i = 0; i <N; i++)
            answer[i] = (int)failure[i][2];

        return answer;
    }
}
