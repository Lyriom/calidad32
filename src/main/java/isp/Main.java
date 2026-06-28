package isp;

/**
 * Demostración del principio ISP con dispositivos de oficina.
 * Cada clase implementa solo las interfaces de las capacidades que realmente posee.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("--- Impresora Multifuncion ---");
        ImpresoraMultifuncion mf = new ImpresoraMultifuncion();
        mf.imprimir("Informe_SOLID.pdf");
        mf.escanear("Contrato.png");
        mf.enviarFax("Factura.pdf", "+593 99 123 4567");

        System.out.println();
        System.out.println("--- Impresora Basica ---");
        ImpresoraBasica basica = new ImpresoraBasica();
        basica.imprimir("Ticket.txt");
        // basica.escanear("x"); -> NO COMPILA: no depende de interfaces que no usa.
    }
}
