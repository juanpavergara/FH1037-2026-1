package fundamentos.cadenas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import fundamentos.Consola;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DeFrontTest {

    // Regla: se eliminan los dos primeros caracteres, salvo que el primero sea
    // 'a'/'A' (se conserva) o el segundo sea 'b'/'B' (se conserva).
    @ParameterizedTest
    @CsvSource({
            "Hello, llo",
            "java, va",
            "away, aay",
            "abcd, abcd",
            "ABcd, ABcd",
            "xbcd, bcd",
            "axcd, acd",
            "ab, ab",
            "xy, ''",
            "aaa, aa"
    })
    void transformarString(String entrada, String esperado) {
        assertEquals(esperado, DeFront.transformarString(entrada));
    }

    @ParameterizedTest
    @CsvSource({
            "Hello, llo",
            "java, va",
            "away, aay",
            "abcd, abcd",
            "ABcd, ABcd",
            "xbcd, bcd",
            "axcd, acd",
            "ab, ab",
            "xy, ''",
            "aaa, aa"
    })
    void transformarStringConArrays(String entrada, String esperado) {
        assertEquals(esperado, DeFront.transformarStringConArrays(entrada));
    }

    @Test
    void transformarStringCasosCortos() {
        assertEquals("", DeFront.transformarString(""));
        assertEquals("a", DeFront.transformarString("a"));
        assertEquals("", DeFront.transformarString("x"));
    }

    @Test
    void transformarStringConArraysCasosCortos() {
        assertEquals("", DeFront.transformarStringConArrays(""));
        assertEquals("a", DeFront.transformarStringConArrays("a"));
        assertEquals("", DeFront.transformarStringConArrays("x"));
    }

    @Test
    void mainLeeLineaEImprimeTransformacion() {
        String salida = Consola.capturar("Hello\n", () -> DeFront.main(new String[0]));
        assertEquals("llo", salida.strip());
    }
}
