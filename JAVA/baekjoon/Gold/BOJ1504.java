import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1504 {

    static class node{
        int des, dis;
        public node(int des, int dis) {
            this.des = des;
            this.dis = dis;
        }
    }


    public static void main(String[] args) throws Exception{
        int N,E, a, b, c, v1, v2;
        Long answer, answerV1,answerV2;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        ArrayList<node>[] grahps = new ArrayList[N];
        boolean [] visited = new boolean[N];
        int[] distanceArr ;

        PriorityQueue<node> priorNode = new PriorityQueue<>((n1,n2)->{
            if(n1.dis==n2.dis){
                return n1.des - n2.des;
            }
            return n1.dis - n2.dis;
        });


        for (int i = 0; i < grahps.length; i++) {       //그래프 생성, 거리배열 초기화
            grahps[i] = new ArrayList<node>();
        }

        for (int i = 0; i < E; i++) {       //그래프 간선 그리기
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken())-1;
            b = Integer.parseInt(st.nextToken())-1;
            c = Integer.parseInt(st.nextToken());
            grahps[a].add(new node(b,c));
            grahps[b].add(new node(a,c));
        }

        //출발지와 목적지 입력.
        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken())-1;
        v2 = Integer.parseInt(st.nextToken())-1;

        distanceArr = dijkstra(0,N,grahps);

        if(distanceArr[N-1]==Integer.MAX_VALUE||distanceArr[v1]==Integer.MAX_VALUE||distanceArr[v2]==Integer.MAX_VALUE){
            System.out.println(-1);
            return;
        }

        if(v1==0){
            if(v2==N-1){
                System.out.println(distanceArr[N-1]);
            }else{
                answer = Long.valueOf(distanceArr[v2]);
                distanceArr = dijkstra(v2,N,grahps);
                answer += distanceArr[N-1];
                System.out.println(answer);
            }
            return;
        }else if(v2==N-1){
            answer = Long.valueOf(distanceArr[v1]);
            distanceArr = dijkstra(v1,N,grahps);
            answer += distanceArr[N-1];
            System.out.println(answer);
            return;
        }


        answerV1 = Long.valueOf(distanceArr[v2]);
        answerV2 = Long.valueOf(distanceArr[v1]);
        distanceArr = dijkstra(v2,N,grahps);
        answerV1 += distanceArr[v1];
        distanceArr = dijkstra(v1,N,grahps);
        answerV1 += distanceArr[N-1];



        distanceArr = dijkstra(v1,N,grahps);
        answerV2 += distanceArr[v2];
        distanceArr = dijkstra(v2,N,grahps);
        answerV2 += distanceArr[N-1];


        System.out.println(Math.min(answerV1,answerV2));
    }

    public static int[] dijkstra(int start,int N,ArrayList<node>[] grahps){
        int[] distanceArr = new int[N];
        for(int i=0;i<distanceArr.length;i++) distanceArr[i] = Integer.MAX_VALUE;
        PriorityQueue<node> priorNode = new PriorityQueue<>((n1,n2)->{
            if(n1.dis==n2.dis){
                return n1.des - n2.des;
            }
            return n1.dis - n2.dis;
        });
        priorNode.add(new node(start,0));
        distanceArr[start]=0;

        while (priorNode.size()>0){
            node temp = priorNode.poll();

            for(node n: grahps[temp.des]){
                if(distanceArr[temp.des]+n.dis<distanceArr[n.des]){
                    distanceArr[n.des] = distanceArr[temp.des]+n.dis;
                    priorNode.add(new node(n.des,distanceArr[n.des]));
                }
            }
        }
        return distanceArr;
    }
}
