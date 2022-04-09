import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class programmersIntegerTriangle{
    public int solution(int[][] triangle) {
        int [][]dp = new int[500][500];
        setDp(0,0,triangle,dp);
        return dp[0][0];
    }
    public int setDp(int n,int i,int[][]triangle,int[][] dp){ //n은 층수 i는 왼쪽에서 몇번째
        if(dp[n][i]!=0) return dp[n][i];
        if(n==triangle.length-1) return triangle[n][i];
        return dp[n][i] = triangle[n][i] + Math.max(setDp(n+1,i,triangle,dp),setDp(n+1,i+1,triangle,dp));
    }
}
