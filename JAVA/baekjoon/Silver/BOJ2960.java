import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2960 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N,K, kcount=0,temp;
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+1];

        for (int i = 2; i <=N; i++) {
            if(arr[i]==0){
                temp = i;
                while (temp<=N){
                    if(arr[temp]==0){
                        arr[temp] =1;
                        kcount++;
                        if(kcount==K){
                            System.out.println(temp);
                            return;
                        }
                    }
                    temp+=i;
                }

            }
        }


    }
}
