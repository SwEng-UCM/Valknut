# Valknut Boss Battles Extension - Exact Change Guide

## Selected option
Option 3: Boss Battles using the Template Method design pattern.

## New files
Create the package `src/me/model/boss/` and add:

1. `AbstractBossEnemy.java`
   - Extends `Enemy`.
   - Contains the final Template Method `performBossTurn(List<Hero>)`.
   - Fixes the boss-turn order:
     1. `announcePhase()`
     2. select target
     3. `applySpecialMechanic(...)`
     4. `performAttack(...)`
     5. `afterTurnEffect(...)`
   - Provides a helper for a normal boss attack.

2. `FafnirBoss.java`
   - Concrete boss.
   - Phase 1 above 100 HP.
   - Phase 2 at 100 HP or lower.
   - In Phase 2, performs fire area damage against all active heroes before the normal attack.

3. `SkollBoss.java`
   - Concrete boss.
   - Restores up to 20 HP on every second boss turn before attacking.

## Modified files

### `src/me/model/Enemy.java`
- Import `me.view.Messages`.
- Add `performTurn(List<Hero>)` for the standard enemy-turn behavior.
- This gives regular enemies and bosses the same entry point.

### `src/me/model/Combat.java`
Replace the old enemy attack block inside `attack(int i)` with:

```java
Enemy e = enemies.get(turn - (heroes.size() + 1));
sb.append(e.performTurn(heroes));
heroes.removeIf(hero -> !hero.isAlive());
```

This makes combat use polymorphism. Normal enemies execute `Enemy.performTurn()`, while bosses execute the final Template Method inherited from `AbstractBossEnemy`.

### `src/me/model/EnemyBuilder.java`
- Import `FafnirBoss` and `SkollBoss`.
- Change the `fafnir` case to return `new FafnirBoss()`.
- Change the `skoll` case to return `new SkollBoss()`.
- No change is required in `Storyteller.java`, because it already requests enemies using `EnemyBuilder.buildEnemy("fafnir")` and `EnemyBuilder.buildEnemy("skoll")`.

### `src/me/view/AudioManager.java`
- Catch `IllegalArgumentException` when audio is unavailable.
- This prevents the whole JAR from crashing on a computer without a compatible audio line.

## Why the integration is safe
- `FafnirBoss` and `SkollBoss` are still `Enemy` objects, so the existing lists and combat GUI continue to work.
- Existing sprites are reused.
- Existing story order is unchanged.
- Save/load can serialize boss objects and their turn counters because the boss hierarchy is serializable through `Enemy`.
- Regular enemies still use the original damage and targeting behavior.

## Build on Windows 11
1. Install a recent JDK, preferably Java 21.
2. Open the project folder.
3. Double-click `build_recovery.bat`.
4. The script creates `Valknut_Hana_Recovery_Extension_v1.0.jar`.
5. Copy the JAR to another folder and double-click `run_recovery.bat`, or run:

```bat
java -jar Valknut_Hana_Recovery_Extension_v1.0.jar
```

## Files that must be shown in the defense
- `AbstractBossEnemy.java`: explain the Template Method and its hook methods.
- `FafnirBoss.java`: explain phase transition and area damage.
- `SkollBoss.java`: explain turn counter and regeneration.
- `Enemy.java`: explain the common `performTurn()` method.
- `Combat.java`: explain polymorphic delegation.
- `EnemyBuilder.java`: explain how the story now creates boss subclasses.
