# Biblioteca

Progetto Java semplice, **senza build tool** (niente Maven/Gradle): solo sorgenti compilabili con `javac`.

Piccola applicazione console per gestire un catalogo di libri: aggiunta, elenco, ricerca per titolo e rimozione per ISBN.

## Struttura

```
biblioteca/
├── README.md
└── src/
    ├── Main.java        # Punto di ingresso (menu console)
    ├── Libro.java       # Modello: un libro
    └── Biblioteca.java  # Logica: gestione del catalogo
```

## Requisiti

- JDK 11 o superiore (`java -version`)

## Compilazione

```bash
cd biblioteca
javac -d out src/*.java
```

## Esecuzione

```bash
java -cp out Main
```

## Pulizia

```bash
rm -rf out
```
