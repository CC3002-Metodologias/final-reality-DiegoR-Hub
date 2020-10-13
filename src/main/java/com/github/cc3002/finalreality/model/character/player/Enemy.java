package com.github.cc3002.finalreality.model.character.player;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.github.cc3002.finalreality.model.character.AbstractCharacter;
import com.github.cc3002.finalreality.model.character.ICharacter;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single enemy of the game.
 *
 * @author Ignacio Slater Muñoz
 * @author Diego Ruiz R.
 */
public class Enemy extends AbstractCharacter {

  private final int weight;

  /**
   * Creates a new Enemy with a turnsQueue, a name
   *
   */
  public Enemy(@NotNull final BlockingQueue<ICharacter> turnsQueue, @NotNull final String name, final int weight) {
    super(turnsQueue, name);
    this.weight = weight;
  }

  /**
   * Returns the weight of this enemy.
   */
  public int getWeight() {
    return this.weight;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Enemy)) {
      return false;
    }
    final Enemy enemy = (Enemy) o;
    return getWeight() == enemy.getWeight() &&
            getName().equals(enemy.getName());
  }
  @Override
  public int hashCode(){
    return Objects.hashCode(Enemy.class);
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
   * Sets a scheduled executor to make this character (thread) wait for {@code speed / 10}
   * seconds before adding the character to the queue.
   */
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
      var enemy = (Enemy) this;
      scheduledExecutor
              .schedule(this::addToQueue, enemy.getWeight() / 10, TimeUnit.SECONDS);

  }
}