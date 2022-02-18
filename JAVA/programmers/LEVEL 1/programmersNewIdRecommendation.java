import java.util.Locale;
import java.util.regex.Pattern;

public class programmersNewIdRecommendation {
    public static void main(String[] args) {
        String new_id = "zsdfasdfasgsdf.sdgasdfagsd-+.^.";
        System.out.println(solution(new_id));
    }

    static public String solution(String new_id) {

        String answer = new_id;
        //1단계 소문자로 변환.
        answer = answer.toLowerCase(Locale.ROOT);

        //2단계 허용문자 이외의 문자 빼기
        answer =  answer.replaceAll("[^a-z0-9.\\-._.\\.]","");

        //3단계 ..들을 .으로 변환
        while (answer.contains("..")) answer =  answer.replaceAll("\\.\\.",".");

        //4단계 처음과 끝의 . 을 빼기
        while ((answer.length()>0&&answer.charAt(0)=='.')||(answer.length()>0&&answer.charAt(answer.length()-1)=='.')){
            if(answer.length()>0&&answer.charAt(0)=='.')       //1개짜리 문자열일경우 ?
                answer = answer.substring(1,answer.length());
            if(answer.length()>0&&answer.charAt(answer.length()-1)=='.')
                answer = answer.substring(0,answer.length()-1);
        }

        //5단계 빈 문자라면 a를 추가해주기.
        if(answer.length()==0) answer  += "a";

        //6단계  16글자 이상이라면 15글자까지 잘라주고, 마지막 문자가 . 인지를 확인하여 제거한다.
        if(answer.length()>=16) answer = answer.substring(0,15);
        while ((answer.length()>0&&answer.charAt(answer.length()-1)=='.')){
            if(answer.length()>0&&answer.charAt(answer.length()-1)=='.')
                answer = answer.substring(0,answer.length()-1);
        }

        //7단계 3글자 이하라면, 3글자가 될 때까지 마지막 문자를 붙여준다.
        while (answer.length()<3) answer += answer.substring(answer.length()-1,answer.length());

        return answer;
    }
}
