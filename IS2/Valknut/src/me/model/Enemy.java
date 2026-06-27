/**
 * 
 * @author Helio Vega Fernández AI assisted: No
 * 
 */
package me.model;

import java.util.List;
import me.view.Messages;

public class Enemy extends Character {
    private static final long serialVersionUID = 1L;

    //It's enemy has the exp it gives, the description of the attack (how it attacks)
    // and the enemy num for the combat

    private final int xpReward;
    private String attkDesc;
    private int enemy_num;

    public Enemy(String name, int life, int max_life, int xpReward, String attack) {
        super(name, life, max_life);
        this.xpReward = xpReward;
        attkDesc = attack;
    }

    public int getXpReward() {
        return xpReward;
    }
//whatever
    public Hero selectTarjet(List<Hero> e){
        int max = 0, i = -1, j = 0;
        for(Hero hero: e){
            if(max < hero.getLife() && !hero.escaped()){
                max = hero.getLife();
                i = j;
            }
            j++;
        }
        if(i == -1)
            return null;
        else
            return e.get(i);
    }
    
    public String getAttack() {
		return attkDesc;
	}

    /**
     * Executes a normal enemy turn. Boss subclasses override this entry point
     * and delegate it to their Template Method implementation.
     */
    public String performTurn(List<Hero> heroes) {
        StringBuilder log = new StringBuilder();
        Hero target = selectTarjet(heroes);

        if (target == null) {
            log.append(name().toUpperCase())
               .append(Messages.ENEMY_MISS)
               .append(Messages.NEW_LINE);
            return log.toString();
        }

        log.append(name().toUpperCase())
           .append(" attacks ")
           .append(target.name().toUpperCase())
           .append(Messages.NEW_LINE);
        log.append(getAttack()).append(Messages.NEW_LINE);

        int damage = target.isDefending() ? 10 : 20;
        log.append(attack(target, getMainElement(), damage)).append(Messages.NEW_LINE);
        return log.toString();
    }
    
    public void setEnemyNum(int n) {
    	enemy_num = n;
    }
    
    public int getEnemyNum() {
    	return enemy_num;
    }
}

