package org.rpg.character;

import org.junit.Test;
import org.rpg.fractions.BisonFraction;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterTest {

  @Test
  public void create() {

    MeleeCharacter blabla = MeleeCharacter.createCharacter("blabla");

    assertNotNull(blabla);
    assertEquals("blabla", blabla.getName());
    assertEquals(200, blabla.getMaxRange());
  }

  @Test
  public void shouldDealDamage() {
    // Basic attack is 75
    RangedCharacter mordex = RangedCharacter.createCharacter("Mordex");
    mordex.setLevel(6);
    RangedCharacter bortex = RangedCharacter.createCharacter("Bordex");
    bortex.setLevel(11);
    RangedCharacter tortex = RangedCharacter.createCharacter("Tortex");
    tortex.setLevel(13);

    mordex.dealDamage(tortex, 1000);

    tortex.dealDamage(bortex, 1000);

    tortex.dealDamage(mordex, 1000);

    // 75 * 0.5 = 37.5
    assertEquals(962.5, tortex.getHealth());
    // 75, 967.5 - 75
    assertEquals(925, bortex.getHealth());
    // 75 * 1.5 = 112,5
    assertEquals(887.5, mordex.getHealth());
  }

  @Test
  public void shouldNotDealDamage() {

    RangedCharacter tortex = RangedCharacter.createCharacter("Tortex");
    MeleeCharacter mordex = MeleeCharacter.createCharacter("Mordex");
    mordex.dealDamage(tortex, 1000);
  }

  @Test
  public void shouldNotDealDamageNotInRange() {

    MeleeCharacter mordex = MeleeCharacter.createCharacter("Mordex");
    RangedCharacter bortex = RangedCharacter.createCharacter("Bordex");

    mordex.dealDamage(bortex, 1000);
    bortex.dealDamage(mordex, 3000);

    assertEquals(1000, bortex.getHealth());


    assertEquals(1000, mordex.getHealth());
  }

  @Test
  public void shouldKillOpponent() {
    RangedCharacter mordex = RangedCharacter.createCharacter("Mordex");
    mordex.setHealth(50.0);
    MeleeCharacter bortex = MeleeCharacter.createCharacter("Bordex");
    bortex.dealDamage(mordex, 200);

    assertFalse(mordex.isAlive());
    assertEquals(0.0, mordex.getHealth());
  }

  @Test
  public void shouldHeal() {
    // basic heal 50

    Character mordex = MeleeCharacter.createCharacter("Mordex");
    mordex.setHealth(200.0);

    mordex.heal();

    assertEquals(250.0, mordex.getHealth());
  }

  @Test
  public void shouldNotHealCharacter() {
    // basic heal 50

    Character mordex = MeleeCharacter.createCharacter("Mordex");

    mordex.setAlive(false);

    mordex.heal();
  }

  @Test
  public void checkIfIsEnemy() {

    MeleeCharacter mordex = MeleeCharacter.createCharacter("Mordex");
    mordex.joinFraction(BisonFraction.createBisonFraction());

    RangedCharacter bortex = RangedCharacter.createCharacter("Bordex");
    bortex.joinFraction(BisonFraction.createBisonFraction());

    boolean ifIsEnemy = mordex.checkIfIsEnemy(bortex);

    assertFalse(ifIsEnemy);


  }

  @Test
  public void joinFraction() {
    Character mordex = MeleeCharacter.createCharacter("Mordex");
    BisonFraction bisonFraction = BisonFraction.createBisonFraction();
    mordex.joinFraction(bisonFraction);

    assertEquals(1,mordex.getFractions().size());


  }
}
