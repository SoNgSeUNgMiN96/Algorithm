public class programmersMatchTable {
    public int solution(int n, int a, int b)
    {
        int answer=1;
        a += a%2;
        b += b%2;
        while(a!=b){
            a = (int)Math.ceil(a/(double)2);
            b = (int)Math.ceil(b/(double)2);
            a += a%2;
            b += b%2;
            answer++;
        }return answer;
    }
}
