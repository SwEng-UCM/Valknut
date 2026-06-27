# AI Declaration - Hana Recovery Extension v1.0

## Student
Hana Perocevic

## AI tool used
ChatGPT

## Scope of AI assistance
AI assistance was used for:

- planning the Template Method structure for the boss hierarchy;
- suggesting how to connect boss turns to the existing `Enemy` and `Combat` classes;
- drafting compilation and smoke-test steps;


## Student work and responsibility
The student is responsible for:

- reviewing every AI-assisted class;
- adapting new code to the actual Valknut repository;
- compiling and executing the project;
- testing the behavior through the GUI;
- correcting any discovered problems;
- ensuring that the final report accurately describes what was personally reviewed, edited, and tested.

## AI-assisted new classes

| Class | AI-assisted | Tool | Type of assistance |
|---|---|---|---|
| `AbstractBossEnemy` | Yes | ChatGPT | integration guidance |
| `FafnirBoss` | Yes | ChatGPT | debugging |
| `SkollBoss` | Yes | ChatGPT | debugging |

## Existing classes modified 

- `Enemy.java`: common polymorphic `performTurn()` entry point.
- `Combat.java`: delegation of enemy turns through `Enemy.performTurn()`.
- `EnemyBuilder.java`: creation of concrete boss objects.
- `AudioManager.java`: defensive handling when an audio line is unavailable.

## Testing performed so far

- Java 21 compilation completed successfully.
- Fafnir Phase 1 smoke test passed.
- Fafnir Phase 2 and area-damage smoke test passed.
- Skoll second-turn regeneration smoke test passed.
- Combat-to-boss Template Method integration test passed.
- Executable JAR manifest and packaged resources were verified.


