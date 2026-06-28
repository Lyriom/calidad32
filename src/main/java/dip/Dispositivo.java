package dip;

/**
 * DIP - Dependency Inversion Principle.
 * Abstracción de la que dependen TANTO el módulo de alto nivel (Interruptor)
 * COMO los módulos de bajo nivel (Bombilla, Ventilador, Cafetera).
 */
public interface Dispositivo {
    void encender();
    void apagar();
    String nombre();
}
