import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11049 {

    static class  Point{
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Info{
        Point mat;
        long min;

        public Info(Point mat, long min) {
            this.mat = mat;
            this.min = min;
        }
    }

     public static void main(String[] args) throws Exception{
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st;
         int N = Integer.parseInt(br.readLine());
         Point[] mat = new Point[N];
         Info[][] dp = new Info[N][N];

         for (int i = 0; i < N; i++) {
             st = new StringTokenizer(br.readLine());
             mat[i] = new Point(Long.parseLong(st.nextToken()),Long.parseLong(st.nextToken()));
         }


         for (int i = 0; i < N; i++) {
             dp[i][i] = new Info(mat[i], 0);
         }


         for (int i = 1; i < dp.length; i++) {
             for (int j = 0; j + i < N; j++) {    //부분계산임.k
                 long min = Long.MAX_VALUE;
                 //1, 끝, 끝 1

                 for (int k = j; k <j+i; k++) {
                     min = Math.min(dp[j][k].min + dp[k+1][j+i].min+ dp[j][k].mat.x *dp[j][k].mat.y*dp[k+1][j+i].mat.y,min);
                 }

//                 min = Math.min(dp[j+1][j+i].min +dp[j+1][j+i].mat.y *mat[j].y*mat[j].x,min);
//                 min = Math.min(dp[j][j+i-1].min +dp[j][j+i-1].mat.y+dp[j][j+i-1].mat.x *mat[j].y,min);
//
//                 for(int k=j+1;k<i+j;k++){
//                     min = Math.min(min,dp[j][k-1].min+dp[j][k-1].mat.x*dp[j][k-1].mat.y*mat[k].y + dp[k+1][j+i].min +dp[k+1][j+i].mat.y *mat[k].y*mat[k].x);
//                     //min = Math.min(min,dp[j][j+i-1].min+ dp[j][j+k].mat.x*dp[j][j+k].mat.y*mat[k].y+ mat[k].y*mat[k].x*dp[j+k+1][j+i].mat.y);
                 //}
                 dp[j][j+i] = new Info(new Point(mat[j].x,mat[j+i].y), min);
             }
         }
         for (int i = 0; i < N; i++) {
             for (int j = 0; j < N; j++) {
                 if(dp[i][j]!=null)
                    System.out.print(dp[i][j].min+" ");
                 else System.out.print(" X ");
             }
             System.out.println();

         }

         System.out.println(dp[0][N-1].min);
     }
}


/*
8
1 100
100 1
1 100
100 1
1 100
100 1
1 100
100 1
 */