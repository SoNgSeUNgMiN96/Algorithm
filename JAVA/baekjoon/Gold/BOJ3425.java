import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BOJ3425 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Queue<String> commads;
        String command;
        int n, num;
        long errorNum =1000000000, a,b;
        Stack<Long>[] stacks;
        String[] results, sp;
        boolean isNum;

        loop: while (true){
            commads = new LinkedList<>();

            isNum=false;
            while (true) {
                command = br.readLine();
                if (command.equals("QUIT")) {
                    break loop;
                }
                if (command.equals("END")) {
                    break;
                }
                if (command.chars().allMatch(Character::isDigit)) {
                    isNum = true;
                    break;
                }
                commads.add(command);
            }


            if(isNum){
                n = Integer.parseInt(command);
            }else n = Integer.parseInt(br.readLine());
            results = new String[n];
            stacks = new Stack[n];

            for(int i=0;i<n;i++){
                stacks[i] = new Stack<>();
                stacks[i].add(Long.parseLong(br.readLine()));
                results[i] = "";
            }

            while (commads.size()>0){   //명령 수행
                command = commads.poll();
                for(int i=0;i<n;i++) {
                    if(!results[i].equals("ERROR")){     //
                        if(command.equals("POP")){
                            if(stacks[i].size()>0){
                                stacks[i].pop();
                            }else{
                                results[i] = "ERROR";
                            }
                        }else if(command.equals("INV")){
                            if(stacks[i].size()>0){
                                stacks[i].add(-stacks[i].pop());
                            }else{
                                results[i] = "ERROR";
                            }
                        }else if(command.equals("DUP")){
                            if(stacks[i].size()>0){
                                stacks[i].add(stacks[i].peek());
                            }else{
                                results[i] = "ERROR";
                            }
                        }
                        else if(command.equals("SWP")){
                            if(stacks[i].size()>1){
                                a = stacks[i].pop();
                                b = stacks[i].pop();
                                stacks[i].add(a);
                                stacks[i].add(b);
                            }else{
                                results[i] = "ERROR";
                            }
                        }else if(command.equals("ADD")){
                            if(stacks[i].size()>1){
                                a = stacks[i].pop();
                                b = stacks[i].pop();
                                if(a+b>errorNum||a+b<-errorNum){
                                    results[i] = "ERROR";
                                    continue ;
                                }
                                stacks[i].add(a+b);
                            }else{
                                results[i] = "ERROR";
                            }
                        }else if(command.equals("SUB")){
                            if(stacks[i].size()>1){
                                a = stacks[i].pop();
                                b = stacks[i].pop();
                                if(b-a>errorNum||b-a<-errorNum){
                                    results[i] = "ERROR";
                                    continue ;
                                }
                                stacks[i].add(b-a);
                            }else{
                                results[i] = "ERROR";
                            }
                        }
                        else if(command.equals("MUL")){
                            if(stacks[i].size()>1){
                                a = stacks[i].pop();
                                b = stacks[i].pop();
                                if(a*b>errorNum||a*b<-errorNum){
                                    results[i] = "ERROR";
                                    continue ;
                                }
                                stacks[i].add(a*b);
                            }else{
                                results[i] = "ERROR";
                            }
                        }
                        else if(command.equals("DIV")){
                            if(stacks[i].size()>1){
                                a = stacks[i].pop();
                                b = stacks[i].pop();
                                if(a==0){
                                    results[i] = "ERROR";
                                    continue ;
                                }
                                if(a<0&&b<0){
                                    a = -a;
                                    b = -b;
                                }
                                stacks[i].add(b/a);
                            }else{
                                results[i] = "ERROR";
                            }
                        }
                        else if(command.equals("MOD")){
                            if(stacks[i].size()>1){
                                a = stacks[i].pop();
                                b = stacks[i].pop();
                                if(a==0){
                                    results[i] = "ERROR";
                                    continue ;
                                }
                                stacks[i].add(b%a);
                            }else{
                                results[i] = "ERROR";
                            }
                        }else if(command.contains("NUM")){
                            sp = command.split(" ");
                            stacks[i].add(Long.parseLong(sp[1]));
                        }
                    }
                }
            }
            //명령 수행 이후
            for(int i=0;i<n;i++){
                if(results[i].equals("ERROR")){
                    bw.write("ERROR\n");
                }else {
                    if(stacks[i].size()==1){
                        bw.write(stacks[i].pop()+"\n");
                    }else bw.write("ERROR\n");
                }
            }
            bw.write("\n");
            br.readLine();
        }
        bw.flush();
        bw.close();
    }
}
