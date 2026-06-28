package ocp;

/**
 * EXTENSIÓN agregada DESPUÉS, sin modificar CalculadoraPrecio ni las demás clases.
 * Esto es exactamente lo que habilita el principio OCP.
 */
public class DescuentoTemporada implements Descuento {
    @Override
    public double aplicar(double monto) {
        return monto * 0.65; // 35% de descuento por temporada
    }

    @Override
    public String descripcion() {
        return "Promo Temporada (35%)";
    }
}
