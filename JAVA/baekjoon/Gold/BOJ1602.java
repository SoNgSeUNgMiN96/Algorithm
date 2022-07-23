import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ1602 {

    //antic  //5글자는 필수
    static HashSet<Character> dic = new HashSet<>(Arrays.asList('a','n','t','c','i'));
    static char[] restAlpha = new char[21];
    static int max = 0, N;
    static String[] words;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N= Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken()) -5;
        words = new String[N];

        int idx = 0;
        for (int i = 0; i < 26; i++) {
            if(!dic.contains((char)('a'+i))) restAlpha[idx++] = (char)('a'+i);
        }

        for (int i = 0; i < N; i++) words[i] = br.readLine();

        combi(0,K);
        System.out.println(max);
    }

    public static void combi(int start, int m){

        if(m<0) return;     //anta tica를 가르치질 못하는 상황.

        if(m==0){
            check();
            return;
        }

        for (int i = start; i < 21-m+1; i++) {
            dic.add(restAlpha[i]);
            combi(i+1,m-1);
            dic.remove(restAlpha[i]);
        }
    }

    private static void check() {

        int count = 0;

        for (int i = 0; i < N; i++) {
            char[] word = words[i].toCharArray();   //현재 단어를 넣어본다.
            boolean fail = false;
            for (int j = 0; j < word.length; j++) {
                if(!dic.contains(word[j])){     //없는 글자를 만났을때 //가르쳐야할.
                    fail = true;
                }
            }
            if(!fail) count++;
        }

        max = Math.max(max,count);
    }
}
