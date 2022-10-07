import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ14003 {

    static class dp{
        long value;
        int pevIdx;

        public dp(long value, int pevIdx) {
            this.value = value;
            this.pevIdx = pevIdx;
        }
    }


     public static void main(String[] args) throws Exception{
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
         StringTokenizer st;
         int N = Integer.parseInt(br.readLine());
         st = new StringTokenizer(br.readLine());
         long[] arr = new long[N];
         dp[] dps = new dp[N];
         dp[] tarray  = new dp[N];
         int left,right, mid, tarraySize = 1, idx=0;
         long init = -1000000002;

         for (int i = 0; i < tarray.length; i++) {
             tarray[i] = new dp(init,i);
         }

         for (int i = 0; i < N; i++) {
             arr[i] = Long.parseLong(st.nextToken());
         }

         tarray[0].value = arr[0];
         tarray[0].pevIdx = 0;
         dps[0] = new dp(0,-1);


         for (int i = 1; i < N; i++) {
             long search = arr[i];
             left = 0;
             right = tarraySize - 1;
             idx = -1;

             while (left <= right) {
                mid = (left + right)/2;

                if(tarray[mid].value>=search){
                    idx= mid;
                    right= mid-1;
                }else{
                    left = mid +1;
                }
             }

             if(idx==-1){   //답이 없었을 경우.
                 tarray[tarraySize].value = search;
                 tarray[tarraySize++].pevIdx = i;
                 dps[i] =  new dp(tarraySize-1,tarray[tarraySize-2].pevIdx);
             }else{
                 tarray[idx].value = search;
                 tarray[idx].pevIdx = i;
                 if(idx==0){
                     dps[i] =  new dp(idx,-1);
                 }else dps[i] =  new dp(idx,tarray[idx-1].pevIdx);
             }
         }

         //System.out.println(tarraySize);


         System.out.println(N);
         System.out.println(N-1);

         int i;
         for(i=N-1;i>=0;i--){
             if(dps[i].value==tarraySize-1){
                 break;
             }
         }

         ArrayList<Long> answer = new ArrayList<>();

         while (i>=0){
             answer.add(arr[i]);
             i = dps[i].pevIdx;
         }

         bw.write(tarraySize+"\n");
         for (int j = tarraySize-1; j >=0  ; j--) {
             bw.write(answer.get(j)+" ");
         }
         bw.write("\n");
         bw.flush();
     }
}

/*
9
3 1 2 4 7 5 6 8 10
 */