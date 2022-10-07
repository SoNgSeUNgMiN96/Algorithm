import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ11438 {

     public static void main(String[] args) throws Exception {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
         StringTokenizer st;
         int N = Integer.parseInt(br.readLine()), M, A, B;
         int K = getTempN(N);
         int[][] parent = new int[K+1][N + 1];
         int[] depthArr = new int[N + 1];
         boolean[] visited = new boolean[N + 1];
         ArrayList<Integer>[] nears = new ArrayList[N + 1];
         Queue<Integer> bfs = new LinkedList();
         int curr;


         for (int i = 1; i < nears.length; i++) nears[i] = new ArrayList<>();

         for (int i = 0; i < N - 1; i++) {
             st = new StringTokenizer(br.readLine());
             A = Integer.parseInt(st.nextToken());
             B = Integer.parseInt(st.nextToken());
             nears[A].add(B);
             nears[B].add(A);  //각각 추가.
         }

         bfs.add(1);
         visited[1] = true;

         while (bfs.size()>0){      //트리 생성.
             curr = bfs.poll();//꺼내주고.
             for (int i = 0; i < nears[curr].size(); i++) {//나랑 연결된 애들의 사이즈 만큼.d
                 int next = nears[curr].get(i);
                 if(!visited[next]){      //현재 노드와 연결된 노드의 방문여부
                     visited[next] = true;
                     depthArr[next] = depthArr[curr] +1;
                     bfs.add(next);
                     parent[0][next] = curr;
                 }
             }
         }



         for (int i = 1; i <= K; i++) {
             for (int j = 1; j <=N; j++) {
                 parent[i][j] = parent[i-1][parent[i-1][j]];        //부모의 이전회차의 부모의 이전회차
             }
         }

         M = Integer.parseInt(br.readLine());


         for (int i = 0; i < M; i++) {
             st = new StringTokenizer(br.readLine());
             A = Integer.parseInt(st.nextToken());
             B = Integer.parseInt(st.nextToken());

             if(depthArr[A]>depthArr[B]){
                 int temp = B;
                 B = A;
                 A = temp;
             }
             int diff = depthArr[B] -depthArr[A];
             for (int j = 0; diff>0; j++) {
                 if((diff&1)==1){
                    B = parent[j][B];
                 }
                 diff >>= 1;
             }

             while (A!=B){
                 int nextIdx = 0;

                 while (nextIdx<=K&&parent[nextIdx][A]!=parent[nextIdx][B]){
                     nextIdx++;
                 }
                 if(nextIdx!=0){//한칸 갔는데 같았다면?
                     nextIdx--;
                 }
                 A = parent[nextIdx][A];      //직전 부모로 갱신한다.
                 B = parent[nextIdx][B];
             }

             bw.write(B+"\n");
         }
        bw.flush();
     }

    public static int getTempN(int n){      //여기서 2^K를 구한다.
        int tempN=1;
        int count=0;
        while (tempN<n){
           count+=1;
           tempN*=2;
        }
        return count-1;
    }

}
