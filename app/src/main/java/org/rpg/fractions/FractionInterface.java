package org.rpg.fractions;

import org.rpg.character.Character;

public interface FractionInterface {

  <T extends Fraction> void joinFraction(T fraction);

  void abandonFraction(Fraction fraction);

  public boolean checkIfIsEnemy(Character character);
}
