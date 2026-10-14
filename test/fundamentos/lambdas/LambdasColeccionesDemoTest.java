package fundamentos.lambdas;

import static org.junit.jupiter.api.Assertions.assertTrue;

import fundamentos.Consola;
import org.junit.jupiter.api.Test;

class LambdasColeccionesDemoTest {

    @Test
    void mainMuestraQueForEachYForMejoradoDanLaMismaSalida() {
        String salida = Consola.capturar(() -> LambdasColeccionesDemo.main(new String[0]));
        assertTrue(salida.contains("Lista antes: [7, 2, 9, 4, 1, 8]"));
        assertTrue(salida.contains("for mejorado          : 7 2 9 4 1 8"));
        assertTrue(salida.contains("forEach(n -> ...)     : 7 2 9 4 1 8"));
        assertTrue(salida.contains("Lista después: [7, 2, 9, 4, 1, 8]"));
    }

    @Test
    void mainMuestraRemoveIfPorCondicion() {
        String salida = Consola.capturar(() -> LambdasColeccionesDemo.main(new String[0]));
        assertTrue(salida.contains("Después de removeIf(n -> n % 2 == 0): [7, 9, 1]"));
    }

    @Test
    void mainMuestraReplaceAllEnElLugar() {
        String salida = Consola.capturar(() -> LambdasColeccionesDemo.main(new String[0]));
        assertTrue(salida.contains("Lista antes: [7, 9, 1]"));
        assertTrue(salida.contains("Después de replaceAll(n -> n * 10): [70, 90, 10]"));
    }

    @Test
    void mainMuestraSortAscendenteYDescendente() {
        String salida = Consola.capturar(() -> LambdasColeccionesDemo.main(new String[0]));
        assertTrue(salida.contains("Lista antes: [5, 3, 8, 1, 9, 2]"));
        assertTrue(salida.contains(
                "Después de sort((a, b) -> Integer.compare(a, b)) [ascendente]: [1, 2, 3, 5, 8, 9]"));
        assertTrue(salida.contains("Lista antes: [1, 2, 3, 5, 8, 9]"));
        assertTrue(salida.contains(
                "Después de sort((a, b) -> Integer.compare(b, a)) [descendente]: [9, 8, 5, 3, 2, 1]"));
    }

    @Test
    void mainMuestraSortPorLongitudConLambdaYConComparatorComparing() {
        String salida = Consola.capturar(() -> LambdasColeccionesDemo.main(new String[0]));
        assertTrue(salida.contains("Lista antes: [manzana, kiwi, pera, banano, uva, fresa]"));
        assertTrue(salida.contains(
                "Después de sort((a, b) -> Integer.compare(a.length(), b.length())): "
                        + "[uva, kiwi, pera, fresa, banano, manzana]"));
        assertTrue(salida.contains(
                "Después de sort(Comparator.comparing(String::length)): "
                        + "[uva, kiwi, pera, fresa, banano, manzana]"));
        assertTrue(salida.contains("¿Mismo resultado que con la lambda? true"));
    }

    @Test
    void mainMuestraLaConclusion() {
        String salida = Consola.capturar(() -> LambdasColeccionesDemo.main(new String[0]));
        assertTrue(salida.contains("== Conclusión =="));
        assertTrue(salida.contains("- removeIf(Predicate) borra por condición, sin la ambigüedad de remove."));
        assertTrue(salida.contains("- removeIf, replaceAll y sort MODIFICAN la lista original."));
    }
}
