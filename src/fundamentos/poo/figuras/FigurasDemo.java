package fundamentos.poo.figuras;

import java.util.ArrayList;
import java.util.List;

/*
 * =============================================================================
 *  DEMO  —  Polimorfismo en acción
 * =============================================================================
 *
 * Este programa pone juntos los conceptos de la clase:
 *
 *   1. Herencia            : Circulo, Rectangulo y Triangulo extienden Figura.
 *   2. Clase abstracta     : Figura comparte estado y `descripcion()`.
 *   3. Clase abstracta pura: Operable (solo definida; no se usa aquí).
 *   4. Interfaz pura       : Escalable.
 *   5. Interfaz con default: Dibujable (con `dibujarConBorde` y `etiqueta`).
 *   6. Polimorfismo        : recorremos una lista de Figura tratándolas
 *                            uniformemente, y luego las re-clasificamos
 *                            por interfaz con `instanceof`.
 */
public class FigurasDemo {

    public static void main(String[] args) {

        // -----------------------------------------------------------------
        // 1) Lista HETEROGÉNEA tipada como List<Figura>.
        //
        //    Aunque cada elemento es de un tipo concreto distinto, todos
        //    son SUBTIPOS de Figura, así que caben en la misma lista.
        //    Esto es el principio de sustitución de Liskov en la práctica.
        // -----------------------------------------------------------------
        List<Figura> figuras = new ArrayList<>();
        figuras.add(new Circulo("Pizza", "rojo", 5.0));
        figuras.add(new Rectangulo("Cuadro", "azul", 4.0, 3.0));
        figuras.add(new Triangulo("Vela", "verde", 3.0, 4.0, 5.0));

        // -----------------------------------------------------------------
        // 2) POLIMORFISMO básico: recorremos la lista llamando un método
        //    declarado en la clase abstracta. Java decide en TIEMPO DE
        //    EJECUCIÓN qué versión concreta de calcularArea/calcularPerimetro
        //    usar, según el objeto real.
        // -----------------------------------------------------------------
        System.out.println("=== Descripcion polimórfica de cada figura ===");
        for (Figura f : figuras) {
            System.out.println(" - " + f.descripcion());
        }
        System.out.println();

        // -----------------------------------------------------------------
        // 3) Filtrado por INTERFAZ: solo algunas figuras son `Escalable`.
        //
        //    Aquí usamos `instanceof` con "pattern matching" (Java 16+),
        //    que de paso hace el cast por nosotros.
        //
        //    El triángulo NO implementa Escalable, así que será ignorado.
        //    Eso ilustra que las interfaces sirven para DECLARAR
        //    CAPACIDADES separadas de la jerarquía de clases.
        // -----------------------------------------------------------------
        System.out.println("=== Escalando (x2) las figuras que sean Escalable ===");
        for (Figura f : figuras) {
            if (f instanceof Escalable e) {
                System.out.println("Antes:  " + f.descripcion());
                e.escalar(2.0);
                System.out.println("Después:" + f.descripcion());
            } else {
                System.out.println("Saltada (no es Escalable): " + f.descripcion());
            }
        }
        System.out.println();

        // -----------------------------------------------------------------
        // 4) Otra interfaz, otro filtro. Aquí TODAS las figuras del ejemplo
        //    son Dibujable, pero el patrón es el mismo: tratar al objeto
        //    bajo otro "lente" (su contrato Dibujable).
        //
        //    Nótese cómo `dibujarConBorde(...)` viene implementado en la
        //    interfaz como `default method`. La clase concreta solo
        //    implementó `dibujar()`, pero igual obtiene el comportamiento
        //    de `dibujarConBorde(...)` "gratis".
        //
        //    También vemos cómo `etiqueta()` puede o no estar sobrescrita:
        //    Rectangulo la personalizó, las demás usan el default.
        // -----------------------------------------------------------------
        System.out.println("=== Dibujando las figuras Dibujable ===");
        for (Figura f : figuras) {
            if (f instanceof Dibujable d) {
                System.out.println(d.etiqueta());
                d.dibujarConBorde(1);
                System.out.println();
            }
        }

        // -----------------------------------------------------------------
        // 5) Comentario final sobre `Operable`:
        //
        //    Si Operable fuera lo que necesitamos para "aumentar"/"reducir"
        //    una figura, NO podríamos hacer `class Circulo extends Figura
        //    extends Operable` (Java no permite herencia múltiple de clases).
        //    Tendríamos que escoger entre Figura y Operable, o reorganizar
        //    toda la jerarquía. Por eso, en la práctica, ese contrato lo
        //    expresamos como una INTERFAZ (justamente como hicimos con
        //    Escalable y Dibujable).
        // -----------------------------------------------------------------
    }
}
