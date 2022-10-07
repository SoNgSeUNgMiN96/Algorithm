import java.util.Arrays;

public class indexTree {

    static int[] indexTree;
    public static void main(String[] args) {
        int[] arr =  {3,7,8,10,4,12,31,1,5};
        int tempN = getTempN(arr.length);
        indexTree = new int[tempN*2];


        for (int i = 0; i < arr.length; i++) {
            indexTree[tempN+i] = arr[i];
        }


        //여기가 init
        for (int i = 0; i < tempN; i++) {
            int tempIdx = tempN + i;
            int temp = indexTree[tempIdx];
            while (tempIdx>1){  //인덱스가 1이상일때 까지
                tempIdx /=2;
                indexTree[tempIdx] += temp;
            }
        }

        //특정 구간의 합을 알고싶어요. Data =  0~ 8 개위치  -> tempN + 0 ~ tempN + 8;  구간이 있겠죠 ??
        int sectorStart = 3, sectorEnd = 6;         //2~6을 구하고 싶어요

        int rootStart = 0, rootEnd = 15;

        int sum = getSum(rootStart,rootEnd,sectorStart,sectorEnd,1);
        System.out.println(Arrays.toString(indexTree));
        System.out.println(sum);


        //요소를 바꾸고 들어가는 부분. 만약에 4번 데이터를 30으로 바꿔라.
        int diff = (30 - arr[4] );  //이건
        arr[4] = 30;
        //indexTree[4+tempN] = 30;
        int tempIdx = 4+tempN;

        while (tempIdx>0){
            indexTree[tempIdx] +=diff;
            tempIdx /=2;
        }

        System.out.println(Arrays.toString(indexTree));
    }

    private static int getSum(int rootStart, int rootEnd, int sectorStart, int sectorEnd, int currIdx) {
        if (rootStart >= sectorStart && rootEnd <= sectorEnd) {     //내가 검증하려는 구간이 현재 나의 구간을 포함한경우.
            return indexTree[currIdx];
        }else if(rootEnd<sectorStart||rootStart>sectorEnd){     //내영역과 상관이 없는 경우
            return 0;
        }else{      //내가 검증영역보다 큰 경우.
            int leftSum = getSum(rootStart, (rootStart + rootEnd) / 2, sectorStart, sectorEnd, currIdx * 2);
            int rightSum = getSum((rootStart + rootEnd) / 2 + 1, rootEnd, sectorStart, sectorEnd, currIdx * 2 + 1);
            return leftSum + rightSum;
        }
    }

    private static int getTempN(int length) {
        int i=1;
        while (length>i){
            i *=2;
        }
        return i;
    }
}
