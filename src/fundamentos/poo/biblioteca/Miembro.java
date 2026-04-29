package fundamentos.poo.biblioteca;

import java.util.Objects;

public abstract class Miembro {
    private final String id;
    private String nombre;

    protected Miembro(String id, String nombre) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El id del miembro es obligatorio.");
        }
        this.id = id.trim();
        setNombre(nombre);
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        this.nombre = nombre.trim();
    }

    public boolean puedePrestar() {
        return true;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{id='" + id + "', nombre='" + nombre + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Miembro)) return false;
        Miembro miembro = (Miembro) o;
        return id.equals(miembro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

