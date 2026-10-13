package fundamentos.entradasalida;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fundamentos.Consola;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;

class EntradaSalidaDemoTest {

    @Test
    void mainEscribeYLeeSalidaTxt() throws IOException {
        String salida = Consola.capturar(() -> EntradaSalidaDemo.main(new String[0]));

        assertTrue(salida.contains("Leí la línea: hola"));
        assertTrue(salida.contains("Leí el entero: 42"));
        assertTrue(salida.contains("El programa continúa después del catch."));
        assertTrue(salida.contains("Archivo escrito correctamente."));
        assertTrue(salida.contains("1: Primera línea"));
        assertTrue(salida.contains("> Segunda línea"));
        assertTrue(salida.contains(">> Tercera línea"));

        // La demo deja salida.txt en el directorio actual (está en .gitignore).
        assertEquals(List.of("Primera línea", "Segunda línea", "Tercera línea"),
                Files.readAllLines(Path.of("salida.txt"), StandardCharsets.UTF_8));
    }
}
