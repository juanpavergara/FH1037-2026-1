package fundamentos.poo.biblioteca;

import java.util.Objects;

public class Copia {
    private final String id;

    // COMPOSICIÓN — navegabilidad inversa (1..1): referencia al Libro contenedor.
    // No es una asociación independiente: es la cara 'parte' de la composición declarada en
    // Libro.copias (una misma relación binaria con AggregationKind = composite del lado del Libro).
    // Ref.: UML 2.5.1, §11.5 Associations / AggregationKind — https://www.omg.org/spec/UML/2.5.1/PDF
    private final Libro libro;

    // ASOCIACIÓN bidireccional (0..1): la Copia conoce a su Prestamo activo.
    // La cara opuesta está en Prestamo.copia (1..1). La invariante "una Copia tiene a lo sumo
    // un Prestamo activo" se mantiene vía asignarPrestamoActivo / cerrarPrestamoActivo.
    // Ref.: UML 2.5.1, §11.5 Associations — https://www.omg.org/spec/UML/2.5.1/PDF
    private Prestamo prestamoActivo;

    Copia(String id, Libro libro) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El id de la copia es obligatorio.");
        }
        this.id = id.trim();
        this.libro = Objects.requireNonNull(libro, "El libro es obligatorio.");
    }

    public String getId() {
        return id;
    }

    public Libro getLibro() {
        return libro;
    }

    public boolean estaDisponible() {
        return prestamoActivo == null;
    }

    public Prestamo getPrestamoActivo() {
        return prestamoActivo;
    }

    void asignarPrestamoActivo(Prestamo prestamo) {
        if (prestamoActivo != null) {
            throw new IllegalStateException("La copia ya tiene un préstamo activo.");
        }
        this.prestamoActivo = Objects.requireNonNull(prestamo, "El préstamo es obligatorio.");
    }

    void cerrarPrestamoActivo() {
        this.prestamoActivo = null;
    }

    @Override
    public String toString() {
        return "Copia{id='" + id + "', libroIsbn='" + libro.getIsbn() + "', disponible=" + estaDisponible() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Copia)) return false;
        Copia copia = (Copia) o;
        return id.equals(copia.id) && libro.equals(copia.libro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, libro);
    }
}

