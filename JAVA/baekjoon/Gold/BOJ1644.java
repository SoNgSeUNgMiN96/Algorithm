import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ1644 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] primeArr = new int[4000001];      //1000000까지의 소수를 담는다.

        int temp, kcount = 0, n, left, right, sum, answer=0;


        for (int i = 2; i*i <= 4000000; i++) {      //
            int i1 = primeArr[i];
            if (primeArr[i] == 0) {
                temp = i + i;
                while (temp <= 4000000) {
                    if (primeArr[temp] == 0) {
                        primeArr[temp] = 1;
                        kcount++;
                    }
                    temp += i;
                }
            }
        }

        int primeSize = 4000000-1-kcount;
        int[] PrimeList = new int[primeSize];
        kcount = 0;
        for (int i = 2; i <= 4000000; i++) {
            if(primeArr[i]==0){
                PrimeList[kcount++] = i;
            }
        }
        int N = Integer.parseInt(br.readLine());        //1과 소수는 예외처리 해주기.
        left=0;
        right=0;
        sum = PrimeList[right];
        while (true){
            if(sum<N){   //
                if(right+1<primeSize) sum+= PrimeList[++right];
                else break;
            }else if(sum>N){
                if(left>right) break;
                sum -= PrimeList[left++];
            }else {
                answer++;
                if(right+1<primeSize) sum+=PrimeList[++right];
                else break;
            }
        }
        System.out.println(answer);
    }
}
//1000000