import java.util.HashMap;
import java.util.Locale;

public class programmersNewsClustering {
    public static int solution(String str1, String str2) {
        int answer = 0,intersection =0, aggregation =0;
        double jaccard;
        str1 = str1.toUpperCase(Locale.ROOT);
        str2 = str2.toUpperCase(Locale.ROOT);


        HashMap<String,Integer> newsSet1 = new HashMap<>();
        HashMap<String,Integer> newsSet2 = new HashMap<>();

        putMap(str1,newsSet1);
        putMap(str2,newsSet2);

        for (String s: newsSet1.keySet()){
            if(newsSet2.containsKey(s)){    //같이 가지고 있는경우
                intersection += Math.min(newsSet1.get(s),newsSet2.get(s));
                aggregation += Math.max(newsSet1.get(s),newsSet2.get(s));
            }else{
                aggregation +=newsSet1.get(s);
            }
        }
        for (String s: newsSet2.keySet()){
            if(!newsSet1.containsKey(s)){
                aggregation += newsSet2.get(s);
            }
        }

        if(aggregation==0) jaccard = 1;
        else jaccard = intersection/(double)aggregation;

        answer = (int)(jaccard*65536);

        return answer;
    }
    public static void putMap(String str, HashMap<String,Integer> newsSet){
        String temp;
        int count;
        for(int i=0;i<str.length()-1;i++){
            temp = str.substring(i,i+2);
            temp = temp.replaceAll("[^A-Z]","");
            if(temp.length()<2) continue;
            count = newsSet.getOrDefault(temp,0);
            newsSet.put(temp,++count);
        }
    }

    public static void main(String[] args) {
        System.out.println(solution("FRANCE","french"));
    }
}
