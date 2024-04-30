package org.rpg.character;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CharacterTest {

    @Test
    public void create() {

        MeleeCharacter blabla = MeleeCharacter.createCharacter("blabla");

        assertNotNull(blabla);
        assertEquals("blabla", blabla.getName());
        assertEquals(200, blabla.getMaxRange());
    }

    @Test
    public void shouldDealDamage(){
        //Basic attack is 75
        RangedCharacter mordex = RangedCharacter.createCharacter("Mordex");
        mordex.setLevel(6);
        RangedCharacter bortex = RangedCharacter.createCharacter("Bordex");
        bortex.setLevel(11);
        RangedCharacter tortex = RangedCharacter.createCharacter("Tortex");
        tortex.setLevel(13);

        mordex.dealDamage(tortex,1000);

        tortex.dealDamage(bortex,1000);

        tortex.dealDamage(mordex,1000);

        // 75 * 0.5 = 37.5
        assertEquals(962.5, tortex.getHealth());
        //75, 967.5 - 75
        assertEquals(925, bortex.getHealth());
        //75 * 1.5 = 112,5
        assertEquals(887.5, mordex.getHealth());
    }
//    @Test
//    public void dealDamage() {
//
//        Character mordex = Character.create("Mordex");
//        Character bortex = Character.create("Bortex");
//
//        bortex.dealDamage(mordex);
//        assertEquals(925.0, mordex.getHealth().doubleValue());
//
//
//      }
//
//      @Test
//      public void heal() {
//        //basic heal 50
//
//        Character mordex = Character.create("Mordex");
//        mordex.setHealth(200.0);
//
//        mordex.heal();
//
//        assertEquals(250.0,mordex.getHealth());
//
//      }
//
//    @Test
//    public void shouldReturnError() {
//        //basic heal 50
//
//        Character mordex = Character.create("Mordex");
//        Character cordex = Character.create("Cordex");
//
//        cordex.setHealth(200.0);
//
//        mordex.heal();
//
//}


}