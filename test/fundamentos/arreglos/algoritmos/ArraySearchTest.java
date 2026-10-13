package fundamentos.arreglos.algoritmos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fundamentos.Consola;
import org.junit.jupiter.api.Test;

class ArraySearchTest {

    @Test
    void linearSearchEncuentraElPrimerIndice() {
        int[] arr = {64, 25, 12, 22, 11, 22};
        assertEquals(3, ArraySearch.linearSearch(arr, 22));
        assertEquals(0, ArraySearch.linearSearch(arr, 64));
        assertEquals(4, ArraySearch.linearSearch(arr, 11));
    }

    @Test
    void linearSearchRetornaMenosUnoSiNoExiste() {
        assertEquals(-1, ArraySearch.linearSearch(new int[]{1, 2, 3}, 100));
        assertEquals(-1, ArraySearch.linearSearch(new int[]{}, 1));
    }

    @Test
    void binarySearchEncuentraElementosEnArregloOrdenado() {
        int[] sorted = {3, 11, 12, 22, 25, 44, 64, 90};
        for (int i = 0; i < sorted.length; i++) {
            assertEquals(i, ArraySearch.binarySearch(sorted, sorted[i]));
        }
    }

    @Test
    void binarySearchRetornaMenosUnoSiNoExiste() {
        int[] sorted = {3, 11, 12, 22, 25, 44, 64, 90};
        assertEquals(-1, ArraySearch.binarySearch(sorted, 13));
        assertEquals(-1, ArraySearch.binarySearch(sorted, 0));
        assertEquals(-1, ArraySearch.binarySearch(sorted, 100));
        assertEquals(-1, ArraySearch.binarySearch(new int[]{}, 5));
    }

    @Test
    void mainImprimeLosCuatroCasos() {
        String salida = Consola.capturar(() -> ArraySearch.main(new String[0]));
        assertTrue(salida.contains("encontrado en índice 3 (valor=22)"));
        assertTrue(salida.contains("encontrado en índice 5 (valor=44)"));
        assertTrue(salida.contains("no encontrado (índice = -1)"));
    }
}
