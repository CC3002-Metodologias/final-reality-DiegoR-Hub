package com.github.cc3002.finalreality.model.character.player;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
  private final int attackPoints;
  private PropertyChangeSupport turnos;

  /**
   * Creates a new Enemy with a turnsQueue, a name
   *
   */
  public Enemy(@NotNull final BlockingQueue<ICharacter> turnsQueue, @NotNull final String name,int weight, int defensePoints, int healthPoints,   int attackPoints) {
    super(turnsQueue, name, defensePoints, healthPoints);
    this.weight = weight;
    this.attackPoints = attackPoints;
    turnos = new PropertyChangeSupport(this);
  }

  /**
   *
   * Asocia este enemy a un listener que indique cuando termina el turno de este enemy
   */
  public void addTurnsListener(PropertyChangeListener turnsHandler) {
    turnos.addPropertyChangeListener(turnsHandler);
  }


  /**
   * Returns the attackPoints of this enemy
   */
  public int getAttackPoints(){
    return this.attackPoints;
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
            getName().equals(enemy.getName()) &&
            getDefensePoints() == enemy.getDefensePoints() &&
            getHealthPoints() == enemy.getHealthPoints() &&
            getAttackPoints() == enemy.getAttackPoints();
  }
  @Override
  public int hashCode(){
    return Objects.hashCode(Enemy.class);
  }

  /**
   * Sets a scheduled executor to make this character (thread) wait for {@code speed / 10}
   * seconds before adding the character to the queue.
   */
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutor.schedule(this::addToQueue, getWeight() / 10, TimeUnit.SECONDS);
    turnos.firePropertyChange("turno", "", this.getName());
  }

  /**
   *
   * retorna el daño a realizar por este enemy
   */
  public int getDamage(){
    return this.getAttackPoints();
  }
}