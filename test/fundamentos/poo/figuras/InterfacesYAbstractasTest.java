package fundamentos.poo.figuras;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fundamentos.Consola;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/** Cubre Dibujable (default methods), Escalable, Operable y FigurasDemo. */
class InterfacesYAbstractasTest {

    @Test
    void etiquetaDefaultYSobrescrita() {
        assertEquals("[Figura dibujable]", new Circulo("C", "rojo", 1).etiqueta());
        assertEquals("[Figura dibujable]", new Triangulo("T", "verde", 3, 4, 5).etiqueta());
        assertEquals("[Rectangulo 4.0x3.0]", new Rectangulo("R", "azul", 4, 3).etiqueta());
    }

    @Test
    void dibujarConBordeEnvuelveAlDibujoConcreto() {
        Dibujable d = () -> System.out.println("FIGURA");
        List<String> lineas = Consola.capturar(() -> d.dibujarConBorde(2)).lines().toList();

        String borde = "*".repeat(24);
        assertEquals(List.of(borde, "*  borde  *", "*  borde  *", "FIGURA",
                "*  borde  *", "*  borde  *", borde), lineas);
    }

    @Test
    void dibujarConBordeValidaGrosor() {
        Dibujable d = () -> { };
        assertThrows(IllegalArgumentException.class, () -> d.dibujarConBorde(0));
    }

    @Test
    void escalableEsUnContratoImplementable() {
        double[] tamano = {3};
        Escalable e = factor -> tamano[0] *= factor;
        e.escalar(2);
        assertEquals(6, tamano[0]);
    }

    @Test
    void operableRequiereSubclaseConcreta() {
        List<String> llamadas = new ArrayList<>();
        Operable o = new Operable() {
            @Override
            public void aumentar(double porcentaje) {
                llamadas.add("aumentar " + porcentaje);
            }

            @Override
            public void reducir(double porcentaje) {
                llamadas.add("reducir " + porcentaje);
            }
        };
        o.aumentar(10);
        o.reducir(5);
        assertEquals(List.of("aumentar 10.0", "reducir 5.0"), llamadas);
    }

    @Test
    void figurasDemoEscalaSoloLasEscalables() {
        String salida = Consola.capturar(() -> FigurasDemo.main(new String[0]));
        assertTrue(salida.contains("=== Descripcion polimórfica de cada figura ==="));
        assertTrue(salida.contains("Saltada (no es Escalable): Triangulo (Vela"));
        assertTrue(salida.contains("<- Circulo radio=10.0"));
        assertTrue(salida.contains("[Rectangulo 8.0x6.0]"));
    }
}
