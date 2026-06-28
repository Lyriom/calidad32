package ocp;

public class DescuentoRegular implements Descuento {
    @Override
    public double aplicar(double monto) {
        return monto; // 0% de descuento
    }

    @Override
    public String descripcion() {
        return "Cliente Regular (0%)";
    }
}
