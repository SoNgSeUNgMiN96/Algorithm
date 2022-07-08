import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ5569 {
     public static void main(String[] args) throws Exception {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st = new StringTokenizer(br.readLine());
         int w = Integer.parseInt(st.nextToken()), h = Integer.parseInt(st.nextToken());
         int[][][] dp = new int[h][w][4];   //0번은 방향없고 북쪽을 향하는중, 1번은 방향없고 동쪽을 향하는중, 2번은 북쪽으로만 진행 가능, 3번은 동쪽으로만 진행 가능
         for (int i = 0; i < h; i++) dp[i][0][0] = 1;
         for (int i = 0; i < w; i++) dp[0][i][1] = 1;
         for (int i = 1; i < h; i++) {
             for (int j = 1; j < w; j++) {
                 dp[i][j][0] = (dp[i-1][j][0] + dp[i-1][j][2])%100000;//아래 체킹 아래에서 이미 오던에만 확인
                 dp[i][j][2] = dp[i-1][j][1]; //동쪽을 향하다 올라온 아이는 방향변경없이 북쪽
                 dp[i][j][1] = (dp[i][j-1][1] + dp[i][j-1][3])%100000;//아래 체킹 아래에서 이미 오던에만 확인
                 dp[i][j][3] = dp[i][j-1][0];
             }
         }
         System.out.println((dp[h-1][w-1][0]+dp[h-1][w-1][1]+dp[h-1][w-1][2]+dp[h-1][w-1][3])%100000);
     }
}
