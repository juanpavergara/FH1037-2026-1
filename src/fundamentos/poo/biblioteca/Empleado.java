package fundamentos.poo.biblioteca;

public class Empleado extends Miembro {
    private String cargo;

    public Empleado(String id, String nombre, String cargo) {
        super(id, nombre);
        setCargo(cargo);
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        if (cargo == null || cargo.isBlank()) {
            throw new IllegalArgumentException("El cargo es obligatorio.");
        }
        this.cargo = cargo.trim();
    }
}

