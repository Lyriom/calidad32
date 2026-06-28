package isp;

/**
 * Implementa TODAS las interfaces porque realmente tiene esas capacidades.
 * No se ve forzada a nada: una multifunción sí imprime, escanea y envía fax.
 */
public class ImpresoraMultifuncion implements Impresora, Escaner, Fax {
    @Override
    public void imprimir(String documento) {
        System.out.println("Multifuncion: imprimiendo '" + documento + "'");
    }

    @Override
    public void escanear(String documento) {
        System.out.println("Multifuncion: escaneando '" + documento + "'");
    }

    @Override
    public void enviarFax(String documento, String destino) {
        System.out.println("Multifuncion: enviando fax de '" + documento + "' a " + destino);
    }
}
