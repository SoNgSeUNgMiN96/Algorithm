import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class programmersFormulaMaximization {


    public static long getOne(ArrayList formula){
        long number = Long.valueOf(String.valueOf(formula.get(0)));
        int j=1;
        ArrayList formulaCpyOne = new ArrayList();
        formulaCpyOne = (ArrayList)formula.clone();
        //System.out.println(Arrays.toString(formula));

        while (j<formulaCpyOne.size()){
            if((char)formulaCpyOne.get(j)=='+') {
                number += Long.valueOf(String.valueOf(formulaCpyOne.get(j+1)));
            }else if((char)formula.get(j)=='-') {
                number -= Long.valueOf(String.valueOf(formulaCpyOne.get(j+1)));
            }else{
                number *= Long.valueOf(String.valueOf(formulaCpyOne.get(j+1)));
            }
            j+=2;
        }
        return Math.abs(number);
    }

    public static long getTwo(ArrayList formula, char[] symbolArray) {

        ArrayList formulaCpyTwo = new ArrayList();
        formulaCpyTwo = (ArrayList)formula.clone();

        long number, nextNum=0, preNum=0;
        int j=1;



        //우선순위 연산자 1 번만 먼저 다 계산한다.
        while (j<formulaCpyTwo.size()){

            if((char)formulaCpyTwo.get(j)==symbolArray[0]) {
                nextNum = Long.valueOf(String.valueOf(formulaCpyTwo.get(j+1)));
                preNum = Long.valueOf(String.valueOf(formulaCpyTwo.get(j-1)));
                if(symbolArray[0]=='-'){
                    formulaCpyTwo.set(j-1,preNum- nextNum);
                    formulaCpyTwo.remove(j);
                    formulaCpyTwo.remove(j);
                }
                else if(symbolArray[0]=='+'){
                    formulaCpyTwo.set(j-1,nextNum+preNum);
                    formulaCpyTwo.remove(j);
                    formulaCpyTwo.remove(j);
                }
                else{
                    formulaCpyTwo.set(j-1,nextNum*preNum);
                    formulaCpyTwo.remove(j);
                    formulaCpyTwo.remove(j);
                }
            }
            else j+=2;
        }


        number = getOne(formulaCpyTwo);


        formulaCpyTwo = new ArrayList();
        formulaCpyTwo = (ArrayList)formula.clone();
        j=1;

        while (j<formulaCpyTwo.size()){
            if((char)formulaCpyTwo.get(j)==symbolArray[1]) {
                nextNum = Long.valueOf(String.valueOf(formulaCpyTwo.get(j+1)));
                preNum = Long.valueOf(String.valueOf(formulaCpyTwo.get(j-1)));
                if(symbolArray[1]=='-'){
                    formulaCpyTwo.set(j-1,preNum - nextNum);
                    formulaCpyTwo.remove(j);
                    formulaCpyTwo.remove(j);
                }
                else if(symbolArray[1]=='+'){
                    formulaCpyTwo.set(j-1,nextNum+preNum);
                    formulaCpyTwo.remove(j);
                    formulaCpyTwo.remove(j);
                }
                else{
                    formulaCpyTwo.set(j-1,nextNum*preNum);
                    formulaCpyTwo.remove(j);
                    formulaCpyTwo.remove(j);
                }
            }
            else j+=2;
        }


        return number = Math.max(number, getOne(formulaCpyTwo));

    }
    private static long getThree(ArrayList formula, char[] symbolArray) {




        ArrayList formulaCpy = new ArrayList();
        formulaCpy = (ArrayList)formula.clone();

        long number, nextNum=0,preNum=0;
        int j=1;
        //getTwo에 쓸 2칸짜리 symbol
        char [] symbolArrayTwo = new char[2];



        //우선순위 연산자 1 번만 먼저 다 계산한다.
        while (j<formulaCpy.size()){
            if((char)formulaCpy.get(j)==symbolArray[0]) {
                nextNum = Long.valueOf(String.valueOf(formulaCpy.get(j+1)));
                preNum = Long.valueOf(String.valueOf(formulaCpy.get(j-1)));
                if(symbolArray[0]=='-'){
                    formulaCpy.set(j-1,preNum- nextNum);
                    formulaCpy.remove(j);
                    formulaCpy.remove(j);
                }
                else if(symbolArray[0]=='+'){
                    formulaCpy.set(j-1,nextNum+preNum);
                    formulaCpy.remove(j);
                    formulaCpy.remove(j);
                }
                else{
                    formulaCpy.set(j-1,nextNum*preNum);
                    formulaCpy.remove(j);
                    formulaCpy.remove(j);
                }
            }else j+=2;
        }

        symbolArrayTwo[0] = symbolArray[1];
        symbolArrayTwo[1] = symbolArray[2];
        number = getTwo(formulaCpy, symbolArrayTwo);


        formulaCpy = new ArrayList();
        formulaCpy = (ArrayList)formula.clone();
        j=1;

        while (j<formulaCpy.size()){
            if((char)formulaCpy.get(j)==symbolArray[1]) {
                nextNum = Long.valueOf(String.valueOf(formulaCpy.get(j+1)));
                preNum = Long.valueOf(String.valueOf(formulaCpy.get(j-1)));
                if(symbolArray[1]=='-'){
                    formulaCpy.set(j-1,preNum - nextNum);
                    formulaCpy.remove(j);
                    formulaCpy.remove(j);
                }
                else if(symbolArray[1]=='+'){
                    formulaCpy.set(j-1,nextNum+preNum);
                    formulaCpy.remove(j);
                    formulaCpy.remove(j);
                }
                else{
                    formulaCpy.set(j-1,nextNum*preNum);
                    formulaCpy.remove(j);
                    formulaCpy.remove(j);
                }
            }
            else j+=2;
        }


        symbolArrayTwo[0] = symbolArray[0];
        symbolArrayTwo[1] = symbolArray[2];

        number = Math.max(number, getTwo(formulaCpy,symbolArrayTwo));


        formulaCpy = new ArrayList();
        formulaCpy = (ArrayList)formula.clone();
        j=1;

        while (j<formulaCpy.size()){
            if((char)formulaCpy.get(j)==symbolArray[2]) {
                nextNum = Long.valueOf(String.valueOf(formulaCpy.get(j+1)));
                preNum = Long.valueOf(String.valueOf(formulaCpy.get(j-1)));
                if(symbolArray[2]=='-'){
                    formulaCpy.set(j-1,preNum - nextNum);
                    formulaCpy.remove(j);
                    formulaCpy.remove(j);
                }
                else if(symbolArray[2]=='+'){
                    formulaCpy.set(j-1,nextNum+preNum);
                    formulaCpy.remove(j);
                    formulaCpy.remove(j);
                }
                else{
                    formulaCpy.set(j-1,nextNum*preNum);
                    formulaCpy.remove(j);
                    formulaCpy.remove(j);
                }
            }
            else j+=2;
        }

        symbolArrayTwo[0] = symbolArray[0];
        symbolArrayTwo[1] = symbolArray[1];
        return number = Math.max(number, getTwo(formulaCpy,symbolArrayTwo));
    }



    public static long solution(String expression) {
        long answer = 0;
        int i=0 ,tempNum=0;
        ArrayList formula = new ArrayList();
        HashSet<Character> symbol = new HashSet<>();

        while (i<expression.length()){
            tempNum = 0;
            while (i<expression.length()&&expression.charAt(i)>='0'&&expression.charAt(i)<='9'){//숫자인동안
                tempNum *=10;
                tempNum += (expression.charAt(i) - '0');
                i++;
            }
            formula.add(tempNum);
            if(i<expression.length()) {
                formula.add(expression.charAt(i));
                symbol.add(expression.charAt(i));
            }
            i++;
        }

        i=0;

        if(symbol.size()==1){
            return getOne(formula);
        }

        char symbolArray[] = new char[symbol.size()];

        for (Character character : symbol) {
            symbolArray[i++] = character;
        }

        if (symbol.size()==2){
            return getTwo(formula,symbolArray);
        }

        return getThree(formula,symbolArray);
    }




    public static void main(String[] args) {
        String s = "200-300-500-600*40+500+500";
        System.out.println(solution("177-661*999*99-133+221+334+555-166-144-551-166*166-166*166-133*88*55-11*4+55*888*454*12+11-66+444*99"));
    }
}
