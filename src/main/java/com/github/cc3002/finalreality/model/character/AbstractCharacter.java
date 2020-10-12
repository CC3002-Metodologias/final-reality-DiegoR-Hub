package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.Enemy;
import com.github.cc3002.finalreality.model.character.player.PlayerCharacter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.jetbrains.annotations.NotNull;
/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Diego Ruiz R.
 */
public abstract class AbstractCharacter implements ICharacter {

  protected final BlockingQueue<ICharacter> turnsQueue;
  protected final String name;
  protected ScheduledExecutorService scheduledExecutor;


  protected AbstractCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                              @NotNull String name) {
    this.turnsQueue = turnsQueue;
    this.name = name;
  }

  /**
   * Adds this character to the turns queue.
   */
  @Override
  public void addToQueue() {
    turnsQueue.add(this);
    scheduledExecutor.shutdown();
  }

  /**
   *
   * Returns this character's name
   */
  @Override
  public String getName() {
    return name;
  }

  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    if (this instanceof PlayerCharacter) {
      scheduledExecutor
              .schedule(this::addToQueue, ((PlayerCharacter) this).getEquippedWeapon().getWeight() / 10, TimeUnit.SECONDS);
    } else {
      var enemy = (Enemy) this;
      scheduledExecutor
              .schedule(this::addToQueue, enemy.getWeight() / 10, TimeUnit.SECONDS);
    }
  }
}


