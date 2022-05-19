import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14500 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N,M, max=Integer.MIN_VALUE;
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N+4][M+4];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
               map[i][j] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for(int k=0;k<19;k++)
                    max = Math.max(max,shape(i,j,k,map));
            }
        }
        System.out.println(max);
    }

    public static int shape(int x,int y,int mode, int[][] map){
        switch (mode){
            case 0:
                return map[x][y] + map[x+1][y] +map[x+2][y] +map[x+3][y];
            case 1:
                return map[x][y] + map[x][y+1] +map[x][y+2] +map[x][y+3];
            case 2:
                return map[x][y] + map[x+1][y] +map[x][y+1] +map[x+1][y+1];
            case 3:
                return map[x][y] + map[x+1][y]+map[x+2][y]+map[x+2][y+1];
            case 4:
                return map[x][y+2] + map[x+1][y]+map[x+1][y+1]+map[x+1][y+2];
            case 5:
                return  map[x][y] + map[x][y+1]+map[x][y+2]+map[x+1][y+2];
            case 6:
                return  map[x][y+1] + map[x][y]+map[x+1][y]+map[x+2][y];
            case 7:
                return map[x][y]+map[x][y+1] +map[x+1][y+1]+map[x+2][y+1];
            case 8:
                return map[x][y] + map[x+1][y]+map[x+1][y+1]+map[x+1][y+2];
            case 9:
                return  map[x][y] + map[x][y+1]+map[x][y+2]+map[x+1][y];
            case 10:
                return map[x+2][y]+map[x][y+1] +map[x+1][y+1]+map[x+2][y+1];
            case 11:
                return map[x][y] +map[x+1][y]+map[x+1][y+1]+map[x+2][y+1];
            case 12:
                return map[x+1][y] +map[x][y+1]+map[x+1][y+1]+map[x][y+2];
            case 13:
                return map[x][y+1] +map[x+1][y]+map[x+1][y+1]+map[x+2][y];
            case 14:
                return map[x+1][y+2] +map[x][y+1]+map[x+1][y+1]+map[x][y];
            case 15:
                return map[x][y] +map[x][y+1]+map[x+1][y+1]+map[x][y+2];
            case 16:
                return map[x+1][y] +map[x][y+1]+map[x+1][y+1]+map[x+2][y+1];
            case 17:
                return map[x][y+1] +map[x+1][y]+map[x+1][y+1]+map[x+1][y+2];
            default:
                return map[x+1][y+1] + map[x][y] +map[x+1][y] + map[x+2][y];
        }
    }
}
