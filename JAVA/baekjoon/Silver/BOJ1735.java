import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1735 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st= new StringTokenizer(br.readLine());
        int a1,b1,a2,b2, gcd , a3, b3;
        a1 = Integer.parseInt(st.nextToken());
        b1 = Integer.parseInt(st.nextToken());
        st= new StringTokenizer(br.readLine());
        a2 = Integer.parseInt(st.nextToken());
        b2 = Integer.parseInt(st.nextToken());
        gcd =gcd(b1,b2);
        b3 = gcd*(b1/gcd)*(b2/gcd);
        a3 = b3/b1*a1 + b3/b2*a2;
        gcd = gcd(b3,a3);
        a3 = a3/gcd;
        b3 = b3/gcd;
        System.out.println(a3+" "+b3);
    }
    public static int gcd(int a, int b){
        if(b==0) return a;
        if(a>b){
            return gcd(b,a%b);
        }else{
            return gcd(a,b%a);
        }
    }
}
