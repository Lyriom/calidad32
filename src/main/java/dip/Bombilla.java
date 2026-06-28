package dip;

public class Bombilla implements Dispositivo {
    @Override
    public void encender() {
        System.out.println("Bombilla encendida.");
    }

    @Override
    public void apagar() {
        System.out.println("Bombilla apagada.");
    }

    @Override
    public String nombre() {
        return "Bombilla";
    }
}
