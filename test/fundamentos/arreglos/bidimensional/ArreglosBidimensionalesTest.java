package fundamentos.arreglos.bidimensional;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fundamentos.Consola;
import org.junit.jupiter.api.Test;

class ArreglosBidimensionalesTest {

    @Test
    void crearArregloBidimensionalIntRetorna3x4() {
        int[][][] resultado = new int[1][][];
        Consola.capturar(() -> resultado[0] = ArreglosBidimensionales.crearArregloBidimensionalInt());
        assertArrayEquals(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        }, resultado[0]);
    }

    @Test
    void crearArregloBidimensionalIntConNewSigueElPatron() {
        int[][][] resultado = new int[1][][];
        Consola.capturar(() -> resultado[0] = ArreglosBidimensionales.crearArregloBidimensionalIntConNew());
        assertArrayEquals(new int[][]{
                {0, 1, 2, 3},
                {10, 11, 12, 13},
                {20, 21, 22, 23}
        }, resultado[0]);
    }

    @Test
    void crearMatriz3x3Del1Al9() {
        assertArrayEquals(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        }, ArreglosBidimensionales.crearMatriz3x3Del1Al9());
    }

    @Test
    void crearMatrizNxNDel1AlNN() {
        int n = 4;
        int[][] m = ArreglosBidimensionales.crearMatrizNxNDel1AlNN(n);
        assertEquals(n, m.length);
        int esperado = 1;
        for (int[] fila : m) {
            assertEquals(n, fila.length);
            for (int v : fila) {
                assertEquals(esperado++, v);
            }
        }
        assertEquals(0, ArreglosBidimensionales.crearMatrizNxNDel1AlNN(0).length);
    }

    @Test
    void crearMatrizAleatoriaUsaDimensionesDelUsuarioYRango0a10() {
        int[][][] resultado = new int[1][][];
        // Entradas inválidas ("x", 0, -1) deben ser ignoradas hasta recibir un positivo.
        Consola.capturar("x\n0\n3\n-1\n5\n",
                () -> resultado[0] = ArreglosBidimensionales.crearMatrizAleatoria0a10DesdeUsuario());
        int[][] m = resultado[0];
        assertEquals(3, m.length);
        for (int[] fila : m) {
            assertEquals(5, fila.length);
            for (int v : fila) {
                assertTrue(v >= 0 && v <= 10, "valor fuera de rango: " + v);
            }
        }
    }

    @Test
    void recorrerPorFilasImprimeCadaFila() {
        String salida = Consola.capturar(
                () -> ArreglosBidimensionales.recorrerPorFilas(new int[][]{{1, 2}, {3, 4}}));
        assertTrue(salida.contains("Fila 0: 1, 2"));
        assertTrue(salida.contains("Fila 1: 3, 4"));
    }

    @Test
    void recorrerPorColumnasImprimeCadaColumna() {
        String salida = Consola.capturar(
                () -> ArreglosBidimensionales.recorrerPorColumnas(new int[][]{{1, 2}, {3, 4}}));
        assertTrue(salida.contains("Columna 0: 1, 3"));
        assertTrue(salida.contains("Columna 1: 2, 4"));
    }

    @Test
    void mainSeEjecutaConEntradaSimulada() {
        String salida = Consola.capturar("2\n2\n", () -> ArreglosBidimensionales.main(new String[0]));
        assertTrue(salida.contains("Ejercicio 1: Matriz 3x3"));
        assertTrue(salida.contains("Ejercicio 2: Matriz 4x4 con números del 1 al 16"));
        assertTrue(salida.contains("Ejercicio 3: Matriz m x n"));
    }
}
