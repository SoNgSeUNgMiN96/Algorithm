import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class BOJ1626 {

    static class Node{
        int to, w;

        public Node(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }

    static class Line {
        int a,b,w;

        public Line(int a, int b, int w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }
    }

    static class Parent{
        int parent, max,secondMax;

        public Parent(int parent, int max, int secondMax) {
            this.parent = parent;
            this.max = max;
            this.secondMax = secondMax;
        }
    }

     public static void main(String[] args) throws Exception{
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st;
         st = new StringTokenizer(br.readLine());
         int V = Integer.parseInt(st.nextToken());
         int E = Integer.parseInt(st.nextToken());
         Line[] lines = new Line[E];
         boolean[] visitedLines = new boolean[E];
         boolean[] visited = new boolean[V+1];
         int[] parent = new int[V+1];
         ArrayList<Node>[] tree = new ArrayList[V+1];    //트리 정보 담을 리스트.
         int K = getK(V);

         Parent[][] parents = new Parent[V + 1][K+1];   //부모 정보와, max의 정보를 담고있다.

         for (int i = 0; i < parent.length; i++) parent[i] = i; //부모배열 초기화.
         for (int i = 0; i < tree.length; i++) tree[i] = new ArrayList<>(); //부모배열 초기화.

         for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            lines[i] = new Line(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
         }

         //parent 초기화
         for (int i = 0; i < V+1; i++) {
             for (int j = 0; j <= K; j++) {
                 parents[i][j] = new Parent(0,-1, -1);
             }
         }


         Arrays.sort(lines, (l1,l2)->l1.w-l2.w);        //w 오름차순


         int j = 0, A, B, parentA, parentB, i = 0;
         long answer = 0;

         for (i = 0; i < V-1&& j<E;j++) {    //간선은 정점 -1개가 될때까지 넣는다. 모든 간선을 넣어본다.
             A = lines[j].a;
             B = lines[j].b;

             parentA = find(parent,A);
             parentB = find(parent,B);

             if(parentA<parentB){       //항상 A가 크게 만든다.
                 int temp =parentB;
                 parentB = parentA;
                 parentA = temp;
             }

             if(parentA!=parentB){    //다른 그룹이라면 이건 노드를 추가해도 된다.     (사이클이없음)
                 visitedLines[j] = true;
                 tree[A].add(new Node(B,lines[j].w));
                 tree[B].add(new Node(A,lines[j].w));
                 answer += lines[j].w;
                 parent[parentA] = parentB; //A그룹을 B그룹에 Union
                 i++;
             }
         }

         //parent 값 초기화.


         if(i!=V-1){        //트리가 되지 않았음.
             System.out.println(-1);
             return;
         }

         //스패닝 트리가 완성되어있음. 루트를 하나 정해서 초기화 해줘야함.
         int[] depth = new int[V + 1];      //V개의 깊이정보.
         Queue<Integer> bfs = new LinkedList<>();   //bfs로 깊이 초기화 예정.

         bfs.add(1);    //1을 루트노드로 설정.
         visited[1] = true;

         int curr, next;


         while (bfs.size()>0){
             curr = bfs.poll();
             ArrayList<Node> currList = tree[curr];
             for (int k = 0; k < currList.size(); k++) {
                 next = currList.get(k).to;
                 if(!visited[next]){
                     depth[next] = depth[curr] +1;
                     visited[next] = true;
                     parents[next][0].parent = curr;
                     parents[next][0].max = currList.get(k).w;
                     parents[next][0].secondMax = -1;
                     bfs.add(next);
                 }
             }
         }

         //parents 초기화, 트리구성 완료, depth 도 갱신.

         for (i = 1; i <=K; i++) {
             for (j = 1; j <=V; j++) {

                 Integer[] maxAndSec = new Integer[4];
                 maxAndSec[0] = parents[j][i-1].max; //지금까지의 내 최댓값.
                 maxAndSec[1] = parents[j][i-1].secondMax; //지금까지의 내 최댓값.
                 maxAndSec[2] = parents[parents[j][i - 1].parent][i - 1].max;   //지금까지 부모의 최댓값.
                 maxAndSec[3] = parents[parents[j][i - 1].parent][i - 1].secondMax;   //지금까지 부모의 둘째값.

                 Arrays.sort(maxAndSec, Collections.reverseOrder());

                 parents[j][i].max = maxAndSec[0];
                 for (int k = 1; k < 4; k++) {
                     if(parents[j][i].max!= maxAndSec[k]){
                         parents[j][i].secondMax = maxAndSec[k];
                         break;
                     }
                 }
                 //parents[j][i].secondMax = maxAndSec[1];


//                 if(parents[j][i].max<parents[parents[j][i - 1].parent][i - 1].max){//부모테이블 max갱신.
//                     parents[j][i].secondMax = parents[j][i].max;
//                     parents[j][i].max = parents[parents[j][i - 1].parent][i - 1].max;
//                 }

                 /***dddlskdjlfjasldkfj;lwkje;flk 두번째 큰 값 처리해주기.*****/
                 parents[j][i].parent = parents[parents[j][i-1].parent][i-1].parent;    //부모테이블 갱신.
             }
         }

         //answer에는 현재 최소 스패닝트리의 가중치가 들어가있다.
         int changeMin = Integer.MAX_VALUE, diff, aMax, bMax, aSec, bSec;

         for (int k = 0; k < E; k++) {      //간선을 기준으로 쿼리를 돌린다.
             if(!visitedLines[k]){  //현재 스패닝트리에 사용한 간선이 아닌경우. 최소공통부모까지의 루트 상에서 max를 가져온다.
                 A = lines[k].a;
                 B = lines[k].b;
                 aMax = aSec = bSec = bMax = -1;


                 if(depth[A]<depth[B]){     //무조건. B가 작은값이 오도록 바꿔준다.
                     int temp =B;
                     B = A;
                     A = temp;
                 }
                 diff = depth[A] - depth[B];

                 for (j = 0; diff>0; j++) {
                     if((diff&1)==1){   //값의 차이가 2진수로 해당한다면.

                         Integer[] maxAndSec = new Integer[4];
                         maxAndSec[0] = aMax;//지금까지의 내 최댓값.
                         maxAndSec[1] = aSec; //지금까지의 내 최댓값.
                         maxAndSec[2] = parents[A][j].max;   //지금까지 부모의 최댓값.
                         maxAndSec[3] = parents[A][j].secondMax;   //지금까지 부모의 둘째값.
                         Arrays.sort(maxAndSec, Collections.reverseOrder());

                         aMax = maxAndSec[0];
                         for (int h = 1; h < 4; h++) {
                             if(aMax!= maxAndSec[h]){
                                 aSec = maxAndSec[h];
                                 break;
                             }
                         }

                         A = parents[A][j].parent;      //A 이동.
                     }
                     diff>>=1;
                 }

                 while (A!=B) {
                     j = 0;

                     for (j = 0; j < K; j++) {      //K점프 까지 탐색
                         if (parents[A][j].parent == parents[B][j].parent) break;
                     }

                     if (j != 0) {
                         j--;
                     }
                     Integer[] maxAndSec = new Integer[4];
                     maxAndSec[0] = aMax;//지금까지의 내 최댓값.
                     maxAndSec[1] = aSec; //지금까지의 내 최댓값.
                     maxAndSec[2] = parents[A][j].max;   //지금까지 부모의 최댓값.
                     maxAndSec[3] = parents[A][j].secondMax;   //지금까지 부모의 둘째값.
                     Arrays.sort(maxAndSec, Collections.reverseOrder());

                     aMax = maxAndSec[0];
                     for (int h = 1; h < 4; h++) {
                         if(aMax!= maxAndSec[h]){
                             aSec = maxAndSec[h];
                             break;
                         }
                     }


                     maxAndSec[0] = bMax;//지금까지의 내 최댓값.
                     maxAndSec[1] = bSec; //지금까지의 내 최댓값.
                     maxAndSec[2] = parents[B][j].max;   //지금까지 부모의 최댓값.
                     maxAndSec[3] = parents[B][j].secondMax;   //지금까지 부모의 둘째값.
                     Arrays.sort(maxAndSec, Collections.reverseOrder());

                     bMax = maxAndSec[0];
                     for (int h = 1; h < 4; h++) {
                         if(bMax!= maxAndSec[h]){
                             bSec = maxAndSec[h];
                             break;
                         }
                     }

                     A = parents[A][j].parent;
                     B = parents[B][j].parent;
                 }
                 //현재 택한 간선과, 가장 큰 간선의 차이가 적은것이 되야한다.

                 Integer[] maxAndSec = new Integer[4];
                 maxAndSec[0] = aMax;//지금까지의 내 최댓값.
                 maxAndSec[1] = aSec; //지금까지의 내 최댓값.
                 maxAndSec[2] = bSec;   //지금까지 부모의 최댓값.
                 maxAndSec[3] = bMax;   //지금까지 부모의 둘째값.

                 Arrays.sort(maxAndSec, Collections.reverseOrder());



                 if(lines[k].w!=maxAndSec[0]){      //같지 않을때
                     if(maxAndSec[0]!=-1){
                         changeMin = Math.min(changeMin,lines[k].w - maxAndSec[0]);
                     }
                 }else{ //같을때
                     if(lines[k].w!=maxAndSec[1]){
                         if(maxAndSec[1]!=-1){
                             changeMin = Math.min(changeMin,lines[k].w - maxAndSec[1]);
                         }
                     }else{
                         if(lines[k].w!=maxAndSec[2]){
                             if(maxAndSec[2]!=-1){
                                 changeMin = Math.min(changeMin,lines[k].w - maxAndSec[2]);
                             }
                         }else{
                             if(maxAndSec[3]!=-1){
                                 changeMin = Math.min(changeMin,lines[k].w - maxAndSec[3]);
                             }
                         }
                     }
                 }



             }
         }

         if(changeMin==Integer.MAX_VALUE){
             System.out.println(-1);
         }else System.out.println(answer+changeMin);
     }

     public static int find(int[] parent, int i){   //find함수.
        if(parent[i]==i) return i;
        return parent[i] = find(parent,parent[i]);
     }

    public static int getK(int n){
        int i=0, temp=1;
        while (temp<n){
            temp*=2;
            i++;
        }
        return i-1;
    }


}
