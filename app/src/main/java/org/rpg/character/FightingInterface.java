package org.rpg.character;

public interface FightingInterface {

     boolean isInRange(Integer range);

     void dealDamage(Character enemy,Integer range);

     void receiveDamage(Character enemy);

}
