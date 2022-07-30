import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1865 {
    static class Vector {
        int from, to, w;

        public Vector(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }

    }
     public static void main(String[] args) throws Exception{
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
         StringTokenizer st;
         int TC = Integer.parseInt(br.readLine());
         ArrayList<Vector> vectors;
         int [] dist;

         int N, M, W, a, b, c;
         loop : while (TC-->0) {
             st = new StringTokenizer(br.readLine());
             N = Integer.parseInt(st.nextToken());
             M = Integer.parseInt(st.nextToken());
             W = Integer.parseInt(st.nextToken());
             dist = new int[N+1];
             for (int i = 2; i < dist.length; i++) {
                 dist[i] = Integer.MAX_VALUE/2 -1;
             }
             vectors = new ArrayList<>();

             for (int i = 0; i < M; i++) {
                 st = new StringTokenizer(br.readLine());
                 a = Integer.parseInt(st.nextToken());
                 b = Integer.parseInt(st.nextToken());
                 c = Integer.parseInt(st.nextToken());
                 vectors.add(new Vector(a,b,c));
                 vectors.add(new Vector(b,a,c));
             }

             for (int i = 0; i < W; i++) {
                 st = new StringTokenizer(br.readLine());
                 a = Integer.parseInt(st.nextToken());
                 b = Integer.parseInt(st.nextToken());
                 c = Integer.parseInt(st.nextToken());
                 vectors.add(new Vector(a,b,-c));
             }

             for (int i = 0; i < M+W-1; i++) {
                 for (Vector vector : vectors) {
                     if(dist[vector.from]+ vector.w< dist[vector.to]){
                         dist[vector.to] = dist[vector.from]+ vector.w;
                     }
                 }
             }

             for (Vector vector : vectors) {
                 if(dist[vector.from]+ vector.w< dist[vector.to]){
                     bw.write("YES\n");
                     continue loop;
                 }
             }
             bw.write("NO\n");
         }
         bw.flush();
     }
}
