import java.util.*;
import java.util.regex.Pattern;


public class programmersFileSort {

    public static String[] solution(String[] files) {

        Arrays.sort(files, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String head1="", head2="", numStr1="", numStr2="", temp;
                int num1, num2, i=0, j=0;
                HashMap<String,Integer> LZWDic = new HashMap<>();

                o1 =  o1.toUpperCase(Locale.ROOT);
                o2 =  o2.toUpperCase(Locale.ROOT);

                for (i=0;i<o1.length();i++){
                    temp = o1.substring(0,i+1);
                    if(!Pattern.matches("^[a-zA-Z.\\-\\s]*$", temp)) break;
                    head1 = temp;
                }
                System.out.println(head1);

                for(j=i;j<o1.length();j++){
                    temp = o1.substring(i,j+1);
                    if(!Pattern.matches("^[0-9]*$", temp)) break;
                    numStr1=temp;
                }

                num1 =  Integer.parseInt(numStr1);


                for (i=0;i<o2.length();i++){
                    temp = o2.substring(0,i+1);
                    if(!Pattern.matches("^[a-zA-Z.\\-\s]*$", temp)) break;
                    head2 = temp;
                }

                System.out.println(head2);

                for(j=i;j<o2.length();j++){
                    temp = o2.substring(i,j+1);
                    if(!Pattern.matches("^[0-9]*$", temp)) break;
                    numStr2=temp;
                }

                num2 =  Integer.parseInt(numStr2);

                if(head1.compareTo(head2)==0) return num1 -num2  ;
                return head1.compareTo(head2);
            }
        });
        return files;
    }

    public static void main(String[] args) {
        String[] files  = {"img-. -12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        System.out.println(solution(files));
    }


}



/*

//이 버전은 정규식 없이 char비교연산으로 푼 풀이
import java.util.*;
import java.util.regex.Pattern;


class Solution {
   public static String[] solution(String[] files) {

        Arrays.sort(files, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String head1="", head2="", numStr1="", numStr2="", temp;
                char a;
                int num1, num2, i=0, j=0;

                o1 =  o1.toUpperCase(Locale.ROOT);
                o2 =  o2.toUpperCase(Locale.ROOT);

                for (i=0;i<o1.length();i++){
                    a=o1.charAt(i);
                    if(a>='a'&&a<='z'||a>='A'&&a<='Z'||a=='.'||a=='-'||a==' ')
                        head1 +=a;
                    else break;
                }
                //System.out.println(head1);

                for(j=i;j<o1.length();j++){
                    temp = o1.substring(i,j+1);
                    if(!Pattern.matches("\\d*", temp)) break;
                    numStr1=temp;
                }

                num1 =  Integer.parseInt(numStr1);


                for (i=0;i<o2.length();i++){
                    a=o2.charAt(i);
                    if(a>='a'&&a<='z'||a>='A'&&a<='Z'||a=='.'||a=='-'||a==' ')
                        head2 +=a;
                    else break;
                }

               // System.out.println(head2);

                for(j=i;j<o2.length();j++){
                    temp = o2.substring(i,j+1);
                    if(!Pattern.matches("^[0-9]*$", temp)) break;
                    numStr2=temp;
                }

                num2 =  Integer.parseInt(numStr2);

                if(head1.compareTo(head2)==0) return num1 -num2  ;
                return head1.compareTo(head2);
            }
        });

        return files;
    }
}
 */
