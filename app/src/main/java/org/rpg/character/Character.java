package org.rpg.character;

import static org.rpg.character.DamageRules.damageMultiplier;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.rpg.fractions.Fraction;
import org.rpg.fractions.FractionInterface;

@Getter
@Setter
public class Character implements FightingInterface, HealingInterface, FractionInterface {

  private Double maxHealth;
  private String name;
  private Double health;
  private Integer level;
  private Integer maxRange;
  private Double attackDamage;
  private Double healPower;
  private boolean isAlive;
  private List<Fraction> fractions;

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
  public void dealDamage(Character enemy, Integer range) {
    if (isInRange(range)) {
      if (!this.equals(enemy)) {
        if (enemy.isAlive) {
          enemy.receiveDamage2(this.attackDamage * damageMultiplier(this.level, enemy.getLevel()));
        }
      } else System.err.println("Cannot deal damage to yourself");
    } else System.err.println("Out of range");
  }

  @Override
  public void heal() {
    if (this.isAlive) {
      if ((this.health + this.healPower) > maxHealth) {
        this.health = maxHealth;
      } else this.health += this.healPower;
    } else System.out.println("You're dead");
  }

  @Override
  public boolean isInRange(Integer range) {
    return range >= 0 && range <= this.maxRange;
  }

  @Override
  public boolean checkIfIsEnemy(Character character) {
    return character.getFractions().stream().anyMatch(fraction -> this.fractions.contains(fraction));
  }

  @Override
  public void receiveDamage(Character enemy) {

  }

  public void receiveDamage2(Double damage) {
    if (damage > this.health) {
      this.health = 0.0;
      this.isAlive = false;
    } else this.health -= damage;
  }

  @Override
  public <T extends Fraction> void joinFraction(T fraction) {
    this.fractions.add(fraction);
  }

  @Override
  public void abandonFraction(Fraction fraction) {
    if (this.fractions.contains(fraction)) {
      this.fractions.remove(fraction);
    }else System.err.println("there is no such fraction to abandon");
  }
}
