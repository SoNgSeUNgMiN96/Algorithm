public class programmersNessence {

    public static String solution(int n, int t, int m, int p) {

        String answer = "", temp="", answertemp="";

        int num=p-1, i=0,size=0;;

        while (size < t*m) {
            temp = changenum(n,i++);
            size += temp.length();
            answertemp += temp;
        }


        for (i = 0; i <t; i++) {
            answer += answertemp.substring(num,num+1);
            num += m;
        }

        return answer;
    }
    public static String changenum(int n, int value) {
        StringBuilder builder = new StringBuilder();
        String alpha[] = {"A","B","C","D","E","F"};
        if (value==0) return "0";
        while (value >= 1) {
            builder.insert(0, (value % n >=10 ? (alpha[value%n-10]) :value % n  ));
            value /= n;
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(solution(16,16,2,2));
    }
}
