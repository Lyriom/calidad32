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

---

## Reflexiones individuales

### Josue Riera
El principio que más me costó aplicar fue **DIP**. Al inicio no veía el problema de que el
`Interruptor` creara su propio dispositivo adentro; me parecía lo natural. Recién cuando quise
cambiar la bombilla por un ventilador entendí que tenía que modificar una clase que ya
funcionaba. Pasar la dependencia por constructor lo resolvió y dejó el código mucho más fácil
de probar. Creo que SOLID mejora el diseño porque permite cambiar una parte del sistema sin
miedo a romper el resto. En mis proyectos actuales aplicaría DIP siempre que dependa de
servicios externos (APIs de pago, notificaciones): definir una interfaz e inyectar la
implementación desde afuera.

### Joao Conde
Para mí el más desafiante fue **LSP**, porque entender que la herencia es un *contrato de
comportamiento* y no solo reutilización de código me tomó tiempo. Con las figuras geométricas
quedó claro: si un subtipo no devuelve un área válida, todo el cálculo total se cae. Lo más
difícil fue decidir qué iba en la clase base y qué no (por eso modelamos `Cuadrado` inmutable).
SOLID mejora el diseño porque reduce las sorpresas: el código hace lo que uno espera. Como QA,
el principio que más aplicaría es LSP, porque garantiza que las clases sean predecibles y por
tanto mucho más fáciles de testear.

### Victor Suquilanda
El que más trabajo me dio fue **SRP**, no por difícil de entender sino por difícil de aplicar
bien: cuesta decidir dónde termina una responsabilidad y empieza otra. Al separar la nómina en
calculadora, generador, repositorio y coordinador dudé si no estaba creando demasiadas clases,
pero al final cada una quedó clara y fácil de mantener. SOLID hace que trabajar en equipo sea
más ordenado: cada quien puede tocar una clase distinta sin pisarse. En el front aplicaría SRP
constantemente, separando componentes de presentación de la lógica de datos; apenas un
componente empieza a "hacer de todo", sé que después va a doler.
