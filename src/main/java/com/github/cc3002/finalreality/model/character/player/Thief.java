package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.Bow;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.weapon.Staff;
import com.github.cc3002.finalreality.model.weapon.Sword;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

    /**
     * La clase que implementa los Thief del juego
     * @author Ignacio Slater Muñoz.
     * @author Diego Ruiz R.
     */
public class Thief extends PlayerCharacter {


    public Thief(@NotNull BlockingQueue<ICharacter> turnsQueue, @NotNull String name, int defensePoints, int healthPoints) {
        super(turnsQueue, name, defensePoints, healthPoints);
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

    @Override
    public void equipWeapon(IWeapon weapon) {
        weapon.equippedByThief(this);
    }
}
