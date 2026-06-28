package isp;

/**
 * ISP - Interface Segregation Principle.
 * Solo implementa lo que puede hacer: imprimir. NO está obligada a depender de
 * escanear() ni enviarFax(), que no usa. El compilador impide invocar esos métodos.
 */
public class ImpresoraBasica implements Impresora {
    @Override
    public void imprimir(String documento) {
        System.out.println("Basica: imprimiendo '" + documento + "'");
    }
}
