package fundamentos.lambdas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Demostración de lambdas con los métodos de ArrayList que ya conocemos.
 *
 * Pregunta central que responde esta clase:
 *   "¿Para qué me sirven las lambdas en el día a día, si ya sé recorrer,
 *    borrar, modificar y ordenar una lista con ciclos?"
 *
 * Respuesta corta:
 *   ArrayList (en realidad, la interfaz List/Collection/Iterable) trae
 *   métodos que reciben COMPORTAMIENTO como parámetro. En lugar de escribir
 *   el ciclo nosotros, le decimos a la lista QUÉ hacer con cada elemento y
 *   ella se encarga de recorrerse:
 *
 *       forEach(Consumer)          -> "haz esto con cada elemento"
 *       removeIf(Predicate)        -> "borra los que cumplan esto"
 *       replaceAll(UnaryOperator)  -> "reemplaza cada uno por esto"
 *       sort(Comparator)           -> "ordénate usando esta comparación"
 *
 *   Cada uno de esos parámetros es una INTERFAZ FUNCIONAL, así que podemos
 *   pasarle una lambda (ver LambdasDemo e InterfacesFuncionalesDemo).
 *
 * ¿Qué cambia respecto al ciclo tradicional?
 *   El resultado es el mismo; lo que cambia es QUIÉN escribe el ciclo.
 *   Con forEach/removeIf/replaceAll/sort el ciclo ya está escrito (y bien
 *   escrito) dentro de ArrayList; nosotros sólo aportamos la parte que
 *   cambia de un problema a otro.
 *
 * Importante: removeIf, replaceAll y sort MODIFICAN la lista en el lugar
 *   (no devuelven una lista nueva). Por eso en cada sección se imprime la
 *   lista ANTES y DESPUÉS de la operación.
 */
public class LambdasColeccionesDemo {

    public static void main(String[] args) {
        // -----------------------------------------------------------------
        // 1) forEach(x -> ...) frente al for mejorado
        // -----------------------------------------------------------------
        // El for mejorado (for-each) lo escribimos nosotros: declaramos la
        // variable, la lista y el cuerpo del ciclo.
        //
        // lista.forEach(...) recibe un Consumer<Integer>: algo que RECIBE
        // un elemento y no devuelve nada. La lista se recorre sola y llama
        // a la lambda una vez por elemento, en el mismo orden.
        System.out.println("== 1) forEach(x -> ...) frente al for mejorado ==");
        ArrayList<Integer> numeros = new ArrayList<>(List.of(7, 2, 9, 4, 1, 8));
        System.out.println("Lista antes: " + numeros);

        System.out.print("for mejorado          :");
        for (Integer n : numeros) {
            System.out.print(" " + n);
        }
        System.out.println();

        System.out.print("forEach(n -> ...)     :");
        numeros.forEach(n -> System.out.print(" " + n));
        System.out.println();

        // forEach sólo LEE: no cambia la lista.
        System.out.println("Lista después: " + numeros);
        System.out.println();

        // -----------------------------------------------------------------
        // 2) removeIf(...) : eliminar por CONDICIÓN
        // -----------------------------------------------------------------
        // removeIf recibe un Predicate<Integer>: algo que recibe un elemento
        // y devuelve true/false. Se eliminan TODOS los elementos para los
        // que el predicado devuelve true.
        //
        // Comparación con fundamentos.colecciones.ArrayListRemoveDemo:
        //   remove está sobrecargado (remove(int index) vs remove(Object o))
        //   y lista.remove(4) borra la POSICIÓN 4, no el VALOR 4. Con
        //   removeIf esa ambigüedad desaparece: no pasamos ni un índice ni
        //   un objeto, sino una CONDICIÓN sobre el valor. Además borra todos
        //   los que cumplen, no sólo la primera aparición, y sin el problema
        //   de modificar la lista mientras la recorremos con un for.
        System.out.println("== 2) removeIf(...) elimina por condición ==");
        System.out.println("Lista antes: " + numeros);
        numeros.removeIf(n -> n % 2 == 0);
        System.out.println("Después de removeIf(n -> n % 2 == 0): " + numeros); // [7, 9, 1]
        System.out.println();

        // -----------------------------------------------------------------
        // 3) replaceAll(...) : transformar EN EL LUGAR
        // -----------------------------------------------------------------
        // replaceAll recibe un UnaryOperator<Integer>: recibe un Integer y
        // devuelve OTRO Integer (del mismo tipo). Cada elemento se reemplaza
        // por el resultado de la lambda, en su misma posición.
        //
        // Equivale a:
        //   for (int i = 0; i < numeros.size(); i++) {
        //       numeros.set(i, numeros.get(i) * 10);
        //   }
        System.out.println("== 3) replaceAll(...) transforma en el lugar ==");
        System.out.println("Lista antes: " + numeros);
        numeros.replaceAll(n -> n * 10);
        System.out.println("Después de replaceAll(n -> n * 10): " + numeros); // [70, 90, 10]
        System.out.println();

        // -----------------------------------------------------------------
        // 4) sort(...) con un Comparator escrito como lambda
        // -----------------------------------------------------------------
        // sort recibe un Comparator<T>, interfaz funcional cuyo único método
        // abstracto es:
        //
        //     int compare(T a, T b)
        //
        // que devuelve:
        //   negativo -> a va ANTES que b
        //   cero     -> son "iguales" para este orden
        //   positivo -> a va DESPUÉS que b
        //
        // Usamos Integer.compare(a, b) en lugar de a - b porque la resta
        // puede desbordarse con números muy grandes o muy negativos.
        System.out.println("== 4) sort(...) con Comparator como lambda ==");

        // 4a) Ascendente: comparar a con b.
        ArrayList<Integer> desordenados = new ArrayList<>(List.of(5, 3, 8, 1, 9, 2));
        System.out.println("Lista antes: " + desordenados);
        desordenados.sort((a, b) -> Integer.compare(a, b));
        System.out.println("Después de sort((a, b) -> Integer.compare(a, b)) [ascendente]: "
                + desordenados); // [1, 2, 3, 5, 8, 9]

        // 4b) Descendente: basta con invertir el orden de los argumentos.
        System.out.println("Lista antes: " + desordenados);
        desordenados.sort((a, b) -> Integer.compare(b, a));
        System.out.println("Después de sort((a, b) -> Integer.compare(b, a)) [descendente]: "
                + desordenados); // [9, 8, 5, 3, 2, 1]
        System.out.println();

        // 4c) Ordenar String por LONGITUD (no alfabéticamente).
        // "kiwi" y "pera" tienen la misma longitud (4). ArrayList.sort es
        // ESTABLE: los elementos "iguales" conservan su orden original, así
        // que "kiwi" (que estaba antes) queda antes que "pera". Por eso el
        // resultado es siempre el mismo.
        ArrayList<String> frutas = new ArrayList<>(
                List.of("manzana", "kiwi", "pera", "banano", "uva", "fresa"));
        ArrayList<String> frutasCopia = new ArrayList<>(frutas);

        System.out.println("Lista antes: " + frutas);
        frutas.sort((a, b) -> Integer.compare(a.length(), b.length()));
        System.out.println("Después de sort((a, b) -> Integer.compare(a.length(), b.length())): "
                + frutas); // [uva, kiwi, pera, fresa, banano, manzana]

        // 4d) Lo mismo con Comparator.comparing y una referencia a método.
        // Comparator.comparing(String::length) construye el Comparator por
        // nosotros: "compara dos String según lo que devuelve length()".
        // String::length es la forma abreviada de la lambda s -> s.length().
        System.out.println("Lista antes: " + frutasCopia);
        frutasCopia.sort(Comparator.comparing(String::length));
        System.out.println("Después de sort(Comparator.comparing(String::length)): "
                + frutasCopia); // [uva, kiwi, pera, fresa, banano, manzana]
        System.out.println("¿Mismo resultado que con la lambda? " + frutas.equals(frutasCopia));
        System.out.println();

        // -----------------------------------------------------------------
        // Conclusión
        // -----------------------------------------------------------------
        System.out.println("== Conclusión ==");
        System.out.println("- forEach(Consumer) recorre la lista; da lo mismo que un for mejorado.");
        System.out.println("- removeIf(Predicate) borra por condición, sin la ambigüedad de remove.");
        System.out.println("- replaceAll(UnaryOperator) transforma cada elemento en el lugar.");
        System.out.println("- sort(Comparator) ordena con la comparación que le pasemos como lambda.");
        System.out.println("- Comparator.comparing(String::length) es una forma abreviada de esa lambda.");
        System.out.println("- removeIf, replaceAll y sort MODIFICAN la lista original.");
    }
}
