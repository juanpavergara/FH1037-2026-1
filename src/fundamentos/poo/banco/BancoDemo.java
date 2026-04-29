package fundamentos.poo.banco;

public class BancoDemo {
    public static void main(String[] args) {
        System.out.println("=== Demo: Encapsulamiento vs Information Hiding ===\n");

        CuentaAhorros ahorros = new CuentaAhorros(
                "AHO-001",
                "Ana Pérez",
                500_000,
                0.12,  // 12% anual
                2      // máximo 2 retiros por mes (ejemplo simple)
        );

        CuentaCorriente corriente = new CuentaCorriente(
                "COR-001",
                "Carlos Ruiz",
                100_000,
                50_000 // cupo de sobregiro
        );

        System.out.println("Estado inicial");
        System.out.println(ahorros);
        System.out.println(corriente);
        System.out.println();

        // En vez de hacer: ahorros.setSaldo(...), usamos operaciones con reglas.
        System.out.println("Depositar 50.000 en ahorros");
        ahorros.depositar(50_000);
        System.out.println(ahorros);
        System.out.println();

        System.out.println("Aplicar interés mensual a ahorros");
        ahorros.aplicarInteresMensual();
        System.out.println(ahorros);
        System.out.println();

        System.out.println("Retirar dos veces de ahorros (límite 2)");
        ahorros.retirar(10_000);
        ahorros.retirar(10_000);
        System.out.println(ahorros);
        System.out.println();

        System.out.println("Intentar un tercer retiro (debe fallar por regla de negocio)");
        try {
            ahorros.retirar(5_000);
        } catch (RuntimeException ex) {
            System.out.println("Error esperado: " + ex.getMessage());
        }
        System.out.println();

        System.out.println("Transferir 200.000 desde ahorros hacia corriente (debe fallar por fondos/limitaciones)");
        try {
            ahorros.transferirA(corriente, 200_000);
        } catch (RuntimeException ex) {
            System.out.println("Error esperado: " + ex.getMessage());
        }
        System.out.println();

        // Demostración de sobregiro: la corriente permite quedar negativa hasta el cupo.
        System.out.println("Retirar 140.000 en corriente (usa sobregiro si hace falta)");
        corriente.retirar(140_000);
        System.out.println(corriente);
        System.out.println();

        System.out.println("Intentar retirar excediendo el cupo de sobregiro (debe fallar)");
        try {
            corriente.retirar(20_000);
        } catch (RuntimeException ex) {
            System.out.println("Error esperado: " + ex.getMessage());
        }
        System.out.println();

        System.out.println("Desactivar corriente y tratar de depositar (debe fallar)");
        corriente.desactivar();
        try {
            corriente.depositar(10_000);
        } catch (RuntimeException ex) {
            System.out.println("Error esperado: " + ex.getMessage());
        }
        System.out.println();

        System.out.println("Estado final");
        System.out.println(ahorros);
        System.out.println(corriente);
    }
}

