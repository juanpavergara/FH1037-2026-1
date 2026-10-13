package fundamentos.colecciones;

import static org.junit.jupiter.api.Assertions.assertTrue;

import fundamentos.Consola;
import org.junit.jupiter.api.Test;

class ArrayListGetDemoTest {

    @Test
    void mainMuestraQueGetEsSiemprePorPosicion() {
        String salida = Consola.capturar(() -> ArrayListGetDemo.main(new String[0]));
        assertTrue(salida.contains("Lista: [100, 200, 300, 400]"));
        assertTrue(salida.contains("numeros.get(0) = 100"));
        assertTrue(salida.contains("numeros.get(2) = 300"));
        assertTrue(salida.contains("numeros.get(Integer.valueOf(1)) = 200"));
        assertTrue(salida.contains("Excepción esperada: java.lang.IndexOutOfBoundsException"));
        assertTrue(salida.contains("numeros.indexOf(300) = 2"));
        assertTrue(salida.contains("numeros.indexOf(999) = -1"));
    }

    @Test
    void mainContrastaRemovePorPosicionYPorValor() {
        String salida = Consola.capturar(() -> ArrayListGetDemo.main(new String[0]));
        assertTrue(salida.contains("Después de remove(1)        [por posición]: [100, 300, 400]"));
        assertTrue(salida.contains("Después de remove(Integer.valueOf(300)) [por valor]: [100, 400]"));
    }
}
