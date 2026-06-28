package ocp;

public class DescuentoPremium implements Descuento {
    @Override
    public double aplicar(double monto) {
        return monto * 0.90; // 10% de descuento
    }

    @Override
    public String descripcion() {
        return "Cliente Premium (10%)";
    }
}
