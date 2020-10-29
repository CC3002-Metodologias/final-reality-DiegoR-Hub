package com.github.cc3002.finalreality.model.character;


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
   * Espera el turno de este character
   */
  void waitTurn();
}
