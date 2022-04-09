public class ProgrammersStealing{
    public int solution(int[] money) {
        int [][]dpFirstOn= new int[money.length][2];
        int [][]dpFirstOff= new int[money.length][2];
        
        dpFirstOn[0][1]= money[0];    //첫집 설치 
        
        for(int i=1;i<money.length;i++){
            
            dpFirstOn[i][0] = (dpFirstOn[i-1][0]>dpFirstOn[i-1][1])?dpFirstOn[i-1][0]:dpFirstOn[i-1][1];
            dpFirstOn[i][1] = money[i] + dpFirstOn[i-1][0];
            dpFirstOff[i][0] = (dpFirstOff[i-1][0]>dpFirstOff[i-1][1])?dpFirstOff[i-1][0]:dpFirstOff[i-1][1];
            dpFirstOff[i][1] = money[i] + dpFirstOff[i-1][0];
        }
        int temp = (dpFirstOff[money.length-1][0]>dpFirstOff[money.length-1][1])?dpFirstOff[money.length-1][0]:dpFirstOff[money.length-1][1];
        temp = (temp>dpFirstOn[money.length-1][0])? temp : dpFirstOn[money.length-1][0];
        return temp;
    }
}
