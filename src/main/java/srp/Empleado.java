package srp;

/**
 * SRP - Single Responsibility Principle.
 * Única responsabilidad: representar los DATOS de un empleado.
 * Su única razón para cambiar es que cambie el modelo de datos del empleado.
 */
public class Empleado {
    private final String nombre;
    private final String cargo;
    private final double salarioBruto;
    private final int horasExtra;

    public Empleado(String nombre, String cargo, double salarioBruto, int horasExtra) {
        this.nombre = nombre;
        this.cargo = cargo;
        this.salarioBruto = salarioBruto;
        this.horasExtra = horasExtra;
    }

    public String getNombre() { return nombre; }
    public String getCargo() { return cargo; }
    public double getSalarioBruto() { return salarioBruto; }
    public int getHorasExtra() { return horasExtra; }
}
