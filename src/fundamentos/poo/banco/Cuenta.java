package fundamentos.poo.banco;

public class Cuenta {
    /*
     * Encapsulamiento vs Information Hiding (ocultamiento de información)
     *
     * - Encapsulamiento: agrupamos datos + comportamiento en una clase.
     * - Information Hiding: ocultamos el "cómo" (representación interna) y exponemos
     *   un "qué" (operaciones) con reglas de negocio.
     *
     * En este ejemplo, el saldo NO se modifica con un setter público. Eso evita
     * un dominio anémico (solo datos) y protege invariantes: el saldo cambia
     * solo a través de operaciones válidas (depositar, retirar, transferir).
     */

    private final String numero;
    private String titular;
    private boolean activa;

    private double saldo;

    public Cuenta(String numero, String titular, double saldoInicial) {
        if (numero == null || numero.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de cuenta es obligatorio.");
        }
        this.numero = numero.trim();

        setTitular(titular);
        this.activa = true;

        if (saldoInicial < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo.");
        }
        this.saldo = saldoInicial;
    }

    // Getters: exponen estado de forma controlada (lectura).
    public String getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public boolean isActiva() {
        return activa;
    }

    public double getSaldo() {
        return saldo;
    }

    // Setter con validación: no es "solo asignar".
    public void setTitular(String titular) {
        if (titular == null || titular.trim().length() < 3) {
            throw new IllegalArgumentException("El titular debe tener al menos 3 caracteres.");
        }
        this.titular = titular.trim();
    }

    public void desactivar() {
        this.activa = false;
    }

    public void activar() {
        this.activa = true;
    }

    // Métodos de dominio (comportamiento).
    public void depositar(double monto) {
        validarActiva();
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser mayor que 0.");
        }
        saldo += monto;
    }

    public void retirar(double monto) {
        validarActiva();
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser mayor que 0.");
        }
        if (!puedeRetirar(monto)) {
            throw new IllegalStateException("Fondos insuficientes para retirar " + monto + ".");
        }
        saldo -= monto;
    }

    public void transferirA(Cuenta destino, double monto) {
        validarActiva();
        if (destino == null) {
            throw new IllegalArgumentException("La cuenta destino es obligatoria.");
        }
        if (destino == this) {
            throw new IllegalArgumentException("No se puede transferir a la misma cuenta.");
        }
        if (!destino.isActiva()) {
            throw new IllegalStateException("La cuenta destino está inactiva.");
        }
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a transferir debe ser mayor que 0.");
        }

        // Regla: se descuenta primero, luego se abona.
        this.retirar(monto);
        destino.depositar(monto);
    }

    /**
     * Punto de extensión: subclases pueden redefinir la regla de retiro
     * (por ejemplo, permitir sobregiro o limitar retiros).
     */
    protected boolean puedeRetirar(double monto) {
        return saldo >= monto;
    }

    protected void validarActiva() {
        if (!activa) {
            throw new IllegalStateException("La cuenta está inactiva.");
        }
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "numero='" + numero + '\'' +
                ", titular='" + titular + '\'' +
                ", activa=" + activa +
                ", saldo=" + saldo +
                '}';
    }
}

