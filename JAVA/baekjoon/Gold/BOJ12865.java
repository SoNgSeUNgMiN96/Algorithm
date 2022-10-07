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
    static int answer = 0, N, K;

     public static void main(String[] args) throws Exception{
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st;
         st = new StringTokenizer(br.readLine());
         N = Integer.parseInt(st.nextToken());
         K = Integer.parseInt(st.nextToken());
         Obj[] objs = new Obj[N];

         for (int i = 0; i < N; i++) {
             st = new StringTokenizer(br.readLine());
             int w = Integer.parseInt(st.nextToken()),v = Integer.parseInt(st.nextToken());
             objs[i] = new Obj(w,v);
         }

         dfs(0,0, 0,objs);

         System.out.println(answer);
     }

    private static void dfs(int curr, int wSum, int vSum, Obj[] objs) {
         if(curr==N) {
             answer = Math.max(answer, vSum);
             return;
         }
         if(wSum + objs[curr].W<=K){
             dfs(curr+1, wSum + objs[curr].W, vSum+objs[curr].V, objs);
         }
        dfs(curr+1, wSum,vSum, objs);
    }
}

