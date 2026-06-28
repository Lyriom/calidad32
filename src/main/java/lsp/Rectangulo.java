package lsp;

public class Rectangulo extends Figura {
    protected final double base;
    protected final double altura;

    public Rectangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double area() {
        return base * altura;
    }

    @Override
    public double perimetro() {
        return 2 * (base + altura);
    }

    @Override
    public String nombre() {
        return "Rectangulo";
    }
}
