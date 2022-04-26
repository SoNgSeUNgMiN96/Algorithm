import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15684 {
    public static void main(String[] args) throws Exception{
        int N,M,H, answer=0, r,c;
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        boolean[][] map= new boolean[H][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken())-1;
            c = Integer.parseInt(st.nextToken())-1;
            map[r][c] = true;
        }

        for (answer = 0; answer <= 3; answer++) {
            if(combi(map,H,N,answer,0)) break;
        }
        System.out.println((answer>3)?-1:answer);
    }
    public static boolean isRight(boolean[][] map, int H,int N){
        for (int i = 0; i < N; i++) {
            int tempC=i; //r,c중 C의 좌표를 임시로 담음.
            for (int j = 0; j < H; j++) {
                if(map[j][tempC]){      //오른쪽 라인을 만나서 오른쪽 이동
                    tempC++;
                }else if(tempC>0&&map[j][tempC-1]){     //왼쪽 라인을 만나서 왼쪽으로 이동.
                    tempC--;
                }
            }
            if(tempC!=i){
                return false;
            }
        }
        return true;
    }
    public static boolean combi(boolean[][] map, int H,int N,int m,int start){
        if(m==0){
            return isRight(map,H,N);
        }
        for (int i=start;i<H*N;i++){
            if((!map[i/N][i%N])&&(i%N<N-1)){  //맨 우측말고, 맵에 이미 뽑힌 애 말고
                map[i/N][i%N]= true;
                if(combi(map,H,N,m-1,i+1)) return true;
                map[i/N][i%N] = false;
            }
        }
        return false;
    }
}
