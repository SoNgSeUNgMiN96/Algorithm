import java.sql.Struct;
import java.util.Arrays;
import java.util.Locale;

public class programmersJoystic {

    public static int temp;
    public static int answer = Integer.MAX_VALUE;

    public static int solution(String name) {
        int i=0,right,left,disRight=0,disLeft=0, change;
        char[] first = new char[name.length()];

        for(i=0;i<name.length();i++){
            first[i] = 'A';
        }

        dfs(name,first,false,0,0);
        dfs(name,first,true,0,0);
        return answer;
    }
    public static void dfs(String name, char[] first, boolean isRight, int index, int count){

        int tempIdx =index , realIdx = index, tempCount = count, change;

        if(isRight){
            while (tempIdx - index<name.length()&&name.charAt(realIdx)==first[realIdx]){
                tempIdx++;
                realIdx = tempIdx % name.length();
            }
            if(tempIdx - index ==name.length()){
                answer = Math.min(answer,count);
                return;
            }else{
                tempCount += (tempIdx-index);
                change = Math.abs(first[realIdx]-name.charAt(realIdx));
                if(change>13){
                    change = 26 - change;
                }
                tempCount += change;
                first[realIdx] = name.charAt(realIdx);
                dfs(name, first,true,realIdx,tempCount);
                dfs(name, first,false,realIdx,tempCount );
                first[realIdx] ='A';
            }
        }else{
            while (index-tempIdx <name.length()&&name.charAt(realIdx)==first[realIdx]){
                tempIdx--;
                realIdx =tempIdx<0?name.length()+ tempIdx: tempIdx;
            }
            if(index-tempIdx==name.length()){       //이건 바꾼게 없다는 의미
                if(count ==0) return;
                answer = Math.min(answer,count);

                return;
            }else{
                tempCount += (index-tempIdx);
                change = Math.abs(first[realIdx]-name.charAt(realIdx));
                if(change>13){
                    change = 26 - change;
                }
                tempCount += change;
                first[realIdx] = name.charAt(realIdx);
                dfs(name, first,true,realIdx,tempCount);
                dfs(name, first,false,realIdx,tempCount );
                first[realIdx] ='A';
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(solution("JEROEN"));
    }
}
