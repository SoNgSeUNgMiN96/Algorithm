import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ9663 {
    static int answer=0,N;
    static int[] queens;
    static boolean[] visted;
    static boolean[][] disable;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        queens = new int[N];
        visted = new boolean[N];
        disable = new boolean[N][N];
        search(0);
        System.out.println(answer);
    }
    private static void search(int n) {
        if(n==N){
            answer++;
            return;
        }
        loop1: for (int i=0;i<N;i++){

            for(int j=0;j<n;j++){   //기존 설치 열과 겹치는지 확인
                if(queens[j]==i){
                    //이미 설치한 자리라면,
                    continue loop1;
                }if(Math.abs(i-queens[j])==n-j){    //열과 행이 정확히 차이나는지 확인
                    continue loop1;
                }
            }
            queens[n] = i;
            search(n+1);
        }
    }
}
