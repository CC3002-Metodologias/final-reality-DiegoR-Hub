package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * La clase que implementa los Knight del juego
 * @author Ignacio Slater Muñoz.
 * @author Diego Ruiz R.
 */
public class Knight extends AbstractPlayerCharacter {

    /**
     * Creates a new Knight with a name and the queue
     * @author Diego Ruiz R.
     */
    public Knight(@NotNull BlockingQueue<ICharacter> turnsQueue, @NotNull String name, int defensePoints, int healthPoints) {
        super(turnsQueue, name, defensePoints, healthPoints);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Knight)) {
            return false;
        }
        final Knight knight = (Knight) o;
        return getName().equals(knight.getName()) &&
                getDefensePoints() == knight.getDefensePoints() &&
                getHealthPoints() == knight.getHealthPoints();
    }
    @Override
    public int hashCode(){
        return Objects.hashCode(Knight.class);
    }

    /**
     *
     * Equipa un arma a este Knight
     */
    @Override
    public void equipWeapon(IWeapon weapon) {
        weapon.equippedByKnight(this);
    }
}
