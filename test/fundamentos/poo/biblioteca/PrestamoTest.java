package fundamentos.poo.biblioteca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Cubre Prestamo y su superclase abstracta Transaccion. */
class PrestamoTest {

    private static final LocalDate HOY = LocalDate.of(2026, 3, 10);

    private Miembro ana;
    private Libro libro;
    private Copia copia;

    @BeforeEach
    void preparar() {
        ana = new Estudiante("E-1", "Ana", "Sis");
        libro = new Libro("123", "T");
        copia = libro.crearCopia("C-1");
    }

    @Test
    void creaPrestamoActivoYOcupaLaCopia() {
        Prestamo p = new Prestamo(" T-1 ", HOY, ana, copia, 7);
        assertEquals("T-1", p.getId());
        assertEquals(HOY, p.getFecha());
        assertSame(ana, p.getMiembro());
        assertSame(copia, p.getCopia());
        assertSame(libro, p.getLibro());
        assertEquals(HOY.plusDays(7), p.getFechaVencimiento());
        assertNull(p.getFechaDevolucion());
        assertTrue(p.estaActivo());
        assertEquals("PRESTAMO", p.getTipo());
        assertFalse(copia.estaDisponible());
    }

    @Test
    void validaParametros() {
        assertThrows(IllegalArgumentException.class, () -> new Prestamo(null, HOY, ana, copia, 7));
        assertThrows(IllegalArgumentException.class, () -> new Prestamo(" ", HOY, ana, copia, 7));
        assertThrows(NullPointerException.class, () -> new Prestamo("T-1", null, ana, copia, 7));
        assertThrows(NullPointerException.class, () -> new Prestamo("T-1", HOY, null, copia, 7));
        assertThrows(NullPointerException.class, () -> new Prestamo("T-1", HOY, ana, null, 7));
        assertThrows(IllegalArgumentException.class, () -> new Prestamo("T-1", HOY, ana, copia, 0));
        assertTrue(copia.estaDisponible());
    }

    @Test
    void noSePuedePrestarUnaCopiaOcupada() {
        new Prestamo("T-1", HOY, ana, copia, 7);
        assertThrows(IllegalStateException.class, () -> new Prestamo("T-2", HOY, ana, copia, 7));
    }

    @Test
    void devolverLiberaLaCopiaYSoloSePuedeUnaVez() {
        Prestamo p = new Prestamo("T-1", HOY, ana, copia, 7);
        LocalDate devolucion = HOY.plusDays(3);
        p.devolver(devolucion);

        assertFalse(p.estaActivo());
        assertEquals(devolucion, p.getFechaDevolucion());
        assertTrue(copia.estaDisponible());
        assertThrows(IllegalStateException.class, () -> p.devolver(devolucion));

        // La copia liberada puede prestarse de nuevo.
        new Prestamo("T-2", HOY, ana, copia, 7);
        assertFalse(copia.estaDisponible());
    }

    @Test
    void devolverRequiereFecha() {
        Prestamo p = new Prestamo("T-1", HOY, ana, copia, 7);
        assertThrows(NullPointerException.class, () -> p.devolver(null));
        assertTrue(p.estaActivo());
    }

    @Test
    void toStringDePrestamo() {
        String s = new Prestamo("T-1", HOY, ana, copia, 7).toString();
        assertEquals("Prestamo{id='T-1', fecha=2026-03-10, miembro=Estudiante{id='E-1', nombre='Ana'}, "
                + "copia=Copia{id='C-1', libroIsbn='123', disponible=false}, "
                + "fechaVencimiento=2026-03-17, fechaDevolucion=null}", s);
    }

    @Test
    void toStringDeTransaccionUsaGetTipoPolimorfico() {
        Transaccion t = new Transaccion("X-1", HOY, ana) {
            @Override
            public String getTipo() {
                return "OTRA";
            }
        };
        assertEquals("Transaccion{tipo='OTRA', id='X-1', fecha=2026-03-10, "
                + "miembro=Estudiante{id='E-1', nombre='Ana'}}", t.toString());
    }
}
