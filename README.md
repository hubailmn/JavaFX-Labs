# Advance Programming JavaFX

A collection of JavaFX coursework exercises and small applications written for an "Advanced Programming" course.

## Features

- `lab4` — Smart Lamp application.
- `lab6` — student registration form.
- `lab7` — student list built with FXML and an MVC controller.
- `media` — folder viewer and media runner.
- `quiz` — login form example.
- `sixseven` — animated figure demo.
- `practice` — practice exercises.
- `FinalProjectV2` — a memory card game.

## Requirements

- Java 24
- Maven (wrapper included: `./mvnw`)
- JavaFX 21 (pulled in as a Maven dependency)

## Build & Run

```bash
./mvnw clean package
```

The `javafx-maven-plugin` is configured with `lab4` as its default main class, so it can also be launched with:

```bash
./mvnw javafx:run
```

Individual labs are run by executing their own main classes (for example `SmartLampApp`, `StudentApp`, `MemoryGameApp`) from an IDE.

## Project structure

- `src/main/java/cc/hubailmn/ap/` — one package per lab or exercise.
- `src/main/resources/assets/` — images used by the examples.

## Notes

Coursework repository; most packages are independent exercises rather than one unified application.
