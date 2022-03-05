import java.util.*;

public class programmersMenuRenewer {

    public static void main(String[] args) {
        String [] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int [] course = {2,3,4};
        programmersMenuRenewer pmr = new programmersMenuRenewer();
        String []result = pmr.solution(orders,course);
        System.out.println(Arrays.toString(result));
    }

    class type{
        String menu;
        int count;

        type(String menu, int count) {
            this.menu = menu;
            this.count = count;
        }
    }

    char[] pick;        //뽑은 아이들을 저장할 배열
    int n,max;      //
    HashSet<Character> customers[];
    ArrayList<type> answerTemp;
    ArrayList<String> answerArray = new ArrayList<>();

    public void combi(int mcount, int m, int start, char[] allMenu){


        if(mcount==m){   //pick을 다 뽑아봤다면
            int answer = 0;
            for(int i=0; i<n;i++){      //손님들을 기준으로
                //뽑은 메뉴를 돌린다.
                int count =0;
                for (int j=0;j<pick.length;j++){

                    if(customers[i].contains(pick[j])){
                        count++;
                    }else break;
                }
                //해당 조합을 가지고있다면.  <-손님이 그 메뉴를 모두 포함한경우
                if(count == pick.length){
                    answer++;
                }
            }
            //내가 기존에 뽑은 갯수 이상을 넘겼다면.(초기 2) , 해당 pick배열을 정답에 추가.
            if(answer>=max){
                max = answer;
                answerTemp.add(new type(new String(pick),answer));
                return;
            }
            return ;
        }
        //조합 재귀
        for(int i=start;i<allMenu.length - (m - mcount)+1;i++){
            pick[mcount] = allMenu[i];
            combi(mcount+1,m,i+1,allMenu);
        }
    }


    public String[] solution(String[] orders, int[] course) {

        String[] answer;
        int i=0;
        char []temp;
        n = orders.length;
        customers = new HashSet[n];
        HashSet<Character> allMenu = new HashSet();

        //각 손님의 메뉴들을 집합에 넣는다.  전체메뉴 집합에도 추가함.
        for (i = 0; i < customers.length; i++) {
            customers[i] = new HashSet<>();
            temp = orders[i].toCharArray();
            for (int j=0;j<temp.length;j++){
                customers[i].add(temp[j]);
                allMenu.add(temp[j]);
            }
        }

        //temp에 총 메뉴 복사.       배열로 다루기위해서 복사
        i = 0;
        temp = new char[allMenu.size()];
        for (Character c : allMenu){
            temp[i++] = c;
        }

        //temp 정렬
        Arrays.sort(temp);

        //모든 메뉴를 각 개수별로 뽑아준다. pick은 뽑을 개수대로 새롭게 만듬
        for(i=0;i<course.length;i++){
            pick = new char[course[i]];
            answerTemp = new ArrayList<>();

            max = 2;
            combi(0,course[i],0,temp);

            //뽑힌 정답을 정렬해준다. type을 기준으로.
            Collections.sort(answerTemp, new Comparator<type>() {
                @Override
                public int compare(type o1, type o2) {
                    return  o2.count - o1.count;
                }
            });
            //뽑힌 메뉴가 없다면 continue
            if(answerTemp.size()==0) continue;
            //가장 많이 뽑힌 메뉴가 몇개 있는지를 countTemp에 담는다.
            int countTemp = answerTemp.get(0).count;
            //해당 메뉴는 정답에 추가.
            answerArray.add(new String(answerTemp.get(0).menu));
            //해당 메뉴와 동일한 메뉴수를 가진 메뉴들을 추가한다.
            for (int k=1;k<answerTemp.size();k++){
                if(countTemp == answerTemp.get(k).count){
                    answerArray.add(new String(answerTemp.get(k).menu));
                }
                else break;
            }

        }

        //마지막으로 정답 String 배열에 ArrayList를 복사한 후 정렬
        answer = new String[answerArray.size()];
        i =0;
        for(String s : answerArray){
            answer[i++] = s;
        }

        Arrays.sort(answer);
        return answer;
    }
}
