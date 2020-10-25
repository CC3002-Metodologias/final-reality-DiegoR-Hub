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
public class PlayerCharacter extends AbstractCharacter {
  protected IWeapon equippedWeapon;

  @Override
  public void attack(ICharacter character) {
    character.attackedByPlayerCharacter(this);
  }

  @Override
  public void attackedByPlayerCharacter(PlayerCharacter playerCharacter) {
    return;
  }

  @Override
  public void attackedByEnemy(Enemy enemy) {
    if (this.isDead()){
      return;
    }
    else if (enemy.getAttackPoints()-this.getDefensePoints() >= this.getHealthPoints()){
      this.setDead();
      this.setHealthPoints(0);
    }
    else{
      this.setHealthPoints(this.getHealthPoints() - (enemy.getAttackPoints()-this.getDefensePoints()));
    }
  }

  @Override
  public void equipWeapon(IWeapon weapon) {

  }

  public PlayerCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue, @NotNull String name, int defensePoints, int healthPoints)
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

  public void setEquippedWeapon(IWeapon equippedWeapon) {
    this.equippedWeapon = equippedWeapon;
  }
}