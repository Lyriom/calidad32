package srp;

/**
 * SRP - Single Responsibility Principle.
 * Única responsabilidad: PERSISTIR el rol de pagos.
 * Si cambia la forma de almacenamiento (BD, archivo, nube), solo cambia esta clase.
 */
public class RepositorioNomina {
    public void guardar(String reporte) {
        System.out.println("Guardando rol de pagos en la base de datos...");
        System.out.println("  > " + reporte);
    }
}
