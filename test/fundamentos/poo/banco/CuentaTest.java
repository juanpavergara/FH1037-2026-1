package fundamentos.poo.banco;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CuentaTest {

    private static final double DELTA = 1e-9;

    @Test
    void constructorInicializaYRecortaEspacios() {
        Cuenta c = new Cuenta("  C-1 ", "  Ana Pérez ", 1000);
        assertEquals("C-1", c.getNumero());
        assertEquals("Ana Pérez", c.getTitular());
        assertTrue(c.isActiva());
        assertEquals(1000, c.getSaldo(), DELTA);
    }

    @Test
    void constructorValidaDatos() {
        assertThrows(IllegalArgumentException.class, () -> new Cuenta(null, "Ana", 0));
        assertThrows(IllegalArgumentException.class, () -> new Cuenta("   ", "Ana", 0));
        assertThrows(IllegalArgumentException.class, () -> new Cuenta("C-1", "Al", 0));
        assertThrows(IllegalArgumentException.class, () -> new Cuenta("C-1", null, 0));
        assertThrows(IllegalArgumentException.class, () -> new Cuenta("C-1", "Ana", -1));
    }

    @Test
    void setTitularValidaLongitudMinima() {
        Cuenta c = new Cuenta("C-1", "Ana", 0);
        c.setTitular(" Beatriz ");
        assertEquals("Beatriz", c.getTitular());
        assertThrows(IllegalArgumentException.class, () -> c.setTitular("  B "));
    }

    @Test
    void depositarAumentaSaldo() {
        Cuenta c = new Cuenta("C-1", "Ana", 100);
        c.depositar(50);
        assertEquals(150, c.getSaldo(), DELTA);
    }

    @Test
    void depositarRechazaMontosNoPositivos() {
        Cuenta c = new Cuenta("C-1", "Ana", 100);
        assertThrows(IllegalArgumentException.class, () -> c.depositar(0));
        assertThrows(IllegalArgumentException.class, () -> c.depositar(-10));
        assertEquals(100, c.getSaldo(), DELTA);
    }

    @Test
    void retirarDisminuyeSaldoHastaCero() {
        Cuenta c = new Cuenta("C-1", "Ana", 100);
        c.retirar(100);
        assertEquals(0, c.getSaldo(), DELTA);
    }

    @Test
    void retirarRechazaMontosInvalidosYFondosInsuficientes() {
        Cuenta c = new Cuenta("C-1", "Ana", 100);
        assertThrows(IllegalArgumentException.class, () -> c.retirar(0));
        assertThrows(IllegalStateException.class, () -> c.retirar(100.01));
        assertEquals(100, c.getSaldo(), DELTA);
    }

    @Test
    void cuentaInactivaNoOperaHastaReactivarse() {
        Cuenta c = new Cuenta("C-1", "Ana", 100);
        c.desactivar();
        assertFalse(c.isActiva());
        assertThrows(IllegalStateException.class, () -> c.depositar(10));
        assertThrows(IllegalStateException.class, () -> c.retirar(10));
        c.activar();
        c.depositar(10);
        assertEquals(110, c.getSaldo(), DELTA);
    }

    @Test
    void transferirMueveElDinero() {
        Cuenta origen = new Cuenta("C-1", "Ana", 100);
        Cuenta destino = new Cuenta("C-2", "Luis", 0);
        origen.transferirA(destino, 40);
        assertEquals(60, origen.getSaldo(), DELTA);
        assertEquals(40, destino.getSaldo(), DELTA);
    }

    @Test
    void transferirValidaReglas() {
        Cuenta origen = new Cuenta("C-1", "Ana", 100);
        Cuenta destino = new Cuenta("C-2", "Luis", 0);

        assertThrows(IllegalArgumentException.class, () -> origen.transferirA(null, 10));
        assertThrows(IllegalArgumentException.class, () -> origen.transferirA(origen, 10));
        assertThrows(IllegalArgumentException.class, () -> origen.transferirA(destino, 0));
        assertThrows(IllegalStateException.class, () -> origen.transferirA(destino, 500));

        destino.desactivar();
        assertThrows(IllegalStateException.class, () -> origen.transferirA(destino, 10));

        destino.activar();
        origen.desactivar();
        assertThrows(IllegalStateException.class, () -> origen.transferirA(destino, 10));

        assertEquals(100, origen.getSaldo(), DELTA);
        assertEquals(0, destino.getSaldo(), DELTA);
    }

    @Test
    void toStringIncluyeLosCampos() {
        assertEquals("Cuenta{numero='C-1', titular='Ana', activa=true, saldo=100.0}",
                new Cuenta("C-1", "Ana", 100).toString());
    }
}
