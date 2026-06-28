package dip;

/**
 * Nuevo dispositivo agregado sin modificar Interruptor: solo cumple la abstracción.
 */
public class Cafetera implements Dispositivo {
    @Override
    public void encender() {
        System.out.println("Cafetera preparando cafe.");
    }

    @Override
    public void apagar() {
        System.out.println("Cafetera en reposo.");
    }

    @Override
    public String nombre() {
        return "Cafetera";
    }
}
