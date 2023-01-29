package baekjoon.Silver;

import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11650 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Point[] points = new Point[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(points, (p1,p2)->{
            if (p1.x == p2.x) {
                return p1.y - p2.y;
            }
            return p1.x - p2.x;
        });

        Arrays.stream(points).forEach( p->{
            try {
                bw.write(p.x + " " + p.y + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bw.flush();
    }
}
