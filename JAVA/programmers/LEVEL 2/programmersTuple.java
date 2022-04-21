
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class programmersTuple {

    public static int[] solution(String s) {
        s = s.substring(1,s.length()-1);
        int[] answer = {};
        ArrayList<Integer> answerArrayList = new ArrayList<>();
        String array[] = s.split("},");
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i].replace("{","");
        }
        array[array.length-1] =array[array.length-1].replace("}","");

        Arrays.sort(array, new Comparator<String>(){        //원소수를 기준으로 정렬
            @Override
            public int compare(String o1,String o2){
                return o1.length() - o2.length();
            }
        });


        HashSet<Integer> hashArray[] = new HashSet[array.length];
        HashSet<Integer> tempSet = new HashSet<>(); //기존 순서를 담은 Set

        for (int i = 0; i < hashArray.length; i++) {
            hashArray[i] = new HashSet<>();
            String temp[] = array[i].split(",");
            for (String s1 : temp) {
                hashArray[i].add(Integer.parseInt(s1));
            }
            if(i>0){
                hashArray[i].removeAll(tempSet);
                for (int key : hashArray[i]) {
                    answerArrayList.add(key);
                    tempSet.add(key);
                }
            }else{
                for (int key : hashArray[i]) {
                    answerArrayList.add(key);
                    tempSet.add(key);
                }
            }
        }
        answer = new int[answerArrayList.size()];
        int i=0;
        for (Integer integer : answerArrayList) {
            answer[i++] = integer;
        }


        return answer;
    }
    public static void main(String[] args) {
        String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
        System.out.println(Arrays.toString(solution(s)));
    }
}
