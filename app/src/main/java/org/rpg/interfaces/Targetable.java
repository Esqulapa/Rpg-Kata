package org.rpg.interfaces;


import org.rpg.character.Character;

public interface Targetable {

    void receiveDamage(Double damage);

    boolean isAlive();

    Integer getLevel();

    boolean checkIfIsAlly(Character character);


}
