# Diagrama de clases — Sistema de Biblioteca

Diagrama UML del paquete `fundamentos.poo.biblioteca`
(código fuente en [`src/fundamentos/poo/biblioteca/`](../../../src/fundamentos/poo/biblioteca/)).

## Convenciones (UML 2.5.1)

| Notación Mermaid | Relación UML |
|---|---|
| `<\|--` | Herencia / generalización |
| `*--`  | Composición (`AggregationKind = composite` — la parte no existe sin el todo) |
| `o--`  | Agregación (`AggregationKind = shared` — ciclo de vida independiente) |
| `-->`  | Asociación unidireccional |
| `--`   | Asociación bidireccional |
| `..>`  | Dependencia / uso |

Referencia oficial: [UML 2.5.1 spec (OMG)](https://www.omg.org/spec/UML/2.5.1/PDF) — §11.5 *Associations* y §7.8 *Dependencies*.

## Diagrama

```mermaid
classDiagram
    direction LR

    class Biblioteca {
        -String nombre
        +registrarMiembro(Miembro)
        +registrarLibro(Libro)
        +prestar(...) Prestamo
        +buscarMiembro(String) Miembro
        +buscarLibro(String) Libro
        +getTransacciones() List~Transaccion~
    }

    class Miembro {
        <<abstract>>
        -String id
        -String nombre
        +puedePrestar() boolean
    }

    class Estudiante {
        -String programa
    }

    class Empleado {
        -String cargo
    }

    class Libro {
        -String isbn
        -String titulo
        +crearCopia(String) Copia
        +buscarCopiaPorId(String) Copia
    }

    class Copia {
        -String id
        +estaDisponible() boolean
        +getPrestamoActivo() Prestamo
    }

    class Transaccion {
        <<abstract>>
        -String id
        -LocalDate fecha
        +getTipo() String*
    }

    class Prestamo {
        -LocalDate fechaVencimiento
        -LocalDate fechaDevolucion
        +devolver(LocalDate)
        +estaActivo() boolean
    }

    %% --- HERENCIA (generalización) ---
    Miembro     <|-- Estudiante
    Miembro     <|-- Empleado
    Transaccion <|-- Prestamo

    %% --- AGREGACIÓN (ciclo de vida independiente) ---
    Biblioteca o-- "0..*" Miembro : miembrosPorId
    Biblioteca o-- "0..*" Libro   : librosPorIsbn

    %% --- COMPOSICIÓN (la parte no existe sin el todo) ---
    Biblioteca *-- "0..*" Transaccion : transacciones
    Libro      *-- "0..*" Copia       : copias

    %% --- ASOCIACIÓN unidireccional ---
    Transaccion "0..*" --> "1" Miembro : miembro

    %% --- ASOCIACIÓN bidireccional (a lo sumo un préstamo activo por copia) ---
    Prestamo "0..1" -- "1" Copia : copia / prestamoActivo

    %% --- DEPENDENCIA / USO (parámetros del método prestar) ---
    Biblioteca ..> Copia : usa (prestar)
```

## Mapeo a fragmentos de código

| Relación | Tipo UML | Multiplicidad | Origen |
|---|---|---|---|
| Biblioteca → Miembro | Agregación (`shared`) | 0..* | [Biblioteca.java:18](../../../src/fundamentos/poo/biblioteca/Biblioteca.java#L18) |
| Biblioteca → Libro | Agregación (`shared`) | 0..* | [Biblioteca.java:24](../../../src/fundamentos/poo/biblioteca/Biblioteca.java#L24) |
| Biblioteca → Transaccion | Composición (`composite`) | 0..* | [Biblioteca.java:30](../../../src/fundamentos/poo/biblioteca/Biblioteca.java#L30) |
| Libro → Copia | Composición (`composite`) | 0..* (creadas en `crearCopia`) | [Libro.java:16](../../../src/fundamentos/poo/biblioteca/Libro.java#L16) |
| Transaccion → Miembro | Asociación unidireccional | 1..1 | [Transaccion.java:14](../../../src/fundamentos/poo/biblioteca/Transaccion.java#L14) |
| Prestamo ↔ Copia | Asociación bidireccional | 1..1 ↔ 0..1 | [Prestamo.java:11](../../../src/fundamentos/poo/biblioteca/Prestamo.java#L11), [Copia.java:18](../../../src/fundamentos/poo/biblioteca/Copia.java#L18) |
| Estudiante ▷ Miembro | Herencia | — | [Estudiante.java:3](../../../src/fundamentos/poo/biblioteca/Estudiante.java#L3) |
| Empleado ▷ Miembro | Herencia | — | [Empleado.java:3](../../../src/fundamentos/poo/biblioteca/Empleado.java#L3) |
| Prestamo ▷ Transaccion | Herencia | — | [Prestamo.java:6](../../../src/fundamentos/poo/biblioteca/Prestamo.java#L6) |
| Biblioteca ⇢ Copia (uso) | Dependencia (parámetro de `prestar`) | — | [Biblioteca.java:73-95](../../../src/fundamentos/poo/biblioteca/Biblioteca.java#L73) |

## Demostración ejecutable

El uso del modelo se muestra en [`BibliotecaDemo.java`](../../../src/fundamentos/poo/biblioteca/BibliotecaDemo.java) (correr con `make all && java -cp out fundamentos.poo.biblioteca.BibliotecaDemo`).
