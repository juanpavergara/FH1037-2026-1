package fundamentos.lambdas;

import static org.junit.jupiter.api.Assertions.assertTrue;

import fundamentos.Consola;
import org.junit.jupiter.api.Test;

class InterfacesFuncionalesDemoTest {

    @Test
    void mainMuestraPredicateConAndYNegate() {
        String salida = Consola.capturar(() -> InterfacesFuncionalesDemo.main(new String[0]));
        assertTrue(salida.contains("esPar.test(4) = true"));
        assertTrue(salida.contains("esPar.test(7) = false"));
        assertTrue(salida.contains("esPar.and(esMayorQueDiez).test(12) = true"));
        assertTrue(salida.contains("esPar.and(esMayorQueDiez).test(8) = false"));
        assertTrue(salida.contains("esPar.negate().test(7) = true"));
    }

    @Test
    void mainMuestraFunctionConsumerYSupplier() {
        String salida = Consola.capturar(() -> InterfacesFuncionalesDemo.main(new String[0]));
        assertTrue(salida.contains("longitud.apply(\"lambda\") = 6"));
        assertTrue(salida.contains("[mensaje] Hola desde un Consumer"));
        assertTrue(salida.contains("saludo.get() = ¡Hola, estudiante de Fundamentos!"));
    }

    @Test
    void mainMuestraUnaryYBinaryOperator() {
        String salida = Consola.capturar(() -> InterfacesFuncionalesDemo.main(new String[0]));
        assertTrue(salida.contains("alCuadrado.apply(5) = 25"));
        assertTrue(salida.contains("suma.apply(7, 3) = 10"));
        assertTrue(salida.contains("OperacionBinaria sumaPropia.aplicar(7, 3) = 10"));
    }

    @Test
    void mainMuestraFiltrarYTransformar() {
        String salida = Consola.capturar(() -> InterfacesFuncionalesDemo.main(new String[0]));
        assertTrue(salida.contains("Lista original: [3, 8, 11, 12, 15, 20]"));
        assertTrue(salida.contains("filtrar(numeros, esPar) = [8, 12, 20]"));
        assertTrue(salida.contains("filtrar(numeros, esPar.and(esMayorQueDiez)) = [12, 20]"));
        assertTrue(salida.contains("filtrar(numeros, esPar.negate()) = [3, 11, 15]"));
        assertTrue(salida.contains("transformar(numeros, n -> \"#\" + n) = [#3, #8, #11, #12, #15, #20]"));
        assertTrue(salida.contains("Lista original después: [3, 8, 11, 12, 15, 20]"));
    }

    @Test
    void mainMuestraQueLasReferenciasAMetodosEquivalenALambdas() {
        String salida = Consola.capturar(() -> InterfacesFuncionalesDemo.main(new String[0]));
        assertTrue(salida.contains("impreso con s -> System.out.println(s)"));
        assertTrue(salida.contains("impreso con System.out::println"));
        assertTrue(salida.contains("s -> s.length() con \"referencia\" = 10"));
        assertTrue(salida.contains("String::length con \"referencia\" = 10"));
        assertTrue(salida.contains("s -> Integer.parseInt(s) con \"42\" = 42"));
        assertTrue(salida.contains("Integer::parseInt con \"42\" = 42"));
        assertTrue(salida.contains("¿Mismo resultado? true"));
    }

    @Test
    void mainTerminaConConclusion() {
        String salida = Consola.capturar(() -> InterfacesFuncionalesDemo.main(new String[0]));
        assertTrue(salida.contains("== Conclusión =="));
        assertTrue(salida.contains("- Una referencia a método (Clase::metodo) es una forma abreviada"));
    }
}
