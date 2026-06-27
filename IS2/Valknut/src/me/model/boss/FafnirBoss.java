/**
 * @author Hana
 * AI-assisted: yes
 * AI tool used: ChatGPT
 * Type of help received: help with debugging and I had some repetitive code i did not know how to get
 * rid of
 */
package me.model.boss;

import java.util.ArrayList;
import java.util.List;
import me.model.Element;
import me.model.Hero;
import me.view.Messages;

/**
 * Fafnir has two phases. Below half of his starting health, he enters an
 * enraged phase and damages every active hero before his normal attack.
 */
public class FafnirBoss extends AbstractBossEnemy {
    private static final long serialVersionUID = 1L;

    private static final int STARTING_LIFE = 200;
    private static final int ENRAGED_THRESHOLD = STARTING_LIFE / 2;
    private static final int AREA_DAMAGE = 8;

    public FafnirBoss() {
        super(Messages.FAFNIR, STARTING_LIFE, 550, 300, Messages.FAFNIR_ATTACK);

        List<Integer> elements = new ArrayList<>();
        elements.add(1); // ICE
        elements.add(1); // CHAOS
        elements.add(1); // NATURE
        elements.add(1); // BLOOD
        elements.add(5); // FIRE
        setElementStats(elements);
        setSprite("/resources/images/Creatures/fafnir.png");
    }

    private boolean isEnraged() {
        return getLife() <= ENRAGED_THRESHOLD;
    }

    @Override
    protected String announcePhase() {
        if (isEnraged()) {
            return "FAFNIR - PHASE 2: INFERNO";
        }
        return "FAFNIR - PHASE 1: WING ASSAULT";
    }

    @Override
    protected String applySpecialMechanic(List<Hero> heroes, Hero target) {
        if (!isEnraged()) {
            return "Fafnir circles above the battlefield and prepares his next strike.";
        }

        StringBuilder log = new StringBuilder();
        log.append(Messages.FAFNIR_SP_ATTACK).append(Messages.NEW_LINE);

        for (Hero hero : new ArrayList<>(heroes)) {
            if (hero.isAlive() && !hero.escaped()) {
                log.append(hero.receiveDamage(AREA_DAMAGE, Element.FIRE))
                   .append(Messages.NEW_LINE);
            }
        }
        return log.toString();
    }

    @Override
    protected String performAttack(Hero target) {
        int normalDamage = isEnraged() ? 28 : 22;
        return performStandardBossAttack(target, normalDamage, 10);
    }

    @Override
    protected String afterTurnEffect(List<Hero> heroes, Hero target) {
        if (isEnraged()) {
            return "The flames around Fafnir grow stronger.";
        }
        return "Fafnir lands and watches the heroes carefully.";
    }
}
