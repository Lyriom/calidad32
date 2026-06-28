package srp;

/**
 * SRP - Single Responsibility Principle.
 * Única responsabilidad: CALCULAR el salario neto (impuestos y horas extra).
 * Si cambia la fórmula de cálculo, solo se modifica esta clase.
 */
public class CalculadoraSalario {
    private static final double TASA_IMPUESTO = 0.1225;   // aporte IESS (aprox.)
    private static final double VALOR_HORA_EXTRA = 6.25;  // USD por hora extra

    public double calcularNeto(Empleado e) {
        double extras = e.getHorasExtra() * VALOR_HORA_EXTRA;
        double impuesto = e.getSalarioBruto() * TASA_IMPUESTO;
        return e.getSalarioBruto() + extras - impuesto;
    }
}
