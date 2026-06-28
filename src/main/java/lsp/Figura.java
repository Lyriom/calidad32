package lsp;

/**
 * LSP - Liskov Substitution Principle.
 * Clase base abstracta. Cualquier subtipo de Figura debe poder SUSTITUIR a Figura
 * sin alterar el comportamiento correcto del programa (área y perímetro válidos, sin excepciones).
 */
public abstract class Figura {
    public abstract double area();
    public abstract double perimetro();
    public abstract String nombre();
}
