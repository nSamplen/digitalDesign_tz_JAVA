import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {

    public  static int openBraces = 0;
    public  static int closeBraces = 0;
    public  static ArrayList<Character> resStr;
    public static void main(String[] args) {
        //Написать на Java программу распаковывания строки.
        //На вход поступает строка вида число[строка],
        //на выход - строка, содержащая повторяющиеся подстроки.

        //Вход: 3[xyz]4[xy]z
        //Выход: xyzxyzxyzxyxyxyxyz

        //- одно повторение может содержать другое. Например: 2[3[x]y]  = xxxyxxxy
        //- допустимые символы на вход: латинские буквы, числа и скобки []
        //- числа означают только число повторений
        //- скобки только для обозначения повторяющихся подстрок
        //- входная строка всегда валидна.

        Scanner in = new Scanner(System.in);
        String str = in.nextLine();

        resStr = new ArrayList<>();
        boolean some = validation(str);
        if (!some)
        {
            System.out.println("String is invalid!");
            return;
        }
        char [] charStr = str.toCharArray();
        ArrayList<String> list = new ArrayList<>();
        int i=0;
        while (i<charStr.length){
           // System.out.println(i+") Next = "+charStr[i]);
            if (charStr[i]>='0' && charStr[i]<='9') {
                StringBuilder sb = new StringBuilder();
                while (charStr[i]>='0' && charStr[i]<='9'){
                    sb.append(charStr[i]);
                    i++;
                }
                String num = sb.toString();
                sb.delete(0,sb.length());
                if (charStr[i]=='['){
                    isOpenBracer(charStr[i]);

                    while (openBraces!=0 && i<charStr.length) {
                        i++;
                        isOpenBracer(charStr[i]);
                        isCloseBracer(charStr[i]);
                        if (openBraces != 0)
                            sb.append(charStr[i]);
                    }

                }
                String subBraces = sb.toString();
                sb.delete(0,sb.length());
               // System.out.println("SUBST:\n"+num+" [ "+subBraces+" ] ");
                String somRez = subStrParse(subBraces);
                for (int k=0; k<Integer.parseInt(num); k++){
                    sb.append(somRez);
                }
                char[] charSome = sb.toString().toCharArray();
                int k=0;
                while (k<charSome.length){
                    resStr.add(charSome[k]);
                    k++;
                }

            }
            else
            if ( i<charStr.length && isLetter(charStr[i])) {

                while ( i<charStr.length && isLetter(charStr[i])){

                    resStr.add(charStr[i]);
                    i++;
                }
                i--;

            }
            i++;
        }
        for (Character c : resStr ) {
            System.out.print(c);
        }

    }

    public static boolean validation(String s) {

        String str = "[a-zA-Z]+";
        String reg1 = "(" + "\\d+" + "\\[" + str + "\\]" + ")+"; //3[xyz]
        String reg2 = "(" + str + ")+";                    //xyz
        String reg3 = "(" + reg1 + "|" + reg2 + ")+";

        if (s.matches("(" +
                reg1 +
                "|" +
                reg2 +
                "|" +
                "(" + "\\d+" + "\\[" + reg3 + "\\]" + ")+" +
                ")+"))
        {
            //System.out.println("OK");
            return true;
        }
        else {
            //System.out.println("NO");
            return false;
        }
    }

    public static void isOpenBracer(char c){
        if (c == '['){
            openBraces++;
        }
    }
    public static void isCloseBracer(char c){
        if (c == ']'){
            openBraces--;
        }
    }

    public static boolean isLetter(char c){
        if ((c>='a'&& c<='z') || (c>='A'&& c<='Z')) {
            return true;
        }
        return false;
    }

    public static String subStrParse(String str){

        boolean some = validation(str);
        if (!some){
            System.out.println("String is invalid");
            return "";
        }
        char [] charStr = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        int i=0;
        while (i<charStr.length){
          //  System.out.println("\t"+i+") Next = "+charStr[i]);
            if (charStr[i]>='0' && charStr[i]<='9') {
                StringBuilder sbTemp = new StringBuilder();
                while (charStr[i]>='0' && charStr[i]<='9'){
                    sbTemp.append(charStr[i]);
                    i++;
                }
                String num = sbTemp.toString();
                sbTemp.delete(0,sbTemp.length());
                if (charStr[i]=='['){
                    isOpenBracer(charStr[i]);

                    while (openBraces!=0 && i<charStr.length) {
                        i++;
                        isOpenBracer(charStr[i]);
                        isCloseBracer(charStr[i]);
                        if (openBraces != 0)
                            sbTemp.append(charStr[i]);
                    }
                    // i++;

                }
                String subBraces = sbTemp.toString();
                sbTemp.delete(0,sbTemp.length());
             //   System.out.println("SUBST:\n"+num+" [ "+subBraces+" ] ");
                String somRez = subStrParse(subBraces);
                for (int k=0; k<Integer.parseInt(num); k++) {
                    sb.append(somRez);
                }

            }
            else
            if ( i<charStr.length && isLetter(charStr[i])) {

                while ( i<charStr.length && isLetter(charStr[i])){
                    sb.append(charStr[i]);
                    i++;
                }
                i--;
            }
            i++;
        }
        return sb.toString();
    }

}
