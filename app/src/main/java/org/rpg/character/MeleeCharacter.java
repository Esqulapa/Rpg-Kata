package org.rpg.character;

public class MeleeCharacter extends Character {

  private static final Integer BASIC_MELEE_RANGE = 200;

  private MeleeCharacter(String name) {
    super(name, BASIC_MELEE_RANGE);
  }

  public static MeleeCharacter createCharacter(String name) {
    return new MeleeCharacter(name);
  }
}
