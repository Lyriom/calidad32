package ocp;

public class DescuentoVip implements Descuento {
    @Override
    public double aplicar(double monto) {
        return monto * 0.80; // 20% de descuento
    }

    @Override
    public String descripcion() {
        return "Cliente VIP (20%)";
    }
}
