package com.github.cc3002.finalreality.model.weapon;


import com.github.cc3002.finalreality.model.character.player.*;

/**
 * A class that holds all the information of a weapon.
 *
 * @author Ignacio Slater Muñoz.
 * @author Diego Ruiz R.
 */
public abstract class AbstractWeapon implements IWeapon {

  private final String name;
  private final int damage;
  private final int weight;

  /**
   * Creates a weapon with a name, a base damage, speed and it's type.
   */
  public AbstractWeapon(final String name, final int damage, final int weight) {
    this.name = name;
    this.damage = damage;
    this.weight = weight;
  }

  /**
   * Metodo para retornar el valor del atributo name de cualquier IWeapon
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Metodo para retornar el valor del atributo damage de cualquier IWeapon
   */
  @Override
  public int getDamage() {
    return this.damage;
  }
  /**
   * Metodo para retornar el valor del atributo weight de cualquier IWeapon
   */
  @Override
  public int getWeight() {
    return this.weight;
  }

  /**
   * Metodo auxiliar, sin efectos
   */
  public void equipVoid(){return;}

  /**
   *
   * Double Dispatch, a menos que se redefina, armas no se equipan a BlackMage
   */
  @Override
  public void equippedByBlackMage(BlackMage blackMage) {
    this.equipVoid();
  }

  /**
   *
   * Double Dispatch, a menos que se redefina, armas no se equipan a WhiteMage
   */
  @Override
  public void equippedByWhiteMage(WhiteMage whiteMage) {
    this.equipVoid();
  }

  /**
   *
   * Double Dispatch, a menos que se redefina, armas no se equipan a Engineer
   */
  @Override
  public void equippedByEngineer(Engineer engineer) {
    this.equipVoid();
  }

  /**
   *
   * Double Dispatch, a menos que se redefina, armas no se equipan a Knight
   */
  @Override
  public void equippedByKnight(Knight knight) {
    this.equipVoid();
  }

  /**
   *
   * Double Dispatch, a menos que se redefina, armas no se equipan a Thief
   */
  @Override
  public void equippedByThief(Thief thief) {
    this.equipVoid();
  }
}
