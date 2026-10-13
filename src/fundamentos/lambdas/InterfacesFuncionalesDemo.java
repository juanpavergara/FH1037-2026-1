package fundamentos.lambdas;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * Demostración de las interfaces funcionales más comunes de
 * java.util.function y de las referencias a métodos.
 *
 * Pregunta central que responde esta clase:
 *   "¿Tengo que crear mi propia interfaz (como OperacionBinaria) cada vez
 *    que quiero usar una lambda, o Java ya trae algunas listas para usar?"
 *
 * Respuesta corta:
 *   Java ya trae muchas en el paquete java.util.function. Las más usadas
 *   son estas seis; basta con saber QUÉ RECIBEN, QUÉ DEVUELVEN y CÓMO SE
 *   LLAMA su único método abstracto:
 *
 *       Interfaz               Método      Recibe     Devuelve
 *       ---------------------  ----------  ---------  --------
 *       Predicate<T>           test(t)     un T       boolean
 *       Function<T, R>         apply(t)    un T       un R
 *       Consumer<T>            accept(t)   un T       nada (void)
 *       Supplier<T>            get()       nada       un T
 *       UnaryOperator<T>       apply(t)    un T       un T
 *       BinaryOperator<T>      apply(a,b)  dos T      un T
 *
 *   La lambda que escribimos es el cuerpo de ese método. Para EJECUTARLA
 *   hay que llamar al método por su nombre: p.test(4), f.apply("hola"),
 *   c.accept("x"), s.get(), ...
 *
 * Existen muchas más (BiFunction<T, U, R>, BiConsumer<T, U>, y versiones
 * para tipos primitivos como IntPredicate o IntFunction<R>), pero siguen
 * la misma idea y no se usan en esta demo.
 *
 * Al final se muestran las REFERENCIAS A MÉTODOS (Clase::metodo), que son
 * una forma todavía más corta de escribir ciertas lambdas.
 */
public class InterfacesFuncionalesDemo {

    public static void main(String[] args) {
        // -----------------------------------------------------------------
        // 1) Predicate<T>: recibe un T, devuelve boolean. Método: test
        // -----------------------------------------------------------------
        // Un Predicate es una "pregunta de sí o no" sobre un valor.
        // Además de test, trae métodos para combinar predicados:
        //   p.and(q)  -> true sólo si se cumplen los dos
        //   p.negate() -> lo contrario de p
        System.out.println("== 1) Predicate<Integer>: recibe Integer, devuelve boolean, método test ==");
        Predicate<Integer> esPar = n -> n % 2 == 0;
        Predicate<Integer> esMayorQueDiez = n -> n > 10;

        System.out.println("esPar.test(4) = " + esPar.test(4));   // true
        System.out.println("esPar.test(7) = " + esPar.test(7));   // false

        // and: combina dos predicados en uno nuevo.
        Predicate<Integer> esParYMayorQueDiez = esPar.and(esMayorQueDiez);
        System.out.println("esPar.and(esMayorQueDiez).test(12) = " + esParYMayorQueDiez.test(12)); // true
        System.out.println("esPar.and(esMayorQueDiez).test(8) = " + esParYMayorQueDiez.test(8));   // false

        // negate: invierte la respuesta. esPar.negate() equivale a "es impar".
        Predicate<Integer> esImpar = esPar.negate();
        System.out.println("esPar.negate().test(7) = " + esImpar.test(7)); // true
        System.out.println();

        // -----------------------------------------------------------------
        // 2) Function<T, R>: recibe un T, devuelve un R. Método: apply
        // -----------------------------------------------------------------
        // Los dos tipos pueden ser distintos: aquí entra un String y sale
        // un Integer (su longitud).
        System.out.println("== 2) Function<String, Integer>: recibe String, devuelve Integer, método apply ==");
        Function<String, Integer> longitud = s -> s.length();
        System.out.println("longitud.apply(\"lambda\") = " + longitud.apply("lambda"));       // 6
        System.out.println("longitud.apply(\"Java\") = " + longitud.apply("Java"));           // 4
        System.out.println();

        // -----------------------------------------------------------------
        // 3) Consumer<T>: recibe un T, NO devuelve nada. Método: accept
        // -----------------------------------------------------------------
        // Un Consumer "consume" el valor: hace algo con él (imprimirlo,
        // guardarlo, ...) pero no produce un resultado.
        System.out.println("== 3) Consumer<String>: recibe String, no devuelve nada (void), método accept ==");
        Consumer<String> imprimirConFormato = s -> System.out.println("[mensaje] " + s);
        imprimirConFormato.accept("Hola desde un Consumer");
        imprimirConFormato.accept("Las lambdas son objetos");
        System.out.println();

        // -----------------------------------------------------------------
        // 4) Supplier<T>: NO recibe nada, devuelve un T. Método: get
        // -----------------------------------------------------------------
        // Es lo contrario de Consumer: no recibe parámetros, por eso la
        // lambda empieza con () -> ...
        System.out.println("== 4) Supplier<String>: no recibe nada, devuelve String, método get ==");
        Supplier<String> saludo = () -> "¡Hola, estudiante de Fundamentos!";
        System.out.println("saludo.get() = " + saludo.get());
        System.out.println();

        // -----------------------------------------------------------------
        // 5) UnaryOperator<T> y BinaryOperator<T>: método apply
        // -----------------------------------------------------------------
        // Son casos especiales de "función" donde TODO es del mismo tipo:
        //   UnaryOperator<T>  : recibe un T,  devuelve un T  (como Function<T, T>)
        //   BinaryOperator<T> : recibe dos T, devuelve un T
        //
        // Fíjate en BinaryOperator<Integer>: recibe dos enteros y devuelve
        // un entero... ¡igual que nuestra OperacionBinaria! OperacionBinaria
        // es esencialmente un BinaryOperator especializado en int (sin
        // genéricos y con el método llamado aplicar en vez de apply).
        System.out.println("== 5) UnaryOperator<Integer> y BinaryOperator<Integer>: método apply ==");
        UnaryOperator<Integer> alCuadrado = x -> x * x;
        System.out.println("alCuadrado.apply(5) = " + alCuadrado.apply(5)); // 25

        BinaryOperator<Integer> suma = (a, b) -> a + b;
        System.out.println("suma.apply(7, 3) = " + suma.apply(7, 3)); // 10

        // La misma lambda escrita como OperacionBinaria da el mismo resultado.
        OperacionBinaria sumaPropia = (a, b) -> a + b;
        System.out.println("OperacionBinaria sumaPropia.aplicar(7, 3) = " + sumaPropia.aplicar(7, 3)); // 10
        System.out.println("OperacionBinaria es esencialmente un BinaryOperator especializado en int.");
        System.out.println();

        // -----------------------------------------------------------------
        // 6) Pasar estas interfaces a métodos propios: Operaciones
        // -----------------------------------------------------------------
        // Operaciones.filtrar recibe un Predicate<Integer> y
        // Operaciones.transformar recibe un Function<Integer, String>.
        // Podemos pasarles las variables de arriba o escribir la lambda
        // directamente en la llamada.
        System.out.println("== 6) Usar Predicate y Function con Operaciones.filtrar y transformar ==");
        List<Integer> numeros = List.of(3, 8, 11, 12, 15, 20);
        System.out.println("Lista original: " + numeros);

        System.out.println("filtrar(numeros, esPar) = " + Operaciones.filtrar(numeros, esPar));
        System.out.println("filtrar(numeros, esPar.and(esMayorQueDiez)) = "
                + Operaciones.filtrar(numeros, esPar.and(esMayorQueDiez)));
        System.out.println("filtrar(numeros, esPar.negate()) = " + Operaciones.filtrar(numeros, esPar.negate()));
        System.out.println("transformar(numeros, n -> \"#\" + n) = "
                + Operaciones.transformar(numeros, n -> "#" + n));

        // filtrar y transformar devuelven listas NUEVAS: la original sigue igual.
        System.out.println("Lista original después: " + numeros);
        System.out.println();

        // -----------------------------------------------------------------
        // 7) Referencias a métodos: una lambda todavía más corta
        // -----------------------------------------------------------------
        // Cuando la lambda SÓLO llama a un método que ya existe, pasándole
        // el mismo parámetro, se puede reemplazar por Clase::metodo:
        //
        //   s -> System.out.println(s)   ==>   System.out::println
        //   s -> s.length()              ==>   String::length
        //   s -> Integer.parseInt(s)     ==>   Integer::parseInt
        //
        // No es otra cosa distinta: el compilador la convierte en la misma
        // implementación de la interfaz funcional.
        System.out.println("== 7) Referencias a métodos: forma abreviada de una lambda ==");

        Consumer<String> imprimirLambda = s -> System.out.println(s);
        Consumer<String> imprimirReferencia = System.out::println;
        imprimirLambda.accept("impreso con s -> System.out.println(s)");
        imprimirReferencia.accept("impreso con System.out::println");

        Function<String, Integer> longitudLambda = s -> s.length();
        Function<String, Integer> longitudReferencia = String::length;
        System.out.println("s -> s.length() con \"referencia\" = " + longitudLambda.apply("referencia"));
        System.out.println("String::length con \"referencia\" = " + longitudReferencia.apply("referencia"));
        System.out.println("¿Mismo resultado? " + longitudLambda.apply("referencia").equals(longitudReferencia.apply("referencia")));

        Function<String, Integer> convertirLambda = s -> Integer.parseInt(s);
        Function<String, Integer> convertirReferencia = Integer::parseInt;
        System.out.println("s -> Integer.parseInt(s) con \"42\" = " + convertirLambda.apply("42"));
        System.out.println("Integer::parseInt con \"42\" = " + convertirReferencia.apply("42"));
        System.out.println("¿Mismo resultado? " + convertirLambda.apply("42").equals(convertirReferencia.apply("42")));
        System.out.println();

        // -----------------------------------------------------------------
        // Conclusión
        // -----------------------------------------------------------------
        System.out.println("== Conclusión ==");
        System.out.println("- java.util.function trae interfaces funcionales listas para usar con lambdas.");
        System.out.println("- Predicate (test) responde sí/no; Function (apply) transforma un valor en otro.");
        System.out.println("- Consumer (accept) usa un valor sin devolver nada; Supplier (get) produce uno sin recibir nada.");
        System.out.println("- UnaryOperator y BinaryOperator (apply) trabajan con un solo tipo;");
        System.out.println("  OperacionBinaria es nuestra versión de BinaryOperator para int.");
        System.out.println("- Los métodos propios (Operaciones.filtrar/transformar) reciben estas interfaces como parámetro.");
        System.out.println("- Una referencia a método (Clase::metodo) es una forma abreviada de una lambda que sólo llama a ese método.");
    }
}
