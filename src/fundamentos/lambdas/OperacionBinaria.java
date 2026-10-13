package fundamentos.lambdas;

/**
 * Interfaz funcional propia: una operación que recibe dos enteros y
 * devuelve un entero.
 *
 * ¿Qué es una INTERFAZ FUNCIONAL?
 *   Es una interfaz que tiene EXACTAMENTE UN método abstracto. Ese único
 *   método es el "hueco" que una lambda viene a llenar. Por eso podemos
 *   escribir:
 *
 *       OperacionBinaria suma = (a, b) -> a + b;
 *
 *   Java sabe que la lambda (a, b) -> a + b es la implementación de
 *   aplicar(int a, int b), porque es el único método abstracto que hay.
 *   Los tipos de a y b (int) y el tipo de retorno (int) los deduce del
 *   propio método aplicar.
 *
 * ¿Para qué sirve la anotación @FunctionalInterface?
 *   La anotación es OPCIONAL: cualquier interfaz con un solo método
 *   abstracto ya se puede usar con lambdas, la tenga o no.
 *
 *   Pero es ÚTIL porque le pide al compilador que verifique la regla.
 *   Si alguien agrega por error un segundo método abstracto, por ejemplo:
 *
 *       int aplicar(int a, int b);
 *       int otra(int a);          // <- segundo método abstracto
 *
 *   el compilador rechaza el archivo con un error parecido a:
 *
 *       error: Unexpected @FunctionalInterface annotation
 *         OperacionBinaria is not a functional interface
 *           multiple non-overriding abstract methods found
 *
 *   Sin la anotación, el error aparecería más lejos: en cada lugar donde
 *   alguien intente asignar una lambda a OperacionBinaria.
 *
 * Nota: java.util.function ya trae BinaryOperator<T>, que hace lo mismo
 * con objetos. OperacionBinaria es la versión "hecha a mano" y
 * especializada en int, para ver que no hay magia detrás.
 */
@FunctionalInterface
public interface OperacionBinaria {

    /**
     * Aplica la operación a los dos enteros y devuelve el resultado.
     */
    int aplicar(int a, int b);
}
