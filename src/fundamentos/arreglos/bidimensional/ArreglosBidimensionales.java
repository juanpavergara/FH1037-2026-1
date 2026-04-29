package fundamentos.arreglos.bidimensional;

import java.util.Random;
import java.util.Scanner;

public class ArreglosBidimensionales {

    public static void main(String[] args) {
        System.out.println("=== Arreglos Bidimensionales (int[][]) ===");

        int[][] matriz = crearArregloBidimensionalInt();
        System.out.println();

        System.out.println("Matriz de ejemplo (impresa por filas):");
        imprimirPorFilas(matriz);
        System.out.println();

        int[][] matrizConNew = crearArregloBidimensionalIntConNew();
        System.out.println("Matriz creada con new (impresa por filas):");
        imprimirPorFilas(matrizConNew);
        System.out.println();

        int[][] matrizEjercicio1 = crearMatriz3x3Del1Al9();
        System.out.println("Ejercicio 1: Matriz 3x3 con números del 1 al 9:");
        imprimirPorFilas(matrizEjercicio1);
        System.out.println();

        int n = 4;
        int[][] matrizEjercicio2 = crearMatrizNxNDel1AlNN(n);
        System.out.println("Ejercicio 2: Matriz " + n + "x" + n + " con números del 1 al " + (n * n) + ":");
        imprimirPorFilas(matrizEjercicio2);
        System.out.println();

        recorrerPorFilas(matriz);
        System.out.println();

        recorrerPorColumnas(matriz);
        System.out.println();

        int[][] matrizEjercicio3 = crearMatrizAleatoria0a10DesdeUsuario();
        System.out.println("Ejercicio 3: Matriz m x n con aleatorios del 0 al 10:");
        imprimirPorFilas(matrizEjercicio3);
    }

    /**
     * Creación de tipo int (int[][]).
     *
     * Un arreglo bidimensional en Java se representa como un arreglo de arreglos:
     * - int[][] matriz;
     * - Cada "fila" es un int[]
     * - matriz.length = número de filas
     * - matriz[0].length = número de columnas (si es rectangular)
     */
    public static int[][] crearArregloBidimensionalInt() {
        System.out.println("Concepto: Creación de un arreglo bidimensional tipo int (int[][]).");

        // Ejemplo 1: inicialización directa con valores (3 filas x 4 columnas)
        int[][] matriz = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };

        System.out.println("- Filas (matriz.length): " + matriz.length);
        System.out.println("- Columnas (matriz[0].length): " + matriz[0].length);

        // Ejemplo 2 (referencial): crear por tamaño y luego asignar
        // int[][] otra = new int[2][3]; // 2 filas x 3 columnas, inicia con 0
        // otra[0][1] = 99;

        return matriz;
    }

    /**
     * Creación de tipo int (int[][]) usando la palabra clave "new".
     *
     * - new int[filas][columnas] crea una matriz rectangular
     * - Sus valores iniciales son 0
     * - Luego puedes asignar valores con matriz[i][j] = ...
     */
    public static int[][] crearArregloBidimensionalIntConNew() {
        System.out.println("Concepto: Creación de int[][] usando \"new\".");

        int filas = 3;
        int columnas = 4;
        int[][] matriz = new int[filas][columnas];

        // Llenamos la matriz con un patrón sencillo para visualizarlo:
        // valor = (fila * 10) + columna
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = (i * 10) + j;
            }
        }

        System.out.println("- Filas (matriz.length): " + matriz.length);
        System.out.println("- Columnas (matriz[0].length): " + matriz[0].length);

        return matriz;
    }

    /**
     * Ejercicio 1:
     * Crear una matriz de 3x3 con los números del 1 al 9.
     */
    public static int[][] crearMatriz3x3Del1Al9() {
        int[][] matriz = new int[3][3];
        int valor = 1;

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = valor;
                valor++;
            }
        }

        return matriz;
    }

    /**
     * Ejercicio 2:
     * Crear una matriz de n x n con los números del 1 al n x n.
     */
    public static int[][] crearMatrizNxNDel1AlNN(int n) {
        int[][] matriz = new int[n][n];
        int valor = 1;

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = valor;
                valor++;
            }
        }

        return matriz;
    }

    /**
     * Ejercicio 3:
     * Crear una matriz de m filas y n columnas (valores pedidos al usuario) y llenarla con
     * números aleatorios del 0 al 10.
     *
     * Utiliza System.in para pedir datos al usuario.
     */
    public static int[][] crearMatrizAleatoria0a10DesdeUsuario() {
        Scanner scanner = new Scanner(System.in);

        int m = capturarEnteroPositivo(scanner, "Ingresa la cantidad de filas (m): ");
        int n = capturarEnteroPositivo(scanner, "Ingresa la cantidad de columnas (n): ");

        int[][] matriz = new int[m][n];
        Random random = new Random();

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = random.nextInt(11); // 0..10
            }
        }

        return matriz;
    }

    private static int capturarEnteroPositivo(Scanner scanner, String mensaje) {
        int valor;
        do {
            System.out.print(mensaje);
            while (!scanner.hasNextInt()) {
                System.out.print("Por favor ingresa un número entero positivo: ");
                scanner.next();
            }
            valor = scanner.nextInt();
        } while (valor <= 0);

        return valor;
    }

    /**
     * Recorrido por filas:
     * - Iteras primero por filas (i), luego por columnas (j).
     * - Acceso: matriz[i][j]
     */
    public static void recorrerPorFilas(int[][] matriz) {
        System.out.println("Concepto: Recorrido por filas (primero filas, luego columnas).");
        for (int i = 0; i < matriz.length; i++) {
            System.out.print("Fila " + i + ": ");
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j]);
                if (j < matriz[i].length - 1) System.out.print(", ");
            }
            System.out.println();
        }
    }

    /**
     * Recorrido por columnas:
     * - Iteras primero por columnas (j), luego por filas (i).
     * - Requiere asumir matriz rectangular (todas las filas con misma cantidad de columnas).
     */
    public static void recorrerPorColumnas(int[][] matriz) {
        System.out.println("Concepto: Recorrido por columnas (primero columnas, luego filas).");
        int columnas = matriz[0].length; // asumiendo matriz rectangular

        for (int j = 0; j < columnas; j++) {
            System.out.print("Columna " + j + ": ");
            for (int i = 0; i < matriz.length; i++) {
                System.out.print(matriz[i][j]);
                if (i < matriz.length - 1) System.out.print(", ");
            }
            System.out.println();
        }
    }

    private static void imprimirPorFilas(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
    }
}

