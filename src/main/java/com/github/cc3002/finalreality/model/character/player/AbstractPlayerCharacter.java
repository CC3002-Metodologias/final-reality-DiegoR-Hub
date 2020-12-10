package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.AbstractCharacter;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
   * retorna el daño a realizar por este player character
   */
  @Override
  public int getDamage(){
    return  this.getEquippedWeapon().getDamage();
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