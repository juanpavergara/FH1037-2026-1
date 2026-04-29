package fundamentos.poo.biblioteca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Biblioteca {
    private final String nombre;

    // RELACIÓN (AGREGACIÓN 0..*): Biblioteca "tiene" muchos Miembros.
    // - Los Miembros se crean fuera y se registran aquí; su ciclo de vida NO depende de la Biblioteca.
    // - En UML: AggregationKind = shared (rombo vacío del lado del 'todo').
    //   Ref.: UML 2.5.1, §11.5 Associations / AggregationKind — https://www.omg.org/spec/UML/2.5.1/PDF
    private final Map<String, Miembro> miembrosPorId = new LinkedHashMap<>();

    // RELACIÓN (AGREGACIÓN 0..*): Biblioteca "tiene" muchos Libros.
    // - Los Libros pueden existir independientemente; aquí solo se referencian/registran.
    // - En UML: AggregationKind = shared.
    //   Ref.: UML 2.5.1, §11.5 Associations / AggregationKind — https://www.omg.org/spec/UML/2.5.1/PDF
    private final Map<String, Libro> librosPorIsbn = new LinkedHashMap<>();

    // RELACIÓN (COMPOSICIÓN 0..*): Biblioteca mantiene el historial de Transacciones.
    // - Las transacciones se crean en operaciones de la biblioteca y se guardan en su historial.
    // - En UML: AggregationKind = composite (rombo lleno; la 'parte' depende del 'todo').
    //   Ref.: UML 2.5.1, §11.5 Associations / AggregationKind — https://www.omg.org/spec/UML/2.5.1/PDF
    private final List<Transaccion> transacciones = new ArrayList<>();

    public Biblioteca(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la biblioteca es obligatorio.");
        }
        this.nombre = nombre.trim();
    }

    public String getNombre() {
        return nombre;
    }

    public void registrarMiembro(Miembro miembro) {
        Objects.requireNonNull(miembro, "El miembro es obligatorio.");
        miembrosPorId.put(miembro.getId(), miembro);
    }

    public Miembro buscarMiembro(String idMiembro) {
        return miembrosPorId.get(idMiembro);
    }

    public List<Miembro> getMiembros() {
        return Collections.unmodifiableList(new ArrayList<>(miembrosPorId.values()));
    }

    public void registrarLibro(Libro libro) {
        Objects.requireNonNull(libro, "El libro es obligatorio.");
        librosPorIsbn.put(libro.getIsbn(), libro);
    }

    public Libro buscarLibro(String isbn) {
        return librosPorIsbn.get(isbn);
    }

    public List<Transaccion> getTransacciones() {
        return Collections.unmodifiableList(transacciones);
    }

    /**
     * Este método ejerce dos relaciones distintas:
     *  - DEPENDENCIA / USO: recibe Miembro, Libro y Copia por parámetro (los "usa" sin poseerlos).
     *    Ref.: UML 2.5.1, §7.8 Dependencies — https://www.omg.org/spec/UML/2.5.1/PDF
     *  - El Prestamo resultante MATERIALIZA una ASOCIACIÓN entre Miembro y Copia, y queda
     *    bajo la composición Biblioteca→Transaccion declarada arriba.
     *    Ref.: UML 2.5.1, §11.5 Associations — https://www.omg.org/spec/UML/2.5.1/PDF
     */
    public Prestamo prestar(String idTransaccion, String idMiembro, String isbn, String idCopia, int diasPrestamo) {
        Miembro miembro = buscarMiembro(idMiembro);
        if (miembro == null) {
            throw new IllegalArgumentException("No existe el miembro con id: " + idMiembro);
        }
        if (!miembro.puedePrestar()) {
            throw new IllegalStateException("El miembro no puede realizar préstamos.");
        }

        Libro libro = buscarLibro(isbn);
        if (libro == null) {
            throw new IllegalArgumentException("No existe el libro con ISBN: " + isbn);
        }

        Copia copia = libro.buscarCopiaPorId(idCopia);
        if (copia == null) {
            throw new IllegalArgumentException("No existe la copia '" + idCopia + "' para el libro con ISBN: " + isbn);
        }

        Prestamo prestamo = new Prestamo(idTransaccion, LocalDate.now(), miembro, copia, diasPrestamo);
        transacciones.add(prestamo);
        return prestamo;
    }
}

