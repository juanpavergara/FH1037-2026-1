package fundamentos.poo.biblioteca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Libro {
    private final String isbn;
    private String titulo;

    // RELACIÓN (COMPOSICIÓN 0..*): un Libro "posee" muchas Copias.
    // - El Libro crea las Copias (ver crearCopia) y mantiene su colección.
    // - En UML: AggregationKind = composite. La parte (Copia) no existe sin el todo (Libro).
    //   Ref.: UML 2.5.1, §11.5 Associations / AggregationKind — https://www.omg.org/spec/UML/2.5.1/PDF
    private final List<Copia> copias = new ArrayList<>();

    public Libro(String isbn, String titulo) {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("El ISBN es obligatorio.");
        }
        this.isbn = isbn.trim();
        setTitulo(titulo);
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("El título es obligatorio.");
        }
        this.titulo = titulo.trim();
    }

    /**
     * RELACIÓN (COMPOSICIÓN): el Libro crea y "posee" sus Copias.
     * La referencia que la Copia mantiene a su Libro NO es una asociación independiente:
     * es la navegabilidad inversa de esta misma composición (una sola relación binaria,
     * con el rombo lleno del lado del Libro).
     * Ref.: UML 2.5.1, §11.5 Associations / AggregationKind = composite — https://www.omg.org/spec/UML/2.5.1/PDF
     */
    public Copia crearCopia(String idCopia) {
        Copia copia = new Copia(idCopia, this);
        copias.add(copia);
        return copia;
    }

    public List<Copia> getCopias() {
        return Collections.unmodifiableList(copias);
    }

    public Copia buscarCopiaPorId(String idCopia) {
        for (Copia c : copias) {
            if (c.getId().equals(idCopia)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Libro{isbn='" + isbn + "', titulo='" + titulo + "', copias=" + copias.size() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Libro)) return false;
        Libro libro = (Libro) o;
        return isbn.equals(libro.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}

