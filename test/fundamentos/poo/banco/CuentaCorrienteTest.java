package fundamentos.poo.banco;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CuentaCorrienteTest {

    private static final double DELTA = 1e-9;

    @Test
    void constructorValidaCupo() {
        assertThrows(IllegalArgumentException.class, () -> new CuentaCorriente("COR-1", "Carlos", 0, -1));
        CuentaCorriente c = new CuentaCorriente("COR-1", "Carlos", 0, 50);
        assertEquals(50, c.getCupoSobregiro(), DELTA);
        c.setCupoSobregiro(80);
        assertEquals(80, c.getCupoSobregiro(), DELTA);
    }

    @Test
    void permiteSobregiroHastaElCupo() {
        CuentaCorriente c = new CuentaCorriente("COR-1", "Carlos", 100, 50);
        c.retirar(150);
        assertEquals(-50, c.getSaldo(), DELTA);
        assertThrows(IllegalStateException.class, () -> c.retirar(0.01));
    }

    @Test
    void sobregiroDisponibleSeCalculaSegunSaldo() {
        CuentaCorriente c = new CuentaCorriente("COR-1", "Carlos", 100, 50);
        assertEquals(50, c.getSobregiroDisponible(), DELTA);
        c.retirar(120);
        assertEquals(30, c.getSobregiroDisponible(), DELTA);
        c.retirar(30);
        assertEquals(0, c.getSobregiroDisponible(), DELTA);
    }

    @Test
    void sobregiroDisponibleNuncaEsNegativoSiSeReduceElCupo() {
        CuentaCorriente c = new CuentaCorriente("COR-1", "Carlos", 0, 50);
        c.retirar(40);
        c.setCupoSobregiro(10);
        assertEquals(0, c.getSobregiroDisponible(), DELTA);
    }

    @Test
    void transferenciaPuedeUsarSobregiro() {
        CuentaCorriente origen = new CuentaCorriente("COR-1", "Carlos", 10, 50);
        Cuenta destino = new Cuenta("C-2", "Luis", 0);
        origen.transferirA(destino, 60);
        assertEquals(-50, origen.getSaldo(), DELTA);
        assertEquals(60, destino.getSaldo(), DELTA);
    }

    @Test
    void toStringIncluyeSobregiro() {
        String s = new CuentaCorriente("COR-1", "Carlos", 100, 50).toString();
        assertTrue(s.startsWith("CuentaCorriente{"));
        assertTrue(s.contains("cupoSobregiro=50.0"));
        assertTrue(s.contains("sobregiroDisponible=50.0"));
    }
}
