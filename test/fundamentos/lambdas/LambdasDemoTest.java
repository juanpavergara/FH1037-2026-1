package fundamentos.lambdas;

import static org.junit.jupiter.api.Assertions.assertTrue;

import fundamentos.Consola;
import org.junit.jupiter.api.Test;

class LambdasDemoTest {

    @Test
    void mainMuestraQueClaseAnonimaYLambdaDanElMismoResultado() {
        String salida = Consola.capturar(() -> LambdasDemo.main(new String[0]));
        assertTrue(salida.contains("sumaAnonima.aplicar(12, 5) = 17"));
        assertTrue(salida.contains("sumaLambda.aplicar(12, 5) = 17"));
        assertTrue(salida.contains("¿Mismo resultado que la clase anónima? true"));
    }

    @Test
    void mainMuestraLasFormasDeLaSintaxis() {
        String salida = Consola.capturar(() -> LambdasDemo.main(new String[0]));
        assertTrue(salida.contains("() -> \"Hola desde una lambda\"        : Hola desde una lambda"));
        assertTrue(salida.contains("n -> n * 2            con n = 12      : 24"));
        assertTrue(salida.contains("(a, b) -> a * b       con 12 y 5      : 60"));
        assertTrue(salida.contains("(int a, int b) -> a - b con 12 y 5    : 7"));
        assertTrue(salida.contains("(a, b) -> { int r = a % b; return r; }: 2"));
    }

    @Test
    void mainMuestraOperarConDistintasLambdas() {
        String salida = Consola.capturar(() -> LambdasDemo.main(new String[0]));
        assertTrue(salida.contains("operar(12, 5, suma)           = 17"));
        assertTrue(salida.contains("operar(12, 5, resta)          = 7"));
        assertTrue(salida.contains("operar(12, 5, multiplicación) = 60"));
        assertTrue(salida.contains("operar(12, 5, máximo)         = 12"));
    }

    @Test
    void mainMuestraVariableCapturadaYConclusion() {
        String salida = Consola.capturar(() -> LambdasDemo.main(new String[0]));
        assertTrue(salida.contains("n -> n * factor       con n = 12      : 36"));
        assertTrue(salida.contains("== Conclusión =="));
        assertTrue(salida.contains("- Las variables locales usadas dentro de una lambda deben ser efectivamente finales."));
    }
}
