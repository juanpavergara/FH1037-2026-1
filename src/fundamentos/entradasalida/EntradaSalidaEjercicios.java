package fundamentos.entradasalida;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class EntradaSalidaEjercicios {

    // ================================================================
    // Ejercicio 1 — Escribir una lista de líneas a un archivo
    // ================================================================
    // Implementar este método para que escriba cada elemento de
    // 'lineas' como una línea independiente en el archivo 'destino',
    // usando codificación UTF-8. Si el archivo ya existe, se debe
    // sobrescribir; si no existe, se debe crear.
    //
    // Pistas:
    //  - Files.write(path, lineas, StandardCharsets.UTF_8) lo resuelve
    //    en una línea.
    //  - También se puede hacer con BufferedWriter + try-with-resources
    //    iterando sobre 'lineas' y llamando write/newLine.
    //  - El método declara IOException; el main lo captura.
    public static void guardarLineas(List<String> lineas, Path destino) throws IOException {
        throw new UnsupportedOperationException("TODO ejercicio 1: implementar guardarLineas");
    }

    // ================================================================
    // Ejercicio 2 — Contar líneas no vacías de un archivo
    // ================================================================
    // Implementar este método para que devuelva la cantidad de líneas
    // del archivo 'archivo' que NO están vacías. Una línea se considera
    // vacía si está en blanco o sólo contiene espacios/tabs.
    //
    // Ejemplo: si el archivo contiene
    //     uno
    //
    //     dos
    //        (tres espacios)
    //     tres
    // el método debe devolver 3.
    //
    // Pistas:
    //  - String.isBlank() devuelve true para "", "   ", "\t", etc.
    //  - BufferedReader.readLine() devuelve null cuando termina el
    //    archivo: ese es el criterio para salir del while.
    //  - Files.readAllLines(archivo, StandardCharsets.UTF_8) carga
    //    todo a memoria y permite recorrer con un for-each. Funciona
    //    bien para archivos chicos.
    public static int contarLineasNoVacias(Path archivo) throws IOException {
        throw new UnsupportedOperationException("TODO ejercicio 2: implementar contarLineasNoVacias");
    }

    // ================================================================
    // Ejercicio 3 — Numerar las líneas de un archivo en otro
    // ================================================================
    // Implementar este método para leer el archivo 'origen' y escribir
    // en 'destino' las mismas líneas pero precedidas por su número
    // (empezando en 1) y dos puntos. Por ejemplo, si origen contiene:
    //     hola
    //     mundo
    // destino debe contener:
    //     1: hola
    //     2: mundo
    //
    // Pistas:
    //  - Se pueden abrir el lector y el escritor en el mismo
    //    try-with-resources separados por punto y coma:
    //
    //        try (BufferedReader br = Files.newBufferedReader(origen, ...);
    //             BufferedWriter bw = Files.newBufferedWriter(destino, ...)) {
    //            ...
    //        }
    //
    //  - Llevar un contador entero que arranque en 1 y se incremente
    //    en cada iteración.
    //  - Recordar bw.newLine() después de cada línea escrita.
    public static void numerarLineas(Path origen, Path destino) throws IOException {
        throw new UnsupportedOperationException("TODO ejercicio 3: implementar numerarLineas");
    }

    // ================================================================
    // Punto de invocación
    // ================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicios: Entrada y Salida ===\n");

        Path archivo1 = Path.of("ejercicio1.txt");
        Path archivo2 = Path.of("ejercicio2.txt");
        Path archivo3 = Path.of("ejercicio3.txt");

        // ------------------------------------------------------------
        // Ejercicio 1
        // ------------------------------------------------------------
        System.out.println("--- Ejercicio 1: guardarLineas ---");
        try {
            List<String> lineas = Arrays.asList(
                    "primera línea",
                    "segunda línea",
                    "tercera línea");
            guardarLineas(lineas, archivo1);
            System.out.println("OK. Revisar el contenido de " + archivo1);
        } catch (IOException ex) {
            System.err.println("Error de E/S: " + ex.getMessage());
        } catch (UnsupportedOperationException ex) {
            System.out.println("Pendiente: " + ex.getMessage());
        }
        System.out.println();

        // ------------------------------------------------------------
        // Ejercicio 2
        // ------------------------------------------------------------
        System.out.println("--- Ejercicio 2: contarLineasNoVacias ---");
        try {
            prepararArchivoConLineasVacias(archivo2);
            int n = contarLineasNoVacias(archivo2);
            System.out.println("Líneas no vacías en " + archivo2 + ": " + n
                    + "  (esperado: 3)");
        } catch (IOException ex) {
            System.err.println("Error de E/S: " + ex.getMessage());
        } catch (UnsupportedOperationException ex) {
            System.out.println("Pendiente: " + ex.getMessage());
        }
        System.out.println();

        // ------------------------------------------------------------
        // Ejercicio 3
        // ------------------------------------------------------------
        System.out.println("--- Ejercicio 3: numerarLineas ---");
        try {
            numerarLineas(archivo2, archivo3);
            System.out.println("OK. Revisar el contenido de " + archivo3);
        } catch (IOException ex) {
            System.err.println("Error de E/S: " + ex.getMessage());
        } catch (UnsupportedOperationException ex) {
            System.out.println("Pendiente: " + ex.getMessage());
        }
    }

    // Genera un archivo de prueba con líneas vacías y en blanco para
    // los ejercicios 2 y 3. No es parte de ningún ejercicio.
    private static void prepararArchivoConLineasVacias(Path archivo) throws IOException {
        Files.writeString(archivo,
                "uno\n\ndos\n   \ntres\n",
                StandardCharsets.UTF_8);
    }
}
