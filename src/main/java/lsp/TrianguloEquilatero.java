package lsp;

public class TrianguloEquilatero extends Figura {
    private final double lado;

    public TrianguloEquilatero(double lado) {
        this.lado = lado;
    }

    @Override
    public double area() {
        return (Math.sqrt(3) / 4) * lado * lado;
    }

    @Override
    public double perimetro() {
        return 3 * lado;
    }

    @Override
    public String nombre() {
        return "Triangulo equilatero";
    }
}
