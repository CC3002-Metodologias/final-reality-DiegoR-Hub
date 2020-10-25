package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.Enemy;
import com.github.cc3002.finalreality.model.character.player.PlayerCharacter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;

import com.github.cc3002.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;
/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Diego Ruiz R.
 */
public abstract class AbstractCharacter implements ICharacter {

  protected final BlockingQueue<ICharacter> turnsQueue;
  protected final String name;
  protected ScheduledExecutorService scheduledExecutor;
  private int defensePoints;
  private int healthPoints;
  private boolean dead;

  /**
   *
   *
   */
  public void setDead(){
    this.dead = true;
  }


  protected AbstractCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                              @NotNull String name, int defensePoints, int healthPoints) {
    this.turnsQueue = turnsQueue;
    this.name = name;
    if (healthPoints <= 0){
      this.dead = true;
    }
    else{
      this.dead = false;
    }
    this.defensePoints = defensePoints;
    this.healthPoints = healthPoints;
  }

  /**
   * Returns is
   */
  public boolean isDead(){
    return this.dead;
  }
  /**
   * Adds this character to the turns queue.
   */
  @Override
  public void addToQueue() {
    turnsQueue.add(this);
    scheduledExecutor.shutdown();
  }

  /**
   * Returns this character's defensePoints
   */
  public int getDefensePoints(){return this.defensePoints;}

  /**
   * Returns this character's healthPoints
   */
  public int getHealthPoints(){
    return this.healthPoints;
  }
  /**
   *
   * Returns this character's name
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   *
   * sets this character's healthPoints
   */
  public void setHealthPoints(int healthPoints) {
    this.healthPoints = healthPoints;
  }
}


