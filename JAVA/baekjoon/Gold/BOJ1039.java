import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1039 {

    static class NumberNode{
        int number;
        int depth;

        public NumberNode(int number, int depth) {
            this.number = number;
            this.depth = depth;
        }
    }



    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        char[] numArr = st.nextToken().toCharArray();

        int K = Integer.parseInt(st.nextToken()), answer=-1,tempNum, orinNum = Integer.parseInt(new String(numArr));
        boolean[][] visited = new boolean[K+1][1000001];

        Queue<NumberNode> bfs = new LinkedList<>();

        bfs.add(new NumberNode(orinNum,0));

        while (bfs.size()>0){
            NumberNode cursor = bfs.poll();

            if(cursor.depth==K){
                int a = (int)Math.log10(cursor.number), b = (int)Math.log10(orinNum);
                if(a==b){
                    answer = Math.max(answer,cursor.number);
                }
                continue;
            }

            for(int i=0;i<numArr.length-1;i++){
                for(int j=i+1;j< numArr.length;j++){

                    tempNum = swap(cursor.number, i,j);

                    if(tempNum!=-1&&!visited[cursor.depth][tempNum]){
                        visited[cursor.depth][tempNum] = true;
                        bfs.add(new NumberNode(tempNum, cursor.depth+1));
                    }
                }
            }
        }
        System.out.println(answer);
    }

    //123 321 312

    private static int swap(int num, int start, int i) {
        char[] numTochar = String.valueOf(num).toCharArray();
        if (start == 0 && numTochar[i] == '0') {
            return -1;
        }
        char temp = numTochar[start];
        numTochar[start] = numTochar[i];
        numTochar[i] = temp;
        return Integer.parseInt(new String(numTochar));
    }
}

