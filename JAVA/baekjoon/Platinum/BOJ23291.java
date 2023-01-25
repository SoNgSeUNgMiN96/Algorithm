package baekjoon.Platinum;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BOJ23291 {

    // 하, 우, 상, 좌
    static int[] dr = {1,0,-1,0}, dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N, K, answer = 0;
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] fishbowl = new int[N];

        st = new StringTokenizer(br.readLine());
        StringTokenizer finalSt = st;

        for (int i = 0; i < fishbowl.length; i++) {
            fishbowl[i] = Integer.parseInt(finalSt.nextToken());
        }

        while (!isArrange(fishbowl,K)) {
            insertFish(fishbowl);
            fishbowl = arrangeFishBowl(fishbowl);
            answer++;
        }

        System.out.println(answer);
    }

    private static int[] arrangeFishBowl(int[] fishbowl) {
        int[] backUp = fishbowl.clone();
        int[][] virtualMap = new int[101][101];
        for (int i = 0; i < virtualMap.length; i++) {
            Arrays.fill(virtualMap[i],-1); //-1 로 초기화
        }
        int code = roll(virtualMap, backUp);
        moveFish(virtualMap);

        spread(virtualMap, backUp, code);


        //fold
        virtualMap = new int[101][101];
        for (int i = 0; i < virtualMap.length; i++) {
            Arrays.fill(virtualMap[i],-1); //-1 로 초기화
        }

        fold(virtualMap, backUp);


        moveFish(virtualMap);

        spread(virtualMap, backUp, 1);//code 무조건 1로 해야함


        return backUp;
    }

    private static void fold(int[][] virtualMap, int[] backUp) {
        int[][] virTuralMapCopy = new int[virtualMap.length][virtualMap[0].length];

        for (int i = 0; i < virTuralMapCopy.length; i++) {
            virTuralMapCopy[i] = virtualMap[i].clone();
        }
        int half = backUp.length/2;

        //1차 폴드
        for (int i = 0; i < half; i++) {
            int tempC = backUp.length - 1;
            virTuralMapCopy[virtualMap.length-2][tempC-i] = backUp[i];
            virTuralMapCopy[virtualMap.length-1][tempC - i] = backUp[tempC - i];
        }

        // 3/4 지점
        int halfAndhalf = (backUp.length / 4) * 3;
        //2차 폴드
        for (int i = halfAndhalf-1; i >= half; i--) {
            virtualMap[virtualMap.length-3][halfAndhalf + (halfAndhalf-i)-1] = virTuralMapCopy[virtualMap.length-2][i];
            virtualMap[virtualMap.length-4][halfAndhalf + (halfAndhalf-i)-1] = virTuralMapCopy[virtualMap.length-1][i];
            virtualMap[virtualMap.length-1][halfAndhalf +(halfAndhalf-i)-1] = virTuralMapCopy[virtualMap.length-1][halfAndhalf +(halfAndhalf-i)-1];
            virtualMap[virtualMap.length-2][halfAndhalf +(halfAndhalf-i)-1] = virTuralMapCopy[virtualMap.length-2][halfAndhalf +(halfAndhalf-i)-1];
        }


    }

    private static void spread(int[][] virtualMap, int[] backUp, int code) {

        int idx = 0, r=0,c=0, tempR, tempC;

        switch (code % 4) {
            case 0:     //왼쪽위에서 탐색하다가 첫 요소부터 아래가 빌 때 까지 오른쪽 스캔하면서 backUp에 채워줌
                loop:
                for (int i = 0; i < virtualMap.length; i++) {
                    for (int j = 0; j < virtualMap[0].length; j++) {
                        if (virtualMap[i][j]!=-1) {
                            r = i;
                            c = j;
                            break loop;
                        }
                    }
                }
                tempR = r;
                while (tempR< virtualMap.length&&virtualMap[tempR][c] >= 0) {
                    tempC = c;
                    while (tempC < virtualMap[0].length && virtualMap[tempR][tempC] >= 0) {     //오른쪽 채워주기
                        backUp[idx++] = virtualMap[tempR][tempC++];
                    }
                    tempR++;
                }
                break;
            case 1: //우

                loop :
                for (int i = virtualMap.length-1; i >= 0; i--) {
                    for (int j = 0; j < virtualMap[0].length; j++) {
                        if (virtualMap[i][j] != -1) {
                            r = i;
                            c = j;
                            break loop;
                        }
                    }
                }
                tempC = c;
                while (tempC < virtualMap[0].length&& virtualMap[r][tempC]>=0){
                    tempR = r;
                    while (tempR >= 0 && virtualMap[tempR][tempC] >= 0) {
                        backUp[idx++] = virtualMap[tempR--][tempC];
                    }
                    tempC ++;
                }
                break;
            case 2://상

                loop :
                for (int i = virtualMap.length-1; i >= 0; i--) {
                    for (int j = virtualMap[0].length-1; j >=0; j--) {
                        if (virtualMap[i][j] != -1) {
                            r = i;
                            c = j;
                            break loop;
                        }
                    }
                }
                tempR = r;

                while (tempR>=0 && virtualMap[tempR][c]>=0){
                    tempC = c;
                    while (tempC >= 0 && virtualMap[tempR][tempC] >= 0) {
                        backUp[idx++] = virtualMap[tempR][tempC--];
                    }
                    tempR --;
                }
                break;
            case 3: //좌
                loop :      //오른쪽 위에서 내려옴
                for (int i =0; i < virtualMap.length; i++) {
                    for (int j = virtualMap[0].length-1; j >=0; j--) {
                        if (virtualMap[i][j] != -1) {
                            r = i;
                            c = j;
                            break loop;
                        }
                    }
                }

                tempC = c;

                while (tempC>=0 && virtualMap[r][tempC]>=0){
                    tempR = r;
                    while (tempR <= virtualMap.length && virtualMap[tempR][tempC] >= 0) {
                        backUp[idx++] = virtualMap[tempR++][tempC];
                    }
                    tempC--;
                }
                break;
            default:
                break;
        }
    }

    static class PointAndD{
        Point point;
        int amount;

        public PointAndD(Point point, int amount) {
            this.point = point;
            this.amount = amount;
        }
    }

    private static void moveFish(int[][] virtualMap) {
        Queue<PointAndD> increaseQueue = new LinkedList<>();
        Queue<PointAndD> decreaseQueue = new LinkedList<>();
        for (int i = 0; i < virtualMap.length; i++) {       //행
            for (int j = 0; j < virtualMap[0].length; j++) {        //열
                if(virtualMap[i][j]!=-1){
                    getFishMoving(virtualMap, increaseQueue, decreaseQueue, i, j);
                }
            }
        }
        while (!increaseQueue.isEmpty()){
            PointAndD thisFish = increaseQueue.poll();
            virtualMap[thisFish.point.x][thisFish.point.y]+=thisFish.amount;
        }
        while (!decreaseQueue.isEmpty()){
            PointAndD thisFish = decreaseQueue.poll();
            virtualMap[thisFish.point.x][thisFish.point.y]-=thisFish.amount;
        }
    }

    //물고기가 동시에 이동해야하는 목록을 반환한다
    private static void getFishMoving(int[][] virtualMap, Queue<PointAndD> increaseQueue, Queue<PointAndD> decreaseQueue, int i, int j) {
        Point temp;
        for (int code = 0; code < 4; code++) {
            temp = new Point(i + dr[code], j + dc[code]);
            if(temp.x>=0&&temp.y>-0&&temp.x< virtualMap.length&&temp.y< virtualMap[0].length&&virtualMap[temp.x][temp.y]!=-1){
                int diff = virtualMap[i][j] - virtualMap[temp.x][temp.y];
                if(diff/5>0){       //내인접한 놈이 나보다 작은 놈이 있다면?
                    increaseQueue.add(new PointAndD(new Point(temp.x,temp.y), diff/5));
                    decreaseQueue.add(new PointAndD(new Point(i,j), diff/5));
                }
            }
        }
    }

    private static int roll(int[][] virtualMap, int[] backUp) {
        int r = virtualMap.length/2, c = virtualMap[0].length/2, code = 0;
        int count =0;


        while (true){

            //몇 개 이동할지?
            int left = backUp.length - count;  //몇개 남았는지?
            //돌리나?  돌려도되는지? 4개 이하면 무조건돌려야함
            if(count>3&&(code)/2+1<=left||count<=3){
                for (int i = 0; i < (code)/2+1&&count<backUp.length; i++) {    //대입 후 방향전환

                    virtualMap[r][c] = backUp[count++];
                    r += dr[code%4];
                    c += dc[code%4];
                }
            }else{
                //남은거 넣어주기
                code--;

                while (count < backUp.length) {
                    virtualMap[r][c] = backUp[count++];
                    r += dr[code%4];
                    c += dc[code%4];
                }
                break;
            }
            code++; //방향전환
        }
        return code;
    }

    private static void insertFish(int[] fishbowl) {
        int min = Arrays.stream(fishbowl).min().getAsInt();
        IntStream.range(0,fishbowl.length).forEach(i->{
            if(fishbowl[i]==min) fishbowl[i]++;
        });
    }

    private static boolean isArrange(int[] fishbowl, int K) {
        int min = Arrays.stream(fishbowl).min().getAsInt();
        int max = Arrays.stream(fishbowl).max().getAsInt();

        return (max - min <= K) ? true : false;
    }
}
