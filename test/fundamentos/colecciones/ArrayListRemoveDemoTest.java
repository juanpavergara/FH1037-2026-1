package fundamentos.colecciones;

import static org.junit.jupiter.api.Assertions.assertTrue;

import fundamentos.Consola;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ArrayListRemoveDemoTest {

    private static String salida;

    @BeforeAll
    static void ejecutarDemo() {
        salida = Consola.capturar(() -> ArrayListRemoveDemo.main(new String[0]));
    }

    @Test
    void removeConIntLanzaExcepcionPorPosicionInexistente() {
        assertTrue(salida.contains("Excepción esperada (posición 30 no existe): java.lang.IndexOutOfBoundsException"));
    }

    @Test
    void solucionesPorValorBorranEl30() {
        assertTrue(salida.contains("remove(Integer.valueOf(30)) -> borrado=true"));
        assertTrue(salida.contains("Lista resultante: [10, 20, 40, 50]"));
        assertTrue(salida.contains("remove((Object) 30) -> [10, 20, 40, 50]"));
        assertTrue(salida.contains("Integer valor=30; remove(valor) -> [10, 20, 40, 50]"));
    }

    @Test
    void removeIfYRemoveAllBorranTodasLasApariciones() {
        assertTrue(salida.contains("removeIf(x -> x == 30) -> [10, 20, 40, 50]"));
        assertTrue(salida.contains("removeAll(List.of(20, 40)) -> [10, 30, 50]"));
    }

    @Test
    void mismoNumeroDistintoTipoEstaticoDistintoResultado() {
        assertTrue(salida.contains("remove(2)                 -> [10, 20, 40, 50]"));
        assertTrue(salida.contains("remove(Integer.valueOf(2)) -> [10, 20, 30, 40, 50]"));
    }
}
