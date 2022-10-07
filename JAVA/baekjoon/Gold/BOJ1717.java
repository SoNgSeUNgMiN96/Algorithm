package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ1717 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        int oper, a, b, aParent, bParent;
        int[] arr = new int[N+1];
        for (int i = 0; i < N; i++) {
            arr[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            oper = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if(oper==0){
                if(a<b){
                    aParent = find(arr,a);
                    bParent = find(arr,a);
                    arr[bParent] = aParent;
                }else{
                    aParent = find(arr,a);
                    bParent = find(arr,a);
                    arr[aParent] = bParent;
                }
            }else{
                if(find(arr,a)==find(arr,b)) {
                    bw.write("YES\n");
                }else bw.write("NO\n");
            }
        }
        bw.flush();
    }
    public static int find(int[] arr, int parent){
        if(arr[parent] == parent) return parent;
        return arr[parent] = find(arr,arr[parent]);
    }
}
