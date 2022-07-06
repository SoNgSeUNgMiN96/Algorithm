import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ7453 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        long answer = 0;

        int N = Integer.parseInt(br.readLine());


        int [][] arr = new int[4][N];
        int []arrAsum = new int[N*N], arrBsum = new int[N*N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                arr[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for(int j=0;j<N;j++){
                arrAsum[i*N+j] = arr[0][i]+arr[1][j];
                arrBsum[i*N+j] = arr[2][i]+arr[3][j];
            }
        }

        //둘다 오름차순 정렬
        Arrays.sort(arrAsum);
        Arrays.sort(arrBsum);

        int aPoint = 0, bPoint = arrBsum.length-1,temp;     //a의 가장 작은 값고 B의 가장 큰값. 투포인팅.
        long acount, bcount;
        int aSize = arrAsum.length, abSum=0;

        while (aPoint<aSize&&bPoint>=0){
            abSum = arrAsum[aPoint] + arrBsum[bPoint];

            if(abSum>0){
                bPoint--;
            }else if(abSum<0){
                aPoint++;
            }else{
                acount= bcount = 0;
                temp = arrAsum[aPoint];
                while (aPoint<aSize&&arrAsum[aPoint]==temp){
                    aPoint++;
                    acount++;
                }
                temp = arrBsum[bPoint];
                while (bPoint>=0&&arrBsum[bPoint]==temp){
                    bPoint--;
                    bcount++;
                }
                answer += acount*bcount;
            }
        }

        System.out.println(answer);
    }
}
