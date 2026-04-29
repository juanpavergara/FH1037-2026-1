package fundamentos.poo.biblioteca;

public class Estudiante extends Miembro {
    private String programa;

    public Estudiante(String id, String nombre, String programa) {
        super(id, nombre);
        setPrograma(programa);
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        if (programa == null || programa.isBlank()) {
            throw new IllegalArgumentException("El programa es obligatorio.");
        }
        this.programa = programa.trim();
    }
}

