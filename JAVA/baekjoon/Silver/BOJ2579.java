import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ2579 {

    public static void main(String[] args)  throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int dp[][] = new int[N+1][2], arr[]= new int[N+1];
        for (int i = 1; i <= N; i++)
            arr[i] = Integer.parseInt(br.readLine());
        int answer = Math.max(setDP(dp,N,arr,0),setDP(dp,N,arr,1));
        System.out.println(answer);
    }

    private static int setDP(int[][] dp, int n, int[] arr, int m) { //m=0 전칸에서 온경우 1=전전칸
        if(n==0)
            return arr[0];
        else if (n==1)
            if (m==1) return arr[n];
            else return arr[n]+arr[0];
        if (dp[n][m]!=0)
            return dp[n][m];
        if(m==0) return dp[n][m] = setDP(dp,n-1,arr,1)+ arr[n];    //그전 친구는 전칸이 비어야한다.
        else return dp[n][m] = Math.max(setDP(dp,n-2,arr,0),setDP(dp,n-2,arr,1))+ arr[n];
    }
}
