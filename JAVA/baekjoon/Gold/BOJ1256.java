import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BOJ1256 {
     public static void main(String[] args) throws Exception{
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
         StringTokenizer st;
         st = new StringTokenizer(br.readLine());
         int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
         BigInteger K = new BigInteger(st.nextToken());
         String  answer="";

         BigInteger allCase = new BigInteger("0"), sum = new BigInteger("0"), temp, temp2;

         while (M>0&&N>0){
             temp = getnCr(N+M-1,M,new BigInteger("1"),0);
             temp2 = getnCr(N+M,M,new BigInteger("1"),0);
             if(K.compareTo(temp)<=0){
                 answer += "a";
                 N--;
             }else if(K.compareTo(temp2)<=0){
                 answer += "z";
                 M--;
                 K = K.subtract(temp);
             }else{
                 System.out.println(-1);
                 return;
             }
         }
         while (N-->0){
             answer +="a";
         }
         while (M-->0){
             answer +="z";
         }
         System.out.println(answer);
     }

    private static BigInteger getnCr(int n, int r,BigInteger sum,int depth) {
        if(r==depth) return sum;
        if(n<r) return getnCr(r,n, sum, depth);
        BigInteger temp = sum.multiply(new BigInteger(String.valueOf((n-depth))));
        return (n-r<r) ? getnCr(n,n-r,sum,depth) : getnCr(n,r,temp.divide(new BigInteger(String.valueOf((depth+1)))),depth+1);
    }
}