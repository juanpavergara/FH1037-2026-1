package fundamentos.entradasalida;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fundamentos.Consola;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Pruebas de los ejercicios de E/S.
 *
 * Los tres métodos son ejercicios con TODO para el estudiante, así que sus
 * pruebas de especificación están deshabilitadas con @Disabled. Al implementar
 * cada ejercicio, quite el @Disabled correspondiente y ejecute "make test".
 */
class EntradaSalidaEjerciciosTest {

    @TempDir
    Path dir;

    @Test
    void mainSeEjecutaSinLanzarExcepciones() {
        String salida = Consola.capturar(() -> EntradaSalidaEjercicios.main(new String[0]));
        assertTrue(salida.contains("--- Ejercicio 1: guardarLineas ---"));
        assertTrue(salida.contains("--- Ejercicio 2: contarLineasNoVacias ---"));
        assertTrue(salida.contains("--- Ejercicio 3: numerarLineas ---"));
    }

    @Disabled("Ejercicio 1 pendiente: quitar @Disabled al implementar guardarLineas")
    @Test
    void guardarLineasEscribeUnaLineaPorElementoYSobrescribe() throws IOException {
        Path destino = dir.resolve("lineas.txt");
        Files.writeString(destino, "contenido viejo\n", StandardCharsets.UTF_8);

        EntradaSalidaEjercicios.guardarLineas(List.of("uno", "dós", "tres"), destino);

        assertEquals(List.of("uno", "dós", "tres"), Files.readAllLines(destino, StandardCharsets.UTF_8));
    }

    @Disabled("Ejercicio 2 pendiente: quitar @Disabled al implementar contarLineasNoVacias")
    @Test
    void contarLineasNoVaciasIgnoraLineasEnBlanco() throws IOException {
        Path archivo = dir.resolve("mixto.txt");
        Files.writeString(archivo, "uno\n\ndos\n   \n\t\ntres\n", StandardCharsets.UTF_8);

        assertEquals(3, EntradaSalidaEjercicios.contarLineasNoVacias(archivo));
    }

    @Disabled("Ejercicio 3 pendiente: quitar @Disabled al implementar numerarLineas")
    @Test
    void numerarLineasAntecedeNumeroYDosPuntos() throws IOException {
        Path origen = dir.resolve("origen.txt");
        Path destino = dir.resolve("destino.txt");
        Files.writeString(origen, "hola\nmundo\n", StandardCharsets.UTF_8);

        EntradaSalidaEjercicios.numerarLineas(origen, destino);

        assertEquals(List.of("1: hola", "2: mundo"), Files.readAllLines(destino, StandardCharsets.UTF_8));
    }
}
