package org.rpg.interfaces;

public interface FightingInterface {

  boolean isInRange(Integer range);

  void dealDamage(Targetable enemy, Integer range);

  default void kill() {}
}
