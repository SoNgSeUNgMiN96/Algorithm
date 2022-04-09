public class programmersWayToSchool{
    public int solution(int m, int n, int[][] puddles) { //아랫벽, 오른벽, 장애물이 오른쪽, 장애물이 아래, 노말 5가지 상태를 갖는다.
        int dp[][] = new int[m+1][n+1];
        for(int[] pudd: puddles)
            dp[pudd[0]][pudd[1]]= -1;
        setDp(m,n,1,1,dp);
        return dp[1][1]%1000000007;
    }
    public int setDp(int m,int n,int x,int y,int[][] dp){
        if(m==x&&n==y) return 1;    //학교 도착시 1리턴
        if(dp[x][y]!=0&&dp[x][y]!=-1) return dp[x][y];
        int code =0;
        if(x!=m&&dp[x+1][y]==-1||x==m) code +=1;  //아래로 내려갈 수 없다.
        if(y!=n&&dp[x][y+1]==-1||y==n) code +=2;  //오른쪽으로 갈 수 없다.
        if(code==3) return 0;   //어디도 갈 수 없다.
        if(code==1) return dp[x][y] = setDp(m,n,x,y+1,dp);  //오른쪽만 
        if(code==2) return dp[x][y] = setDp(m,n,x+1,y,dp);  //아래만
        return dp[x][y] = (setDp(m,n,x,y+1,dp)+setDp(m,n,x+1,y,dp))%1000000007;  //둘다
    }
}
