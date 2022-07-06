import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2749 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        String binarys = "";
        if(n==1||n==2){
            System.out.println(1);
            return;
        }
        n=n-2;

        while (n>0){
            binarys += n%2;
            n /=2;
        }
        long[][][] fiboMat = new long[binarys.length()][2][2];
        fiboMat[0] = new long[][]{{1L, 1L}, {1L, 0L}};
        long[][] result = {{1L,0L},{0L,1L}};

        for (int i=0;i< fiboMat.length-1;i++){
            fiboMat[i+1] = matMul(fiboMat[i],fiboMat[i]);
        }

        for (int i = 0; i < binarys.length(); i++) {
            if(binarys.charAt(i)=='1'){
                result = matMul(result,fiboMat[i]);
            }
        }

        System.out.println((result[0][0]+result[0][1])%1000000L);
    }

    private static long[][] matMul(long[][] a,long [][] b){ //a를 제곱하여 b에 넣어줌.
        long [][] temp = new long[2][2];

        temp[0][0] = (a[0][0]*b[0][0]%1000000 + a[0][1]*b[1][0]%1000000)%1000000L;
        temp[0][1] = (a[0][0]*b[0][1]%1000000 + a[0][1]*b[1][1]% 1000000)%1000000L;
        temp[1][0] = (a[1][0]*b[0][0]%1000000 + a[1][1]*b[1][0]% 1000000)%1000000L;
        temp[1][1] = (a[1][0]*b[0][1]%1000000+ a[1][1]*b[1][1]% 1000000)%1000000L;

        return temp;
    }



}
