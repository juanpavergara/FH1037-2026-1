package fundamentos.poo.figuras;

/*
 * =============================================================================
 *  INTERFAZ PURA TRADICIONAL  (estilo pre-Java 8)
 * =============================================================================
 *
 * Una interfaz, en su forma más clásica, es un CONTRATO sin implementación:
 *
 *   - Solo declara métodos abstractos (sin cuerpo).
 *   - No tiene estado (no puede declarar atributos de instancia).
 *   - No tiene constructores.
 *   - Una clase puede implementar VARIAS interfaces (`implements A, B, C`).
 *     Esto resuelve la limitación de la herencia simple de clases.
 *
 * Diferencia clave con la "clase abstracta pura" (`Operable`):
 *   - Una clase solo puede EXTENDER UNA clase (abstracta o no).
 *   - Una clase puede IMPLEMENTAR MUCHAS interfaces.
 *
 * Por eso, en Java moderno, cuando lo único que queremos es declarar
 * un contrato sin estado, usamos una INTERFAZ y no una clase abstracta.
 *
 * Esta interfaz es "pura" porque NO tiene métodos `default`. Es decir,
 * todo el peso de la implementación recae en la clase que la implemente.
 * Compare con `Dibujable`, que sí trae implementación parcial.
 */
public interface Escalable {

    /*
     * Contrato: "todo Escalable debe saber escalarse por un factor".
     *
     *   - factor > 1  → la figura crece.
     *   - factor < 1  → la figura se reduce.
     *   - factor == 1 → no hay cambio.
     *
     * Cómo se implemente esto depende totalmente de la figura concreta:
     *   - Un círculo escala su radio.
     *   - Un rectángulo escala su base y su altura.
     */
    void escalar(double factor);
}
