import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2003 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()),left=0,right=0, answer=0,sum;
        int[] arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        sum = arr[right];

        while (right<N){
            System.out.println(left+" , " +right+" : "+sum);
            if(sum <M){
                sum += arr[++right];
            }else if(sum>M){
                sum -= arr[left++];
            } else{
                answer++;
                sum += arr[++right];
                sum -= arr[left++];
            }
        }
        System.out.println(answer);
    }
}
