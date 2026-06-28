package lsp;

/**
 * LSP correcto: un Cuadrado SUSTITUYE a un Rectangulo sin romper su contrato.
 * Al ser inmutable (sin setAncho/setAlto), evita la trampa clásica donde cambiar
 * un lado del cuadrado invalidaría las expectativas sobre un rectángulo.
 */
public class Cuadrado extends Rectangulo {
    public Cuadrado(double lado) {
        super(lado, lado);
    }

    @Override
    public String nombre() {
        return "Cuadrado";
    }
}
