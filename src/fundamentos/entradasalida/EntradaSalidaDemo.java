package fundamentos.entradasalida;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class EntradaSalidaDemo {

    public static void main(String[] args) {

        System.out.println("=== Demo: Entrada y Salida ===\n");

        // ---------------------------------------------------------------
        // 1. stdout vs stderr
        // ---------------------------------------------------------------
        // Cuando se ejecuta desde la terminal, ambos mensajes aparecen
        // en pantalla, pero son canales independientes: stdout lleva los
        // resultados del programa y stderr lleva los mensajes de error
        // o diagnóstico. Si se redirige stdout a un archivo
        // ("java ... > salida.txt"), stderr sigue viéndose en pantalla.
        System.out.println("--- 1. stdout vs stderr ---");
        System.out.println("Esto sale por stdout (System.out)");
        System.err.println("Esto sale por stderr (System.err)");
        System.out.println();

        // ---------------------------------------------------------------
        // 2. Scanner sobre una fuente arbitraria
        // ---------------------------------------------------------------
        // Un Scanner no está atado al teclado: puede leer de cualquier
        // fuente con forma de flujo de caracteres. Acá usamos un String
        // para que la demo corra sin pedir input al usuario; el API es
        // exactamente el mismo que con System.in.
        System.out.println("--- 2. Scanner sobre un String (mismo API que con System.in) ---");
        Scanner sc = new Scanner("hola\n42\n");
        String linea = sc.nextLine();
        int numero = sc.nextInt();
        System.out.println("Leí la línea: " + linea);
        System.out.println("Leí el entero: " + numero);
        sc.close();
        System.out.println();

        // ---------------------------------------------------------------
        // 3. IOException al abrir un archivo inexistente
        // ---------------------------------------------------------------
        // La E/S puede fallar por causas externas (archivo inexistente,
        // permisos, disco lleno). Java obliga a manejarlo con try/catch
        // o con "throws": IOException es una excepción "checked".
        System.out.println("--- 3. Intentar leer un archivo que no existe ---");
        try (BufferedReader br = new BufferedReader(new FileReader("no_existe.txt"))) {
            System.out.println(br.readLine());
        } catch (IOException ex) {
            System.err.println("No se pudo abrir el archivo: " + ex.getMessage());
        }
        System.out.println("El programa continúa después del catch.\n");

        // ---------------------------------------------------------------
        // 4. Escribir un archivo con try-with-resources
        // ---------------------------------------------------------------
        // try-with-resources cierra el writer automáticamente al salir
        // del bloque, aunque ocurra una excepción a mitad de camino.
        Path archivo = Path.of("salida.txt");
        System.out.println("--- 4. Escribir un archivo (" + archivo + ") ---");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo.toFile()))) {
            bw.write("Primera línea");
            bw.newLine();
            bw.write("Segunda línea");
            bw.newLine();
            bw.write("Tercera línea");
            bw.newLine();
            System.out.println("Archivo escrito correctamente.");
        } catch (IOException ex) {
            System.err.println("Error escribiendo: " + ex.getMessage());
        }
        System.out.println();

        // ---------------------------------------------------------------
        // 5. Leer el archivo línea por línea
        // ---------------------------------------------------------------
        System.out.println("--- 5. Leer el archivo línea por línea ---");
        try (BufferedReader br = new BufferedReader(new FileReader(archivo.toFile()))) {
            String l;
            int n = 1;
            while ((l = br.readLine()) != null) {
                System.out.println(n + ": " + l);
                n++;
            }
        } catch (IOException ex) {
            System.err.println("Error leyendo: " + ex.getMessage());
        }
        System.out.println();

        // ---------------------------------------------------------------
        // 6. Atajo con java.nio.file.Files
        // ---------------------------------------------------------------
        // Para casos simples, Files ofrece métodos de una sola línea que
        // hacen el equivalente a los bloques 4 y 5 juntos.
        System.out.println("--- 6. Leer todo el archivo con Files.readAllLines ---");
        try {
            for (String s : Files.readAllLines(archivo)) {
                System.out.println("> " + s);
            }
        } catch (IOException ex) {
            System.err.println("Error: " + ex.getMessage());
        }

        System.out.println();

        // ---------------------------------------------------------------
        // 7. Files.newBufferedReader con codificación explícita
        // ---------------------------------------------------------------
        // Devuelve un BufferedReader ya configurado y permite indicar
        // la codificación. Es la forma recomendada cuando importa el
        // encoding (por ejemplo, leer archivos UTF-8 de forma portable
        // entre sistemas operativos con codificación por defecto distinta).
        System.out.println("--- 7. Files.newBufferedReader con UTF-8 ---");
        try (BufferedReader br = Files.newBufferedReader(archivo, StandardCharsets.UTF_8)) {
            String l;
            while ((l = br.readLine()) != null) {
                System.out.println(">> " + l);
            }
        } catch (IOException ex) {
            System.err.println("Error: " + ex.getMessage());
        }

        System.out.println("\nFin. El archivo '" + archivo + "' quedó en el directorio actual para inspección.");
    }
}
