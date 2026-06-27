/**
 * @author Hana
 * AI-assisted: yes
 * AI tool used: ChatGPT
 * Type of help received: Debugging and some help with adapting
 */
package me.model.boss;

import java.util.List;
import me.model.Enemy;
import me.model.Hero;
import me.view.Messages;

/**
 * Base class for all Valknut bosses.
 *
 * The final performBossTurn method is the Template Method. It fixes the
 * algorithm used by every boss, while subclasses customize individual steps.
 */
public abstract class AbstractBossEnemy extends Enemy {
    private static final long serialVersionUID = 1L;

    private int bossTurnNumber;

    protected AbstractBossEnemy(String name, int life, int maxLife,
            int xpReward, String attackDescription) {
        super(name, life, maxLife, xpReward, attackDescription);
        bossTurnNumber = 0;
    }

    /**
     * Connects the boss hierarchy to the normal enemy-turn API.
     */
    @Override
    public final String performTurn(List<Hero> heroes) {
        return performBossTurn(heroes);
    }

    /**
     * Template Method: all bosses execute these steps in this exact order.
     */
    public final String performBossTurn(List<Hero> heroes) {
        bossTurnNumber++;
        StringBuilder log = new StringBuilder();

        appendStep(log, announcePhase());

        Hero target = selectTarjet(heroes);
        if (target == null) {
            log.append(name().toUpperCase())
               .append(Messages.ENEMY_MISS)
               .append(Messages.NEW_LINE);
            return log.toString();
        }

        appendStep(log, applySpecialMechanic(heroes, target));

        if (target.isAlive() && !target.escaped()) {
            appendStep(log, performAttack(target));
        } else {
            log.append(name().toUpperCase())
               .append(" cannot complete the normal attack because the target is no longer available.")
               .append(Messages.NEW_LINE);
        }

        appendStep(log, afterTurnEffect(heroes, target));
        return log.toString();
    }

    protected final int getBossTurnNumber() {
        return bossTurnNumber;
    }

    protected final String performStandardBossAttack(Hero target, int normalDamage,
            int defendedDamage) {
        StringBuilder log = new StringBuilder();
        log.append(name().toUpperCase())
           .append(" attacks ")
           .append(target.name().toUpperCase())
           .append(Messages.NEW_LINE);
        log.append(getAttack()).append(Messages.NEW_LINE);

        int damage = target.isDefending() ? defendedDamage : normalDamage;
        log.append(attack(target, getMainElement(), damage)).append(Messages.NEW_LINE);
        return log.toString();
    }

    private void appendStep(StringBuilder log, String text) {
        if (text == null || text.isBlank()) {
            return;
        }
        log.append(text);
        if (!text.endsWith(Messages.NEW_LINE)) {
            log.append(Messages.NEW_LINE);
        }
    }

    protected abstract String announcePhase();

    protected abstract String applySpecialMechanic(List<Hero> heroes, Hero target);

    protected abstract String performAttack(Hero target);

    protected abstract String afterTurnEffect(List<Hero> heroes, Hero target);
}
