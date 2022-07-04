import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BOJ1103 {
    static int[] dx={0,0,-1,1}, dy={1,-1,0,0};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N= Integer.parseInt(st.nextToken()),M = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][M];
        int[][] dp = new int[N][M];
        boolean[][] visited = new boolean[N][M];

        Point coin = new Point(0,0), cursor;

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        visited[0][0] = true;
        dfs(map, visited, 1,coin,N,M,dp);

        System.out.println(dp[0][0]);

    }

    private static int dfs(char[][] map, boolean[][] visited, int depth, Point coin,int N,int M, int[][] dp) {
        Point cursor;

        int count =(map[coin.x][coin.y]-'0');
        int myMax=depth;

        if(dp[coin.x][coin.y]!=0){
            return depth + dp[coin.x][coin.y];
        }

        for(int i=0;i<4;i++){

            cursor = new Point(coin.x+count*dx[i],coin.y+count*dy[i]);
            if(cursor.x>=0&&cursor.x<N&&cursor.y>=0&&cursor.y<M){   //map 안이고 방문한적이 없다?
                if(visited[cursor.x][cursor.y]){        //무한
                    System.out.println(-1);
                    System.exit(0);
                }
                if(map[cursor.x][cursor.y]=='H'){
                    myMax = Math.max(myMax, depth+1);
                    continue;
                }
                visited[cursor.x][cursor.y] = true;
                myMax = Math.max(dfs(map,visited,depth+1,cursor,N,M,dp),myMax);
                visited[cursor.x][cursor.y] = false;
            }else {
                myMax = Math.max(myMax, depth+1);
            }
        }
        dp[coin.x][coin.y] = myMax - depth; //자신을 기준으로 탐색을 한적이 있음.
        return myMax;       //가장 깊이 가본곳을 리턴해준다.
    }
}
