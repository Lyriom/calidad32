package isp;

/**
 * ISP - Interface Segregation Principle.
 * Interfaz pequeña y específica: solo la capacidad de escanear.
 */
public interface Escaner {
    void escanear(String documento);
}
