package fundamentos.colecciones;

import java.util.ArrayList;

/**
 * Demostración del método .get(...) de ArrayList<Integer>.
 *
 * Pregunta central que responde esta clase:
 *   "¿Cómo hace Java para saber si estoy consultando por POSICIÓN
 *    o por el OBJETO Integer con el valor?"
 *
 * Respuesta corta:
 *   Con .get NO HAY AMBIGÜEDAD. ArrayList sólo tiene UNA versión de get:
 *
 *       public E get(int index)
 *
 *   Es decir, .get SIEMPRE recibe un int y SIEMPRE lo interpreta como
 *   una POSICIÓN (índice 0..size-1). Nunca busca por valor.
 *
 *   La confusión típica viene de .remove(...), que SÍ tiene dos versiones
 *   sobrecargadas:
 *       remove(int index)      -> elimina por posición
 *       remove(Object o)       -> elimina por valor
 *   pero .get NO está sobrecargado: sólo existe get(int).
 *
 * Entonces, ¿qué pasa si le paso un Integer a .get?
 *   Java aplica AUTOUNBOXING: convierte el Integer en un int y lo usa
 *   como índice. Es decir, sigue siendo una consulta por POSICIÓN, no
 *   por valor.
 *
 * ¿Y cómo busco por valor (la posición donde está un cierto valor)?
 *   Con .indexOf(Object), que sí recibe el objeto y devuelve su índice
 *   (o -1 si no está).
 */
public class ArrayListGetDemo {

    public static void main(String[] args) {
        // Construimos una lista de enteros con valores intencionalmente
        // distintos a sus posiciones, para que se vea la diferencia entre
        // "índice" y "valor".
        ArrayList<Integer> numeros = new ArrayList<>();
        numeros.add(100); // posición 0 -> valor 100
        numeros.add(200); // posición 1 -> valor 200
        numeros.add(300); // posición 2 -> valor 300
        numeros.add(400); // posición 3 -> valor 400

        System.out.println("Lista: " + numeros);
        System.out.println("Tamaño (size): " + numeros.size());
        System.out.println();

        // -----------------------------------------------------------------
        // 1) get(int) : SIEMPRE es por POSICIÓN
        // -----------------------------------------------------------------
        // numeros.get(0) NO significa "buscame el valor 0".
        // Significa "dame el elemento en la POSICIÓN 0".
        System.out.println("== 1) get(int) interpreta el argumento como POSICIÓN ==");
        System.out.println("numeros.get(0) = " + numeros.get(0)); // 100
        System.out.println("numeros.get(2) = " + numeros.get(2)); // 300
        System.out.println();

        // -----------------------------------------------------------------
        // 2) ¿Y si le paso un Integer en vez de un int?
        // -----------------------------------------------------------------
        // Como sólo existe get(int index), Java aplica AUTOUNBOXING:
        // toma el Integer y lo convierte en int para usarlo como índice.
        // Sigue siendo una consulta por POSICIÓN.
        System.out.println("== 2) Pasar Integer no cambia nada: autounboxing -> índice ==");
        Integer indiceComoObjeto = Integer.valueOf(1);
        Integer resultado = numeros.get(indiceComoObjeto); // se desempaca a int 1
        System.out.println("numeros.get(Integer.valueOf(1)) = " + resultado); // 200
        System.out.println();

        // -----------------------------------------------------------------
        // 3) Demostración del error típico: pensar que get busca por valor
        // -----------------------------------------------------------------
        // Si pido get(300) NO me devuelve "el 300"; intenta ir a la
        // POSICIÓN 300, que no existe, y lanza IndexOutOfBoundsException.
        System.out.println("== 3) get(300) NO busca el valor 300, busca la POSICIÓN 300 ==");
        try {
            Integer x = numeros.get(300);
            System.out.println("Esto no debería imprimirse: " + x);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Excepción esperada: " + e);
        }
        System.out.println();

        // -----------------------------------------------------------------
        // 4) Para buscar POR VALOR se usa indexOf(Object)
        // -----------------------------------------------------------------
        // indexOf sí recibe el objeto buscado y devuelve su índice.
        // Aquí es donde Java distingue "índice" vs "objeto": NO por
        // adivinanza en tiempo de ejecución, sino por la FIRMA del método
        // que estás llamando (get(int) vs indexOf(Object)).
        System.out.println("== 4) Para buscar por VALOR se usa indexOf(Object) ==");
        int posDel300 = numeros.indexOf(300);
        System.out.println("numeros.indexOf(300) = " + posDel300); // 2
        System.out.println("numeros.indexOf(999) = " + numeros.indexOf(999)); // -1
        System.out.println();

        // -----------------------------------------------------------------
        // 5) El caso famoso de ambigüedad: remove
        // -----------------------------------------------------------------
        // remove SÍ está sobrecargado:
        //   remove(int index)   -> elimina por posición
        //   remove(Object o)    -> elimina por valor
        // Aquí Java decide en tiempo de COMPILACIÓN según el tipo
        // estático del argumento:
        //   - int      -> llama a remove(int)
        //   - Integer  -> llama a remove(Object)
        // Por eso con remove sí debes tener cuidado; con get NO, porque
        // sólo hay una versión.
        System.out.println("== 5) Contraste: remove SÍ es ambiguo, get NO ==");
        ArrayList<Integer> copia = new ArrayList<>(numeros);
        System.out.println("Antes: " + copia);

        copia.remove(1);                  // remove(int)    -> borra POSICIÓN 1 (200)
        System.out.println("Después de remove(1)        [por posición]: " + copia);

        copia.remove(Integer.valueOf(300)); // remove(Object) -> borra VALOR 300
        System.out.println("Después de remove(Integer.valueOf(300)) [por valor]: " + copia);
        System.out.println();

        // -----------------------------------------------------------------
        // Conclusión
        // -----------------------------------------------------------------
        System.out.println("== Conclusión ==");
        System.out.println("- ArrayList.get(int) sólo existe en una versión: SIEMPRE es por posición.");
        System.out.println("- Si pasas un Integer, Java lo desempaca a int (autounboxing).");
        System.out.println("- Para buscar por valor usa indexOf(Object), no get.");
        System.out.println("- La ambigüedad índice/objeto aparece en remove, no en get,");
        System.out.println("  y se resuelve en tiempo de compilación según el tipo del argumento.");
        System.out.println();
        System.out.println("Para profundizar en cómo resolver la ambigüedad de remove ver:");
        System.out.println("  fundamentos.colecciones.ArrayListRemoveDemo");
    }
}
