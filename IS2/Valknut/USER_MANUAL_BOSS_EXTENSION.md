# User Manual - Boss Battles Extension

## Starting the game

1. Install Java 21 or another recent Java version.
2. Place `Valknut_Hana_Recovery_Extension_v1.0.jar` in any folder.
3. Open Command Prompt in that folder.
4. Run:

```bat
java -jar Valknut_Hana_Recovery_Extension_v1.0.jar
```

5. Start a new game and select the desired game mode and heroes.

## Reaching the new boss battles

The boss battles are integrated into the existing story. No special menu option is required.

- **Fafnir** appears in the third story combat.
- **Skoll** appears with Hati in the fourth story combat.

## Fafnir battle

### Phase 1: Wing Assault
When Fafnir has more than 100 HP, the combat log displays:

```text
FAFNIR - PHASE 1: WING ASSAULT
```

Fafnir attacks one active hero. Players can attack, defend, use items, inspect stats, run, or use Undo according to the existing game rules.

### Phase 2: Inferno
When Fafnir reaches 100 HP or less, the combat log displays:

```text
FAFNIR - PHASE 2: INFERNO
```

Fafnir first deals fire damage to every active hero and then performs a stronger normal attack against one target. The player should watch all hero health values and use healing or defensive actions when necessary.

## Skoll battle

The log displays the current Skoll boss-turn number.

- On odd boss turns, Skoll gathers moonlight but does not heal.
- On every even boss turn, Skoll restores up to 20 HP before attacking.
- Healing never exceeds Skoll's maximum health.

Players should consider dealing enough damage between healing turns to prevent Skoll from recovering repeatedly.

## Combat log

All boss phases, special mechanics, healing amounts, targets, damage, and health values are displayed in the existing combat log.

## Saving and loading

Use the existing Save and Load controls. Boss objects and their internal turn counters are serializable, so a saved boss battle can continue after loading.

## Known limitations

- The extension reuses the existing Fafnir and Skoll sprites.
- Boss mechanics are communicated through the combat log; there are no new animations.
- Fafnir's area attack is not reduced by the Defend action, while the following targeted attack is reduced.
- Skoll is the boss in the fourth combat; Hati remains a normal enemy.
