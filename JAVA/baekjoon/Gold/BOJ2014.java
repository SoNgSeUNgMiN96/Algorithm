package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BOJ2014 {
     public static void main(String[] args) throws Exception{
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st;
         st = new StringTokenizer(br.readLine());
         int K = Integer.parseInt(st.nextToken()), N = Integer.parseInt(st.nextToken());
         int[] primeArr = new int[K];
         Set<Integer> primeMulSet = new HashSet<>();

         st = new StringTokenizer(br.readLine());
         for (int i = 0; i < K; i++) {
             primeArr[i] = Integer.parseInt(st.nextToken());
         }

         IntStream.range(0,K).forEach(i->{
             IntStream.range(0,K).forEach(j->{
                 primeMulSet.add(primeArr[i] * primeArr[j]);
             });
         });

         System.out.println(primeMulSet);
         Object[] sortedPrimeMulList = primeMulSet.stream().sorted().toArray();

         System.out.println(sortedPrimeMulList[N-1]);

     }
}
