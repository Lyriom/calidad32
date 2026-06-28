package srp;

/**
 * Demostración del principio SRP con un sistema de nómina (rol de pagos).
 * Cada clase tiene una sola responsabilidad; ServicioNomina solo las coordina.
 */
public class Main {
    public static void main(String[] args) {
        ServicioNomina servicio = new ServicioNomina();

        System.out.println("=== Rol de pagos: empleado 1 ===");
        servicio.procesar(new Empleado("Josue Riera", "Backend Developer", 1500.0, 8));

        System.out.println();
        System.out.println("=== Rol de pagos: empleado 2 ===");
        servicio.procesar(new Empleado("Joao Conde", "QA Engineer", 1200.0, 0));

        System.out.println();
        System.out.println("=== Rol de pagos: empleado 3 ===");
        servicio.procesar(new Empleado("Victor Suquilanda", "Frontend Developer", 1350.0, 4));
    }
}
