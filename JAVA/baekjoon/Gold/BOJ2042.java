import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2042 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = (Integer.parseInt(st.nextToken())+ Integer.parseInt(st.nextToken()));
        int tempN = getTempN(N),a, idx;
        long b,c;
        long diff;
        long[] indexTree = new long[tempN*2];

        for (int i = 0; i < N; i++) {
            indexTree[tempN + i] = Long.parseLong(br.readLine());
        }

        initTree(indexTree,1, tempN);       //트리의 합계 계산.

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Long.parseLong(st.nextToken());
            c = Long.parseLong(st.nextToken());
            if(a==1){
                diff = c - indexTree[tempN+(int)b-1];
                indexTree[tempN+(int)b-1] = c;   //c로 변경하고 차이값 저장
                idx = tempN+(int)b-1;
                while (idx!=0){ //차이값 부모까지 계산.
                    idx /=2;
                    indexTree[idx]+=diff;
                }
            }else{
                long sum = getSum(indexTree,(int)b-1,(int)c-1,0,tempN-1,1);
                bw.write(sum+"\n");
            }
        }
        bw.flush();
    }

    private static long getSum(long[] indexTree, int sumLeft, int sumRight, int startIdx, int endIdx, int myIdx) {
        //System.out.println("sumLeft = "+sumLeft+" sumRight = "+sumRight+" startIdx = "+startIdx+"end ="+endIdx+" my = "+myIdx);
        if(sumLeft<=startIdx&&sumRight>=endIdx) return indexTree[myIdx];    //나를 완전히 포함하면 리턴
        if(sumRight<startIdx||sumLeft>endIdx) return 0; //포함안되는 경우
        int leftChildLeftIdx = startIdx, leftChildRightIdx =startIdx+(endIdx-startIdx)/2,rightChildLeftIdx=startIdx+(endIdx-startIdx)/2+1, rightChildRightIdx = endIdx; //자식의 구간으로 쪼갬
        return getSum(indexTree,sumLeft,sumRight,leftChildLeftIdx,leftChildRightIdx,myIdx*2)+getSum(indexTree,sumLeft,sumRight,rightChildLeftIdx,rightChildRightIdx,myIdx*2+1);
    }

    private static long initTree(long[] indexTree, int i, int tempN) {
        if(i>=tempN&&i<tempN*2){    //리프인경우
            return indexTree[i];
        }
        return indexTree[i] = initTree(indexTree,i*2,tempN) + initTree(indexTree,i*2+1,tempN);
    }

    public static int getTempN(int n){
        int tempN=1;
        while (true){
            if(n<=tempN) return tempN;
            tempN *=2;
        }
    }

}
