package org.things;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.rpg.things.Thing;

public class ThingTest {

  @Test
  public void shouldCreateThing() {

    Thing tree = Thing.createThing(20.0, "tree", 2);

    assertNotNull(tree);
    assertEquals("tree", tree.getName());
    assertEquals(2, tree.getLevel());
    assertEquals(20.0, tree.getHealth());
  }

  @Test
  public void shouldReceiveDamage() {
    Thing tree = Thing.createThing(20.0, "tree", 2);
    tree.receiveDamage(20.0);
    assertEquals(0.0, tree.getHealth());
  }
}
