package fundamentos.poo.biblioteca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fundamentos.Consola;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BibliotecaTest {

    private Biblioteca biblioteca;
    private Miembro ana;
    private Libro libro;

    @BeforeEach
    void preparar() {
        biblioteca = new Biblioteca("Central");
        ana = new Estudiante("E-1", "Ana", "Sis");
        libro = new Libro("123", "T");
        libro.crearCopia("C-1");
        biblioteca.registrarMiembro(ana);
        biblioteca.registrarLibro(libro);
    }

    @Test
    void nombreObligatorio() {
        assertEquals("Central", new Biblioteca(" Central ").getNombre());
        assertThrows(IllegalArgumentException.class, () -> new Biblioteca(null));
        assertThrows(IllegalArgumentException.class, () -> new Biblioteca("  "));
    }

    @Test
    void registraYBuscaMiembrosYLibros() {
        Miembro luis = new Empleado("F-1", "Luis", "Cargo");
        biblioteca.registrarMiembro(luis);

        assertSame(ana, biblioteca.buscarMiembro("E-1"));
        assertNull(biblioteca.buscarMiembro("nadie"));
        assertEquals(List.of(ana, luis), biblioteca.getMiembros());
        assertSame(libro, biblioteca.buscarLibro("123"));
        assertNull(biblioteca.buscarLibro("999"));

        assertThrows(NullPointerException.class, () -> biblioteca.registrarMiembro(null));
        assertThrows(NullPointerException.class, () -> biblioteca.registrarLibro(null));
    }

    @Test
    void registrarMismoIdReemplazaAlMiembro() {
        Miembro otraAna = new Empleado("E-1", "Ana B", "Cargo");
        biblioteca.registrarMiembro(otraAna);
        assertEquals(1, biblioteca.getMiembros().size());
        assertSame(otraAna, biblioteca.buscarMiembro("E-1"));
    }

    @Test
    void listasExpuestasSonInmodificables() {
        assertThrows(UnsupportedOperationException.class, () -> biblioteca.getMiembros().clear());
        assertThrows(UnsupportedOperationException.class, () -> biblioteca.getTransacciones().clear());
    }

    @Test
    void prestarCreaPrestamoYLoGuardaEnElHistorial() {
        Prestamo p = biblioteca.prestar("T-1", "E-1", "123", "C-1", 7);

        assertSame(ana, p.getMiembro());
        assertSame(libro, p.getLibro());
        assertEquals(LocalDate.now(), p.getFecha());
        assertEquals(LocalDate.now().plusDays(7), p.getFechaVencimiento());
        assertEquals(List.of(p), biblioteca.getTransacciones());
        assertTrue(p.estaActivo());
    }

    @Test
    void prestarValidaMiembroLibroYCopia() {
        assertThrows(IllegalArgumentException.class, () -> biblioteca.prestar("T-1", "nadie", "123", "C-1", 7));
        assertThrows(IllegalArgumentException.class, () -> biblioteca.prestar("T-1", "E-1", "999", "C-1", 7));
        assertThrows(IllegalArgumentException.class, () -> biblioteca.prestar("T-1", "E-1", "123", "C-9", 7));
        assertTrue(biblioteca.getTransacciones().isEmpty());
    }

    @Test
    void prestarRechazaMiembroQueNoPuedePrestar() {
        Miembro bloqueado = new Estudiante("E-2", "Bloqueado", "Sis") {
            @Override
            public boolean puedePrestar() {
                return false;
            }
        };
        biblioteca.registrarMiembro(bloqueado);
        assertThrows(IllegalStateException.class, () -> biblioteca.prestar("T-1", "E-2", "123", "C-1", 7));
    }

    @Test
    void prestarCopiaOcupadaFallaYNoSeRegistra() {
        biblioteca.prestar("T-1", "E-1", "123", "C-1", 7);
        assertThrows(IllegalStateException.class, () -> biblioteca.prestar("T-2", "E-1", "123", "C-1", 7));
        assertEquals(1, biblioteca.getTransacciones().size());
    }

    @Test
    void bibliotecaDemoSeEjecuta() {
        String salida = Consola.capturar(() -> BibliotecaDemo.main(new String[0]));
        assertTrue(salida.contains("Préstamo creado: Prestamo{id='T-001'"));
        assertTrue(salida.contains("Prestatario (tipo dinámico Estudiante): Ana Gómez"));
        assertTrue(salida.contains("[PRESTAMO]"));
    }
}
