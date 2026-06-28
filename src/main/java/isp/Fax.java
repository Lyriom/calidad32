package isp;

/**
 * ISP - Interface Segregation Principle.
 * Interfaz pequeña y específica: solo la capacidad de enviar fax.
 */
public interface Fax {
    void enviarFax(String documento, String destino);
}
