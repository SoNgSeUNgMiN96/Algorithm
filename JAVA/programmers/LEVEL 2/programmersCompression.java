
import java.util.*;
public class programmersCompression {

    public static int[] solution(String msg) {
        int numbering,j;
        HashMap<String,Integer> LZWDic = new HashMap<>();
        ArrayList<Integer> answerList = new ArrayList<>();

        String w="",c="",temp="";


        for(numbering=1;numbering<=26;numbering++){
            w="";
            char tempChar = (char)('A'+ numbering-1);
            w +=tempChar;
            LZWDic.put(w,numbering);
        }

        for(int i=0;i<msg.length();i++){

            for(j=i;j<msg.length();j++){
                temp = msg.substring(i,j+1);

                if(!LZWDic.containsKey(temp)){   //없는 경우
                    c = msg.substring(j,j+1);
                    LZWDic.put(w+c,numbering++);
                    i=j-1;
                    break;
                }else{
                    w = temp;
                }
            }
            answerList.add(LZWDic.get(w));
            if(j==msg.length()) break;
        }

        int[] answer = answerList.stream().mapToInt(i -> i).toArray();
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("AB"));
    }
}
