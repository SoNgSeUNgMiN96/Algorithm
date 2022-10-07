import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2098 {
     public static void main(String[] args) throws Exception{
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st;
         int N = Integer.parseInt(br.readLine());
         int[][] map = new int [N][N];

         for (int i = 0; i < N; i++) {  //인접행렬 입력.
             st=  new StringTokenizer(br.readLine());
             for (int j = 0; j < N; j++) {
                 map[i][j] = Integer.parseInt(st.nextToken());
             }
         }


     }
}
