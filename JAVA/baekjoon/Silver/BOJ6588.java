import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ6588 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] primeArr = new int[1000001];      //1000000까지의 소수를 담는다.

        int temp, kcount=0, n,left,right,sum;


        for (int i = 2; i <= Math.sqrt(1000000); i++) {
            int i1 = primeArr[i];
            if(primeArr[i]==0){
                temp = i+i;
                while (temp<=1000000){
                    if(primeArr[temp]==0){
                        primeArr[temp] =1;
                        kcount++;
                    }
                    temp+=i;
                }
            }
        }
        int primeSize = 1000000-1-kcount;
        String answer;
        int[] PrimeList = new int[primeSize];
        kcount = 0;
        for (int i = 2; i <= 1000000; i++) {
            if(primeArr[i]==0){
                PrimeList[kcount++] = i;
            }
        }

        while (true){
            n = Integer.parseInt(br.readLine());
            if (n==0) break;

            left = 0;
            right = primeSize-1;
            answer="";

            while (PrimeList[left]<=n){

                if(primeArr[n-PrimeList[left]]==0){     //두 수수의 합.
                    answer = n+" = "+PrimeList[left]+" + "+(n-PrimeList[left]);
                    bw.write(answer+"\n");
                    break;
                }else left++;
            }
        }
        bw.flush();
    }
}
