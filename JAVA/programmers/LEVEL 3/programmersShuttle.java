import java.util.Arrays;

public class programmersShuttle {
    public static String solution(int n, int t, int m, String[] timetable) {
        int startTime = 540, endTime = 1080, lastSuttle = startTime + (n-1)*t, allSpace = n*m;
        int timeTemp,shuttleTemp, crewIdx=0, j;
        int[] crew = new int[timetable.length];


        for (int i = 0; i < crew.length; i++) {
            String[] temp = timetable[i].split(":");
            crew[i] = Integer.parseInt(temp[0])*60+Integer.parseInt(temp[1]);
        }
        Arrays.sort(crew);

        for(int i=0;i<n;i++){
            shuttleTemp = startTime + i*t;
            System.out.println(shuttleTemp);
            boolean rest = false;

            for(j=0;j<m&&crewIdx<timetable.length&&crew[crewIdx]<=shuttleTemp;j++){   //현재시간까지, 최대 m명 태움
                crewIdx++;
            }

            if(i==n-1){
                if(j!=m){   //덜 태웠다면.
                    return getTimeToString(lastSuttle);
                }
            }
            System.out.println(crewIdx);
        }


        if(crewIdx!=0)
            timeTemp = crew[crewIdx-1];
        else
            timeTemp = lastSuttle;

        if(timeTemp>lastSuttle){
            timeTemp = lastSuttle;
        }else{
            timeTemp--;
        }

        return getTimeToString(timeTemp);
    }

    public static String getTimeToString(int time){
        String stringTime="";
        if(time%60<10){
            stringTime += "0"+(time%60);
        }else{
            stringTime += (time%60);
        }
        if(time/60<10){
            stringTime = "0"+(time/60)+":"+stringTime;
        }else{
            stringTime = (time/60)+":"+stringTime;
        }
        return stringTime;
    }


    public static void main(String[] args) {
        String[] timeTable = {"08:00", "08:01", "08:02", "08:03"};
        System.out.println(solution(1,1,5,timeTable));
    }
}
