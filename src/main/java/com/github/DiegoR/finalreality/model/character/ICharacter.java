package com.github.cc3002.finalreality.model.character;



/**
 * This represents a character from the game.
 * A character can be controlled by the player or by the CPU (an enemy).
 *
 * @author Ignacio Slater Muñoz.
 * @author <Your name>
 */
public interface ICharacter {

  /**
   * Sets a scheduled executor to make this character (thread) wait for {@code speed / 10}
   * seconds before adding the character to the queue.
   */
  void waitTurn();

  /**
   * Returns this character's name.
   */
  String getName();

  /**
   * Adds this character to the turns queue.
   */
  void addToQueue();

  boolean equals();


}
/**Cambios:
 * Quitar equip() y su getEquippedWeapon():
 -Razon:    *Rompe Liskov ya que Enemy no se puede equipar armas, Ademas
 -Solucion: * getEquippedWeapon() a AbstractPlayerCharacter ya que cada character del player se puede equipar armas
 * equip() se divide en equipKnife(), equipStaff(), ...con fin en cumplir Single Responsibility Principle.
 Se implementa dentro de cada clase que extiende AbstractPlayerCharacter
 * Quitar getCharacterClass:
 -Razon:    *Rompe Liskov ya que Enemy no tiene atributo CharacterClass y ademas el fin mas probable de
 este atributo es romper Single Responsibility Principle al atacar y equipar armas
 -Solucion: *Se elimina atributo characterClass de todas partes y se elimina clase Enum CharacterClass
 *
 */
