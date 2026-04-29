package fundamentos.poo.biblioteca;

import java.time.LocalDate;

public class BibliotecaDemo {

    public static void main(String[] args) {
        System.out.println("=== Demo: Sistema de Biblioteca ===\n");

        // USO: Biblioteca recibe su nombre por parámetro (dependencia transitoria sobre String).
        Biblioteca biblioteca = new Biblioteca("Biblioteca Central FH");

        // HERENCIA: Estudiante y Empleado heredan de Miembro (clase abstracta).
        // Las variables se declaran como Miembro (tipo base) para evidenciar polimorfismo.
        Miembro ana = new Estudiante("E-001", "Ana Gómez", "Ingeniería de Sistemas");
        Miembro luis = new Empleado("F-010", "Luis Pérez", "Bibliotecario");

        // USO: registrarMiembro consume Miembros que viven fuera de la Biblioteca (agregación).
        biblioteca.registrarMiembro(ana);
        biblioteca.registrarMiembro(luis);

        // USO: los Libros también se crean fuera y se entregan a la Biblioteca (agregación).
        Libro elQuijote = new Libro("978-84-376-0494-7", "Don Quijote de la Mancha");
        Libro cienAnios = new Libro("978-03-073-8868-1", "Cien años de soledad");

        // COMPOSICIÓN: el Libro CREA y posee sus Copias (Copia no existe sin un Libro).
        elQuijote.crearCopia("Q-1");
        elQuijote.crearCopia("Q-2");
        cienAnios.crearCopia("C-1");

        biblioteca.registrarLibro(elQuijote);
        biblioteca.registrarLibro(cienAnios);

        // USO: prestar() recibe identificadores y datos por parámetro; los "usa" sin poseerlos.
        // COMPOSICIÓN (dentro de prestar): la Biblioteca CREA un Prestamo y lo guarda en su historial.
        // HERENCIA: Prestamo extiende Transaccion; el historial se tipa con la superclase.
        Prestamo prestamoAna = biblioteca.prestar("T-001", "E-001", "978-84-376-0494-7", "Q-1", 7);
        System.out.println("Préstamo creado: " + prestamoAna);

        // HERENCIA + polimorfismo: getMiembro() devuelve Miembro, pero la instancia real es Estudiante.
        Miembro prestatario = prestamoAna.getMiembro();
        System.out.println("Prestatario (tipo dinámico " + prestatario.getClass().getSimpleName()
                + "): " + prestatario.getNombre());

        // USO: devolver() recibe la fecha por parámetro y libera la Copia.
        prestamoAna.devolver(LocalDate.now().plusDays(3));
        System.out.println("Préstamo devuelto: " + prestamoAna);

        // HERENCIA: el historial es List<Transaccion>; cada elemento puede ser un subtipo (Prestamo, ...).
        System.out.println("\nHistorial de transacciones:");
        for (Transaccion t : biblioteca.getTransacciones()) {
            // Polimorfismo: getTipo() resuelve al método de la subclase concreta.
            System.out.println("  - [" + t.getTipo() + "] " + t);
        }
    }
}
