import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ14501 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] temp;

        int arr[][] = new int[N][2];
        int dp[][] = new int[N][2]; //dp[i][0] 은 상담한 경우, dp[i][1]은 상담하지 않은경우

        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(temp[0]);  //걸리는 일수
            arr[i][1] = Integer.parseInt(temp[1]);  //코스드
        }

        System.out.println(setDp(dp,arr,0,N-1));
    }
    static public int setDp(int dp[][], int arr[][],int depth, int N){
          if(depth>N)   return 0;
          if(dp[depth][1]==0) dp[depth][1] = setDp(dp, arr, depth + 1, N); //상담을 안하는경우
          if(dp[depth][0]==0&&arr[depth][0]+depth<=N+1) dp[depth][0] = arr[depth][1] + setDp(dp,arr,depth+arr[depth][0],N);
          return  Integer.max(dp[depth][1],dp[depth][0]);
    }
}
