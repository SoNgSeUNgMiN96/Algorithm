import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ5582 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;
        String A, B;

        A = br.readLine();
        B = br.readLine();
        int aLength = A.length(), bLength = B.length();


        boolean[][] dp = new boolean[bLength][aLength];     //dp[i][j] 란 A의 j번째 글자와 B의 i 번째 글자가 같은것을 의미함

        for (int i = 0; i < bLength; i++) {
            for (int j = 0; j < aLength; j++) {
                if(A.charAt(j)==B.charAt(i)) {      //dp 초기화.
                    dp[i][j] = true;
                }
            }
        }


        for (int i = 0; i < aLength + bLength -1; i++) {        // 왼쪽 하단부터 올라오다가 0행에도착하면 오른쪽으로 가면서 시작점을 잡음. 우하단 탐색을 하면 얼마나 길게 일치하는지를 확인할 수 있음.
            int count =0;
            int j =0, k=0;

            if(i<bLength){
                j = bLength - i - 1;
                k = 0;
            }else{
                k = i - bLength + 1;
                j = 0;
            }

            while (k<aLength&&j<bLength){
                count =0;
                while (j<bLength&&k<aLength&&dp[j][k]){
                    count++;
                    j++;
                    k++;
                }
                answer = Math.max(answer,count);
                j++;
                k++;
            }
        }
        System.out.println(answer);
    }
}
