package fundamentos.poo.biblioteca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/** Cubre Miembro (abstracta) a través de sus subclases Estudiante y Empleado. */
class MiembroTest {

    @Test
    void estudianteInicializaYRecortaCampos() {
        Estudiante e = new Estudiante(" E-1 ", " Ana ", " Sistemas ");
        assertEquals("E-1", e.getId());
        assertEquals("Ana", e.getNombre());
        assertEquals("Sistemas", e.getPrograma());
        assertTrue(e.puedePrestar());
    }

    @Test
    void empleadoInicializaYRecortaCampos() {
        Empleado e = new Empleado("F-1", "Luis", " Bibliotecario ");
        assertEquals("Bibliotecario", e.getCargo());
        e.setCargo("Director");
        assertEquals("Director", e.getCargo());
        assertTrue(e.puedePrestar());
    }

    @Test
    void validaCamposObligatorios() {
        assertThrows(IllegalArgumentException.class, () -> new Estudiante(null, "Ana", "Sis"));
        assertThrows(IllegalArgumentException.class, () -> new Estudiante(" ", "Ana", "Sis"));
        assertThrows(IllegalArgumentException.class, () -> new Estudiante("E-1", " ", "Sis"));
        assertThrows(IllegalArgumentException.class, () -> new Estudiante("E-1", "Ana", null));
        assertThrows(IllegalArgumentException.class, () -> new Empleado("F-1", "Luis", ""));

        Estudiante e = new Estudiante("E-1", "Ana", "Sis");
        assertThrows(IllegalArgumentException.class, () -> e.setNombre(null));
        assertThrows(IllegalArgumentException.class, () -> e.setPrograma(" "));
    }

    @Test
    void equalsYHashCodeSeBasanEnElId() {
        Miembro a = new Estudiante("X-1", "Ana", "Sis");
        Miembro b = new Empleado("X-1", "Luis", "Cargo");
        Miembro c = new Estudiante("X-2", "Ana", "Sis");
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, c);
        assertNotEquals(a, "X-1");
        assertEquals(a, a);
    }

    @Test
    void toStringUsaElNombreDeLaSubclase() {
        assertEquals("Estudiante{id='E-1', nombre='Ana'}", new Estudiante("E-1", "Ana", "Sis").toString());
        assertEquals("Empleado{id='F-1', nombre='Luis'}", new Empleado("F-1", "Luis", "Cargo").toString());
    }
}
