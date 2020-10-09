package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.AbstractCharacter;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single character of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author <Your name>
 */
public class PlayerCharacter extends AbstractCharacter {
  protected IWeapon equippedWeapon;

  public PlayerCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue, @NotNull String name)
  {
    super(turnsQueue, name);
    equippedWeapon=null;
  }

  public IWeapon getEquippedWeapon() {return this.equippedWeapon;}

  public void addToQueue() {
    turnsQueue.add(this);
    scheduledExecutor.shutdown();
  }
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
      scheduledExecutor
              .schedule(this::addToQueue, equippedWeapon.getWeight() / 10, TimeUnit.SECONDS);
  }

}