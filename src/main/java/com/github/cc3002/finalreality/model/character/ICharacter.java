package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.Enemy;
import com.github.cc3002.finalreality.model.character.player.PlayerCharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;

/**
 * This represents a character from the game.
 * A character can be controlled by the player or by the CPU (an enemy).
 *
 * @author Ignacio Slater Muñoz.
 * @author Diego Ruiz R.
 */
public interface ICharacter {

  /**
   * Returns this character's name.
   */
  String getName();

  /**
   * Adds this character to the turns queue.
   */
  void addToQueue();

  /**
   * 
   */
  void attack(ICharacter character);

  /**
   *
   */
  void attackedByPlayerCharacter(PlayerCharacter playerCharacter);

  /**
   *
   */
  void attackedByEnemy(Enemy enemy);

  /**
   *
   */
  void equipWeapon(IWeapon weapon);

}
