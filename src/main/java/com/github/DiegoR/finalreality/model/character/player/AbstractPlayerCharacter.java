package com.github.cc3002.finalreality.model.character.player;

import com.github.DiegoR.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.character.AbstractCharacter;
import com.github.cc3002.finalreality.model.character.ICharacter;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single character of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author <Your name>
 */
public abstract class AbstractPlayerCharacter extends AbstractCharacter {
  private IWeapon equippedWeapon;
  /**
   * Creates a new character.
   *
   * @param name
   *     the character's name
   * @param turnsQueue
   *     the queue with the characters waiting for their turn
   */
  public AbstractPlayerCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,@NotNull String name)
  {
    super(turnsQueue, name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCharacterClass());
  } //ver que se hace con estooo AAAAAAAAAA e equals

  @Override
  public boolean equals(final Object o) { //wtf esta funcion, pendiente
    if (this == o) {
      return true;
    }
    if (!(o instanceof AbstractPlayerCharacter)) {
      return false;
    }
    final AbstractPlayerCharacter that = (AbstractPlayerCharacter) o;
    return getCharacterClass() == that.getCharacterClass()
            && getName().equals(that.getName());
  }
  public IWeapon getEquippedWeapon() {return this.equippedWeapon}

  public void setEquippedWeapon(IWeapon newEquippedWeapon) {
    this.equippedWeapon = newEquippedWeapon;
  }
}

/**Cambios:
 *  PlayerCharacter cambia a AbstractPlayerCharacter:
 -Razon:Siempre instanciamos Knight, Mage, etc, nunca un PlayerCharacter
 *  Se implementa metodo getEquippedWeapon() y atributo equippedWeapon:
 -Razon: Solucion a haber quitar getEquippedWeapon en ICharacter y AbstractCharacter, cada character usado por el
 player tiene la capacidad de equipar armas, luego sigue Liskov
 * Se agrega setter de equippedWeapon()
 -Razon: Se hace con fin en poder implementar metodos equip en subclases de AbstractPlayerCharacter manteniendo
 atributo equippedWeapon private
 */