import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ10845{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());    //명령의 수
        int rear=-1 ,front = -1, temp;

        String command;
        StringTokenizer st;
        int[] queue = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            command = st.nextToken();
            if(command.equals("push")){
                temp = Integer.parseInt(st.nextToken());
                queue[++rear] = temp;
            }else if(command.equals("pop")){
                if(rear==front){
                    bw.write("-1\n");
                }else{
                    bw.write(queue[(++front)%N]+"\n");
                }
            }else if(command.equals("size")){
                bw.write((rear-front)+"\n");
            }else if(command.equals("empty")){
                if(rear==front){
                    bw.write("1\n");
                }else{
                    bw.write("0\n");
                }
            }else if(command.equals("front")){  //top
                if(rear==front){
                    bw.write("-1\n");
                }else{
                    bw.write(queue[(front+1)%N]+"\n");
                }
            }else{
                if(rear==front){
                    bw.write("-1\n");
                }else{
                    bw.write(queue[rear%N]+"\n");
                }
            }
        }
        bw.flush();
        bw.close();
    }
}
