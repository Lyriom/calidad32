package dip;

/**
 * Demostración del principio DIP con un interruptor y varios dispositivos.
 * El mismo Interruptor (alto nivel) controla cualquier Dispositivo inyectado desde afuera.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("--- Interruptor conectado a una Bombilla ---");
        Interruptor i1 = new Interruptor(new Bombilla());
        i1.presionar();  // enciende
        i1.presionar();  // apaga

        System.out.println();
        System.out.println("--- Interruptor conectado a un Ventilador ---");
        Interruptor i2 = new Interruptor(new Ventilador());
        i2.presionar();
        i2.presionar();

        System.out.println();
        System.out.println("--- Interruptor conectado a una Cafetera ---");
        Interruptor i3 = new Interruptor(new Cafetera());
        i3.presionar();
        i3.presionar();
    }
}
