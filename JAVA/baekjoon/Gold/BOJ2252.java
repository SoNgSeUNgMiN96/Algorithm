package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2252 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] nodes = new ArrayList[N];
        ArrayList<Integer> answer = new ArrayList<>();
        int A,B;
        boolean[] isChild = new boolean[N];
        boolean[] visited = new boolean[N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken())-1;
            B = Integer.parseInt(st.nextToken())-1;
            if(nodes[B]==null){     //없을경우 생성.  날 보는 친구 생성
                nodes[B] = new ArrayList<>();
            }
            nodes[B].add(A);
            isChild[A] = true;
        }
        for (int i = 0; i < N; i++) {
            if(!isChild[i]){  //부모인 노드 탐색.
                if(!visited[i]){
                    dfs(bw, visited, nodes, i,answer);
                }
            }
        }
        for (int i = 0; i < N; i++) {
            if(!visited[i]){
               answer.add(i);
            }
        }
        for (int i = 0; i < answer.size(); i++) {
            bw.write((answer.get(i)+1)+" ");
        }
        bw.flush();
    }

    private static void dfs(BufferedWriter bw, boolean[] visited, ArrayList<Integer>[] nodes,int i, ArrayList<Integer> answer) {
        for (int j = 0;nodes[i]!=null&& j < nodes[i].size(); j++) {
            if(!visited[nodes[i].get(j)]){
                visited[nodes[i].get(j)] = true;
                dfs(bw,visited,nodes,nodes[i].get(j), answer);
                answer.add(nodes[i].get(j));
            }
        }
        if(!visited[i]){
            visited[i] = true;
            answer.add(i);
        }
    }
}
