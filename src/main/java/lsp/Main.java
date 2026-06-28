package lsp;

import java.util.List;
import java.util.Locale;

/**
 * Demostración del principio LSP con figuras geométricas.
 * Todas las figuras se tratan como su tipo base 'Figura': cualquier subtipo
 * puede sustituir a otro en el mismo cálculo sin romper el programa.
 */
public class Main {
    public static void main(String[] args) {
        List<Figura> figuras = List.of(
            new Circulo(5),
            new Rectangulo(4, 6),
            new Cuadrado(3),
            new TrianguloEquilatero(4)
        );

        System.out.println("--- Calculando area y perimetro de cada figura ---");
        double areaTotal = 0;
        for (Figura f : figuras) {   // f puede ser CUALQUIER subtipo: todos cumplen el contrato
            System.out.printf(Locale.US, "%-22s | area = %8.2f | perimetro = %7.2f%n",
                f.nombre(), f.area(), f.perimetro());
            areaTotal += f.area();
        }

        System.out.printf(Locale.US, "%nArea total de todas las figuras: %.2f%n", areaTotal);
    }
}
