import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2580 {

    public static void main(String[] args) throws Exception{
        int [][] map = new int[9][9];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int count, answer;
        ArrayList<Point> zeros = new ArrayList<>();
        boolean[] visited;

        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==0) zeros.add(new Point(i,j));
            }
        }

        dfs(map,zeros,0,zeros.size());
    }

    private static void dfs(int[][] map, ArrayList<Point> zeros, int depth, int size) {
        boolean[] visited = new boolean[10];
        if(depth==size){
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map.length; j++) {
                    System.out.print(map[i][j]+" ");
                }
                System.out.println();
            }
            System.exit(0);
        }
        boxCheck(map,zeros.get(depth),visited);
        rowCheck(map,zeros.get(depth).x,visited);
        colCheck(map,zeros.get(depth).y,visited);
        for (int j = 1; j < visited.length; j++) {
            if(!visited[j]){
                map[zeros.get(depth).x][zeros.get(depth).y] = j;
                dfs(map, zeros,depth+1,size);
                map[zeros.get(depth).x][zeros.get(depth).y] = 0;
            }
        }
    }



    private static void boxCheck(int[][] map, Point temp, boolean[] visited) {
        int secX = (temp.x/3)*3;
        int secY = (temp.y/3)*3;

        for (int i = secX; i < secX+3; i++) {
            for (int j = secY; j < secY+3; j++){
                visited[map[i][j]] =true;
            }
        }

    }


    private static void colCheck(int[][] map, int y, boolean[] visited) {
        for(int i=0;i<9;i++){
            visited[map[i][y]] = true;
        }
    }

    private static void rowCheck(int[][] map, int j, boolean[] visited) {
        for(int i=0;i<9;i++){
            visited[map[j][i]] = true;
        }
    }
}
