package fundamentos.poo.biblioteca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class LibroYCopiaTest {

    @Test
    void libroValidaYRecortaCampos() {
        Libro l = new Libro(" 123 ", " Título ");
        assertEquals("123", l.getIsbn());
        assertEquals("Título", l.getTitulo());
        l.setTitulo("Otro");
        assertEquals("Otro", l.getTitulo());

        assertThrows(IllegalArgumentException.class, () -> new Libro(null, "T"));
        assertThrows(IllegalArgumentException.class, () -> new Libro(" ", "T"));
        assertThrows(IllegalArgumentException.class, () -> new Libro("1", " "));
        assertThrows(IllegalArgumentException.class, () -> l.setTitulo(null));
    }

    @Test
    void crearCopiaLaAgregaYLaVinculaAlLibro() {
        Libro l = new Libro("123", "T");
        Copia c1 = l.crearCopia(" C-1 ");
        Copia c2 = l.crearCopia("C-2");

        assertEquals(2, l.getCopias().size());
        assertEquals("C-1", c1.getId());
        assertSame(l, c1.getLibro());
        assertSame(c1, l.buscarCopiaPorId("C-1"));
        assertSame(c2, l.buscarCopiaPorId("C-2"));
        assertNull(l.buscarCopiaPorId("C-9"));
    }

    @Test
    void copiasSonInmodificablesDesdeAfuera() {
        Libro l = new Libro("123", "T");
        l.crearCopia("C-1");
        assertThrows(UnsupportedOperationException.class, () -> l.getCopias().clear());
    }

    @Test
    void crearCopiaValidaId() {
        Libro l = new Libro("123", "T");
        assertThrows(IllegalArgumentException.class, () -> l.crearCopia(null));
        assertThrows(IllegalArgumentException.class, () -> l.crearCopia(" "));
        assertThrows(NullPointerException.class, () -> new Copia("C-1", null));
    }

    @Test
    void libroEqualsYHashCodePorIsbn() {
        Libro a = new Libro("123", "A");
        Libro b = new Libro("123", "B");
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, new Libro("456", "A"));
        assertNotEquals(a, "123");
        assertEquals(a, a);
    }

    @Test
    void copiaEqualsYHashCodePorIdYLibro() {
        Libro l1 = new Libro("123", "A");
        Libro l2 = new Libro("456", "B");
        Copia a = l1.crearCopia("C-1");
        Copia b = new Copia("C-1", new Libro("123", "otro título"));
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, l2.crearCopia("C-1"));
        assertNotEquals(a, l1.crearCopia("C-2"));
        assertNotEquals(a, "C-1");
        assertEquals(a, a);
    }

    @Test
    void copiaGestionaUnSoloPrestamoActivo() {
        Libro l = new Libro("123", "T");
        Copia c = l.crearCopia("C-1");
        Miembro m = new Estudiante("E-1", "Ana", "Sis");
        assertTrue(c.estaDisponible());
        assertNull(c.getPrestamoActivo());

        Prestamo p = new Prestamo("T-1", LocalDate.of(2026, 1, 1), m, c, 7);
        assertFalse(c.estaDisponible());
        assertSame(p, c.getPrestamoActivo());
        assertThrows(IllegalStateException.class, () -> c.asignarPrestamoActivo(p));

        c.cerrarPrestamoActivo();
        assertTrue(c.estaDisponible());
        assertThrows(NullPointerException.class, () -> c.asignarPrestamoActivo(null));
    }

    @Test
    void toStrings() {
        Libro l = new Libro("123", "T");
        Copia c = l.crearCopia("C-1");
        assertEquals("Libro{isbn='123', titulo='T', copias=1}", l.toString());
        assertEquals("Copia{id='C-1', libroIsbn='123', disponible=true}", c.toString());
    }
}
