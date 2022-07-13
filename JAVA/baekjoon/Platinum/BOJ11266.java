import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ11266 {

    static int order=0;
    static int[] visted;
    static ArrayList<Integer>[] nodes;
    static boolean[] answer;

     public static void main(String[] args) throws Exception{
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
         StringTokenizer st;
         st =new StringTokenizer(br.readLine());
         int V = Integer.parseInt(st.nextToken()), E = Integer.parseInt(st.nextToken());
         nodes = new ArrayList[V+1];
         int A,B;
         answer = new boolean[V+1];

         visted = new int[V+1];

         for (int i = 1; i < nodes.length; i++) nodes[i] = new ArrayList<>();

         for (int i = 0; i < E; i++) {
             st = new StringTokenizer(br.readLine());
             A = Integer.parseInt(st.nextToken());
             B = Integer.parseInt(st.nextToken());
             nodes[A].add(B);
             nodes[B].add(A);
         }

         for (int i = 1; i < nodes.length; i++) {
             if(visted[i]==0){      //방문한적이 없다면?
                 dfs(i,0);
             }
         }

         int size = 0;
         String answerBF = "";
         for (int i = 0; i < V+1; i++) {
             if(answer[i]){
                 size++;
                 answerBF += i+ " ";
             }
         }
         bw.write(size+"\n");
         bw.write(answerBF+"\n");

         bw.flush();
     }

    private static int dfs(int curr, int parent) {

         visted[curr] = ++order;
         int child = 0, min=visted[curr];

        for (int i = 0; i < nodes[curr].size(); i++) {  //자신과 연결된 자식 확인
            if(nodes[curr].get(i)!=parent){ //부모가 아닌 것중에서.
                if(visted[nodes[curr].get(i)]!=0) {
                    min = Math.min(visted[nodes[curr].get(i)], min);    //min갱신.
                }else{
                    child++;

                    int low = dfs(nodes[curr].get(i), curr);    //내 자식의 최솟값? 나랑 상관없이 연결할 수 있는 가장 작은 값?
                    if(parent!=0&&visted[curr]<=low){
                        answer[curr] = true;
                    }
                    min = Math.min(low,min);
                }
            }
        }

        if(parent==0&&child>=2){
            answer[curr] = true;
        }

        return min;
    }
}
