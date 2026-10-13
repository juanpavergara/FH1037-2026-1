package fundamentos.poo.banco;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CuentaAhorrosTest {

    private static final double DELTA = 1e-6;

    private CuentaAhorros nueva(double saldo, double tasa, int limite) {
        return new CuentaAhorros("AHO-1", "Ana Pérez", saldo, tasa, limite);
    }

    @Test
    void constructorInicializaAtributos() {
        CuentaAhorros c = nueva(1000, 0.12, 2);
        assertEquals(0.12, c.getTasaInteresAnual(), DELTA);
        assertEquals(2, c.getLimiteRetirosMensuales());
        assertEquals(0, c.getRetirosHechosEsteMes());
        assertTrue(c instanceof Cuenta);
    }

    @Test
    void validaTasaYLimite() {
        assertThrows(IllegalArgumentException.class, () -> nueva(0, -0.01, 1));
        assertThrows(IllegalArgumentException.class, () -> nueva(0, 1.01, 1));
        assertThrows(IllegalArgumentException.class, () -> nueva(0, 0.1, -1));

        CuentaAhorros c = nueva(0, 0, 0);
        c.setTasaInteresAnual(1.0);
        c.setLimiteRetirosMensuales(5);
        assertEquals(1.0, c.getTasaInteresAnual(), DELTA);
        assertEquals(5, c.getLimiteRetirosMensuales());
    }

    @Test
    void aplicarInteresMensualEsInteresSimpleSobreDoce() {
        CuentaAhorros c = nueva(1200, 0.12, 2);
        c.aplicarInteresMensual();
        assertEquals(1212, c.getSaldo(), DELTA);
    }

    @Test
    void aplicarInteresConSaldoOTasaCeroNoCambiaNada() {
        CuentaAhorros sinSaldo = nueva(0, 0.12, 2);
        sinSaldo.aplicarInteresMensual();
        assertEquals(0, sinSaldo.getSaldo(), DELTA);

        CuentaAhorros sinTasa = nueva(1000, 0, 2);
        sinTasa.aplicarInteresMensual();
        assertEquals(1000, sinTasa.getSaldo(), DELTA);
    }

    @Test
    void aplicarInteresEnCuentaInactivaFalla() {
        CuentaAhorros c = nueva(1000, 0.12, 2);
        c.desactivar();
        assertThrows(IllegalStateException.class, c::aplicarInteresMensual);
    }

    @Test
    void retirarRespetaLimiteMensualYSeReinicia() {
        CuentaAhorros c = nueva(1000, 0.12, 2);
        c.retirar(100);
        c.retirar(100);
        assertEquals(2, c.getRetirosHechosEsteMes());
        assertThrows(IllegalStateException.class, () -> c.retirar(100));
        assertEquals(800, c.getSaldo(), DELTA);

        c.reiniciarRetirosDelMes();
        assertEquals(0, c.getRetirosHechosEsteMes());
        c.retirar(100);
        assertEquals(700, c.getSaldo(), DELTA);
    }

    @Test
    void limiteCeroSignificaSinLimite() {
        CuentaAhorros c = nueva(1000, 0, 0);
        for (int i = 0; i < 5; i++) {
            c.retirar(10);
        }
        assertEquals(950, c.getSaldo(), DELTA);
        assertEquals(5, c.getRetirosHechosEsteMes());
    }

    @Test
    void retiroFallidoNoCuentaComoRetiro() {
        CuentaAhorros c = nueva(100, 0, 2);
        assertThrows(IllegalStateException.class, () -> c.retirar(500));
        assertEquals(0, c.getRetirosHechosEsteMes());
    }

    @Test
    void transferenciaCuentaComoRetiro() {
        CuentaAhorros origen = nueva(1000, 0, 1);
        Cuenta destino = new Cuenta("C-2", "Luis", 0);
        origen.transferirA(destino, 100);
        assertEquals(1, origen.getRetirosHechosEsteMes());
        assertThrows(IllegalStateException.class, () -> origen.transferirA(destino, 100));
    }

    @Test
    void toStringIncluyeCamposPropios() {
        String s = nueva(1000, 0.12, 2).toString();
        assertTrue(s.startsWith("CuentaAhorros{"));
        assertTrue(s.contains("tasaInteresAnual=0.12"));
        assertTrue(s.contains("limiteRetirosMensuales=2"));
        assertTrue(s.contains("retirosHechosEsteMes=0"));
    }
}
