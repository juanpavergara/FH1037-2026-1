package fundamentos.lambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Métodos propios que reciben COMPORTAMIENTO como parámetro.
 *
 * Pregunta central que responde esta clase:
 *   "¿Cómo escribo un método que recibe una lambda, y qué hace ese método
 *    con ella por dentro?"
 *
 * Respuesta corta:
 *   El parámetro se declara con el tipo de una INTERFAZ FUNCIONAL
 *   (OperacionBinaria, Predicate, Function, ...). Dentro del método, la
 *   lambda es un objeto normal y se usa llamando a su único método:
 *
 *       op.aplicar(a, b)        // OperacionBinaria
 *       criterio.test(n)        // Predicate<Integer>
 *       f.apply(n)              // Function<Integer, String>
 *
 *   Quien llama al método decide QUÉ hacer (la lambda); el método decide
 *   CUÁNDO y CON QUÉ datos hacerlo. Así, un mismo método sirve para
 *   sumar, restar, filtrar pares, filtrar positivos, etc.
 *
 * ¿Por qué filtrar y transformar usan un for explícito?
 *   Para que se vea exactamente qué pasa: se recorre la lista, se le
 *   pregunta al predicado (o se le aplica la función) elemento por
 *   elemento y se arma una lista NUEVA. La lista original no se toca.
 */
public final class Operaciones {

    // Clase utilitaria: sólo métodos static, no tiene sentido crear objetos.
    private Operaciones() {
    }

    /**
     * Aplica la operación recibida a a y b.
     *
     * Ejemplo: operar(7, 3, (x, y) -> x - y) devuelve 4.
     */
    public static int operar(int a, int b, OperacionBinaria op) {
        return op.aplicar(a, b);
    }

    /**
     * Devuelve una lista NUEVA con los números que cumplen el criterio.
     *
     * Ejemplo: filtrar([1, 2, 3, 4], n -> n % 2 == 0) devuelve [2, 4].
     */
    public static List<Integer> filtrar(List<Integer> numeros, Predicate<Integer> criterio) {
        List<Integer> resultado = new ArrayList<>();
        for (Integer n : numeros) {
            // test(n) ejecuta el cuerpo de la lambda con n y devuelve true/false.
            if (criterio.test(n)) {
                resultado.add(n);
            }
        }
        return resultado;
    }

    /**
     * Devuelve una lista NUEVA con f aplicada a cada número.
     *
     * Ejemplo: transformar([1, 2], n -> "#" + n) devuelve ["#1", "#2"].
     */
    public static List<String> transformar(List<Integer> numeros, Function<Integer, String> f) {
        List<String> resultado = new ArrayList<>();
        for (Integer n : numeros) {
            // apply(n) ejecuta el cuerpo de la lambda con n y devuelve su resultado.
            resultado.add(f.apply(n));
        }
        return resultado;
    }
}
