import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ10828 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());    //명령의 수
        int top = -1, temp;
        String command;
        StringTokenizer st;
        int[] stack = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            command = st.nextToken();
            if(command.equals("push")){
                temp = Integer.parseInt(st.nextToken());
                stack[++top] = temp;
            }else if(command.equals("pop")){
                if(top<0){
                    bw.write("-1\n");
                }else{
                    bw.write(stack[top--]+"\n");
                }
            }else if(command.equals("size")){
                bw.write((top+1)+"\n");
            }else if(command.equals("empty")){
                if(top<0){
                    bw.write("1\n");
                }else{
                    bw.write("0\n");
                }
            }else{  //top
                if(top<0){
                    bw.write("-1\n");
                }else{
                    bw.write(stack[top]+"\n");
                }
            }
        }
        bw.flush();
        bw.close();
    }
}
