package org.rpg.character;

public class DamageRules {

  public static Double damageMultiplier(Integer dealerLevel, Integer enemyLevel) {
    int levelDifference = dealerLevel - enemyLevel;
    if (levelDifference >= 5) {
      return 1.5;
    } else if (levelDifference <= -5) {
      return 0.5;
    } else {
      return 1.0;
    }
  }
}
