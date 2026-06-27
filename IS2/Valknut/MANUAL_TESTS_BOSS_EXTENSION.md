# Manual Tests - Boss Battles Extension

## Test 1: JAR starts outside the IDE

**Precondition:** Java 21 is installed.
**Steps:** Copy the JAR to an arbitrary folder and run `java -jar Valknut_Hana_Recovery_Extension_v1.0.jar`.
**Expected result:** The main menu opens and the game does not require Eclipse.
**Actual result:** The JAR was successfully started from the command line outside Eclipse, and the main menu opened correctly.
**Status:** Passed.

## Test 2: Fafnir Phase 1

**Precondition:** Reach the Fafnir combat while Fafnir has more than 100 HP.
**Steps:** Complete a hero round and observe Fafnir's turn.
**Expected result:** The log shows `FAFNIR - PHASE 1: WING ASSAULT`. Fafnir attacks one active hero and does not damage all heroes.
**Actual result:** The combat log displayed `FAFNIR - PHASE 1: WING ASSAULT`. Fafnir selected and attacked one active hero without applying area damage.
**Status:** Passed.

## Test 3: Fafnir Phase 2 transition

**Precondition:** Fafnir has 100 HP or less.
**Steps:** Complete a hero round and observe Fafnir's turn.
**Expected result:** The log shows `FAFNIR - PHASE 2: INFERNO`.
**Actual result:** After Fafnir's health dropped to 100 HP or less, the combat log displayed `FAFNIR - PHASE 2: INFERNO`.
**Status:** Passed.

## Test 4: Fafnir area damage

**Precondition:** At least two heroes are active and Fafnir is in Phase 2.
**Steps:** Complete a hero round.
**Expected result:** Every active, non-escaped hero receives fire damage before Fafnir performs his normal attack.
**Actual result:** Both active heroes received fire damage during the Inferno mechanic. Fafnir then selected one hero and performed his normal targeted attack.
**Status:** Passed.

## Test 5: Skoll first turn

**Precondition:** Reach the Skoll and Hati combat.
**Steps:** Complete the first hero round.
**Expected result:** The log states that Skoll gathers moonlight but does not heal on boss turn 1. Skoll then attacks normally.
**Actual result:** On boss turn 1, the combat log stated that Skoll gathered moonlight without healing. Skoll then selected a hero and performed a normal attack.
**Status:** Passed.

## Test 6: Skoll regeneration

**Precondition:** Skoll has lost at least 20 HP and is about to execute boss turn 2.
**Steps:** Complete the next hero round.
**Expected result:** Skoll heals up to 20 HP without exceeding maximum HP, and the combat log reports the exact amount healed.
**Actual result:** On boss turn 2, Skoll activated Moonlight Regeneration and restored 20 health points without exceeding his maximum health. The healed amount was shown in the combat log.
**Status:** Passed.

## Test 7: Defend still works against bosses

**Precondition:** A hero acts immediately before a boss turn.
**Steps:** Select Defend.
**Expected result:** The boss's normal targeted attack uses reduced damage. Other special mechanics, such as Fafnir's area damage, remain independent.
**Actual result:** When the boss selected the defending hero as the target of its normal attack, the received damage was reduced. Fafnir's area-damage mechanic was still applied independently to all active heroes.
**Status:** Passed.

## Test 8: Original enemies still work

**Precondition:** Start the first combat against Ice and Fire Giants.
**Steps:** Play one complete round.
**Expected result:** The ordinary enemies target and attack heroes as before, with no boss phase or regeneration messages.
**Actual result:** The Ice and Fire Giants selected and attacked active heroes normally. No boss phase, special mechanic, or regeneration message appeared.
**Status:** Passed.

## Test 9: Story integration

**Precondition:** Start a normal new game.
**Steps:** Progress through the story to combat 3 and combat 4.
**Expected result:** Fafnir appears in combat 3 and Skoll appears with Hati in combat 4; no additional menu action is required.
**Actual result:** Fafnir was created automatically in combat 3. Skoll and Hati appeared automatically in combat 4 through the existing story progression.
**Status:** Passed.

## Test 10: Save/load during a boss battle

**Precondition:** Start a Skoll boss battle and complete at least one Skoll turn.
**Steps:** Save, close, reopen, and load the game. Continue until Skoll acts again.
**Expected result:** The game loads without a class error, and the boss remains functional. The serialized boss turn counter continues from the saved state.
**Actual result:** The saved game loaded without a class or serialization error. Skoll remained functional, and his boss turn counter continued from the saved value instead of restarting.
**Status:** Passed.
