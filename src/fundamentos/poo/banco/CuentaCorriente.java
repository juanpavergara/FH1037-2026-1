package fundamentos.poo.banco;

public class CuentaCorriente extends Cuenta {
    /*
     * Subclase con regla distinta de retiro: permite sobregiro (hasta un cupo).
     * Esto muestra polimorfismo: la regla de negocio cambia según el tipo de cuenta.
     */

    private double cupoSobregiro; // cuánto puede quedar "en negativo"

    public CuentaCorriente(String numero, String titular, double saldoInicial, double cupoSobregiro) {
        super(numero, titular, saldoInicial);
        setCupoSobregiro(cupoSobregiro);
    }

    public double getCupoSobregiro() {
        return cupoSobregiro;
    }

    public void setCupoSobregiro(double cupoSobregiro) {
        // Precondición de negocio: no tiene sentido un sobregiro negativo
        if (cupoSobregiro < 0) {
            throw new IllegalArgumentException("El cupo de sobregiro no puede ser negativo.");
        }
        this.cupoSobregiro = cupoSobregiro;
    }

    /**
     * Método propio: cuánto cupo queda disponible dado el saldo actual.
     */
    public double getSobregiroDisponible() {
        // Si saldo es 100 y cupo 50 => disponible 50 (nadie ha usado sobregiro)
        // Si saldo es -20 y cupo 50 => disponible 30 (ya usó 20)
        double usado = Math.max(0, -getSaldo());
        return Math.max(0, cupoSobregiro - usado);
    }

    @Override
    protected boolean puedeRetirar(double monto) {
        // Permite retirar si al final no baja de -cupoSobregiro
        return getSaldo() - monto >= -cupoSobregiro;
    }

    @Override
    public String toString() {
        return "CuentaCorriente{" +
                "numero='" + getNumero() + '\'' +
                ", titular='" + getTitular() + '\'' +
                ", activa=" + isActiva() +
                ", saldo=" + getSaldo() +
                ", cupoSobregiro=" + cupoSobregiro +
                ", sobregiroDisponible=" + getSobregiroDisponible() +
                '}';
    }
}

