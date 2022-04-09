import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class programmersPresentN {
    public int solution(int N, int number) {
        int answer = 0,temp;
        int dp[] = new int[32001];
        
        HashSet<Integer>[] digitSet = new HashSet[9];
        for(int i=1;i<9;i++){
            digitSet[i] = new HashSet<>();
        }
        digitSet[1].add(N);
        dp[N] = 1;
        
        for(int i=2;i<=8;i++){
            temp  = getNum(N,i);
            digitSet[i].add(temp);
            if(temp<=32000){
                dp[temp] = i;
            }
            for(int j=1;j<i/2+1;j++){
                for(Integer k: digitSet[i-j]){
                    for(Integer m: digitSet[j]){
                        temp = k*m;
                        digitSet[i].add(temp);
                        if(temp<=32000&&temp>0) {
                            if(dp[temp]==0) dp[temp] = i;
                        } 
                        if(m!=0)
                        temp = k/m;
                        digitSet[i].add(temp);
                        if(temp<=32000&&temp>0) {
                            if(dp[temp]==0) dp[temp] = i;     
                        }
                        temp = k-m;
                        digitSet[i].add(temp);
                        if(temp<=32000&&temp>0) {
                            if(dp[temp]==0) dp[temp] = i;
                        } 
                        temp = m-k;
                        digitSet[i].add(temp);
                        if(temp<=32000&&temp>0) {
                            if(dp[temp]==0) dp[temp] = i;
                        } 
                        temp = k+m;
                        digitSet[i].add(temp);
                         if(temp<=32000&&temp>0) {
                            if(dp[temp]==0) dp[temp] = i;
                        }
                    }
                    
                } 
            }   
        }
        
        return (dp[number]==0)?-1:dp[number];
    }
    public int getNum(int N,int digit){
        int temp=0;
        for(int i=0;i<digit;i++){
            temp = temp*10 + N;
        }
        return temp;
    }
}
