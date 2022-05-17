import java.awt.*;
import java.util.*;

class Solution {
    public int[] solution(int m, int n, int[][] picture) {

        int[][] map = new int[m][n];

        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++)
                map[i][j] = picture[i][j];

        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        int sizeOfArea=0, temp;
        int[] dx = {0,0,1,-1}, dy = {1,-1,0,0};

        Point cursor, movePoint;
        Queue<Point> queue = new LinkedList<>();

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                temp = map[i][j];
                if(temp!=0){
                    numberOfArea++;
                    sizeOfArea = 0;
                    map[i][j] = 0;
                    queue.add(new Point(i,j));

                    while(queue.size()>0){
                        cursor = queue.poll();
                        sizeOfArea++;

                        for(int k=0;k<4;k++){
                            movePoint = new Point(cursor.x+dx[k],cursor.y+dy[k]);
                            if(movePoint.x>=0&&movePoint.x<m&&movePoint.y>=0&&movePoint.y<n){
                                if(map[movePoint.x][movePoint.y]==temp){
                                    map[movePoint.x][movePoint.y] = 0;
                                    queue.add(movePoint);
                                }
                            }
                        }
                    }
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea,sizeOfArea);
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
}