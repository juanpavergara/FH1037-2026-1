package fundamentos.arreglos.algoritmos;

import java.util.Arrays;

public class ArraySorting {

    /**
     * Intercambia (swap) los valores en dos posiciones de un arreglo.
     *
     * Idea clave: muchos algoritmos de ordenamiento necesitan "mover" valores.
     * En lugar de repetir la lógica del intercambio en cada algoritmo, se centraliza aquí
     * para mejorar legibilidad y evitar errores (por ejemplo, pisar un valor sin guardarlo).
     */
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * Demostración de Selection Sort (ordenamiento por selección).
     *
     * Estrategia:
     * - Recorre el arreglo por posiciones (i) de izquierda a derecha.
     * - En cada i, busca el índice del menor elemento en el rango [i..fin].
     * - Coloca ese mínimo en la posición i usando swap.
     *
     * Propiedad importante: al terminar cada iteración i, la porción [0..i] queda ordenada.
     */
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            if (minIdx != i) {
                swap(arr, i, minIdx);
            }
        }
    }

    /**
     * Demostración de Bubble Sort (ordenamiento burbuja).
     *
     * Estrategia:
     * - Repite pasadas sobre el arreglo.
     * - En cada pasada, compara pares adyacentes y los intercambia si están en orden incorrecto.
     * - El elemento más grande "burbujea" hacia el final en cada pasada.
     *
     * Optimización didáctica:
     * - Si en una pasada no hubo intercambios, el arreglo ya está ordenado y se termina temprano.
     */
    public static void bubbleSort(int[] arr) {
        for (int pass = 0; pass < arr.length - 1; pass++) {
            boolean swapped = false;

            for (int j = 0; j < arr.length - 1 - pass; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }

            if (!swapped) {
                return;
            }
        }
    }

    private static void printCase(String label, int[] original, int[] sorted) {
        System.out.println(label);
        System.out.println("Antes:  " + Arrays.toString(original));
        System.out.println("Después:" + Arrays.toString(sorted));
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] casos = {
                {64, 25, 12, 22, 11},
                {5, 4, 3, 2, 1},
                {1, 2, 3, 4, 5},
                {3, 1, 2, 3, 0, 2}
        };

        System.out.println("=== Pruebas de Selection Sort ===");
        for (int i = 0; i < casos.length; i++) {
            int[] original = casos[i].clone();
            int[] arr = casos[i].clone();
            selectionSort(arr);
            printCase("Caso " + (i + 1), original, arr);
        }

        System.out.println("=== Pruebas de Bubble Sort ===");
        for (int i = 0; i < casos.length; i++) {
            int[] original = casos[i].clone();
            int[] arr = casos[i].clone();
            bubbleSort(arr);
            printCase("Caso " + (i + 1), original, arr);
        }
    }
}

