# Principios SOLID en Java

**Universidad de Las Américas (UDLA) — Calidad de Software**
**Actividad Integral sobre los Principios SOLID**

| | |
|---|---|
| **Categoría de grupo** | Semana 1 - Autoinscripcion |
| **Nombre de grupo** | S1 1 - Martes |
| **Integrantes** | Josue Riera · Joao Conde · Victor Suquilanda |

Este repositorio implementa y refactoriza cinco ejemplos en Java, uno por cada principio
SOLID, con dominios propios distintos a los del proyecto base. Cada principio vive en su
propio paquete y tiene su clase `Main`.

```
src/main/java/
├── srp/  -> Sistema de nómina (rol de pagos)
├── ocp/  -> Cálculo de descuentos por tipo de cliente
├── lsp/  -> Áreas y perímetros de figuras geométricas
├── isp/  -> Dispositivos de oficina (impresora / escáner / fax)
└── dip/  -> Interruptor que controla dispositivos inyectados
```

## Cómo compilar y ejecutar

Con `javac`/`java` (no requiere Maven):

```bash
# Compilar todo
javac -encoding UTF-8 -d target/classes $(find src/main/java -name "*.java")

# Ejecutar cada principio
java -cp target/classes srp.Main
java -cp target/classes ocp.Main
java -cp target/classes lsp.Main
java -cp target/classes isp.Main
java -cp target/classes dip.Main
```

O simplemente: `./run.sh` (todos) o `./run.sh ocp` (uno solo).

Las capturas de la ejecución están en la carpeta [`capturas/`](capturas/).

---

## 1. Single Responsibility Principle (SRP)

> *"Una clase debe tener una sola razón para cambiar."*

### Problema que resuelve
Una clase que valida, calcula, formatea y guarda al mismo tiempo tiene **muchas razones para
cambiar**. Cualquier ajuste (la fórmula de impuestos, el formato del reporte, el motor de base
de datos) obliga a tocar la misma clase, con riesgo de romper lo demás.

### Cómo se aplicó
Modelamos un **sistema de nómina** y separamos cada responsabilidad en su propia clase:

| Clase | Única responsabilidad |
|---|---|
| `Empleado` | Almacenar los datos del empleado |
| `CalculadoraSalario` | Calcular el salario neto (impuestos + horas extra) |
| `GeneradorReporte` | Dar formato al reporte |
| `RepositorioNomina` | Persistir el rol de pagos |
| `ServicioNomina` | **Coordinar** el flujo, delegando cada paso |

`ServicioNomina` no calcula ni guarda: solo orquesta. Si cambia la fórmula del salario, solo
cambia `CalculadoraSalario`; el resto del sistema ni se entera.

---

## 2. Open/Closed Principle (OCP)

> *"Abierto a la extensión, cerrado a la modificación."*

### Problema que resuelve
Calcular descuentos con `if/else` por tipo de cliente significa que cada nuevo tipo de cliente
obliga a **modificar** la calculadora y arriesgarse a romper los descuentos que ya funcionaban.

### Cómo se aplicó
Definimos la abstracción `Descuento` y una implementación por estrategia:
`DescuentoRegular`, `DescuentoPremium`, `DescuentoVip`. La clase `CalculadoraPrecio` trabaja
contra la interfaz mediante polimorfismo, **sin condicionales por tipo**.

Para demostrar la extensión agregamos `DescuentoTemporada` (35%) **sin tocar ni una línea** de
`CalculadoraPrecio`: basta crear la nueva clase. Eso es estar abierto a la extensión y cerrado
a la modificación.

---

## 3. Liskov Substitution Principle (LSP)

> *"Un subtipo debe poder sustituir a su tipo base sin alterar el comportamiento correcto del programa."*

### Problema que resuelve
Si una subclase no puede cumplir el contrato de su clase base (lanza excepciones, devuelve
valores inválidos o cambia el comportamiento esperado), tratarla como la base rompe el programa.

### Cómo se aplicó
Creamos la clase base abstracta `Figura` con `area()`, `perimetro()` y `nombre()`. Los subtipos
`Circulo`, `Rectangulo`, `Cuadrado` y `TrianguloEquilatero` la implementan respetando el
contrato: **todos** devuelven valores válidos y ninguno lanza excepciones.

En `Main` recorremos una `List<Figura>` y calculamos el área total tratando a cada elemento como
`Figura`: cualquier subtipo sustituye a otro sin que el cálculo se rompa. Además modelamos
`Cuadrado` como **inmutable** (sin `setAncho`/`setAlto`), evitando la trampa clásica donde un
cuadrado que hereda de rectángulo viola las expectativas de quien usa el rectángulo.

---

## 4. Interface Segregation Principle (ISP)

> *"Ningún cliente debería verse obligado a depender de métodos que no usa."*

### Problema que resuelve
Una interfaz "gorda" como `Dispositivo { imprimir(); escanear(); enviarFax(); }` obliga a una
impresora básica a implementar `escanear()` y `enviarFax()` aunque no pueda hacerlo, forzando
métodos vacíos o `UnsupportedOperationException`.

### Cómo se aplicó
Partimos la interfaz grande en tres interfaces pequeñas y específicas: `Impresora`, `Escaner`
y `Fax`. Así:

- `ImpresoraMultifuncion` implementa las tres, porque realmente las tiene.
- `ImpresoraBasica` implementa **solo** `Impresora`.

La impresora básica ya no depende de capacidades que no usa, y el compilador impide invocar
`escanear()` sobre ella. El error se detecta en compilación, no en producción.

---

## 5. Dependency Inversion Principle (DIP)

> *"Los módulos de alto nivel no deben depender de los de bajo nivel; ambos deben depender de abstracciones."*

### Problema que resuelve
Si un `Interruptor` crea internamente un `new Bombilla()`, queda acoplado a esa clase concreta.
Cambiar a un ventilador o una cafetera obliga a modificar el interruptor — un módulo de alto
nivel que no debería conocer los detalles.

### Cómo se aplicó
Creamos la abstracción `Dispositivo` (`encender`, `apagar`, `nombre`). El módulo de alto nivel
`Interruptor` depende solo de esa interfaz y recibe la implementación **por constructor**
(inyección de dependencias). `Bombilla`, `Ventilador` y `Cafetera` la implementan.

El mismo `Interruptor` controla cualquier dispositivo presente o futuro sin modificarse: la
dirección de la dependencia se invierte hacia la abstracción.
