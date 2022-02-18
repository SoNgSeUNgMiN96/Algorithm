public class programmersPessingKeypad {
    public static void main(String[] args) {
        int numbers[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        System.out.println(solution(numbers,"right"));
    }
    static public String solution(int[] numbers, String hand) {
        int lastLeft=10, lastRight=12;
        String answer = "";
        int distance[][][] = {{{1,2,3,4},{0,1,2,3}}, {{2,1,2,3},{1,0,1,2}},{{3,2,1,2},{2,1,0,1}},{{4,3,2,1},{3,2,1,0}}};    //각각의 거리를 계산해주는 배열
        /*
            distance 배열은 [위에서 몇번째 줄인지][왼쪽(오른쪽) 줄인지 가운데 줄인지][2,5,8,0까지의 각각 거리]

            위에서 몇번쨰 줄인지
            왼손의 경우 1245780(11)*(10) 이가능하다. 몇번째 줄인지는  /3을 하면 위에서 몇째줄인지 나옴(0째줄부터 시작)
            오른손의 경우 2356890(11)#9(12) 가 가능하다. 몇 번째 줄인지는 (lastRight%3==0)?lastRight/3-1 : lastRight/3 이다. (오른쪽줄일경우 1빼줌)

            왼쪽(오른쪽) 줄인지 가운데 줄인지에 따라 거리를 다르게 두었다
            왼손의 왼쪽줄의 경우 (1,4,7,*(10)) 은 %3 ==1  중앙줄은 %3 ==2이다. 따라서 lastLeft%3-1  을 하여 본인줄(0번) 중앙줄(1번)
            오른손의 오른쪽 줄의경우  (3,6,9,#(11)) 는 %3 == 0   중앙줄은 %3 ==2이다. 따라서 (lastRight%3==0)?lastRight%3:1 로 본인줄(0번) 중앙줄(1번)

            그리고 각각 위에서 몇째줄 이고 본인줄 또는 가운데줄을 알았다면 본인을 기준으로 2, 5, 8, 0 의 거리가 각각 담겨있다.
        */

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i]==0) numbers[i] = 11;

            if(numbers[i]%3==1){        //왼손일경우 마지막 왼손을갱신 , L 을 넣어준다.
                lastLeft = numbers[i];
                answer += "L";
            }else if(numbers[i]%3==2){        //중앙일경우
                //만약 왼쪽 오른쪽간의 거리가 같다면 왼손잡이 또는 오른손잡이로 구분
                if(distance[lastLeft/3][lastLeft%3-1][numbers[i]/3]==distance[(lastRight%3==0)?lastRight/3-1 : lastRight/3][(lastRight%3==0)?lastRight%3:1][numbers[i]/3]){
                    if(hand.equals("left")) {
                        answer += "L";
                        lastLeft = numbers[i];
                    }
                    else {
                        answer += "R";
                        lastRight = numbers[i];
                    }
                }// 만약 왼손이 더 가깝다면
                else if(distance[lastLeft/3][lastLeft%3-1][numbers[i]/3]<distance[(lastRight%3==0)?lastRight/3-1 : lastRight/3][(lastRight%3==0)?lastRight%3:1][numbers[i]/3]){
                    answer += "L";
                    lastLeft = numbers[i];
                }//오른손이 더 가깝다면
                else{
                    answer += "R";
                    lastRight = numbers[i];
                }
            }else{        //오른손이 가깝다면.
                lastRight = numbers[i];
                answer += "R";
            }
        }
        return answer;
    }
}
