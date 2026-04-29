package fundamentos.poo.biblioteca;

import java.time.LocalDate;
import java.util.Objects;

public class Prestamo extends Transaccion {
    // ASOCIACIÓN bidireccional (1..1 desde este lado): un Prestamo referencia a una Copia.
    // La cara opuesta está en Copia.prestamoActivo (0..1) — juntas modelan la invariante
    // "a lo sumo un préstamo activo por copia". AggregationKind = none en ambos extremos.
    // Ref.: UML 2.5.1, §11.5 Associations — https://www.omg.org/spec/UML/2.5.1/PDF
    private final Copia copia;
    private final LocalDate fechaVencimiento;
    private LocalDate fechaDevolucion;

    public Prestamo(String id, LocalDate fecha, Miembro miembro, Copia copia, int diasPrestamo) {
        super(id, fecha, miembro);
        if (diasPrestamo <= 0) {
            throw new IllegalArgumentException("Los días de préstamo deben ser > 0.");
        }
        this.copia = Objects.requireNonNull(copia, "La copia es obligatoria.");
        if (!copia.estaDisponible()) {
            throw new IllegalStateException("La copia no está disponible para préstamo.");
        }

        this.fechaVencimiento = fecha.plusDays(diasPrestamo);
        this.fechaDevolucion = null;

        // Asegura la relación 1-1 (activo) entre Prestamo y Copia.
        copia.asignarPrestamoActivo(this);
    }

    @Override
    public String getTipo() {
        return "PRESTAMO";
    }

    public Copia getCopia() {
        return copia;
    }

    public Libro getLibro() {
        return copia.getLibro();
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public boolean estaActivo() {
        return fechaDevolucion == null;
    }

    public void devolver(LocalDate fechaDevolucion) {
        if (!estaActivo()) {
            throw new IllegalStateException("El préstamo ya fue devuelto.");
        }
        this.fechaDevolucion = Objects.requireNonNull(fechaDevolucion, "La fecha de devolución es obligatoria.");
        copia.cerrarPrestamoActivo();
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "id='" + getId() + '\'' +
                ", fecha=" + getFecha() +
                ", miembro=" + getMiembro() +
                ", copia=" + copia +
                ", fechaVencimiento=" + fechaVencimiento +
                ", fechaDevolucion=" + fechaDevolucion +
                '}';
    }
}

