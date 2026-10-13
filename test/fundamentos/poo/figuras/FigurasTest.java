package fundamentos.poo.figuras;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fundamentos.Consola;
import org.junit.jupiter.api.Test;

/** Cubre Figura, Circulo, Rectangulo y Triangulo. */
class FigurasTest {

    private static final double DELTA = 1e-9;

    @Test
    void figuraValidaNombreYColor() {
        assertThrows(IllegalArgumentException.class, () -> new Circulo(null, "rojo", 1));
        assertThrows(IllegalArgumentException.class, () -> new Circulo(" ", "rojo", 1));
        assertThrows(IllegalArgumentException.class, () -> new Circulo("C", null, 1));
        assertThrows(IllegalArgumentException.class, () -> new Circulo("C", "  ", 1));

        Figura f = new Circulo(" Pizza ", " rojo ", 1);
        assertEquals("Pizza", f.getNombre());
        assertEquals("rojo", f.getColor());
    }

    @Test
    void circuloCalculaAreaYPerimetro() {
        Circulo c = new Circulo("Pizza", "rojo", 5);
        assertEquals(5, c.getRadio(), DELTA);
        assertEquals(Math.PI * 25, c.calcularArea(), DELTA);
        assertEquals(Math.PI * 10, c.calcularPerimetro(), DELTA);
        assertThrows(IllegalArgumentException.class, () -> new Circulo("C", "rojo", 0));
        assertThrows(IllegalArgumentException.class, () -> new Circulo("C", "rojo", -1));
    }

    @Test
    void circuloEscalaSuRadio() {
        Circulo c = new Circulo("Pizza", "rojo", 5);
        c.escalar(2);
        assertEquals(10, c.getRadio(), DELTA);
        c.escalar(0.5);
        assertEquals(5, c.getRadio(), DELTA);
        assertThrows(IllegalArgumentException.class, () -> c.escalar(0));
        assertThrows(IllegalArgumentException.class, () -> c.escalar(-2));
    }

    @Test
    void rectanguloCalculaAreaYPerimetro() {
        Rectangulo r = new Rectangulo("Cuadro", "azul", 4, 3);
        assertEquals(4, r.getBase(), DELTA);
        assertEquals(3, r.getAltura(), DELTA);
        assertEquals(12, r.calcularArea(), DELTA);
        assertEquals(14, r.calcularPerimetro(), DELTA);
        assertThrows(IllegalArgumentException.class, () -> new Rectangulo("R", "azul", 0, 3));
        assertThrows(IllegalArgumentException.class, () -> new Rectangulo("R", "azul", 4, -3));
    }

    @Test
    void rectanguloEscalaAmbasDimensiones() {
        Rectangulo r = new Rectangulo("Cuadro", "azul", 4, 3);
        r.escalar(2);
        assertEquals(8, r.getBase(), DELTA);
        assertEquals(6, r.getAltura(), DELTA);
        assertEquals(48, r.calcularArea(), DELTA);
        assertThrows(IllegalArgumentException.class, () -> r.escalar(0));
    }

    @Test
    void trianguloUsaFormulaDeHeron() {
        Triangulo t = new Triangulo("Vela", "verde", 3, 4, 5);
        assertEquals(6, t.calcularArea(), DELTA);
        assertEquals(12, t.calcularPerimetro(), DELTA);

        Triangulo equilatero = new Triangulo("E", "verde", 2, 2, 2);
        assertEquals(Math.sqrt(3), equilatero.calcularArea(), DELTA);
    }

    @Test
    void trianguloValidaLados() {
        assertThrows(IllegalArgumentException.class, () -> new Triangulo("T", "verde", 0, 4, 5));
        assertThrows(IllegalArgumentException.class, () -> new Triangulo("T", "verde", 3, -4, 5));
        // Desigualdad triangular (incluye el caso degenerado a + b == c).
        assertThrows(IllegalArgumentException.class, () -> new Triangulo("T", "verde", 1, 2, 3));
        assertThrows(IllegalArgumentException.class, () -> new Triangulo("T", "verde", 10, 1, 1));
        assertThrows(IllegalArgumentException.class, () -> new Triangulo("T", "verde", 1, 10, 1));
    }

    @Test
    void capacidadesPorInterfaz() {
        Figura c = new Circulo("C", "rojo", 1);
        Figura r = new Rectangulo("R", "azul", 1, 1);
        Figura t = new Triangulo("T", "verde", 3, 4, 5);

        assertTrue(c instanceof Escalable && c instanceof Dibujable);
        assertTrue(r instanceof Escalable && r instanceof Dibujable);
        assertFalse(t instanceof Escalable);
        assertTrue(t instanceof Dibujable);
    }

    @Test
    void descripcionUsaDespachoDinamico() {
        assertEquals("Rectangulo (Cuadro, color azul) — área=12.00, perímetro=14.00",
                new Rectangulo("Cuadro", "azul", 4, 3).descripcion());
        assertEquals("Triangulo (Vela, color verde) — área=6.00, perímetro=12.00",
                new Triangulo("Vela", "verde", 3, 4, 5).descripcion());
        assertTrue(new Circulo("Pizza", "rojo", 5).descripcion()
                .matches("Circulo \\(Pizza, color rojo\\) — área=78[.,]54, perímetro=31[.,]42"));
    }

    @Test
    void dibujarImprimeCadaFigura() {
        assertTrue(Consola.capturar(() -> new Circulo("C", "rojo", 2).dibujar())
                .contains("<- Circulo radio=2.0"));
        String rect = Consola.capturar(() -> new Rectangulo("R", "azul", 4, 3).dibujar());
        assertEquals(3, rect.lines().filter("####"::equals).count());
        assertTrue(rect.contains("<- Rectangulo 4.0 x 3.0"));
        assertTrue(Consola.capturar(() -> new Triangulo("T", "verde", 3, 4, 5).dibujar())
                .contains("<- Triangulo lados 3.0, 4.0, 5.0"));
    }

    @Test
    void rectanguloPequenoSeDibujaConTamanoMinimo() {
        String rect = Consola.capturar(() -> new Rectangulo("R", "azul", 0.1, 0.1).dibujar());
        assertEquals(1, rect.lines().filter("##"::equals).count());
    }
}
