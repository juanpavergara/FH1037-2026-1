package fundamentos.poo.figuras;

/*
 * Esta clase es DELIBERADAMENTE distinta de `Circulo` y `Rectangulo`:
 *
 *   - SÍ extiende `Figura` (es una figura más).
 *   - SÍ implementa `Dibujable` (sabe dibujarse).
 *   - NO implementa `Escalable`.
 *
 * ¿Por qué dejarla por fuera de Escalable? Para enseñar dos cosas:
 *
 *   1. Que cada clase decide qué contratos cumplir; no es obligación
 *      implementar todas las interfaces del paquete.
 *
 *   2. Que en la demo principal podremos hacer un filtrado polimórfico
 *      con `instanceof Escalable` y veremos que el triángulo NO entra
 *      en ese filtro. Esto demuestra el valor real de las interfaces:
 *      separar capacidades.
 *
 * Para el cálculo del área usamos la fórmula de Herón, que solo necesita
 * los tres lados (no requiere conocer altura ni ángulos).
 */
public class Triangulo extends Figura implements Dibujable {

    private final double ladoA;
    private final double ladoB;
    private final double ladoC;

    public Triangulo(String nombre, String color, double a, double b, double c) {
        super(nombre, color);
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException("Los lados deben ser mayores que 0.");
        }
        // Desigualdad triangular: la suma de dos lados debe superar al tercero.
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException(
                "Los lados " + a + ", " + b + ", " + c + " no forman un triángulo."
            );
        }
        this.ladoA = a;
        this.ladoB = b;
        this.ladoC = c;
    }

    @Override
    public double calcularArea() {
        // Fórmula de Herón:
        //   s = (a + b + c) / 2
        //   area = sqrt( s (s-a) (s-b) (s-c) )
        double s = (ladoA + ladoB + ladoC) / 2.0;
        return Math.sqrt(s * (s - ladoA) * (s - ladoB) * (s - ladoC));
    }

    @Override
    public double calcularPerimetro() {
        return ladoA + ladoB + ladoC;
    }

    @Override
    public void dibujar() {
        // Dibujo ASCII genérico de un triángulo (no depende de los lados reales).
        System.out.println("    ^    ");
        System.out.println("   / \\   ");
        System.out.println("  /   \\  ");
        System.out.println(" /     \\ ");
        System.out.println("/_______\\  <- Triangulo lados " + ladoA + ", " + ladoB + ", " + ladoC);
    }
}
