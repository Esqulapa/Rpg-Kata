package org.rpg.things;

import lombok.Getter;
import org.rpg.character.Character;
import org.rpg.interfaces.Targetable;

@Getter
public class Thing implements Targetable {

    private Double health;
    private final String name;
    private final Integer level;
    private boolean exist;

    private Thing(Double health, String name, Integer level) {
        this.health = health;
        this.name = name;
        this.level = level;
    }

    public static Thing createThing(Double health, String name, Integer level) {
        return new Thing(health, name, level);
    }



    @Override
    public void receiveDamage(Double damage) {
        if (damage > this.health) {
            this.health = 0.0;
            this.exist = false;
        } else this.health -= damage;

    }

    @Override
    public boolean isAlive() {
        return exist;
    }

    @Override
    public Integer getLevel() {
        return level;
    }

    @Override
    public boolean checkIfIsAlly(Character character) {
        return false;
    }

}
