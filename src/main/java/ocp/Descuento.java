package ocp;

/**
 * OCP - Open/Closed Principle.
 * Contrato (abstracción) abierto a EXTENSIÓN: nuevas estrategias de descuento
 * se agregan creando nuevas clases, sin modificar el código existente.
 */
public interface Descuento {
    double aplicar(double monto);
    String descripcion();
}
