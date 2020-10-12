package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.AbstractCharacter;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import java.util.concurrent.BlockingQueue;

import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single character of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author <Your name>
 */
public class PlayerCharacter extends AbstractCharacter {
  protected IWeapon equippedWeapon;

  public PlayerCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue, @NotNull String name)
  {
    super(turnsQueue, name);
    equippedWeapon=null;
  }

  /**
   * Returns this character's equippedWeapon
   *
   */
  public IWeapon getEquippedWeapon() {return this.equippedWeapon;}




}