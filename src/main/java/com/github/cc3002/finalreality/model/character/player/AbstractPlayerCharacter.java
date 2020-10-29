package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.AbstractCharacter;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single character of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Diego Ruiz R.
 */
public abstract class AbstractPlayerCharacter extends AbstractCharacter implements IPlayerCharacter {
  protected IWeapon equippedWeapon;

  /**
   *
   * player characters' common methos to attack an enemy
   */
  @Override
  public void attack(Enemy enemy) {
    enemy.attackedByPlayerCharacter(this);
  }

  /**
   *
   * Player characters' common method for being attacked by an enemy
   */
  @Override
  public void attackedByEnemy(Enemy enemy) {
    if (this.isDead()){
    }
    else if (enemy.getAttackPoints()-this.getDefensePoints() >= this.getHealthPoints()){
      this.setDead();
      this.setHealthPoints(0);
    }
    else{
      this.setHealthPoints(this.getHealthPoints() - (enemy.getAttackPoints()-this.getDefensePoints()));
    }
  }

  /**
   *
   * Constructor comun para subclases de AbstractPlayerCharacter, sets Queue, name, defensePoints, healthPoints
   */
  public AbstractPlayerCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue, @NotNull String name, int defensePoints, int healthPoints)
  {
    super(turnsQueue, name, defensePoints, healthPoints);
    equippedWeapon=null;
  }

  /**
   * Returns this character's equippedWeapon
   *
   */
  public IWeapon getEquippedWeapon() {return this.equippedWeapon;}


  /**
   * Sets a scheduled executor to make this character (thread) wait for {@code speed / 10}
   * seconds before adding the character to the queue.
   */
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutor.schedule(this::addToQueue,getEquippedWeapon().getWeight() / 10, TimeUnit.SECONDS);
  }

  /**
   *
   * common method for setting player characters' equipped weapon
   */
  public void setEquippedWeapon(IWeapon weapon) {
    this.equippedWeapon = weapon;
  }
}