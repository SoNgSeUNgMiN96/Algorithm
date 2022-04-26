import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17825 {
    static int max = Integer.MIN_VALUE;
    static int[][] maps = {{2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,0},{2,4,6,8,10,13,16,19,25,30,35,40,0}, {2,4,6,8,10,12,14,16,18,20,22,24,25,30,35,40,0}, {2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,28,27,26,25,30,35,40,0}};
    static int[][] horses = new int[4][2]; //말들의 좌표를 담는 곳.  [n][i] n = 몇번째 말, i =0일때 map번호, i =1일때 좌표
    static int[] mapIdx = new int[4]; // map1 맥스,map2 맥스, map3맥스,map4맥스 인덱스
    public static void main(String[] args) throws Exception{
        int[] dices = new int[10];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < dices.length; i++) {
            dices[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < 4; i++) {
            mapIdx[i] = maps[i].length-1;
            horses[i][1] = -1;
        }
        moving(dices,0,0);
        System.out.println(max);
    }

    public static void moving(int[] dices, int depth, int score){
        if(depth==10) {
            max = Math.max(max,score);
            return;
        }
        int tempIdx, tempScore,tempMap, beforeIdx;
        Loop1 : for (int i = 0; i < 4; i++) {
            tempScore =0;
            tempMap = horses[i][0];
            beforeIdx = horses[i][1];
            //여기까지 tempMap은 밟고있는 맵, beforeIdx = 말이 있던 인덱스
            if( horses[i][1] ==mapIdx[tempMap]) continue;   //말이 이미 도착상태라면 다른말을 굴려본다.
            tempIdx = horses[i][1]+dices[depth];        //이 말을 주사위만큼 이동시킨다면
            if(tempIdx<mapIdx[tempMap]) { //말이 이번회차 도착이 아니라면
                for (int j = 0; j < 4; j++) {
                    if(j==i||horses[j][1]<0) continue;
                    if(horses[i][0]==horses[j][0]&&tempIdx==horses[j][1]){      //아예 같은 자리일경우
                        continue Loop1;
                    }else if(maps[tempMap][tempIdx]==25&&maps[horses[j][0]][horses[j][1]]==25||maps[tempMap][tempIdx]==10&&maps[horses[j][0]][horses[j][1]]==10||maps[tempMap][tempIdx]==20&&maps[horses[j][0]][horses[j][1]]==20||maps[tempMap][tempIdx]==40&&maps[horses[j][0]][horses[j][1]]==40||maps[tempMap][tempIdx]==35&&maps[horses[j][0]][horses[j][1]]==35){      //25일 경우!
                        continue Loop1;
                    }else if(maps[tempMap][tempIdx]==30&&maps[horses[j][0]][horses[j][1]]==30){
                        if(tempIdx==14&&horses[j][1]==14||tempIdx!=14&&horses[j][1]!=14) continue Loop1;
                    }
                }
                tempScore = maps[horses[i][0]][tempIdx];
            }else {
                tempIdx = mapIdx[tempMap];      //도착인덱스에 고정
            }

            horses[i][1] = tempIdx;
            if(tempMap==0&&tempIdx==4){
                horses[i][0] = 1;
            }else if(tempMap==0&&tempIdx==9){
                horses[i][0] = 2;
            }else if(tempMap==0&&tempIdx==14){
                horses[i][0] = 3;
            }
            moving(dices,depth+1,score+tempScore);
            horses[i][0] = tempMap;
            horses[i][1] = beforeIdx;
        }
    }
}
