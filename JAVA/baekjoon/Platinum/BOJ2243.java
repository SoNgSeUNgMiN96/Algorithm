import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ2243 {

     public static void main(String[] args) throws Exception{
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
         StringTokenizer st;

         int N = Integer.parseInt(br.readLine()), A, B, C, tempIdx;
         int tempN = getTempN(1000001);
         long[] tree = new long[tempN * 2];
         long diff;

         for (int i = 0; i < N; i++) {
             st = new StringTokenizer(br.readLine());
             A = Integer.parseInt(st.nextToken());
             if(A==2){
                 B = Integer.parseInt(st.nextToken());
                 C = Integer.parseInt(st.nextToken());
                 tempIdx = tempN + B;
                 while (tempIdx>0){ //세그먼트 트리 채우기.
                     tree[tempIdx] += C;
                     tempIdx /=2;
                 }
             }else{
                 B = Integer.parseInt(st.nextToken());
                 tempIdx = 1;
                 while (tempIdx*2<tempN*2){   //트리 범위까지.
                     if(tree[tempIdx*2]<B){     //내 자식보다 순위가 낮은지?
                         B -=tree[tempIdx*2];
                         tempIdx = tempIdx*2+1;
                     }else{
                         tempIdx = tempIdx*2;
                     }
                 }
                 bw.write((tempIdx-tempN)+" ");
                 while (tempIdx>0){ //세그먼트 트리 채우기.
                     tree[tempIdx]--;
                     tempIdx /=2;
                 }
             }
         }
         bw.flush();
     }
     public static int getTempN(int temp){
         int tempN = 1;
         while (tempN<temp){
             tempN*=2;
         }
         return tempN;
     }
}
