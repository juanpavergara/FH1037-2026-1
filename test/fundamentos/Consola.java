package fundamentos;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Utilidad para las pruebas: ejecuta un bloque de código capturando lo que
 * imprime por System.out (y opcionalmente simulando System.in).
 */
public final class Consola {

    private Consola() {
    }

    public static String capturar(Runnable accion) {
        return capturar("", accion);
    }

    public static String capturar(String entrada, Runnable accion) {
        PrintStream outOriginal = System.out;
        PrintStream errOriginal = System.err;
        InputStream inOriginal = System.in;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(buffer, true, StandardCharsets.UTF_8));
            System.setErr(new PrintStream(new ByteArrayOutputStream(), true, StandardCharsets.UTF_8));
            System.setIn(new ByteArrayInputStream(entrada.getBytes(StandardCharsets.UTF_8)));
            accion.run();
        } finally {
            System.setOut(outOriginal);
            System.setErr(errOriginal);
            System.setIn(inOriginal);
        }
        return buffer.toString(StandardCharsets.UTF_8);
    }
}
