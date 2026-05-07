package fundamentos.poo.figuras;

/*
 * =============================================================================
 *  CLASE CONCRETA  —  Herencia + múltiples interfaces
 * =============================================================================
 *
 *   `extends Figura`              → herencia de clase (única).
 *   `implements Escalable, Dibujable`  → implementación de varias interfaces.
 *
 * Este es el "milagro" que hace posible el sistema de tipos de Java:
 *   - Un Circulo ES-UN Figura          (subtipo por herencia).
 *   - Un Circulo ES-UN Escalable       (subtipo por interfaz).
 *   - Un Circulo ES-UN Dibujable       (subtipo por interfaz).
 *
 * Esto es POLIMORFISMO: el mismo objeto puede tratarse bajo distintos
 * "lentes" según el contrato que nos interese en cada momento.
 */
public class Circulo extends Figura implements Escalable, Dibujable {

    private double radio;

    public Circulo(String nombre, String color, double radio) {
        // `super(...)` llama al constructor de la clase abstracta padre.
        // Es OBLIGATORIO porque Figura no tiene constructor sin argumentos.
        super(nombre, color);
        if (radio <= 0) {
            throw new IllegalArgumentException("El radio debe ser mayor que 0.");
        }
        this.radio = radio;
    }

    public double getRadio() {
        return radio;
    }

    // ----- Implementación de los métodos abstractos de Figura -----

    @Override
    public double calcularArea() {
        return Math.PI * radio * radio;
    }

    @Override
    public double calcularPerimetro() {
        return 2 * Math.PI * radio;
    }

    // ----- Implementación del contrato Escalable -----

    @Override
    public void escalar(double factor) {
        if (factor <= 0) {
            throw new IllegalArgumentException("El factor de escala debe ser > 0.");
        }
        this.radio *= factor;
    }

    // ----- Implementación del contrato Dibujable -----

    @Override
    public void dibujar() {
        // Representación ASCII muy simplificada de un círculo.
        System.out.println("   ***   ");
        System.out.println(" *     * ");
        System.out.println("*   o   *   <- Circulo radio=" + radio);
        System.out.println(" *     * ");
        System.out.println("   ***   ");
    }
}
