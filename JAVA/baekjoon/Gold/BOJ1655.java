import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class BOJ1655 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine()), mid, temp,leftSize=0,rightSize=0;
        PriorityQueue<Integer> right = new PriorityQueue<>();
        PriorityQueue<Integer> left = new PriorityQueue<>((a,b)->b-a);
        mid = Integer.parseInt(br.readLine());
        bw.write(mid+"\n");
        for (int i = 1; i < N; i++) {
            temp = Integer.parseInt(br.readLine());
            if(temp>=mid){
                right.add(temp);
                rightSize++;
            }else{
                left.add(temp);
                leftSize++;
            }

            if(leftSize+1<rightSize){
                left.add(mid);
                mid = right.poll();
                rightSize--;
                leftSize++;
            }else if(leftSize>rightSize){
                right.add(mid);
                mid = left.poll();
                rightSize++;
                leftSize--;
            }
            bw.write(mid+"\n");
        }
        bw.flush();
        bw.close();
    }
}
