package com.github.DiegoR.finalreality.model.weapon;

import java.util.Objects;

/**
 * A class that holds all the information of a weapon.
 *
 * @author Ignacio Slater Muñoz.
 * @author <Your name>
 */
public abstract class AbstractWeapon implements IWeapon {

  private final String name;
  private final int damage;
  private final int weight;

  /**
   * Creates a weapon with a name, a base damage, speed and it's type.
   *
   */
  public AbstractWeapon(final String name, final int damage, final int weight) {
    this.name = name;
    this.damage = damage;
    this.weight = weight;
  }
  @Override
  public String getName() {
    return name;
  }
  @Override
  public int getDamage() {
    return damage;
  }
  @Override
  public int getWeight() {
    return weight;
  }


  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof com.github.cc3002.finalreality.model.weapon.AbstractWeapon)) {
      return false;
    }
    final com.github.cc3002.finalreality.model.weapon.AbstractWeapon weapon = (com.github.cc3002.finalreality.model.weapon.AbstractWeapon) o;
    return getDamage() == weapon.getDamage() &&
            getWeight() == weapon.getWeight() &&
            getName().equals(weapon.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getDamage(), getWeight());
  }
}
/**Cambios:
 * Se elimina cada rastro de atributo type (en consecuencia de modifica el constructor)  y tambien se elimina metodo getType():
 -Razon:     *El fin mas probable de este atributo es romper Single Responsibility Principle al atacar por ejemplo al
 implementar un unico metodo attack() que diferencie cuando es type magic o common
 -Solucion: *Se elimina atributo type de todas partes y se elimina clase Enum WeaponType
 * Se cambia el grado de privacidad de getters de private a public:
 -Razon:     *Permitir que los characters puedan, en un futuro conocer los atributos de sus armas
 * Agregar hashCode() e equals() a ICharacter
 -Razon:     *No tiene que ver con SOLID sino con explicitar aun mas el contrato de cada Weapon del juego

 */