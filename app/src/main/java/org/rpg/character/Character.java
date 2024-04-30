package org.rpg.character;

import lombok.Getter;
import lombok.Setter;

import static org.rpg.character.DamageRules.damageMultiplier;

@Getter
@Setter
public abstract class Character implements FightingInterface, HealingInterface {

  private Double maxHealth;
  private String name;
  private Double health;
  private Integer level;
  private Integer maxRange;
  private Double attackDamage;
  private Double healPower;
  private boolean isAlive;

  protected Character(String name,Integer range) {
    this.name = name;
    this.health = 1000.0;
    this.maxHealth = 1000.0;
    this.level = 1;
    this.attackDamage = 75.0;
    this.healPower = 50.0;
    this.isAlive = true;
    this.maxRange = range;
  }


    @Override
  public void dealDamage(Character enemy,Integer range) {

    if (!this.equals(enemy) && isInRange(range)) {
      if (enemy.isAlive) {
            enemy.receiveDamage(this.attackDamage * damageMultiplier(this.level, enemy.getLevel()));
      }
    } else System.out.println("You cannot deal damage to yoursel");
  }


  @Override
  public void heal() {
    if (this.isAlive) {
      if ((this.health + this.healPower) > maxHealth) {
        this.health = maxHealth;
      } else this.health += this.healPower;
    } else System.out.println("You're dead");
  }

  public boolean isInRange(Integer range) {
      return range >= 0 && range <= this.maxRange;
  }

  public void receiveDamage(Double damage) {
      if (damage > this.health) {
          this.health = 0.0;
          this.isAlive = false;
      }else this.health -= damage;
  }
}
