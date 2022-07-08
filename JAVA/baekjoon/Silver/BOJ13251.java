import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

import static java.math.BigDecimal.ROUND_CEILING;

public class BOJ13251 {
     public static void main(String[] args) throws Exception{
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
         StringTokenizer st;
         int N = Integer.parseInt(br.readLine()), K, sum = 0;
         BigInteger allCase = new BigInteger("0"), sameCase = new BigInteger("0"), temp;

         st = new StringTokenizer(br.readLine());
         int[] box = new int[N];

         for (int i = 0; i < N; i++) {
             box[i] = Integer.parseInt(st.nextToken());
             sum += box[i];
         }
         K =  Integer.parseInt(br.readLine());
         allCase = getnCr(sum, K, new BigInteger("1"), new BigInteger("1"), 0);
         for (int i = 0; i < N; i++){
             temp = getnCr(box[i], K, new BigInteger("1"), new BigInteger("1"), 0);
             //System.out.println("temp = "+temp);
             sameCase = sameCase.add(temp);
         }
         //System.out.println(allCase + "  " + sameCase);
         //System.out.println(box[0]);
         System.out.println((new BigDecimal(sameCase).divide(new BigDecimal(allCase),9,ROUND_CEILING)).toPlainString());
     }

    private static BigInteger getnCr(int n, int r, BigInteger sum, BigInteger div, int depth) {
        if (n < r) return new BigInteger("0");
        if (r == depth) return sum.divide(div);
        return (n - r < r) ? getnCr(n, n - r, sum, div, depth) : getnCr(n, r, sum.multiply(new BigInteger(String.valueOf(n - depth))), div.multiply(new BigInteger(String.valueOf(depth + 1))), depth + 1);
    }
}
