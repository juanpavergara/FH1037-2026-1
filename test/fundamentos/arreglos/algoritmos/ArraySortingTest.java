package fundamentos.arreglos.algoritmos;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fundamentos.Consola;
import java.util.Arrays;
import java.util.function.Consumer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Test;
import java.util.stream.Stream;

class ArraySortingTest {

    static Stream<Arguments> casos() {
        return Stream.of(
                Arguments.of((Object) new int[]{64, 25, 12, 22, 11}),
                Arguments.of((Object) new int[]{5, 4, 3, 2, 1}),
                Arguments.of((Object) new int[]{1, 2, 3, 4, 5}),
                Arguments.of((Object) new int[]{3, 1, 2, 3, 0, 2}),
                Arguments.of((Object) new int[]{-5, 0, -1, 7}),
                Arguments.of((Object) new int[]{42}),
                Arguments.of((Object) new int[]{}));
    }

    private static void verificarOrdena(Consumer<int[]> algoritmo, int[] entrada) {
        int[] esperado = entrada.clone();
        Arrays.sort(esperado);
        int[] arr = entrada.clone();
        algoritmo.accept(arr);
        assertArrayEquals(esperado, arr);
    }

    @ParameterizedTest
    @MethodSource("casos")
    void selectionSortOrdenaAscendente(int[] entrada) {
        verificarOrdena(ArraySorting::selectionSort, entrada);
    }

    @ParameterizedTest
    @MethodSource("casos")
    void bubbleSortOrdenaAscendente(int[] entrada) {
        verificarOrdena(ArraySorting::bubbleSort, entrada);
    }

    @Test
    void mainImprimeAmbosAlgoritmos() {
        String salida = Consola.capturar(() -> ArraySorting.main(new String[0]));
        assertTrue(salida.contains("=== Pruebas de Selection Sort ==="));
        assertTrue(salida.contains("=== Pruebas de Bubble Sort ==="));
        assertTrue(salida.contains("[11, 12, 22, 25, 64]"));
    }
}
