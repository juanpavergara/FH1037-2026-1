package fundamentos.cadenas;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import fundamentos.Consola;
import org.junit.jupiter.api.Test;

class WordTest {

    @Test
    void retornaLongitudYPrimeraPosicionDeA() {
        assertArrayEquals(new int[]{5, 1}, Word.posicionA("casas"));
        assertArrayEquals(new int[]{4, 0}, Word.posicionA("Arbo"));
    }

    @Test
    void retornaMenosUnoSiNoHayA() {
        assertArrayEquals(new int[]{4, -1}, Word.posicionA("pero"));
        assertArrayEquals(new int[]{0, -1}, Word.posicionA(""));
    }

    @Test
    void mainImprimeLongitudYPosicion() {
        String salida = Consola.capturar("perla\n", () -> Word.main(new String[0]));
        assertArrayEquals(new String[]{"5", "4"}, salida.strip().split("\\R"));
    }
}
