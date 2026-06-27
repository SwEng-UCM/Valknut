# Valknut - Hana Recovery Extension v1.0

Valknut is a Java Swing turn-based game inspired by Norse mythology. This recovery extension adds real boss battles using the **Template Method design pattern**.

## New functionality

- `FafnirBoss`
  - Phase 1: targeted wing attack.
  - Phase 2 at 100 HP or lower: fire damage to every active hero, followed by a stronger targeted attack.
- `SkollBoss`
  - Restores up to 20 HP on every second boss turn before attacking.
- Existing story combats automatically create the new boss subclasses.
- Ordinary enemies keep their previous behavior.

## Design pattern

`AbstractBossEnemy.performBossTurn()` is the Template Method. It defines the fixed boss-turn algorithm:

1. announce the phase;
2. select a target;
3. apply the boss-specific mechanic;
4. perform the attack;
5. apply an after-turn effect.

Concrete bosses implement the variable steps without changing the algorithm order.

## Requirements

- Windows 11
- A recent Java Development Kit; Java 21 is recommended

Check Java:

```bat
java -version
javac -version
```

## Build

Double-click:

```text
build_recovery.bat
```

The script compiles all Java files and creates:

```text
Valknut_Hana_Recovery_Extension_v1.0.jar
```

## Run

```bat
java -jar Valknut_Hana_Recovery_Extension_v1.0.jar
```

The JAR includes the game classes and required image/audio resources, so it can be copied and executed from an arbitrary folder.

## Important source files

```text
src/me/model/boss/AbstractBossEnemy.java
src/me/model/boss/FafnirBoss.java
src/me/model/boss/SkollBoss.java
src/me/model/Enemy.java
src/me/model/Combat.java
src/me/model/EnemyBuilder.java
```

## Testing

See `MANUAL_TESTS_BOSS_EXTENSION.md` for ten documented manual tests. The implementation was also compiled with Java 21 and smoke-tested for:

- Fafnir Phase 1;
- Fafnir Phase 2 and area damage;
- Skoll regeneration;
- delegation from `Combat` to the boss Template Method;
- executable JAR structure and manifest.

