package fundamentos.lambdas;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * Demostración de qué es una expresión lambda y cómo se escribe.
 *
 * Pregunta central que responde esta clase:
 *   "¿Qué es exactamente una lambda y en qué se diferencia de lo que ya
 *    sabía hacer con interfaces?"
 *
 * Respuesta corta:
 *   Una lambda es una forma CORTA de implementar una INTERFAZ FUNCIONAL
 *   (una interfaz con un solo método abstracto). No es nada nuevo en el
 *   fondo: hace lo mismo que una clase anónima que implementa la
 *   interfaz, pero sin escribir todo lo que el compilador ya puede
 *   deducir por su cuenta.
 *
 *       // Clase anónima
 *       OperacionBinaria suma = new OperacionBinaria() {
 *           @Override
 *           public int aplicar(int a, int b) {
 *               return a + b;
 *           }
 *       };
 *
 *       // Lambda equivalente
 *       OperacionBinaria suma = (a, b) -> a + b;
 *
 * ¿Cómo sabe Java qué método implementa la lambda?
 *   Por el TIPO de la variable (o del parámetro) que la recibe. Si el
 *   tipo es OperacionBinaria, la lambda es la implementación de su único
 *   método abstracto, aplicar(int a, int b). De ahí salen también los
 *   tipos de a, b y del valor devuelto.
 *
 * ¿Qué más muestra esta demo?
 *   - Las distintas formas de escribir una lambda (sin parámetros, uno,
 *     varios, con tipos, con cuerpo de bloque).
 *   - Cómo un mismo método (Operaciones.operar) cambia de comportamiento
 *     según la lambda que le pasemos.
 *   - La regla de las variables capturadas: "efectivamente final".
 */
public class LambdasDemo {

    public static void main(String[] args) {
        int x = 12;
        int y = 5;
        System.out.println("Datos: x = " + x + ", y = " + y);
        System.out.println();

        // -----------------------------------------------------------------
        // 1) Antes de las lambdas: CLASE ANÓNIMA
        // -----------------------------------------------------------------
        // Creamos un objeto que implementa OperacionBinaria "en el sitio",
        // sin declarar una clase con nombre. Hay que escribir: new, el
        // nombre de la interfaz, @Override, la firma completa del método
        // (public int aplicar(int a, int b)), las llaves y el return.
        System.out.println("== 1) OperacionBinaria implementada con una CLASE ANÓNIMA ==");
        OperacionBinaria sumaAnonima = new OperacionBinaria() {
            @Override
            public int aplicar(int a, int b) {
                return a + b;
            }
        };
        int resultadoAnonima = sumaAnonima.aplicar(x, y);
        System.out.println("sumaAnonima.aplicar(12, 5) = " + resultadoAnonima); // 17
        System.out.println();

        // -----------------------------------------------------------------
        // 2) La MISMA operación como LAMBDA
        // -----------------------------------------------------------------
        // Comparando con la clase anónima, "desaparece" todo lo que el
        // compilador puede deducir:
        //   - new OperacionBinaria() { ... } -> lo deduce del tipo de la variable.
        //   - @Override public int aplicar   -> es el ÚNICO método abstracto,
        //                                       no hace falta nombrarlo.
        //   - los tipos int de a y b         -> salen de la firma de aplicar.
        //   - las llaves { } y el return     -> si el cuerpo es UNA expresión,
        //                                       su valor se devuelve solo.
        // Lo único que queda es lo esencial: los parámetros, la flecha ->
        // y lo que se calcula con ellos.
        System.out.println("== 2) La MISMA suma escrita como LAMBDA ==");
        OperacionBinaria sumaLambda = (a, b) -> a + b;
        int resultadoLambda = sumaLambda.aplicar(x, y);
        System.out.println("sumaLambda.aplicar(12, 5) = " + resultadoLambda); // 17
        System.out.println("¿Mismo resultado que la clase anónima? " + (resultadoAnonima == resultadoLambda)); // true
        System.out.println();

        // -----------------------------------------------------------------
        // 3) Formas de la sintaxis de una lambda
        // -----------------------------------------------------------------
        // La forma general es:  (parámetros) -> cuerpo
        // Según cuántos parámetros haya y qué tan largo sea el cuerpo, se
        // pueden omitir algunas partes.
        System.out.println("== 3) Formas de escribir una lambda ==");

        // a) SIN parámetros: paréntesis vacíos obligatorios.
        //    Supplier<String> tiene el método get(): no recibe nada y
        //    devuelve un valor.
        Supplier<String> saludo = () -> "Hola desde una lambda";
        System.out.println("() -> \"Hola desde una lambda\"        : " + saludo.get());

        // b) UN parámetro: los paréntesis se pueden omitir.
        //    UnaryOperator<Integer> recibe un Integer y devuelve un Integer.
        UnaryOperator<Integer> doble = n -> n * 2;
        System.out.println("n -> n * 2            con n = 12      : " + doble.apply(x)); // 24

        // c) VARIOS parámetros: los paréntesis son obligatorios.
        OperacionBinaria producto = (a, b) -> a * b;
        System.out.println("(a, b) -> a * b       con 12 y 5      : " + producto.aplicar(x, y)); // 60

        // d) Parámetros con TIPO EXPLÍCITO: válido, pero normalmente
        //    innecesario porque Java ya conoce los tipos. Si se pone el
        //    tipo a uno, hay que ponérselo a todos.
        OperacionBinaria resta = (int a, int b) -> a - b;
        System.out.println("(int a, int b) -> a - b con 12 y 5    : " + resta.aplicar(x, y)); // 7

        // e) Cuerpo de BLOQUE: si se necesitan varias instrucciones, se
        //    usan llaves y entonces el return SÍ es obligatorio (igual que
        //    en un método normal).
        OperacionBinaria residuo = (a, b) -> {
            int r = a % b;
            return r;
        };
        System.out.println("(a, b) -> { int r = a % b; return r; }: " + residuo.aplicar(x, y)); // 2
        System.out.println();

        // -----------------------------------------------------------------
        // 4) Pasar lambdas como PARÁMETRO: mismo método, distinto comportamiento
        // -----------------------------------------------------------------
        // Operaciones.operar(a, b, op) sólo hace op.aplicar(a, b). No sabe
        // si va a sumar, restar o lo que sea: eso lo decide quien llama,
        // al elegir qué lambda le pasa.
        System.out.println("== 4) Operaciones.operar: el mismo método, distinto comportamiento ==");
        System.out.println("operar(12, 5, suma)           = " + Operaciones.operar(x, y, (a, b) -> a + b)); // 17
        System.out.println("operar(12, 5, resta)          = " + Operaciones.operar(x, y, (a, b) -> a - b)); // 7
        System.out.println("operar(12, 5, multiplicación) = " + Operaciones.operar(x, y, (a, b) -> a * b)); // 60
        System.out.println("operar(12, 5, máximo)         = " + Operaciones.operar(x, y, (a, b) -> a > b ? a : b)); // 12
        System.out.println();

        // -----------------------------------------------------------------
        // 5) Variables CAPTURADAS: la regla de "efectivamente final"
        // -----------------------------------------------------------------
        // Una lambda puede usar variables locales del método donde se
        // escribe (se dice que las "captura"). Pero sólo si esas variables
        // son EFECTIVAMENTE FINALES: no se declaran con final, pero nunca
        // se les vuelve a asignar un valor después de inicializarlas.
        //
        // ¿Por qué? La lambda se guarda una COPIA del valor. Si Java
        // permitiera cambiar la variable después, la lambda y el método
        // verían valores distintos, y eso sería muy confuso.
        System.out.println("== 5) Variables capturadas: deben ser efectivamente finales ==");
        int factor = 3; // nunca se reasigna -> es efectivamente final
        UnaryOperator<Integer> multiplicarPorFactor = n -> n * factor;
        System.out.println("factor = " + factor);
        System.out.println("n -> n * factor       con n = 12      : " + multiplicarPorFactor.apply(x)); // 36

        // Si descomentamos la siguiente línea, factor deja de ser
        // efectivamente final (se le asigna un valor nuevo) y el
        // compilador rechaza la LAMBDA de arriba con el error:
        //
        //   error: local variables referenced from a lambda expression
        //          must be final or effectively final
        //
        // Es decir: "las variables locales usadas dentro de una lambda
        // deben ser final o efectivamente finales". Fíjate que el error
        // aparece en la línea de la lambda, no en la de la reasignación.
        //
        // factor = 4;
        System.out.println();

        // -----------------------------------------------------------------
        // Conclusión
        // -----------------------------------------------------------------
        System.out.println("== Conclusión ==");
        System.out.println("- Una lambda implementa el único método abstracto de una interfaz funcional.");
        System.out.println("- Hace lo mismo que una clase anónima, pero sin lo que el compilador puede deducir.");
        System.out.println("- Sintaxis: (parámetros) -> expresión, o (parámetros) -> { instrucciones; return ...; }.");
        System.out.println("- Un método que recibe una interfaz funcional cambia de comportamiento según la lambda.");
        System.out.println("- Las variables locales usadas dentro de una lambda deben ser efectivamente finales.");
        System.out.println();
        System.out.println("Para ver las interfaces funcionales estándar de Java ver:");
        System.out.println("  fundamentos.lambdas.InterfacesFuncionalesDemo");
    }
}
