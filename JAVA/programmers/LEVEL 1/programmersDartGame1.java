import java.io.BufferedReader;
import java.io.InputStreamReader;

public class programmersDartGame1 {
    public static void main(String[] args) {
        System.out.println(solution("1D#2S*3S"));
    }
    static public int solution(String dartResult) {

        int num , sum = 0, last=0, lastLast =0;
        boolean lastCode = false;
        char array[] = dartResult.toCharArray();

        for (int i = 0; i < array.length; i++) {
            num = 0;
            //숫자부분만 먼저 추출
            while (array[i] >= '0' && array[i] <= '9') {
                num *= 10;
                num += array[i] - '0';
                i++;
            }

            // S 일경우
            if (array[i] == 'S') {
                if (lastCode) { // 한번이라도 last  가 들어간 적이 있다면  2회차 전 값을 넣어준다.
                    lastLast = last;
                }
                last = num;
                sum += num;
            } else if (array[i] == 'D') {   //D일경우
                if (lastCode) {
                    lastLast = last;
                }
                last = num * num;
                sum += last;

            } else if (array[i] == 'T') {       //T일경우
                if (lastCode) {
                    lastLast = last;
                }
                last = num * num * num;
                sum += last;
            } else if (array[i] == '*') {       // *일경우 last와 lastLast가 갱신, 및 두배
                sum += last + lastLast;
                last *= 2;
                lastLast *= 2;
            } else {        // #은 이전값을 - 부호로 만들어준다.
                sum -= last * 2;
                last *= -1;
            }
            lastCode = true;        //last가 한번이라도 갱신이 일어나서 이후부터 lastLast갱신 가능
        }
        return sum;
    }
}
