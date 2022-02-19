public class programmersSecretMap1 {

    public static void main(String[] args) {
        int n=5;
        int [] arr1 = {9, 20, 28, 18, 11};
        int [] arr2 = {30, 1, 21, 17, 28};
        String answer[] = solution(n,arr1,arr2);
        for (int i = 0; i < answer.length; i++) {
            System.out.println(answer[i]);
        }
    }
    static public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = "";
        }
        int map [][] = new int[n][n];
        int a,b, index;
        //anwer 맵 초기화

        for (int i = 0; i < arr1.length; i++) {
            a = arr1[i];
            b = arr2[i];
            index = n -1;
            while ((a!=0 || b!=0)&&index>=0){
                if(a%2==1||b%2==1)
                    map[i][index] = 1;
                a /= 2;
                b /= 2;
                index --;
            }
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if(map[i][j]==1) answer[i] += "#";
                else answer[i] += " ";
            }
        }

        return answer;
    }
}
