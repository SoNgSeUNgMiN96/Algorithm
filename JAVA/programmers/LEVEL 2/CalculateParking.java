import java.util.*;
public class CalculateParking {
    public int getCost(int[] fees, int minutes){
        if(fees[0]>=minutes){//기본요금
            return fees[1];
        }
        return ((int)Math.ceil((minutes-fees[0])/(double)fees[2]))*fees[3]+fees[1];
    }
    public int[] solution(int[] fees, String[] records) {
        int[] answer;
        HashMap<String,Integer> result = new HashMap<>();   //누적시간.
        HashMap<String,Integer> parking = new HashMap<>();      //단위시간
        StringTokenizer st;
        ArrayList<String> keyArray = new ArrayList<>();
        String[] timeSp;
        String number;
        int in,out, time;
        for(String record: records){
            st = new StringTokenizer(record);
            timeSp = st.nextToken().split(":");
            number = st.nextToken();

            if(parking.containsKey(number)){

                in = parking.get(number);
                out = Integer.parseInt(timeSp[0])*60+Integer.parseInt(timeSp[1]);
                if(result.containsKey(number)){
                    result.put(number,out-in+result.get(number));
                }else{
                    keyArray.add(number);
                    result.put(number,out-in);
                }
                parking.remove(number);
            }else{
                time = Integer.parseInt(timeSp[0])*60+Integer.parseInt(timeSp[1]);
                parking.put(number,time);
            }
        }

        for(String key: parking.keySet()){
            in = parking.get(key);
            out = 23*60+59;
            if(result.containsKey(key)){
                result.put(key,out-in+result.get(key));
            }else{
                keyArray.add(key);
                result.put(key,out-in);
            }
        }

        Collections.sort(keyArray,(s1,s2)->s1.compareTo(s2));
        answer = new int[keyArray.size()];
        for(int i=0;i<keyArray.size();i++){
            answer[i] = getCost(fees,result.get(keyArray.get(i)));
        }

        return answer;
    }
}
