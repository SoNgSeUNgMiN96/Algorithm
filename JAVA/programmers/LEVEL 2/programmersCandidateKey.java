import java.util.HashSet;
import java.awt.Point;

public class programmersCandidateKey {
    public boolean getKey(String [][]relation, String candidateKeyBit,HashSet<String> candidateKeySet ){
        HashSet<String> isKey = new HashSet<>();
        String candidateKey = "";

        for (int i = 0; i < relation.length; i++) {
            String temp = "";
            //키 튜플 대로 넣어본다.
            for (int j = 0; j < candidateKeyBit.length(); j++) {
                if(candidateKeyBit.charAt(j)=='1'){
                    temp += relation[i][j];
                }
            }
            //이게 겹치면? 키가 아님. 실패.
            if(isKey.contains(temp)){
                return false;
            }else isKey.add(temp);
        }
        //모든 데이터가 안 겹쳤다면, 최소 키인지를 검사. 이미 자신의 상위키는 실패한것이다.
        boolean success = true;

        for (int i = 0; i < candidateKeyBit.length(); i++) {
            //활성키 하나를 지우고 실행해본다.
            if(candidateKeyBit.charAt(i)=='1'){
                String newKey = candidateKeyBit.substring(0,i) + '0' +candidateKeyBit.substring(i+1);
                //자기의 부분키가 있다면 실패.
                if(getKey(relation,newKey,candidateKeySet)){
                    success = false;
                }
            }
        }
        Point a = new Point(1,1);
        System.out.println(a.x);
        //이건 최소키라는 의미이니 추가한다. 나의 자식을 포함해서 나는 키이다.
        if(success){
            candidateKeySet.add(candidateKeyBit);
        }
        return true;
    }

    public int solution(String[][] relation) {


        int answer = 0, colNum = relation[0].length, tupleNum = relation.length;
        String candidateKeyBit = "";
        HashSet<String> candidateKeySet = new HashSet<>();

        //키로 다룰 비트마스킹 초기엔 모든 키를 다 넣는다.
        for (int i = 0; i < colNum; i++) {
            candidateKeyBit += "1";
        }

        getKey(relation,candidateKeyBit,candidateKeySet);
        answer = candidateKeySet.size();
        return answer;
    }
}
