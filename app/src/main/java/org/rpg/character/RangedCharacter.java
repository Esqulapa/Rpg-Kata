package org.rpg.character;

public class RangedCharacter extends Character {
    private static final Integer BASIC_RANGED_RANGE = 2000;

    private RangedCharacter(String name) {
        super(name, BASIC_RANGED_RANGE);

    }
    public static RangedCharacter createCharacter(String name) {
        return new RangedCharacter(name);

    }

}

