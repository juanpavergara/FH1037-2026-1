package fundamentos.poo.figuras;

/*
 * =============================================================================
 *  CLASE ABSTRACTA PURA  (todos los métodos abstractos, sin estado)
 * =============================================================================
 *
 * Esta clase es PEDAGÓGICA. No la usamos en la demo: existe para
 * mostrar qué se podía hacer ANTES de tener interfaces y por qué Java
 * decidió introducir `interface` como mecanismo separado.
 *
 * Características de una "clase abstracta pura":
 *
 *   - Es `abstract`, así que no se puede instanciar.
 *   - No tiene atributos de instancia.
 *   - No tiene constructores útiles (ningún estado que inicializar).
 *   - TODOS sus métodos son abstractos.
 *
 * Es decir, se comporta como un contrato puro... pero sigue siendo
 * una CLASE. Y aquí está la limitación crítica:
 *
 *   ┌───────────────────────────────────────────────────────────────┐
 *   │  Java tiene HERENCIA SIMPLE de clases:                        │
 *   │  una clase solo puede `extends` UNA clase a la vez.           │
 *   └───────────────────────────────────────────────────────────────┘
 *
 * Consecuencia: si `Circulo` ya `extends Figura`, NO puede además
 * `extends Operable`. Tendríamos que elegir entre una jerarquía u otra.
 *
 * Esa es exactamente la razón por la que existen las interfaces:
 *
 *   - Una clase puede `implements A, B, C, ...` cuantas quiera.
 *   - Así, una figura puede ser "una Figura" Y "Escalable" Y
 *     "Dibujable" al mismo tiempo, sin colisión de jerarquías.
 *
 * En Java moderno, este tipo de clase NO se debe usar. Cuando
 * queramos un contrato puro sin estado, escribimos una `interface`.
 *
 * NOTA: deliberadamente esta clase no tiene subclases en el ejemplo.
 * Su propósito es servir de contraste conceptual con `Escalable`.
 */
public abstract class Operable {

    /*
     * Aumenta la magnitud (lo que sea que eso signifique para la subclase)
     * en un porcentaje dado. Sin cuerpo: contrato puro.
     */
    public abstract void aumentar(double porcentaje);

    /*
     * Reduce la magnitud en un porcentaje dado.
     */
    public abstract void reducir(double porcentaje);
}
