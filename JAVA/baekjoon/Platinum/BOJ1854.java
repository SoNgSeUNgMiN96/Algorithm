import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1854 {

    static class Node{
        int to;
        long w;

        public Node(int to, long w) {
            this.to = to;
            this.w = w;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
        ArrayList<Node>[] nodes = new ArrayList[N];
        PriorityQueue<Long>[] visited = new PriorityQueue[N];
        int a, b, c;

        PriorityQueue<Node> pq = new PriorityQueue<>((n1,n2)->{
            if(n1.w<n2.w){
                return -1;
            }else if(n1.w==n2.w) return 0;
            return 1;
        });    //거리를 기준으로 pq


        for (int i = 0; i < N; i++) {
            nodes[i] = new ArrayList<>();
            visited[i] = new PriorityQueue<>((n1,n2)->{
                if(n1>n2){
                    return -1;
                }else if(n1==n2) return 0;
                return 1;
            });
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken())-1;
            b = Integer.parseInt(st.nextToken())-1;
            c = Integer.parseInt(st.nextToken());
            Node temp = new Node(b,c);
            nodes[a].add(temp);
        }

        pq.add(new Node(0,0));
        visited[0].add(0L);

        while (pq.size()>0){
            Node curr = pq.poll();

            for (int i = 0; i < nodes[curr.to].size(); i++) {
                long nextW = curr.w +  nodes[curr.to].get(i).w;  //지금 노드가
                int to = nodes[curr.to].get(i).to; //지금 노드가 가려는 곳의 비용.
                if(visited[to].size()==K&&visited[to].peek()>nextW) visited[to].poll();
                if(visited[to].size()<K){   //K번 방문 전이라면.
                    visited[to].add(nextW);
                    pq.add(new Node(to,nextW));
                }
            }
        }

        for (int i = 0; i < N; i++) {
            if(visited[i].size()<K){
                bw.write("-1\n");
            }else bw.write(visited[i].poll()+"\n");
        }
        bw.flush();

    }
}
