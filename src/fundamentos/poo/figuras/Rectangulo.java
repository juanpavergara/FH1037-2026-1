package fundamentos.poo.figuras;

/*
 * Otra clase concreta. Igual que `Circulo`, hereda de `Figura` e
 * implementa `Escalable` y `Dibujable`. Esto refuerza la idea de
 * que distintas subclases pueden cumplir los mismos contratos pero
 * con implementaciones totalmente distintas.
 */
public class Rectangulo extends Figura implements Escalable, Dibujable {

    private double base;
    private double altura;

    public Rectangulo(String nombre, String color, double base, double altura) {
        super(nombre, color);
        if (base <= 0 || altura <= 0) {
            throw new IllegalArgumentException("Base y altura deben ser mayores que 0.");
        }
        this.base = base;
        this.altura = altura;
    }

    public double getBase() {
        return base;
    }

    public double getAltura() {
        return altura;
    }

    @Override
    public double calcularArea() {
        return base * altura;
    }

    @Override
    public double calcularPerimetro() {
        return 2 * (base + altura);
    }

    @Override
    public void escalar(double factor) {
        if (factor <= 0) {
            throw new IllegalArgumentException("El factor de escala debe ser > 0.");
        }
        // Para un rectángulo, escalar significa multiplicar AMBAS dimensiones.
        // Así el área final crece en factor^2, lo cual es consistente con
        // el comportamiento del círculo (cuyo área crece también en factor^2).
        this.base *= factor;
        this.altura *= factor;
    }

    @Override
    public void dibujar() {
        // Dibujo ASCII del rectángulo, escalado a un tamaño "razonable" para consola.
        int filas = Math.max(1, (int) Math.round(altura));
        int columnas = Math.max(2, (int) Math.round(base));
        String linea = "#".repeat(columnas);
        for (int i = 0; i < filas; i++) {
            System.out.println(linea);
        }
        System.out.println("<- Rectangulo " + base + " x " + altura);
    }

    // Personalizamos la etiqueta default de Dibujable: una subclase puede
    // sobrescribir un default method igual que cualquier método heredado.
    @Override
    public String etiqueta() {
        return "[Rectangulo " + base + "x" + altura + "]";
    }
}
