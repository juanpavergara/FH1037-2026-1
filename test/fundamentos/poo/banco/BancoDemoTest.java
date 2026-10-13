package fundamentos.poo.banco;

import static org.junit.jupiter.api.Assertions.assertTrue;

import fundamentos.Consola;
import org.junit.jupiter.api.Test;

class BancoDemoTest {

    @Test
    void mainMuestraReglasDeNegocio() {
        String salida = Consola.capturar(() -> BancoDemo.main(new String[0]));
        assertTrue(salida.contains("Error esperado: Límite de retiros mensuales alcanzado."));
        assertTrue(salida.contains("Error esperado: Fondos insuficientes para retirar 20000.0."));
        assertTrue(salida.contains("Error esperado: La cuenta está inactiva."));
        // Estado final: 500.000 + 50.000 + 1% interés - 2 x 10.000 = 535.500
        assertTrue(salida.contains("saldo=535500.0"));
        assertTrue(salida.contains("saldo=-40000.0"));
    }
}
