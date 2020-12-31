package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

    /**
     * La clase que implementa los Thief del juego
     * @author Ignacio Slater Muñoz.
     * @author Diego Ruiz R.
     */
public class Thief extends AbstractPlayerCharacter {

    /**
     * Creates a new WhiteMage with a name, defense points, health points, mana and its observer
     *
     */
    public Thief( @NotNull String name, int defensePoints, int healthPoints) {
        super(name, defensePoints, healthPoints);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Thief)) {
            return false;
        }
        final Thief thief = (Thief) o;
        return getName().equals(thief.getName()) &&
                getDefensePoints() == thief.getDefensePoints() &&
                getHealthPoints() == thief.getHealthPoints();
    }
    @Override
    public int hashCode(){
        return Objects.hashCode(Thief.class);
    }

    /**
     *
     * Equipa un arma a este Thief
     */
    @Override
    public void equipWeapon(IWeapon weapon) {
        weapon.equippedByThief(this);
    }
}
