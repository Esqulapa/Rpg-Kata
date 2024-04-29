package org.rpg.character;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Character {

    private Double maxHealth;
    private String name;
    private Double health;
    private Integer level;
    private Integer range;
    private Double attackDamage;
    private Double heal;
    private boolean isAlive;

    private Character(String name) {
        this.name = name;
        this.health = 1000.0;
        this.maxHealth = 1000.0;
        this.level = 1;
        this.attackDamage = 75.0;
        this.heal = 50.0;
        this.isAlive = true;
    }

    public static Character create(String name) {
        return new Character(name);
    }

    public void dealDamage(Character enemy) {

    if (!this.equals(enemy)) {
      if (enemy.isAlive && enemy.health > this.attackDamage) {
        enemy.setHealth(enemy.getHealth() - damageCalc(enemy));
      } else {
        enemy.setAlive(false);
        enemy.setHealth(0.0);
      }
    } else System.out.println("You cannot deal damage to itself");
    }

    public void heal(Character character) {
        if (character.isAlive && this.equals(character)) {
           if((character.health += this.heal) > maxHealth){
               character.health = maxHealth;
           }
           else character.health += this.heal;
    } else System.out.println("You cannot heal because character is dead");
    }

    public Double damageCalc(Character character){
        if (this.level - character.getLevel() >= 5){
            return this.attackDamage * 1.5;
        }else if(this.level - character.getLevel() <= -5){
            return this.attackDamage * 0.5;
        }else return attackDamage;
    }
}
