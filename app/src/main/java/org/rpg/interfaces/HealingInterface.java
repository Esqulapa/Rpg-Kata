package org.rpg.interfaces;

import org.rpg.character.Character;

public interface HealingInterface {

 void healYourself();

 void healAlly(Character character);

 void receiveHeal(Double heal);

}
