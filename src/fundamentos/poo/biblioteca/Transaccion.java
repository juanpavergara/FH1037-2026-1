package fundamentos.poo.biblioteca;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Transaccion {
    private final String id;
    private final LocalDate fecha;

    // ASOCIACIÓN unidireccional (1..1): toda Transacción referencia a un Miembro.
    // El Miembro es independiente: no es parte ni propiedad de la Transacción
    // (AggregationKind = none).
    // Ref.: UML 2.5.1, §11.5 Associations — https://www.omg.org/spec/UML/2.5.1/PDF
    private final Miembro miembro;

    protected Transaccion(String id, LocalDate fecha, Miembro miembro) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El id de la transacción es obligatorio.");
        }
        this.id = id.trim();
        this.fecha = Objects.requireNonNull(fecha, "La fecha es obligatoria.");
        this.miembro = Objects.requireNonNull(miembro, "El miembro es obligatorio.");
    }

    public String getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Miembro getMiembro() {
        return miembro;
    }

    public abstract String getTipo();

    @Override
    public String toString() {
        return "Transaccion{tipo='" + getTipo() + "', id='" + id + "', fecha=" + fecha + ", miembro=" + miembro + "}";
    }
}

