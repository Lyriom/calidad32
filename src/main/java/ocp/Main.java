package ocp;

/**
 * Demostración del principio OCP con un sistema de descuentos.
 * Se agrega DescuentoTemporada sin tocar CalculadoraPrecio: abierto a extensión,
 * cerrado a modificación.
 */
public class Main {
    public static void main(String[] args) {
        CalculadoraPrecio calculadora = new CalculadoraPrecio();
        double monto = 200.0;

        System.out.println("Monto base de la compra: $" + monto);
        System.out.println();

        calculadora.calcular(monto, new DescuentoRegular());
        calculadora.calcular(monto, new DescuentoPremium());
        calculadora.calcular(monto, new DescuentoVip());
        // Nuevo tipo de descuento agregado SIN modificar la calculadora:
        calculadora.calcular(monto, new DescuentoTemporada());
    }
}
