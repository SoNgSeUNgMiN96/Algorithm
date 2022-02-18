import java.util.Stack;

public class programmersCranClawMachineGame {

    public static void main(String[] args) {
        int[][] board ={{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
        int[] moves = {1,5,3,5,1,2,1,4};
        System.out.println(solution(board,moves));
    }
    static public int solution(int[][] board, int[] moves) {

        int answer = 0, j;
        Stack<Integer> lastDoll = new Stack<>();        //인형을 쌓을 스택

        for (int i = 0; i < moves.length; i++) {
            j=0;        //처음엔 위에서부터 내려간다.
            while (j<board.length&&board[j][moves[i]-1]==0) j++;        //인형이 나올때까지 내려간다.
            if(j!=board.length) {       //끝까지 내려가서 없는경우를 제외하면
                if (lastDoll.size()>0&& lastDoll.peek()==board[j][moves[i]-1] ){        //인형이 같을경우 인형을 빼고 2개 더해줌.
                    lastDoll.pop();
                    answer+=2;
                }
                else lastDoll.push(board[j][moves[i]-1]);   //다를경우 인형을 넣어준다.
                board[j][moves[i]-1] =0;        //뽑은 인형은 비워준다.
            }

        }
        return answer;
    }
}
