import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

public class programmersGetReportResult {
    public static void main(String[] args) {
        String idList[] = {"con", "ryan"};
        String report[] ={"ryan con", "ryan con", "ryan con", "ryan con"};
        int k=3;
        Solution s = new Solution();
        int [] answer = s.solution(idList,report,k);
        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i]+" ");
        }
    }

    static class Solution {
        //id_list 에 총 아이디들이 담겨있다. report배열에 각각의 누가 누구를 리폿한지가 나와있고, 신고받은 횟수가 k를 넘기면 정지가 된다.
        public int[] solution(String[] id_list, String[] report, int k) {

            int[] answer = new int[id_list.length]; //각각의 사용자가 신고한 사람 중 정지된 사람은 몇 명인지를 저장하는 정답배열
            int tempCount;  //리폿 수를 임시 저장.
            HashMap<String, HashSet> ids = new HashMap();       //ids는 각각의 id를 가진 사용자 들이 신고한 사람들 목록이 Set으로 있다.(중복제거)
            HashMap<String, Integer> reportCount = new HashMap();   //reportCount에는 각각의 사용자와 신고를 받은 횟수가 있다.
            HashSet<String> suspensions = new HashSet<>();      //suspensions에는 정지된 사용자들의 집합니다.

            for (String s : id_list){ //각각 id에 대해서 ids 와 reportCount를 넣어준다. (초기화)
                HashSet<String> temp = new HashSet<>();
                ids.put(s,temp);
                reportCount.put(s,0);
            }


            String reporterReceiver[];      //누가 reporterReceiver[0] 누구를 reporterReceiver[1]리폿하는지를 담을 스트링배열

            for (String s: report){ //리폿한사람 추가 구문
                reporterReceiver  = s.split(" ");
                HashSet<String> temp = ids.get(reporterReceiver[0]);        //신고자가 신고한 사람들을 Set으로 넣어 중복신고 방지
                temp.add(reporterReceiver[1]);
            }

            for (String s : id_list){   //실제로 중복이 제거되어 누가 누구를 신고했는지의 목옥을 가지고 신고 당한 횟수를 늘려준다.
                HashSet<String> temp = ids.get(s);      //현재 사용자가 신고한 사용자들의 목록

                for (String receiver : temp){       //그 목록을 기준으로 신고 당한 사람의 id를 가져와 reportCount의 신고횟수를 늘려준다.
                    tempCount = reportCount.get(receiver)+1;
                    reportCount.put(receiver,tempCount);
                    if(tempCount>=k){       //리폿 수가 정지횟수 이상이라면 해당 사용자를 suspensions에 추가
                        suspensions.add(receiver);
                    }
                }
            }


            for (int i = 0; i < id_list.length; i++) {      //각 사용자별로 본인이 신고한 사람들의 집합과, 정지된 사용자들의 집합의 교집합 수를 정밥에 넣는다.
                HashSet<String> retain =ids.get(id_list[i]);
                retain.retainAll(suspensions);
                answer[i] = retain.size();
            }

            return answer;
        }
    }
}
