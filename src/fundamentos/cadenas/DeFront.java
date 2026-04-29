package fundamentos.cadenas;

import java.util.Scanner;

public class DeFront {

    public static String transformarStringConArrays(String str) {

        char[] chars = str.toCharArray();
        int newCharLength = chars.length - 2;

        if (chars.length > 0) {
            if (chars[0] == 'a' || chars[0] == 'A') {
                newCharLength++;
            }
            if (chars.length >= 2) {
                if (chars[1] == 'b' || chars[1] == 'B') {
                    newCharLength++;
                }
            }
        }

        char[] newWordChars = new char[newCharLength];

        int j = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (i == 0 && (c == 'A' || c == 'a')) {
                newWordChars[j] = chars[i];
                j++;
            }
            if (i == 1 && (c == 'B' || c == 'b')) {
                newWordChars[j] = chars[i];
                j++;
            }
            if (i != 0 && i != 1) {
                newWordChars[j] = chars[i];
                j++;
            }

        }

        return new String(newWordChars);
    }

    public static String transformarString(String str) {

        char[] l = str.toCharArray();
        String fw = "";

        if (l.length >= 1) {
            if (l[0] == 'A' || l[0] == 'a') {
                fw = fw + l[0];
            }
        }
        if (l.length >= 2) {
            if (l[1] == 'B' || l[1] == 'b') {
                fw = fw + l[1];
            }
        }

        for (int i = 2; i < l.length; i++) {
            fw = fw + l[i];
        }

        return fw;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        //String nuevaPalabra = DeFront.transformarString(str);
        String nuevaPalabra = DeFront.transformarStringConArrays(str);
        System.out.println(nuevaPalabra);
        sc.close();
    }
}

