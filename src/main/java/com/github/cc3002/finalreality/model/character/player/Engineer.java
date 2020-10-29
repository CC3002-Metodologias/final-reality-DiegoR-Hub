package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * La clase que implementa los Engineer del juego
 * @author Ignacio Slater Muñoz.
 * @author Diego Ruiz R.
 */
public class Engineer extends AbstractPlayerCharacter {

    public Engineer(@NotNull BlockingQueue<ICharacter> turnsQueue, @NotNull String name, int defensePoints, int healthPoints) {
        super(turnsQueue, name, defensePoints, healthPoints);
    }

    /**
     * Creates a new Engineer with a name, a weight and the queue
     */

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Engineer)) {
            return false;
        }
        final Engineer engineer = (Engineer) o;
        return getName().equals(engineer.getName()) &&
                getDefensePoints() == engineer.getDefensePoints() &&
                getHealthPoints() == engineer.getHealthPoints();
    }
    @Override
    public int hashCode(){
        return Objects.hashCode(Engineer.class);
    }

    /**
     *
     * Equipa un arma a este Engineer
     */
    @Override
    public void equipWeapon(IWeapon weapon) {
        weapon.equippedByEngineer(this);
    }
}
