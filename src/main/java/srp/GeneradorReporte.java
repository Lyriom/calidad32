package srp;

import java.util.Locale;

/**
 * SRP - Single Responsibility Principle.
 * Única responsabilidad: dar FORMATO al reporte (presentación).
 * Si cambia el formato del rol de pagos, solo se modifica esta clase.
 */
public class GeneradorReporte {
    public String generar(Empleado e, double neto) {
        return String.format(Locale.US,
            "Empleado: %-20s | Cargo: %-20s | Bruto: $%8.2f | Neto a pagar: $%8.2f",
            e.getNombre(), e.getCargo(), e.getSalarioBruto(), neto);
    }
}
