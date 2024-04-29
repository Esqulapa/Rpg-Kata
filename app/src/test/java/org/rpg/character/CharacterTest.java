package org.rpg.character;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class CharacterTest {

    @Test
    public void create() {
        Character mordex = Character.create("Mordex");
        assertNotNull(mordex);
        assertEquals("Mordex", mordex.getName());
    }
    @Test
    public void shouldCalculateDamage(){
        //Basic attack is 75
        Character mordex = Character.create("Mordex");
        mordex.setLevel(6);
        Character bortex = Character.create("Bortex");
        bortex.setLevel(11);
        Character tortex = Character.create("Tortex");
        tortex.setLevel(2);
        Character cortex = Character.create("Cortex");
        cortex.setLevel(3);

        Double damageDealtToFiveOrLoverLevelCharacter = bortex.damageCalc(mordex);
        Double damageDealtToFiveOrHigherLevelCharter = mordex.damageCalc(bortex);
        Double damageDealtToSimilarLevelCharacter = tortex.damageCalc(tortex);

        assertEquals(75,damageDealtToSimilarLevelCharacter);

        assertEquals(75*1.5,damageDealtToFiveOrLoverLevelCharacter);

        assertEquals(75*0.5,damageDealtToFiveOrHigherLevelCharter);



    }
    @Test
    public void dealDamage() {

        Character mordex = Character.create("Mordex");
        Character bortex = Character.create("Bortex");

        bortex.dealDamage(mordex);
        assertEquals(925.0, mordex.getHealth().doubleValue());


      }

      @Test
      public void heal() {
      }
}