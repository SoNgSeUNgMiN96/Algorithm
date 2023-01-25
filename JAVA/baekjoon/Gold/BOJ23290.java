package baekjoon.Gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class BOJ23290 {

    static class sharkMove{
        String moving;
        int count;

        sharkMove(String moving, int count) {
            this.moving = moving;
            this.count = count;
        }
    }

    static int fishId = 1;

    static boolean[][] isVisited= new boolean[4][4];

    static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};

    //상,좌,하,우
    static int[] sharkDr = {-1, 0, 1, 0};
    static int[] sharkDc = {0, -1, 0, 1};

    static ArrayList<sharkMove> sharkMoving = new ArrayList<>();

    static class Fish{

        int id;
        Point point;
        int direct;

        public Fish(Point point, int direct,int id) {
            this.point = point;
            this.direct = direct;
        }
    }

    static class FishCopy{
        mapHash mapHash;
        int value;

        public FishCopy(BOJ23290.mapHash mapHash, int value) {
            this.mapHash = mapHash;
            this.value = value;
        }
    }

    static class mapHash{
        int r,c,d;

        public mapHash(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Queue<Point>[] fishSmells = new Queue[3];

        HashMap<Point, Integer> fishSmellHash = new HashMap<>();

        int smellIdx = 0;
        Point shark;

        int r,c,d;

        HashMap<mapHash, Integer> hashMap = new HashMap<>();

        ArrayList<Fish>[][] map = new ArrayList[4][4];
        Queue<mapHash>[] smellQueue = new Queue[3];
        for (int i = 0; i < 3; i++) {
            smellQueue[i] = new LinkedList<>();
        }

        //임시 큐
        Queue<FishCopy> copyHashFish = new LinkedList<>();

        for (int i = 0; i < fishSmells.length; i++) {
            fishSmells[i] = new LinkedList<>();
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken()), S = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken())-1;
            c = Integer.parseInt(st.nextToken())-1;
            d = Integer.parseInt(st.nextToken())-1;
            mapHash mapHash = new mapHash(r, c, d);
            Integer orDefault = hashMap.getOrDefault(mapHash, 0);
            hashMap.put(mapHash,orDefault+1);
        }

        st = new StringTokenizer(br.readLine());
        shark = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

        while (0<S--){
            copyFishHash(hashMap, copyHashFish);
            fishMovingHash(fishSmellHash, shark, hashMap);
            sharkMovingHash(fishSmellHash, smellIdx++, shark, hashMap,fishSmells);
            copyCompleteHash(hashMap, copyHashFish);
        }

        int  answer = getAnswerHash(hashMap);

        System.out.println(answer);
    }

    private static int getAnswerHash(HashMap<mapHash, Integer> hashMap) {
        return hashMap.values().stream().mapToInt(i -> i).sum();
    }

    private static void sharkMovingHash(HashMap<Point, Integer> fishSmellHash, int smellIdx, Point shark, HashMap<mapHash, Integer> map, Queue<Point>[] smellQueue) {
        char[] direcDic = new char[3];
        sharkMoving = new ArrayList<>();
        dfsSharkHash(0, direcDic, shark, map,0, fishSmellHash);
        sharkMoving.sort((s1, s2) -> {
            if(s1.count==s2.count) return s1.moving.compareTo(s2.moving);
            return s2.count-s1.count;
        });

        sharkMove sharkMove = sharkMoving.get(0);

        for (int i = 0; i < 3; i++) {
            int thisDirec = sharkMove.moving.charAt(i) - '1';
            shark.x += sharkDr[thisDirec%4];
            shark.y += sharkDc[thisDirec%4];

            //이제 냄새를 남길 차례
            for (int j = 0; j < 8; j++) {
                mapHash mapHash = new mapHash(shark.x, shark.y, j);
                Integer mapFish = map.getOrDefault(mapHash,0);
                if (mapFish > 0) {  //0마리 이상 있었다면
                    fishSmellHash.put(new Point(mapHash.r,mapHash.c), fishSmellHash.getOrDefault(mapHash, 0) + 1);
                    smellQueue[smellIdx%3].add(new Point(mapHash.r,mapHash.c));
                    break;
                }
                map.put(mapHash, 0);
            }
        }
        while (!smellQueue[(smellIdx + 1) % 3].isEmpty()) {
            Point poll = smellQueue[(smellIdx + 1) % 3].poll();
            if (fishSmellHash.containsKey(poll)) {
                Integer integer = fishSmellHash.get(poll);
                if(integer>0) fishSmellHash.put(poll,integer-1);
            }
        }
    }

    private static void dfsSharkHash(int depth, char[] direcDic, Point shark, HashMap<mapHash, Integer> map, int count, HashMap<Point, Integer> fishSmellHash) {
        if(depth==3){
            String direcString = new String(direcDic);
            sharkMoving.add(new sharkMove(direcString,count));
            return;
        }

        for (int i = 0; i < 4; i++) {
            int smellCount =0;
            Point newSharkPoint = new Point(shark.x + sharkDr[i], shark.y + sharkDc[i]);
            //샤크 이동가능
            if(isInmap(newSharkPoint)){
                if(!isVisited[newSharkPoint.x][newSharkPoint.y]){

                    for (int j = 0; j < 8; j++) {
                        mapHash mapHash = new mapHash(newSharkPoint.x, newSharkPoint.y, i);
                        smellCount += map.getOrDefault(mapHash,0);
                    }

                    direcDic[depth] = (char) ('1' + i);
                    isVisited[newSharkPoint.x][newSharkPoint.y] = true;
                    dfsSharkHash(depth + 1, direcDic, newSharkPoint, map, count + smellCount, fishSmellHash);
                    isVisited[newSharkPoint.x][newSharkPoint.y] = false;
                }else{
                    direcDic[depth] = (char) ('1' + i);
                    dfsSharkHash(depth + 1, direcDic, newSharkPoint, map, count + smellCount, fishSmellHash);
                }
            }
        }
    }

    private static void fishMovingHash(HashMap<Point, Integer> fishSmells, Point shark, HashMap<mapHash, Integer> hashMap) {

        Object[] objects = hashMap.keySet().toArray();

        for (Object mapHash : objects) {
            int direc = ((mapHash)mapHash).d;

            for (int i = 0; i < 8; i++) {
                mapHash nextMaphash = new mapHash(((mapHash) mapHash).r + dr[direc % 8], ((mapHash) mapHash).c + dc[direc % 8], direc);
                int countFish = hashMap.get((mapHash)mapHash);

                Point point = new Point(nextMaphash.r, nextMaphash.c);

                if (isInmap(point)) {

                    if ((!(fishSmells.containsKey(point) && fishSmells.get(point) > 0) && !(nextMaphash.r == shark.x && nextMaphash.c == shark.y))) {
                        Integer orDefault = hashMap.getOrDefault(nextMaphash, 0);
                        hashMap.put(nextMaphash, orDefault + countFish);
                        hashMap.put(((mapHash) mapHash), 0);
                        break;
                    } else {
                        direc += 7;
                    }
                } else {
                    direc += 7;
                }
            }
        }
    }

    private static void copyCompleteHash(HashMap<mapHash, Integer> hashMap, Queue<FishCopy> copyHashFish) {
        while (!copyHashFish.isEmpty()) {
            FishCopy poll = copyHashFish.poll();
            Integer orDefault = hashMap.getOrDefault(poll.mapHash, 0);
            orDefault += poll.value;
            hashMap.put(poll.mapHash, orDefault);
        }
    }

    private static boolean isInmap(Point point) {
        return (point.x>=0&&point.x<4&&point.y>=0&&point.y<4)? true : false;
    }

    private static void copyFishHash(HashMap<mapHash, Integer> map, Queue<FishCopy> copyFish) {
        for (mapHash mapHash : map.keySet()) {
            copyFish.add(new FishCopy(mapHash,map.get(mapHash)));
        }
    }
}
