import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ15663 {
     public static void main(String[] args) throws Exception{
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
         StringTokenizer st;
         HashSet<String> results = new HashSet<>();

         st = new StringTokenizer(br.readLine());
         int N = Integer.parseInt(st.nextToken());
         int M = Integer.parseInt(st.nextToken());
         st = new StringTokenizer(br.readLine());
         boolean[] visited = new boolean[N];
         int[] arr = new int[N];

         for (int i = 0; i < N; i++) {
             arr[i] = Integer.parseInt(st.nextToken());
         }
         getPerm(arr,visited,0,M,results,"", N);
         Object[] key = results.toArray();

         Arrays.sort(key, (s1,s2)->{
             StringTokenizer st1 = new StringTokenizer((String)s1);
             StringTokenizer st2 = new StringTokenizer((String)s2);
             while (st1.hasMoreElements()){
                 int a = Integer.parseInt(st1.nextToken());
                 int b = Integer.parseInt(st2.nextToken());
                 if(a!=b) return a-b;
             }
             return 0;
         });
         for (Object result : key) {
             bw.write(result+"\n");
         }
         bw.flush();
     }

    private static void getPerm(int[] arr, boolean[] visited, int i, int m, HashSet<String> results,String result, int n) {
         if(i==m){
             results.add(result);
             return;
         }
        for (int j = 0; j < n; j++) {
            if(!visited[j]){
                visited[j] = true;
                String copy = new String(result);
                if(i==0){
                    copy = arr[j]+"";
                }else{
                    copy = result+" "+arr[j];
                }
                getPerm(arr,visited,i+1,m,results,copy,n);
                visited[j] = false;
            }
        }
    }
}
