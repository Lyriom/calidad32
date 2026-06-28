package dip;

/**
 * DIP - Dependency Inversion Principle.
 * Módulo de ALTO NIVEL. Depende de la abstracción Dispositivo, nunca de clases
 * concretas. La implementación se inyecta por constructor (inyección de dependencias),
 * por lo que el interruptor funciona con cualquier dispositivo presente o futuro.
 */
public class Interruptor {
    private final Dispositivo dispositivo;
    private boolean encendido = false;

    public Interruptor(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    public void presionar() {
        if (encendido) {
            dispositivo.apagar();
        } else {
            dispositivo.encender();
        }
        encendido = !encendido;
    }
}
