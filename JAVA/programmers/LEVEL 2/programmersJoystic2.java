public class programmersJoystic2 {

    //가장 긴 A구간을 빼고 계산한다!!
    public static int solution(String name) {
        int length = name.length();
        int end= length-1, answer=0, min=Integer.MAX_VALUE,temp;
        int left,right, max = 0,leftA=length,rightA=length;

        for (int i = 0; i < name.length(); i++) {

            left = i;
            while (i<name.length()&&name.charAt(i)=='A') i++;
            right = i;

            if(max<right-left) {    //지금이 가장 긴 구간이라면?
                max = right-left;
                leftA = left-1;
                rightA = right;
            }
        }

        if(leftA<0){   //왼쪽만  A로 도배인경우
            answer += (length - rightA);
        }else{
            while (end>=0&&name.charAt(end)=='A') end--;
            min = Math.min(min,end);
            temp = (leftA<length-rightA)? 2*leftA + length -rightA : (length-rightA)*2 + leftA;
            min = Math.min(min,temp);
            answer += min;
        }

        for (int i = 0; i < name.length(); i++) {
             temp = name.charAt(i)-'A';
             answer += (temp>13)? 26-temp : temp;
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("JEROEN"));
    }
}

