package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2374 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long answer = 0;
        int  next, number;
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            next =  Integer.parseInt(br.readLine());
            list.add(next);
        }

        stack.add(list.get(0));
        int min,max = 0;

        for (int i = 1; i < list.size(); i++) {
            number = list.get(i);
            //만약 더 작은수가 왔다면?

            min = stack.peek();
            //지금 수 보다 같거나 작은 수는 다 뺀다
            while (!stack.isEmpty()&&stack.peek() <= number) {
                stack.pop();
            }

            //올라온 만큼 올려준다.
            if (min < number) {
                answer += (number - min);
            }
            stack.add(number);
        }

        while (!stack.isEmpty()) {

            min = stack.peek();

            while (!stack.isEmpty()) {
                max = stack.pop();
            }
            answer += (max - min);
        }
        
        System.out.println(answer);
    }
}
