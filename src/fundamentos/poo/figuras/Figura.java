package fundamentos.poo.figuras;

/*
 * =============================================================================
 *  CLASE ABSTRACTA  (con implementación parcial)
 * =============================================================================
 *
 * Una clase abstracta es un híbrido entre una clase concreta y un contrato:
 *
 *   - PUEDE tener estado (atributos) y constructores.
 *   - PUEDE tener métodos YA implementados que las subclases heredan.
 *   - PUEDE declarar métodos ABSTRACTOS (sin cuerpo) que cada subclase
 *     concreta está OBLIGADA a implementar.
 *   - NO se puede instanciar directamente: `new Figura(...)` es un error
 *     de compilación. Solo se instancian sus subclases concretas.
 *
 * ¿Cuándo usar una clase abstracta?
 *   Cuando tenemos una "familia" de clases que comparten estado y/o
 *   comportamiento, pero hay UNA parte del comportamiento que solo cada
 *   subclase puede definir (porque depende de su forma específica).
 *
 * En este ejemplo, todas las figuras tienen `nombre` y `color`, y todas
 * pueden producir una `descripcion()`. Pero NINGUNA Figura genérica sabe
 * calcular su área: eso depende de SI es un círculo, un rectángulo, etc.
 *
 * Compárese esto más adelante con `Operable` (clase abstracta PURA, sin
 * estado ni implementación) y con las interfaces.
 */
public abstract class Figura {

    // Estado COMPARTIDO por todas las subclases. Esto es algo que una
    // interfaz (incluso con default methods) NO podría tener: las
    // interfaces no pueden definir atributos de instancia.
    private final String nombre;
    private final String color;

    // Constructor `protected`: solo las subclases pueden llamarlo.
    // Refuerza que `Figura` no se instancia directamente.
    protected Figura(String nombre, String color) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la figura es obligatorio.");
        }
        if (color == null || color.isBlank()) {
            throw new IllegalArgumentException("El color de la figura es obligatorio.");
        }
        this.nombre = nombre.trim();
        this.color = color.trim();
    }

    // ----- Métodos CONCRETOS: implementación heredada por las subclases -----

    public String getNombre() {
        return nombre;
    }

    public String getColor() {
        return color;
    }

    /*
     * Este es el corazón del polimorfismo en este ejemplo.
     *
     * `descripcion()` está implementado AQUÍ, pero llama a `calcularArea()`,
     * que es ABSTRACTO. En tiempo de ejecución, Java escogerá la versión de
     * `calcularArea()` que corresponda al objeto real (Circulo, Rectangulo,
     * Triangulo). Esto se conoce como "despacho dinámico" (dynamic dispatch)
     * y es la base del polimorfismo de subtipo.
     */
    public String descripcion() {
        return String.format(
            "%s (%s, color %s) — área=%.2f, perímetro=%.2f",
            getClass().getSimpleName(), nombre, color,
            calcularArea(), calcularPerimetro()
        );
    }

    // ----- Métodos ABSTRACTOS: contrato obligatorio para las subclases -----

    /*
     * Sin cuerpo. La palabra clave `abstract` le dice al compilador:
     * "toda subclase concreta DEBE implementar este método". Si una
     * subclase no lo implementa, debe declararse también como abstracta.
     */
    public abstract double calcularArea();

    public abstract double calcularPerimetro();
}
