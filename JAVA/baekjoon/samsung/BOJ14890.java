import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14890 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N,L,answer=0,j,before,count,now,beforeCount=0;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];
        for (int i = 0; i < map.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (j = 0; j < map.length; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }


        for (int i = 0; i < map.length; i++) {

            //가로 검사.
            j=0;
            before = map[i][0];
            now = map[i][0];
            count=0;
            beforeCount=0;
            boolean have=false;

            while (j<N){
                while (j<N&&before==map[i][j]){
                    j++;
                    count++;
                }
                if(j==N){       //구간 종료 검사
                    if(have){
                        if(before+1==now&&beforeCount>=L){//지금이 1층 높을 때
                            System.out.println(i);
                            answer++;
                        }else if(before-1==now&&count>=L){ //지금이 1층 낮을 때
                            answer++;
                        }
                    }else{  //이전 구간이 없을 때 (쭉 같았단 소리
                        System.out.println(i);
                        answer++;
                    }
                }else{
                    if(have){
                        if(before+1==now&&beforeCount>=L){//지금이 1층 높을 때
                            before = now;
                            now = map[i][j];
                            beforeCount = count;
                        }else if(before-1==now&&count>=L){ //지금이 1층 낮을 때
                            before = now;
                            now = map[i][j];
                            beforeCount = count-L;
                        }
                        else break;
                    }else{  //두번째 구간 진입.
                        before = now;
                        now = map[i][j];
                        beforeCount = count;
                        have = true;
                    }
                }
            }
        }

        System.out.println(answer);

    }
}
