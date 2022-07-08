import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3955 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int K,C;
        StringTokenizer st;
        long[] arrBB ;
        long[] arrB;
        long[] arr = new long[4];


        for (int i = 0; i < N; i++) {   //TestCase
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());       //K가 항상 더 큰지 확인 필요
            C = Integer.parseInt(st.nextToken());
            if(K==1&&C==1){
                bw.write("2\n");
                continue;
            }else if(K==1){
                bw.write(C+"\n");
                continue;
            }else if(C==1){
                if(K==1000000000){
                    bw.write("IMPOSSIBLE\n");
                }else bw.write((K+1)+"\n");
                continue;
            }
            if(getGcd(K,C)!=1){ //서로소가 아닌경우, 불가능하다.
                bw.write("IMPOSSIBLE\n");
            }else{  //서로소인 경우. 확장된 유클리드 알고리즘을 사용한다.
                arrBB = new long[]{1,0,K,0};
                arrB = new long[]{0,1,C,0};
                while (true){
                    arr[3] = arrBB[2]/arrB[2];
                    arr[2] = arrBB[2] %arrB[2];
                    arr[0] = arrBB[0] - arrB[0] * arr[3];
                    arr[1] = arrBB[1] - arrB[1] * arr[3];
//                    System.out.println();
//                    System.out.println(Arrays.toString(arrBB));
//                    System.out.println(Arrays.toString(arrB));
//                    System.out.println(Arrays.toString(arr));
//                    System.out.println();
                    if(arr[2]==1) break;
                    arrBB = arrB;       //밀어주기
                    arrB = arr;
                    arr = new long[4];
                }

                if(arr[1]<0) arr[1] = K + arr[1];

                if(-arr[1]>1000000000){
                    bw.write("IMPOSSIBLE\n");
                }else{
                    bw.write(arr[1]+"\n");
                }
            }
        }bw.flush();
    }

    private static int getGcd(int k, int c) {
        if(k>c) return (c==0)? k : getGcd(c,k%c);
        return (k==0) ? c: getGcd(k,c%k);
    }
}
