# FH1037-2026-1

Ejercicios del curso **Fundamentos de Programación** — semestre 2026-1.

## Estructura

```
src/fundamentos/
├── arreglos/        # algoritmos y arreglos bidimensionales
├── cadenas/         # ejercicios de manipulación de cadenas
└── poo/             # programación orientada a objetos
    ├── banco/       # encapsulamiento, herencia (Cuenta)
    └── biblioteca/  # composición, agregación, asociación
docs/poo/biblioteca/ # diagrama de clases (Mermaid)
```

## Compilar y ejecutar

```bash
make all          # compila todos los .java a out/
make run          # ejecuta la clase indicada en MAIN del Makefile
make clean        # borra out/
```

Para correr una clase puntual (ejemplo `BibliotecaDemo`):

```bash
java -cp out fundamentos.poo.biblioteca.BibliotecaDemo
```

## Documentación

- Diagrama de clases del sistema de Biblioteca: [docs/poo/biblioteca/diagrama-clases.md](docs/poo/biblioteca/diagrama-clases.md)
