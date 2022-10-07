import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ7579 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N =Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        int[] appMemory = new int[N];
        int[] appCost = new int[N];
        int[][] dp = new int[N+1][N*100+1];  //그까지의 최소비용.

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            appMemory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            appCost[i] = Integer.parseInt(st.nextToken());
        }
        dp[0][appCost[0]] = appMemory[0];

        for (int i = 1; i <N; i++) {
            for (int j = 0; j < appCost[i]; j++) {
                dp[i][j] = dp[i-1][j];  //이전앱 그대로 사용한 만큼
            }
            for (int j = appCost[i]; j < N*100+1; j++) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-appCost[i]] + appMemory[i]);
            }
        }
        int i;
        for (i = 0; i < N*100+1; i++) {
            if(dp[N-1][i]>=M) break;
        }
        System.out.println(i);
    }
}