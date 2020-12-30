package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.AbstractCharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;

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
  public AbstractPlayerCharacter(@NotNull String name, int defensePoints, int healthPoints)
  {
    super(name, defensePoints, healthPoints);
    equippedWeapon=null;
  }

  /**
   * Returns this character's equippedWeapon
   *
   */
  public IWeapon getEquippedWeapon() {return this.equippedWeapon;}

  public int getWeight(){
    return this.getEquippedWeapon().getWeight();
  }

  /**
   *
   * common method for setting player characters' equipped weapon
   */
  public void setEquippedWeapon(IWeapon weapon) {
    this.equippedWeapon = weapon;
  }

}