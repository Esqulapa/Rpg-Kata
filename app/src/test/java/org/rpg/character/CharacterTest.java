package org.rpg.character;

import org.junit.Test;
import org.rpg.fractions.Fractions;
import org.rpg.things.Thing;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterTest {

  @Test
  public void create() {

    MeleeCharacter blabla = MeleeCharacter.createCharacter("blabla");

    RangedCharacter bababoey = RangedCharacter.createCharacter("bababoey");

    assertNotNull(bababoey);
    assertEquals("bababoey", bababoey.getName());
    assertEquals(2000, bababoey.getMaxRange());

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

    assertEquals(tortex.getHealth(), tortex.getMaxHealth());
  }

  @Test
  public void shouldNotDealDamageToYourself() {

    RangedCharacter tortex = RangedCharacter.createCharacter("Tortex");
    tortex.dealDamage(tortex, 1000);
    assertEquals(tortex.getHealth(), tortex.getMaxHealth());
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

    assertEquals(0.0, mordex.getHealth());
    assertFalse(mordex.isAlive());
  }

  @Test
  public void shouldHealYourself() {
    // basic heal 50

    Character mordex = MeleeCharacter.createCharacter("Mordex");
    mordex.setHealth(200.0);

    mordex.healYourself();

    assertEquals(250.0, mordex.getHealth());
  }

  @Test
  public void shouldKillCharacter() {
    MeleeCharacter steven = MeleeCharacter.createCharacter("steven");
    steven.kill();

    assertEquals(0.0, steven.getHealth());
    assertFalse(steven.isAlive());
  }

  @Test
  public void shouldNotHealYourselfCharacter() {
    // basic heal 50

    Character mordex = MeleeCharacter.createCharacter("Mordex");

    mordex.kill();
    mordex.healYourself();

    assertEquals(0.0, mordex.getHealth());
  }

  @Test
  public void checkIfIsAlly() {

    MeleeCharacter mordex = MeleeCharacter.createCharacter("Mordex");
    mordex.joinFraction(Fractions.BISONS);

    RangedCharacter bortex = RangedCharacter.createCharacter("Bordex");
    bortex.joinFraction(Fractions.BISONS);

    boolean ifIsAlly = mordex.checkIfIsAlly(bortex);

    assertTrue(ifIsAlly);
  }

  @Test
  public void joinFraction() {
    Character mordex = MeleeCharacter.createCharacter("Mordex");
    mordex.joinFraction(Fractions.BISONS);

    assertEquals(1, mordex.getFractions().size());
  }

  @Test
  public void shouldAbandonFraction() {
    Character mordex = MeleeCharacter.createCharacter("Mordex");
    mordex.joinFraction(Fractions.BISONS);
    mordex.abandonFraction(Fractions.BISONS);

    assertEquals(0, mordex.getFractions().size());
  }

  @Test
  public void healAlly() {
    MeleeCharacter mordex = MeleeCharacter.createCharacter("Mordex");
    mordex.joinFraction(Fractions.BISONS);

    RangedCharacter bortex = RangedCharacter.createCharacter("Bordex");
    bortex.joinFraction(Fractions.BISONS);
    bortex.receiveDamage(100.0);

    mordex.healAlly(bortex);
    assertEquals(950.0, bortex.getHealth());
  }

  @Test
  public void shouldNotHealEnemy() {
    MeleeCharacter mordex = MeleeCharacter.createCharacter("Mordex");
    mordex.joinFraction(Fractions.BISONS);

    RangedCharacter bortex = RangedCharacter.createCharacter("Bordex");
    bortex.joinFraction(Fractions.BOARS);
    bortex.receiveDamage(100.0);

    mordex.healAlly(bortex);
    assertEquals(900.0, bortex.getHealth());
  }

  @Test
  public void shouldHitThing() {
    RangedCharacter bortex = RangedCharacter.createCharacter("Bordex");

    Thing tree = Thing.createThing(2000.0, "tree", 10);

    bortex.dealDamage(tree,2000);

    assertEquals(1925.0,tree.getHealth());

  }
}
