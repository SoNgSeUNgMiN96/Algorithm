import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class LierGame {

    public static void main(String[] args) throws IOException {

        String[] list = {"박효신" ,
                "조나단" ,
                "뉴진스" ,
                "르세라핌" ,
                "이병헌" ,
                "이광수" ,
                "이선빈" ,
                "손흥민" ,
                "송중기" ,
                "공유" ,
                "송혜교" ,
                "강동원" ,
                "장원영" ,
                "임영웅" ,
                "차은우" ,
                "송가인" ,
                "이강인" ,
                "꽈뚜룹" ,
                "곽튜브" ,
                "김계란" ,
                "김우빈" ,
                "잇지"};

        Random random = new Random();

        int randomWord = random.nextInt(list.length);


        System.out.print("진행 인원을 입력해주세요 >>");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int lierIdx = random.nextInt(N);

        for (int i = 0; i < N; i++) {
            System.out.print("엔터를 눌러 확인해주세요 >>");
            br.readLine();

            if (i  == lierIdx) {
                System.out.println("라이어 당첨!!");
            } else {
                System.out.println("제시어는 >>"+list[randomWord-1]);
            }


            System.out.print("엔터를 눌러 확인해주세요 >>");
            br.readLine();

            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }






    }
}
