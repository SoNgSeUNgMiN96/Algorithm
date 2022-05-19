import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14888 {

    static int max=Integer.MIN_VALUE,min =Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N;
        N = Integer.parseInt(br.readLine());
        int[] numArr = new int[N], operators = new int[4];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numArr.length; i++)
            numArr[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++)
            operators[i] = Integer.parseInt(st.nextToken());
        dfs(numArr,1,N,operators,numArr[0]);
        System.out.println(max);
        System.out.println(min);
    }

    private static void dfs(int[] numArr,int nCount, int N, int[] operators,int sum) {
        if(N==nCount){
            min = Math.min(min,sum);
            max = Math.max(max,sum);
            return;
        }
        if(operators[0]>0){
            operators[0]--;
            dfs(numArr,nCount+1,N,operators,sum+numArr[nCount]);
            operators[0]++;
        }
        if(operators[1]>0){
            operators[1]--;
            dfs(numArr,nCount+1,N,operators,sum-numArr[nCount]);
            operators[1]++;
        }if(operators[2]>0){
            operators[2]--;
            dfs(numArr,nCount+1,N,operators,sum*numArr[nCount]);
            operators[2]++;
        }if(operators[3]>0){
            operators[3]--;
            dfs(numArr,nCount+1,N,operators,sum/numArr[nCount]);
            operators[3]++;
        }
    }
}
