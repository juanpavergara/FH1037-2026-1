package fundamentos.lambdas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class OperacionesTest {

    @Test
    void operarAplicaLaLambdaRecibida() {
        assertEquals(10, Operaciones.operar(7, 3, (a, b) -> a + b));
        assertEquals(4, Operaciones.operar(7, 3, (a, b) -> a - b));
        assertEquals(7, Operaciones.operar(7, 3, (a, b) -> a > b ? a : b));
        assertEquals(9, Operaciones.operar(2, 9, Math::max));
    }

    @Test
    void filtrarDevuelveSoloLosQueCumplenElCriterio() {
        List<Integer> numeros = List.of(1, 2, 3, 4, 5, 6);
        assertEquals(List.of(2, 4, 6), Operaciones.filtrar(numeros, n -> n % 2 == 0));
    }

    @Test
    void filtrarConListaVaciaDevuelveListaVacia() {
        assertTrue(Operaciones.filtrar(new ArrayList<>(), n -> true).isEmpty());
    }

    @Test
    void filtrarSinElementosQueCumplanDevuelveListaVacia() {
        assertTrue(Operaciones.filtrar(List.of(1, 3, 5), n -> n > 100).isEmpty());
    }

    @Test
    void filtrarNoModificaLaListaOriginal() {
        List<Integer> numeros = new ArrayList<>(List.of(1, 2, 3, 4));
        Operaciones.filtrar(numeros, n -> n > 2);
        assertEquals(List.of(1, 2, 3, 4), numeros);
    }

    @Test
    void transformarAplicaLaFuncionACadaElemento() {
        assertEquals(List.of("#1", "#2", "#3"),
                Operaciones.transformar(List.of(1, 2, 3), n -> "#" + n));
    }

    @Test
    void transformarConListaVaciaDevuelveListaVacia() {
        assertTrue(Operaciones.transformar(new ArrayList<>(), n -> "x").isEmpty());
    }

    @Test
    void transformarNoModificaLaListaOriginal() {
        List<Integer> numeros = new ArrayList<>(List.of(1, 2, 3));
        Operaciones.transformar(numeros, n -> "valor " + n);
        assertEquals(List.of(1, 2, 3), numeros);
    }
}
