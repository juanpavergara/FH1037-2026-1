# FH1037-2026-1

Ejercicios del curso **Fundamentos de Programación** — semestre 2026-1.

## Estructura

```
src/fundamentos/
├── cadenas/             # ejercicios de manipulación de cadenas
├── arreglos/
│   ├── algoritmos/      # búsqueda y ordenamiento en arreglos
│   └── bidimensional/   # arreglos bidimensionales (matrices)
├── colecciones/         # ArrayList<Integer>: .get vs .remove (índice u objeto)
├── entradasalida/       # entrada y salida con archivos (demo + ejercicios)
└── poo/                 # programación orientada a objetos
    ├── banco/           # encapsulamiento, herencia, polimorfismo (Cuenta)
    ├── biblioteca/      # composición, agregación, asociación
    └── figuras/         # clases abstractas vs interfaces (default methods)
docs/
├── fundamentos/entradasalida/  # guía de entrada y salida
└── poo/
    ├── biblioteca/      # diagrama de clases (Mermaid)
    └── figuras/         # diagrama de clases (Mermaid)
```

## Temas

| Paquete | Tema | Clases ejecutables |
|---|---|---|
| `cadenas` | Manipulación de cadenas | `Word`, `DeFront` |
| `arreglos.algoritmos` | Búsqueda y ordenamiento | `ArraySearch`, `ArraySorting` |
| `arreglos.bidimensional` | Matrices | `ArreglosBidimensionales` |
| `colecciones` | Ambigüedad de `.get`/`.remove` en `ArrayList<Integer>` | `ArrayListGetDemo`, `ArrayListRemoveDemo` |
| `entradasalida` | Lectura y escritura de archivos (`EntradaSalidaEjercicios` trae 3 ejercicios con `TODO` para completar) | `EntradaSalidaDemo`, `EntradaSalidaEjercicios` |
| `poo.banco` | Encapsulamiento, herencia y polimorfismo (`Cuenta`, `CuentaAhorros`, `CuentaCorriente`) | `BancoDemo` |
| `poo.biblioteca` | Composición, agregación y asociación (`Biblioteca`, `Libro`, `Copia`, `Miembro`, `Prestamo`) | `BibliotecaDemo` |
| `poo.figuras` | Clase abstracta `Figura`, interfaces `Escalable` y `Dibujable` (default methods), y `Operable` (clase abstracta pura, como contraste con las interfaces) | `FigurasDemo` |

## Compilar y ejecutar

```bash
make all          # compila todos los .java a out/
make run          # ejecuta la clase indicada en MAIN del Makefile
make clean        # borra out/
```

Para correr una clase puntual, use su nombre completo (paquete + clase):

```bash
java -cp out fundamentos.poo.biblioteca.BibliotecaDemo
java -cp out fundamentos.poo.figuras.FigurasDemo
java -cp out fundamentos.entradasalida.EntradaSalidaEjercicios
```

> Los programas de `entradasalida` generan archivos (`salida.txt`, `ejercicio*.txt`) en el directorio desde donde se ejecutan. Están en `.gitignore`.

## Documentación

- Guía de entrada y salida: [docs/fundamentos/entradasalida/entrada-y-salida.md](docs/fundamentos/entradasalida/entrada-y-salida.md)
- Diagrama de clases del sistema de Biblioteca: [docs/poo/biblioteca/diagrama-clases.md](docs/poo/biblioteca/diagrama-clases.md)
- Diagrama de clases de Figuras: [docs/poo/figuras/diagrama-clases.md](docs/poo/figuras/diagrama-clases.md)
