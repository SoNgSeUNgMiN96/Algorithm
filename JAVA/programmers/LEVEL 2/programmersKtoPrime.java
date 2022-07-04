public class programmersKtoPrime {
    public int solution(int n, int k) {
        int answer = 0;
        String num = Integer.toString(n,k);

        while(num.contains("00")){
            num = num.replaceAll("00","0");
        }


        if(!num.contains("0")){
            return (isPrime(Long.parseLong(num))) ? 1:0;
        }

        String[] nums = num.split("0");

        for(String numParse: nums){

            if(numParse.matches("-?\\d+")&&isPrime(Long.parseLong(numParse))){
                answer++;
            }
        }

        return answer;
    }
    public boolean isPrime(long n) {
        if(n<=1) return false;
        for (long i = 2; i<=(long)Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
