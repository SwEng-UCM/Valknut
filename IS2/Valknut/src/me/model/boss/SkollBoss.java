/**
 * @author Hana
 * AI-assisted:no
 *
 */
package me.model.boss;

import java.util.ArrayList;
import java.util.List;
import me.model.Hero;
import me.view.Messages;

/**
 * Skoll regenerates health every second boss turn before attacking.
 */
public class SkollBoss extends AbstractBossEnemy {
    private static final long serialVersionUID = 1L;

    private static final int HEAL_AMOUNT = 20;

    public SkollBoss() {
        super(Messages.SKOLL, 150, 450, 250, Messages.SKOLL_ATTACK);

        List<Integer> elements = new ArrayList<>();
        elements.add(1); // ICE
        elements.add(4); // CHAOS
        elements.add(1); // NATURE
        elements.add(1); // BLOOD
        elements.add(1); // FIRE
        setElementStats(elements);
        setSprite("/resources/images/Creatures/skoll.png");
    }

    @Override
    protected String announcePhase() {
        return "SKOLL - MOON HUNT, BOSS TURN " + getBossTurnNumber();
    }

    @Override
    protected String applySpecialMechanic(List<Hero> heroes, Hero target) {
        if (getBossTurnNumber() % 2 != 0) {
            return "Skoll gathers moonlight but does not heal this turn.";
        }

        int lifeBeforeHealing = getLife();
        changeLife(HEAL_AMOUNT);
        int healed = getLife() - lifeBeforeHealing;

        return "MOONLIGHT REGENERATION: Skoll restores " + healed
                + " health points. Current health: " + getLife() + ".";
    }

    @Override
    protected String performAttack(Hero target) {
        return performStandardBossAttack(target, 24, 10);
    }

    @Override
    protected String afterTurnEffect(List<Hero> heroes, Hero target) {
        return "Skoll completes the hunt and waits for the next moon cycle.";
    }
}
