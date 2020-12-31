package com.github.cc3002.finalreality.model.character;


import java.beans.PropertyChangeListener;

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

  int getWeight();

  /**
   *
   * retorna el daño a realizar por este character
   */
  int getDamage();

  /**
   *
   * Double dispatch, recibe un ataque de otro character
   */
  void attackedBy(ICharacter character);

  /**
   *
   * Double dispatch, este character ataca a otro character
   */
  void attack(ICharacter character);

  /**
   *
   * Indica a un handler que observe a este character
   */
  void addDeathListener(PropertyChangeListener handler);

  /**
   *
   * Retorna boolean, true si este character está muerto
   */
  boolean isDead();

  /**
   *
   * Setea que de ahora en adelante este character está muerto
   */
  void setDead();

  int getHealthPoints();

  int getDefensePoints();

}
