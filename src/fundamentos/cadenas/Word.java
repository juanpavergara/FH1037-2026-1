package fundamentos.cadenas;

import java.util.Scanner;

public class Word {
    public static int[] posicionA(String palabra) {

        char[] chars = palabra.toCharArray();

        int[] resultado = new int[2];

        resultado[0] = chars.length;
        resultado[1] = -1;


        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'a' || chars[i] == 'A') {
                resultado[1] = i;
                break;
            }
        }

        return resultado;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String palabra = sc.nextLine();
        int[] r = Word.posicionA(palabra);
        System.out.println(r[0]);
        System.out.println(r[1]);
        sc.close();
    }
}

