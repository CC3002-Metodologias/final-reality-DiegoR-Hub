package com.github.cc3002.finalreality.model.character.player;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.github.cc3002.finalreality.model.character.AbstractCharacter;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single enemy of the game.
 *
 * @author Ignacio Slater Muñoz
 * @author Diego Ruiz R.
 */
public class Enemy extends AbstractCharacter {

  private final int weight;
  private int attackPoints;

  /**
   * Creates a new Enemy with a turnsQueue, a name
   *
   */
  public Enemy(@NotNull final BlockingQueue<ICharacter> turnsQueue, @NotNull final String name,int weight, int defensePoints, int healthPoints,   int attackPoints) {
    super(turnsQueue, name, defensePoints, healthPoints);
    this.weight = weight;
    this.attackPoints = attackPoints;
  }

  /**
   * Returns the attackPoints of this enemy
   */
  public int getAttackPoints(){
    return this.attackPoints;
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


  @Override
  public void attack(ICharacter character) {
    character.attackedByEnemy(this);
  }

  @Override
  public void attackedByPlayerCharacter(PlayerCharacter playerCharacter) {
    if (this.isDead()){
      return;
    }
    else if (playerCharacter.getEquippedWeapon().getDamage()-this.getDefensePoints() >= this.getHealthPoints()){
      this.setDead();
      this.setHealthPoints(0);
    }
    else{
      this.setHealthPoints(this.getHealthPoints() - (playerCharacter.getEquippedWeapon().getDamage()-this.getDefensePoints()));
    }
  }

  @Override
  public void attackedByEnemy(Enemy enemy) {
    return;
  }

  @Override
  public void equipWeapon(IWeapon weapon) {
    return;
  }

  /**
   * Sets a scheduled executor to make this character (thread) wait for {@code speed / 10}
   * seconds before adding the character to the queue.
   */
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutor.schedule(this::addToQueue, getWeight() / 10, TimeUnit.SECONDS);

  }
}