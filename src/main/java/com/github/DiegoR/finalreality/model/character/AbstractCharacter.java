package com.github.cc3002.finalreality.model.character;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.enemy.Enemy;
/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author <Your name>
 */
public abstract class AbstractCharacter implements ICharacter {

  protected final BlockingQueue<ICharacter> turnsQueue;
  protected final String name;
  private ScheduledExecutorService scheduledExecutor;

  protected AbstractCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                              @NotNull String name) {
    this.turnsQueue = turnsQueue;
    this.name = name;
  }

  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    if (this instanceof PlayerCharacter) {
      scheduledExecutor
              .schedule(this::addToQueue, equippedWeapon.getWeight() / 10, TimeUnit.SECONDS);
    } else {
      var enemy = (Enemy) this;
      scheduledExecutor
              .schedule(this::addToQueue, enemy.getWeight() / 10, TimeUnit.SECONDS);
    }
  }

  /**
   * Adds this character to the turns queue.
   */
  @Override
  public void addToQueue() {
    turnsQueue.add(this);
    scheduledExecutor.shutdown();
  }
  public abstract boolean equals();
  @Override
  public String getName() {
    return name;
  }

}

/**Cambios:
 * Quitar implementacion de getCharacterClass(), getEquippedWeapon(), equip():
 -Razon:    * Efecto de modificaciones en ICharacter (explicacion en ICharacter)
 * Se elimina atributo equippedWeapon y en consecuencia se modifica constructor:
 -Razon:    *Enemy rompe Liskov ya que hereda atributo equippedWeapon de AbstractCharacter y como requerimiento
 de enunciaco no puede tener un arma equipada
 -Solucion: *equippedWeapon se implementa como atributo de AbstractPlayerCharacter ya que cada character del player
 puede equipar armas
 * Se elimina atributo characterClass y en consecuencia se modifica el constructor:
 -Razon:     *Consecuencia de lo discutido en ICharacter (explicacion en ICharacter)
 * Agregar addToQueue() e equals() a ICharacter
 -Razon:     *No tiene que ver con SOLID sino con explicitar aun mas el contrato de cada Character del juego
 */

