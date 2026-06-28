package isp;

/**
 * ISP - Interface Segregation Principle.
 * Interfaz pequeña y específica: solo la capacidad de imprimir.
 */
public interface Impresora {
    void imprimir(String documento);
}
