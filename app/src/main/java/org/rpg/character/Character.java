package org.rpg.character;

import static org.rpg.character.DamageRules.damageMultiplier;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import lombok.Getter;
import lombok.Setter;
import org.rpg.fractions.FractionInterface;
import org.rpg.fractions.Fractions;
import org.rpg.interfaces.FightingInterface;
import org.rpg.interfaces.HealingInterface;
import org.rpg.interfaces.Targetable;

@Getter
@Setter
public class Character
    implements FightingInterface, HealingInterface, FractionInterface, Targetable {

  private Double maxHealth;
  private String name;
  private Double health;
  private Integer level;
  private Integer maxRange;
  private Double attackDamage;
  private Double healPower;
  private boolean isAlive;
  private List<Fractions> fractions;

  private final Logger logger = Logger.getLogger(this.getClass().getName());

  protected Character(String name, Integer range) {
    this.name = name;
    this.health = 1000.0;
    this.maxHealth = 1000.0;
    this.level = 1;
    this.attackDamage = 75.0;
    this.healPower = 50.0;
    this.isAlive = true;
    this.maxRange = range;
    this.fractions = new ArrayList<>();
  }

  @Override
  public void dealDamage(Targetable enemy, Integer range) {
    if (isInRange(range)) {
      if (!this.equals(enemy)) {
        if (enemy.isAlive() && !checkIfIsAlly((Character) enemy)) {
          dealDamageToCharacter(enemy);
        } else enemy.receiveDamage(this.attackDamage);
      } else logger.warning("Cannot deal damage to yourself");
      System.err.println();
    } else System.err.println("Out of range");
  }

  @Override
  public void kill() {
    this.health = 0.0;
    this.isAlive = false;
  }

  private void dealDamageToCharacter(Targetable characterEnemy) {
    characterEnemy.receiveDamage(
        this.attackDamage * damageMultiplier(this.level, characterEnemy.getLevel()));
  }

  @Override
  public void healYourself() {
    if (this.isAlive) {
      receiveHeal(this.healPower);
    } else System.err.println("Cannot heal you're dead");
  }

  @Override
  public void healAlly(Character character) {
    if (checkIfIsAlly(character)) {
      character.receiveHeal(this.healPower);
    } else System.err.println("Cannot heal enemy");
  }

  @Override
  public void receiveHeal(Double heal) {
    if (heal + this.health > maxHealth) {
      this.health = maxHealth;
    } else this.health += heal;
  }

  @Override
  public boolean isInRange(Integer range) {
    return range >= 0 && range <= this.maxRange;
  }

  @Override
  public boolean checkIfIsAlly(Character character) {
    return character.getFractions().stream()
        .anyMatch(fraction -> this.fractions.contains(fraction));
  }

  @Override
  public void receiveDamage(Double damage) {
    if (damage > this.health) {
      this.health = 0.0;
      this.isAlive = false;
    } else this.health -= damage;
  }

  @Override
  public void joinFraction(Fractions fraction) {
    this.fractions.add(fraction);
  }

  @Override
  public void abandonFraction(Fractions fraction) {
    if (this.fractions.contains(fraction)) {
      this.fractions.remove(fraction);
    } else System.err.println("There is no such fraction to abandon");
  }
}
