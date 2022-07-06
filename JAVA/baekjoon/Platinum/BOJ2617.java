import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class BOJ2617 {
    static class Runner{
        long speed;
        int order;
        public Runner(long speed, int order) {
            this.speed = speed;
            this.order = order;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine()),idx;
        int tempN = getTempN(N);
        HashMap<Long,Integer> speedToIdx = new HashMap<>();
        long temp,pass;
        int myIdx ,tempIdx;

        ArrayList<Runner> runners = new ArrayList<>();
        long[] indexTree = new long[tempN*2];


        for (int i = 0; i < N; i++) {
            temp = Long.parseLong(br.readLine());
            runners.add(new Runner(temp,i));
        }

        Collections.sort(runners, (r1,r2)-> {
            if(r1.speed<=r2.speed){
                return -1;
            }else return 1;
        });


        //index 매치
        for(int i=0;i< runners.size();i++){
            speedToIdx.put(runners.get(i).speed,tempN+i);
        }

        Collections.sort(runners, (r1,r2)-> {
            if(r1.order<=r2.order){
                return -1;
            }else return 1;
        });

        for(int i=0;i< runners.size();i++){
            //내스피드의 인덱스를 더해 넣고, 내 등수를 확인하여 계산.
            myIdx = tempIdx = speedToIdx.get(runners.get(i).speed);

            indexTree[tempIdx]++;
            while (tempIdx>1){    //부모갱신.
                tempIdx/=2;
                indexTree[tempIdx]++;
            }
            //System.out.println("runner.order = "+runners.get(i).order+"   my idx = "+(myIdx-tempN));
            pass = getSum(indexTree,0,myIdx-1-tempN,0,tempN-1,1);
            //System.out.println(Arrays.toString(indexTree));
            //System.out.println("runner.order = "+runners.get(i).order+" runner pass = "+pass+"   my idx = "+myIdx);

            bw.write((runners.get(i).order+1-pass)+"\n");
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
