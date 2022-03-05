public class programmersStringCompress {
    public static void main(String[] args) {
        String s = "abcabcabcabcdededededede";
        System.out.println(solution(s));
    }

    static public int solution(String s) {
        int answer = s.length(), counter=0,k=0, answerTemp;
        String temp ="", next="";

        for (int i = 1; i < s.length(); i++) {          //몇글자  (간격)
            answerTemp = s.length();
            for (int j = 0; j < s.length() - i*2 + 1; j=j+i) {      //어디부터
                temp = s.substring(j,j+i);
                counter = 1;

                for (k = j+i; k <s.length() - i + 1; k=k+i) { //다음애부터 검사
                    if(temp.equals(s.substring(k,k+i))) counter++;
                    else break;
                }
                if(counter>1){
                    j=k-i;
                    answerTemp -= (temp.length()*(counter-1)-(int)(Math.log10(counter)+1));
                }
            }
            if(answer>answerTemp) answer = answerTemp;      //압축이되었다면 갱신
        }
        return answer;
    }
}
