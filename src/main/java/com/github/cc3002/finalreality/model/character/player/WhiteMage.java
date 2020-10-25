package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.weapon.Staff;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * La clase que implementa los WhiteMage del juego
 * @author Ignacio Slater Muñoz.
 * @author Diego Ruiz R.
 */
public class WhiteMage extends PlayerCharacter {
    private int mana;

    /**
     * Creates a new WhiteMage with a turnsQueue, a name, defense points, health points, mana
     *
     */
    public WhiteMage(@NotNull BlockingQueue<ICharacter> turnsQueue, @NotNull String name, int defensePoints, int healthPoints, int mana) {
        super(turnsQueue, name, defensePoints, healthPoints);
        this.mana = mana;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WhiteMage)) {
            return false;
        }
        final WhiteMage whiteMage = (WhiteMage) o;
        return getName().equals(whiteMage.getName()) &&
                getDefensePoints() == whiteMage.getDefensePoints() &&
                getHealthPoints() == whiteMage.getHealthPoints();
    }
    @Override
    public int hashCode(){
        return Objects.hashCode(WhiteMage.class);
    }

    @Override
    public void equipWeapon(IWeapon weapon) {
        weapon.equippedByWhiteMage(this);
    }
}