package fundamentos.arreglos.algoritmos;

import java.util.Arrays;

public class ArraySearch {

    /**
     * Demostración de búsqueda lineal (secuencial).
     *
     * Idea:
     * - Recorre el arreglo desde el inicio hasta el final.
     * - Compara cada elemento con el valor buscado.
     * - Si lo encuentra, retorna su índice; si no, retorna -1.
     *
     * Cuándo usarla:
     * - Cuando el arreglo NO está ordenado.
     * - Cuando el arreglo es pequeño o no vale la pena ordenarlo.
     *
     * Complejidad:
     * - Tiempo: O(n) en el peor caso (n = tamaño del arreglo).
     * - Espacio: O(1).
     */
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Demostración de búsqueda binaria.
     *
     * Requisito clave:
     * - El arreglo debe estar ORDENADO de menor a mayor.
     *
     * Idea:
     * - Mantiene un rango [left..right] donde el target podría estar.
     * - Calcula el punto medio (mid) y compara arr[mid] con target.
     * - Si arr[mid] es menor, descarta la mitad izquierda (left = mid + 1).
     * - Si arr[mid] es mayor, descarta la mitad derecha (right = mid - 1).
     * - Repite hasta encontrar el elemento o hasta que el rango se vacíe.
     *
     * Complejidad:
     * - Tiempo: O(log n) en el peor caso (siempre divide el rango en 2).
     * - Espacio: O(1) (versión iterativa).
     */
    public static int binarySearch(int[] sortedArr, int target) {
        int left = 0;
        int right = sortedArr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // evita overflow en (left + right)

            if (sortedArr[mid] == target) {
                return mid;
            }

            if (sortedArr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    private static void printResult(String method, int[] arr, int target, int index) {
        System.out.println(method);
        System.out.println("Arreglo: " + Arrays.toString(arr));
        System.out.println("Target:  " + target);
        if (index >= 0) {
            System.out.println("Resultado: encontrado en índice " + index + " (valor=" + arr[index] + ")");
        } else {
            System.out.println("Resultado: no encontrado (índice = -1)");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11, 3, 90, 44};

        System.out.println("=== Demostración de búsquedas en arreglos ===\n");

        // 1) Búsqueda lineal: funciona incluso si el arreglo NO está ordenado
        int target1 = 22;
        int idxLinear1 = linearSearch(arr, target1);
        printResult("Búsqueda lineal (arreglo no ordenado)", arr, target1, idxLinear1);

        int target2 = 100;
        int idxLinear2 = linearSearch(arr, target2);
        printResult("Búsqueda lineal (elemento inexistente)", arr, target2, idxLinear2);

        // 2) Búsqueda binaria: requiere arreglo ORDENADO
        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        int target3 = 44;
        int idxBinary1 = binarySearch(sorted, target3);
        printResult("Búsqueda binaria (arreglo ordenado)", sorted, target3, idxBinary1);

        int target4 = 13;
        int idxBinary2 = binarySearch(sorted, target4);
        printResult("Búsqueda binaria (elemento inexistente)", sorted, target4, idxBinary2);
    }
}

