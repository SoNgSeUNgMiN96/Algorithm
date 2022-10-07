import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1915 {
     public static void main(String[] args) throws Exception{
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st;
         st = new StringTokenizer(br.readLine());
         int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), max=0;
         int[][] map = new int[N][M];
         int[][] dp = new int[N][M];
         for (int i = 0; i < N; i++) {
             String a = br.readLine();
             for (int j = 0; j < M; j++) {
                 map[i][j] = a.charAt(j)-'0';
                 dp[i][j] = map[i][j];
                 max = Math.max(dp[i][j],max);
             }
         }

         for (int i = 1; i < N; i++) {
             for (int j = 1; j < M; j++) {
                 if(dp[i][j]==0) continue;
                 dp[i][j] = Math.min(Math.min(dp[i-1][j-1],dp[i-1][j]),dp[i][j-1])+1;
                 max = Math.max(dp[i][j],max);
             }
         }
         System.out.println(max*max);
     }
}