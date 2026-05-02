package fundamentos.colecciones;

import java.util.ArrayList;
import java.util.List;

/**
 * Complemento de {@link ArrayListGetDemo}.
 *
 * Pregunta central que responde esta clase:
 *   "Si .get nunca es ambiguo, ¿cómo resuelvo la ambigüedad de .remove
 *    cuando trabajo con ArrayList<Integer>?"
 *
 * El problema:
 *   ArrayList define DOS métodos remove sobrecargados:
 *
 *       boolean remove(Object o)   // borra la primera aparición de ese VALOR
 *       E       remove(int index)  // borra el elemento en esa POSICIÓN
 *
 *   Con ArrayList<String> no hay drama: String no es int, así que el
 *   compilador siempre puede distinguir. El problema aparece SÓLO con
 *   ArrayList<Integer> (o Long, Short, etc.), porque el "tipo del
 *   elemento" es muy parecido al "tipo del índice".
 *
 * Cómo elige Java entre los dos remove:
 *   La decisión la toma el COMPILADOR, en tiempo de compilación, mirando
 *   el TIPO ESTÁTICO del argumento que escribiste. No es magia en
 *   tiempo de ejecución:
 *
 *     - Argumento de tipo int      -> match exacto con remove(int)
 *     - Argumento de tipo Integer  -> match exacto con remove(Object)
 *                                     (no hace falta unboxing)
 *     - Argumento de tipo Object   -> match exacto con remove(Object)
 *
 *   Ojo: el compilador NO va a hacer autoboxing (int -> Integer) si ya
 *   tiene un overload exacto para int. Por eso lista.remove(2) llama
 *   SIEMPRE a remove(int) y borra por posición, aunque vos hayas
 *   "querido decir" el valor 2.
 */
public class ArrayListRemoveDemo {

    /** Devuelve una lista nueva [10, 20, 30, 40, 50] para cada experimento. */
    private static ArrayList<Integer> nuevaLista() {
        ArrayList<Integer> l = new ArrayList<>();
        l.add(10); // pos 0
        l.add(20); // pos 1
        l.add(30); // pos 2
        l.add(40); // pos 3
        l.add(50); // pos 4
        return l;
    }

    public static void main(String[] args) {
        System.out.println("Lista base para todos los experimentos: " + nuevaLista());
        System.out.println();

        // -----------------------------------------------------------------
        // 0) El error típico
        // -----------------------------------------------------------------
        // El estudiante quiere borrar el VALOR 30, escribe lista.remove(30)
        // y se sorprende: borra el elemento en la POSICIÓN 30 (o lanza
        // IndexOutOfBoundsException si la lista no tiene esa posición).
        System.out.println("== 0) El error típico: lista.remove(30) NO borra el valor 30 ==");
        ArrayList<Integer> l0 = nuevaLista();
        try {
            l0.remove(30); // remove(int) -> intenta borrar la POSICIÓN 30
            System.out.println("Esto no debería imprimirse: " + l0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Excepción esperada (posición 30 no existe): " + e);
        }
        System.out.println();

        // -----------------------------------------------------------------
        // 1) Solución recomendada: Integer.valueOf(valor)
        // -----------------------------------------------------------------
        // Es la forma más clara y la que aparece en la documentación oficial
        // desde Java 9 (new Integer(...) está deprecado).
        // El argumento queda con tipo estático Integer -> el compilador
        // elige remove(Object) -> borra por VALOR.
        System.out.println("== 1) Solución A: Integer.valueOf(valor) ==");
        ArrayList<Integer> l1 = nuevaLista();
        boolean borrado = l1.remove(Integer.valueOf(30)); // remove(Object)
        System.out.println("remove(Integer.valueOf(30)) -> borrado=" + borrado);
        System.out.println("Lista resultante: " + l1);
        System.out.println();

        // -----------------------------------------------------------------
        // 2) Solución alternativa: cast explícito a Object
        // -----------------------------------------------------------------
        // Funciona porque el tipo estático del argumento pasa a ser Object,
        // y eso hace que el compilador elija remove(Object).
        // Es válido pero menos legible que Integer.valueOf(...).
        System.out.println("== 2) Solución B: cast a (Object) ==");
        ArrayList<Integer> l2 = nuevaLista();
        l2.remove((Object) 30); // remove(Object)
        System.out.println("remove((Object) 30) -> " + l2);
        System.out.println();

        // -----------------------------------------------------------------
        // 3) Solución alternativa: variable tipada como Integer
        // -----------------------------------------------------------------
        // Si guardás el valor en una variable Integer, el tipo estático ya
        // es Integer y el compilador elige remove(Object) sin necesidad de
        // wrappers ni casts en la llamada.
        System.out.println("== 3) Solución C: variable Integer ==");
        ArrayList<Integer> l3 = nuevaLista();
        Integer valor = 30; // tipo estático Integer
        l3.remove(valor);   // remove(Object)
        System.out.println("Integer valor=30; remove(valor) -> " + l3);
        System.out.println();

        // -----------------------------------------------------------------
        // 4) Solución moderna y más legible: removeIf(predicado)
        // -----------------------------------------------------------------
        // Ventajas:
        //  - No depende de la sobrecarga: lee como "borrá los que cumplan X".
        //  - Sirve para borrar TODAS las apariciones, no sólo la primera.
        //  - Acepta cualquier criterio (mayor que, par, etc.), no sólo
        //    igualdad.
        System.out.println("== 4) Solución D: removeIf(predicado) ==");
        ArrayList<Integer> l4 = nuevaLista();
        l4.add(30); // ahora hay dos 30s
        System.out.println("Antes: " + l4);
        l4.removeIf(x -> x == 30); // borra TODAS las apariciones del 30
        System.out.println("removeIf(x -> x == 30) -> " + l4);
        System.out.println();

        // -----------------------------------------------------------------
        // 5) Solución moderna: removeAll(Collection)
        // -----------------------------------------------------------------
        // Útil cuando querés borrar varios valores de una.
        // No hay ambigüedad porque el parámetro es Collection<?>, no int.
        System.out.println("== 5) Solución E: removeAll(Collection) ==");
        ArrayList<Integer> l5 = nuevaLista();
        l5.removeAll(List.of(20, 40)); // borra todas las apariciones de 20 y 40
        System.out.println("removeAll(List.of(20, 40)) -> " + l5);
        System.out.println();

        // -----------------------------------------------------------------
        // 6) Verificación: cómo decide el compilador
        // -----------------------------------------------------------------
        // Mismo "valor" en runtime, pero distinto tipo estático en el
        // código fuente -> distinto método elegido -> distinto resultado.
        System.out.println("== 6) Mismo número, distinto tipo estático, distinto remove ==");
        ArrayList<Integer> l6a = nuevaLista();
        l6a.remove(2);                  // int      -> remove(int)    -> borra POSICIÓN 2 (valor 30)
        System.out.println("remove(2)                 -> " + l6a + "   (borró posición 2)");

        ArrayList<Integer> l6b = nuevaLista();
        l6b.remove(Integer.valueOf(2)); // Integer  -> remove(Object) -> intenta borrar VALOR 2 (no existe)
        System.out.println("remove(Integer.valueOf(2)) -> " + l6b + "   (no borró nada: el valor 2 no está)");
        System.out.println();

        // -----------------------------------------------------------------
        // Conclusión
        // -----------------------------------------------------------------
        System.out.println("== Conclusión ==");
        System.out.println("- La ambigüedad la resuelve el COMPILADOR según el TIPO ESTÁTICO,");
        System.out.println("  no según el valor en runtime.");
        System.out.println("- Para borrar por POSICIÓN: pasá un int -> remove(int).");
        System.out.println("- Para borrar por VALOR en ArrayList<Integer>, elegí una de:");
        System.out.println("    A. lista.remove(Integer.valueOf(v))   <- recomendada");
        System.out.println("    B. lista.remove((Object) v)");
        System.out.println("    C. Integer v2 = v; lista.remove(v2);");
        System.out.println("    D. lista.removeIf(x -> x == v)        <- la más clara");
        System.out.println("    E. lista.removeAll(List.of(v, ...))   <- para varios valores");
    }
}
