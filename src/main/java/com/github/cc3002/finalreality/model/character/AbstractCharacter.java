package com.github.cc3002.finalreality.model.character;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;

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
  private PropertyChangeSupport muertes;

  /**
   *
   * Setea que de ahora en adelante este character está muerto
   */
  public void setDead(){
    this.dead = true;
  }

  /**
   *
   * Indica a un handler que observe a este character
   */
  @Override
  public void addDeathListener(PropertyChangeListener deathHandler) {
    muertes.addPropertyChangeListener(deathHandler);
  }


  /**
   *
   * Constructor comun para subclases de AbstractCharacter, su queue, name, defensePoints, healthPoints y su observer
   */
  protected AbstractCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                              @NotNull String name, int defensePoints, int healthPoints) {
    muertes = new PropertyChangeSupport(this);
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
   *
   * Retorna boolean, true si este character está muerto
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

  /**
   *
   * Double dispatch, recibe un ataque de otro character
   */
  @Override
  public void attackedBy(ICharacter character) {
    if (this.isDead()){
    }
    else if (character.getDamage()-this.getDefensePoints() >= this.getHealthPoints()){
      this.setDead();
      this.setHealthPoints(0);
      muertes.firePropertyChange("death", "", this.getName());
    }
    else{
      this.setHealthPoints(this.getHealthPoints() - (character.getDamage()-this.getDefensePoints()));
    }
  }

  /**
   *
   * Double dispatch, este character ataca a otro character
   */
  @Override
  public void attack(ICharacter character) {
    character.attackedBy(this);
  }
}


