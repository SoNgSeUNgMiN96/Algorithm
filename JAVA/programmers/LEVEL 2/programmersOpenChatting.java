import java.util.Arrays;
import java.util.HashMap;

public class programmersOpenChatting {

    public static void main(String[] args) {
        String [] record = {"Enter uid1234 Muzi", "Change uid1234 Muzi", "Leave uid1234", "Enter uid1234 Prodo"};
        String [] result = solution(record);
        System.out.println(Arrays.toString(result));
    }

    static public String enter(String s){
        return s+"님이 들어왔습니다.";
    }
    static public String leave(String s){
        return s+"님이 나갔습니다.";
    }

    static public String[] solution(String[] record) {
        String[] answer, sp, command;
        command = new String[record.length];

        int result = 0;
        HashMap<String, String> idMap = new HashMap<>();

        for (int i = 0; i < record.length; i++) {
            sp = record[i].split(" ");      //0번에 명령, 1번에 유저아이디, 2번에 닉네임

             if(sp[0].equals("Enter")){
                idMap.put(sp[1],sp[2]);
                command[result++] = "in "+ sp[1];
             }else if(sp[0].equals("Leave")){
                 command[result++] = "out "+ sp[1];
             }else{
                 idMap.put(sp[1],sp[2]);        //id 갱신
            }
        }
        answer = new String[result];

        for (int i = 0; i < result; i++) {
            sp = command[i].split(" ");
            if(sp[0].equals("in")){
                answer[i] =  enter(idMap.get(sp[1]));
            }else {
                answer[i] = leave(idMap.get(sp[1]));
            }
        }
        return answer;
    }
}
