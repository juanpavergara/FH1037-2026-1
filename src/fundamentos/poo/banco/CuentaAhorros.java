package fundamentos.poo.banco;

public class CuentaAhorros extends Cuenta {
    /*
     * Subclase con comportamiento propio:
     * - Tasa de interés anual
     * - Límite simple de retiros mensuales
     *
     * Nota: mantenemos el ejemplo simple (Fundamentos). No modelamos fechas;
     * el contador se reinicia manualmente con reiniciarRetirosDelMes().
     */

    private double tasaInteresAnual; // ej: 0.10 = 10%
    private int limiteRetirosMensuales;
    private int retirosHechosEsteMes;

    public CuentaAhorros(String numero, String titular, double saldoInicial,
                         double tasaInteresAnual, int limiteRetirosMensuales) {
        super(numero, titular, saldoInicial);
        setTasaInteresAnual(tasaInteresAnual);
        setLimiteRetirosMensuales(limiteRetirosMensuales);
        this.retirosHechosEsteMes = 0;
    }

    public double getTasaInteresAnual() {
        return tasaInteresAnual;
    }

    public void setTasaInteresAnual(double tasaInteresAnual) {
        // Precondición de negocio: una tasa razonable [0%..100%]
        if (tasaInteresAnual < 0 || tasaInteresAnual > 1) {
            throw new IllegalArgumentException("La tasa debe estar entre 0.0 y 1.0 (ej: 0.08 = 8%).");
        }
        this.tasaInteresAnual = tasaInteresAnual;
    }

    public int getLimiteRetirosMensuales() {
        return limiteRetirosMensuales;
    }

    public void setLimiteRetirosMensuales(int limiteRetirosMensuales) {
        if (limiteRetirosMensuales < 0) {
            throw new IllegalArgumentException("El límite de retiros mensuales no puede ser negativo.");
        }
        this.limiteRetirosMensuales = limiteRetirosMensuales;
    }

    public int getRetirosHechosEsteMes() {
        return retirosHechosEsteMes;
    }

    public void reiniciarRetirosDelMes() {
        this.retirosHechosEsteMes = 0;
    }

    /**
     * Método propio de la subclase.
     * Aplica interés SIMPLE mensual: saldo * (tasaAnual / 12).
     *
     * Es un ejemplo didáctico de comportamiento (no un setter del saldo).
     */
    public void aplicarInteresMensual() {
        validarActiva();
        double interes = getSaldo() * (tasaInteresAnual / 12.0);
        if (interes > 0) {
            depositar(interes);
        }
    }

    @Override
    public void retirar(double monto) {
        validarActiva();
        if (limiteRetirosMensuales > 0 && retirosHechosEsteMes >= limiteRetirosMensuales) {
            throw new IllegalStateException("Límite de retiros mensuales alcanzado.");
        }
        super.retirar(monto);
        retirosHechosEsteMes++;
    }

    @Override
    public String toString() {
        return "CuentaAhorros{" +
                "numero='" + getNumero() + '\'' +
                ", titular='" + getTitular() + '\'' +
                ", activa=" + isActiva() +
                ", saldo=" + getSaldo() +
                ", tasaInteresAnual=" + tasaInteresAnual +
                ", limiteRetirosMensuales=" + limiteRetirosMensuales +
                ", retirosHechosEsteMes=" + retirosHechosEsteMes +
                '}';
    }
}

