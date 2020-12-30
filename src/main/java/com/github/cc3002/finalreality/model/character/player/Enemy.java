package com.github.cc3002.finalreality.model.character.player;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.github.cc3002.finalreality.model.character.AbstractCharacter;
import com.github.cc3002.finalreality.model.character.ICharacter;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single enemy of the game.
 *
 * @author Ignacio Slater Muñoz
 * @author Diego Ruiz R.
 */
public class Enemy extends AbstractCharacter {

  private final int weight;
  private final int attackPoints;

  /**
   * Creates a new Enemy with a name, its weight, defensePoints, healthPoints and attakPoints
   *
   */
  public Enemy(@NotNull final String name,int weight, int defensePoints, int healthPoints,   int attackPoints) {
    super(name, defensePoints, healthPoints);
    this.weight = weight;
    this.attackPoints = attackPoints;
  }

  /**
   * Returns the weight of this enemy.
   */
  public int getWeight() {
    return this.weight;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Enemy)) {
      return false;
    }
    final Enemy enemy = (Enemy) o;
    return getWeight() == enemy.getWeight() &&
            getName().equals(enemy.getName()) &&
            getDefensePoints() == enemy.getDefensePoints() &&
            getHealthPoints() == enemy.getHealthPoints() &&
            getAttackPoints() == enemy.getAttackPoints();
  }
  @Override
  public int hashCode(){
    return Objects.hashCode(Enemy.class);
  }


  /**
   *
   * retorna el daño a realizar por este enemy
   */
  public int getDamage(){
    return this.getAttackPoints();
  }

  /**
   *
   * retorna el valor del atributo attackPoints de esta instancia de Enemy
   */
  private int getAttackPoints() {
    return this.attackPoints;
  }
}