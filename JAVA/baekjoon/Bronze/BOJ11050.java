import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11050 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()),  R = Integer.parseInt(st.nextToken());
        System.out.println(getnCr(N,R,1,1,0));
    }

    private static int getnCr(int n, int r,int sum,int div,int depth) {
        if(r==depth) return sum/div;
        return (n-r<r) ? getnCr(n,n-r,sum,div,depth) : getnCr(n,r,sum*(n-depth),div*(depth+1),depth+1);
    }
}
