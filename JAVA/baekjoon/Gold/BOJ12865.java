package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12865 {

    static class Obj{
        int W,V;

        public Obj(int w, int v) {
            W = w;
            V = v;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N,K;
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Obj[] objs = new Obj[N];
        int[][] dp = new int[N][K+1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken()),v = Integer.parseInt(st.nextToken());
            objs[i] = new Obj(w,v);
        }

        int firstW = objs[0].W;
        for(int i=firstW; i<=K; i++){
            dp[0][i] = objs[0].V;
        }
        for (int i = 1; i <=K; i++) {
            for (int j = 1; j < N; j++) {

                if (i - objs[j].W < 0) {
                    dp[j][i] = dp[j - 1][i];
                    continue;
                }
                dp[j][i] = Math.max(dp[j - 1][i], dp[j - 1][i - objs[j].W] + objs[j].V);
            }
        }

        System.out.println(dp[N-1][K]);
    }
}

