import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14889 {

    static int min =Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N;
        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        boolean[] pick = new boolean[N];
        for (int i = 0; i < map.length; i++) {
            st =  new StringTokenizer(br.readLine());
            for (int j = 0; j < map.length; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        combi(N,0,N/2,map,pick);
        System.out.println(min);
    }
    private static void combi(int n,int start, int i, int[][] map,boolean[] pick) {
        if(i==0){
            calc(map,pick,n);
            return;
        }
        for (int j = start; j < map.length-i+1; j++) {
            pick[j] = true;
            combi(n,j+1,i-1,map,pick);
            pick[j] = false;
        }
    }

    private static void calc(int[][] map, boolean[] pick, int n) {
        int sumA =0, sumB =0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if(pick[i]){        //뽑힌 팀
                    if(pick[j]) sumA += map[i][j];
                }else{      //안 뽑힌 팀
                    if(!pick[j]) sumB += map[i][j];
                }
            }
        }
        min = Math.min(min, Math.abs(sumA-sumB));
    }
}
