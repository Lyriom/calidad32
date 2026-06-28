package srp;

/**
 * SRP - Single Responsibility Principle.
 * Única responsabilidad: COORDINAR el flujo de nómina delegando cada paso.
 * No calcula, no formatea y no guarda: solo orquesta a las clases especializadas.
 */
public class ServicioNomina {
    private final CalculadoraSalario calculadora = new CalculadoraSalario();
    private final GeneradorReporte generador = new GeneradorReporte();
    private final RepositorioNomina repositorio = new RepositorioNomina();

    public void procesar(Empleado e) {
        double neto = calculadora.calcularNeto(e);
        String reporte = generador.generar(e, neto);
        repositorio.guardar(reporte);
    }
}
