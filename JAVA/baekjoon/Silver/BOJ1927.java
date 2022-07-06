import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ1927 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine()),temp;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            temp = Integer.parseInt(br.readLine());
            if(temp==0){
                if(pq.size()>0) bw.write(pq.poll()+"\n");
                else bw.write("0\n");
            }else pq.add(temp);
        }
        bw.flush();
        bw.close();
    }
}
