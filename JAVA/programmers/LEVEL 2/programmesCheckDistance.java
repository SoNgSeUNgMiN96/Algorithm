import java.util.*;
import java.awt.Point;

public class programmesCheckDistance {
    static class node{
        int distance;
        Point point;
        node(int distance, Point point){
            this.distance = distance;
            this.point = point;
        }
    }

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length],dx={0,0,1,-1},dy={1,-1,0,0};
        boolean[][] visited;
        Queue<node> queue = new LinkedList<>();


        Loop1 : for(int k=0;k<places.length;k++){
            answer[k] = 1;
            for(int i=0;i<5;i++){
                for(int j=0;j<5;j++){
                    if(places[k][i].charAt(j)=='P'){
                        visited = new boolean[5][5];
                        queue = new LinkedList<>();
                        visited[i][j] = true;
                        queue.add(new node(2,new Point(i,j)));
                        while(queue.size()>0){
                            node temp = queue.poll();

                            if(temp.distance!=2&&places[k][temp.point.x].charAt(temp.point.y)=='P'){
                                answer[k] = 0;
                                continue Loop1;
                            }

                            if(temp.distance==0){
                                continue;
                            }

                            for(int l=0;l<4;l++){
                                Point cursor = new Point(temp.point.x+dx[l],temp.point.y+dy[l]);
                                if(cursor.x<0||cursor.x>=5||cursor.y<0||cursor.y>=5) continue;

                                if(!visited[cursor.x][cursor.y]&&places[k][cursor.x].charAt(cursor.y)!='X'){
                                    visited[cursor.x][cursor.y] = true;
                                    queue.add(new node(temp.distance-1,new Point(cursor.x,cursor.y)));
                                }
                            }

                        }
                    }
                }
            }
        }

        return answer;
    }

}
