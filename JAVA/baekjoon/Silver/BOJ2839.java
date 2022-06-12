import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class BOJ2839 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int dp[] = IntStream.generate(()->5001).limit((N>5)?N+1:6).toArray();
        dp[3] = dp[5] = 1;
        for(int i=6;i<=N;i++) dp[i] = Math.min(dp[i-3],dp[i-5])+1;
        dp[N]= (dp[N]>5000)? -1:dp[N];
        System.out.println(dp[N]);
    }
}
