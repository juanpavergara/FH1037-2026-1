package fundamentos.poo.figuras;

/*
 * =============================================================================
 *  INTERFAZ CON IMPLEMENTACIÓN PARCIAL  (default methods, Java 8+)
 * =============================================================================
 *
 * Desde Java 8, las interfaces pueden traer métodos `default` con cuerpo.
 * Esto las acerca un poco a las clases abstractas, pero sin perder la
 * gran ventaja de las interfaces: una clase puede implementar VARIAS.
 *
 * ¿Para qué sirven los default methods?
 *
 *   1. Para evolucionar interfaces sin romper código existente:
 *      si agregamos un método nuevo `default`, las clases que ya
 *      implementaban la interfaz siguen compilando.
 *
 *   2. Para ofrecer comportamiento "gratis" derivado de los métodos
 *      abstractos del contrato. Aquí, `dibujarConBorde(...)` se apoya
 *      en `dibujar()`, así que cualquier clase que implemente
 *      `dibujar()` obtiene `dibujarConBorde(...)` sin escribir más código.
 *
 * Importante: las interfaces SIGUEN sin poder tener atributos de instancia.
 * Solo pueden declarar `static final` (constantes).
 *
 * Compare con `Escalable`: aquella es una interfaz pura, sin defaults.
 * Esta es una interfaz con implementación parcial.
 */
public interface Dibujable {

    // ----- Método ABSTRACTO: parte obligatoria del contrato -----

    /*
     * Cada figura concreta decide CÓMO se dibuja.
     */
    void dibujar();

    // ----- Métodos DEFAULT: implementación reutilizable -----

    /*
     * Comportamiento "gratis" para todo Dibujable. Reusa `dibujar()`
     * mediante despacho dinámico: en tiempo de ejecución se llama a la
     * versión concreta del objeto real (Circulo, Rectangulo, ...).
     */
    default void dibujarConBorde(int grosor) {
        if (grosor < 1) {
            throw new IllegalArgumentException("El grosor del borde debe ser >= 1.");
        }
        String borde = "*".repeat(20 + grosor * 2);
        System.out.println(borde);
        for (int i = 0; i < grosor; i++) {
            System.out.println("*  borde  *");
        }
        dibujar(); // <-- llamada polimórfica al método abstracto
        for (int i = 0; i < grosor; i++) {
            System.out.println("*  borde  *");
        }
        System.out.println(borde);
    }

    /*
     * Otro default: una etiqueta genérica para cualquier dibujable.
     * Las subclases pueden sobreescribirlo si quieren personalizar el texto.
     */
    default String etiqueta() {
        return "[Figura dibujable]";
    }
}
