package dip;

public class Ventilador implements Dispositivo {
    @Override
    public void encender() {
        System.out.println("Ventilador girando a velocidad media.");
    }

    @Override
    public void apagar() {
        System.out.println("Ventilador detenido.");
    }

    @Override
    public String nombre() {
        return "Ventilador";
    }
}
