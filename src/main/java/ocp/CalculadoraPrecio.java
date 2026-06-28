package ocp;

import java.util.Locale;

/**
 * OCP - Open/Closed Principle.
 * CERRADA a modificación: usa la abstracción Descuento mediante polimorfismo.
 * No necesita if/else por tipo de cliente; acepta cualquier Descuento presente o futuro.
 */
public class CalculadoraPrecio {
    public double calcular(double monto, Descuento descuento) {
        double total = descuento.aplicar(monto);
        System.out.printf(Locale.US, "%-26s -> Total a pagar: $%.2f%n", descuento.descripcion(), total);
        return total;
    }
}
