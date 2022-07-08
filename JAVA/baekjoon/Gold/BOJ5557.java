import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ5557 {
     public static void main(String[] args) throws Exception{
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st;
         int N = Integer.parseInt(br.readLine());
         st = new StringTokenizer(br.readLine());
         int[] arr = new int[N], answer;
         long[][] dp = new long[N][21];
         for (int i = 0; i < N; i++) {
             arr[i] = Integer.parseInt(st.nextToken());
         }
         dp[0][arr[0]] = 1;
         for (int i = 1; i < N-1; i++) {
             for (int j = 0; j < 21; j++) {
                 if(j+arr[i]<21) dp[i][j+arr[i]] += dp[i-1][j];
                 if(j-arr[i]>=0) dp[i][j-arr[i]] += dp[i-1][j];
             }
         }
         System.out.println(dp[N-2][arr[N-1]]);
     }
}
