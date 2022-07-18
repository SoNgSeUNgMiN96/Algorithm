import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2014 {
     public static void main(String[] args) throws Exception{
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st;
         st = new StringTokenizer(br.readLine());
         int K = Integer.parseInt(st.nextToken()), N = Integer.parseInt(st.nextToken());
         int[] primeArr = new int[K];
         int[] table = new int[500000];

         st = new StringTokenizer(br.readLine());
         for (int i = 0; i < K; i++) {
             primeArr[i] = Integer.parseInt(st.nextToken());
         }


         for (int j = 0; j < K; j++) {
            int temp = primeArr[j];
            while (temp<500000){
                table[temp] = 1;
                temp += primeArr[j];
            }
         }

         int i;
         for (i = 2; i < 500000&&N>0; i++) {
             if(table[i]==1){
                 System.out.println(i);
                 N--;
             }
         }

         System.out.println(i-1);
     }
}
