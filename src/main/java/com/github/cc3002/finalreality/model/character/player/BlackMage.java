package com.github.cc3002.finalreality.model.character.player;


import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.weapon.Knife;
import com.github.cc3002.finalreality.model.weapon.Staff;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * La clase que implementa los BlackMage del juego
 * @author Ignacio Slater Muñoz.
 * @author Diego Ruiz R.
 *
 */
public class BlackMage extends PlayerCharacter {
    private int mana;
    /**
     * Creates a new BlackMage with a turnsQueue, a name, a mana, defenePoints and healthPoints
     *
     */
    public BlackMage(@NotNull BlockingQueue<ICharacter> turnsQueue, @NotNull String name, int defensePoints, int healthPoints, int mana) {
        super(turnsQueue, name, defensePoints, healthPoints);
        this.mana = mana;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BlackMage)) {
            return false;
        }
        final BlackMage blackMage = (BlackMage) o;
        return getName().equals(blackMage.getName()) &&
                getDefensePoints() == blackMage.getDefensePoints() &&
                getHealthPoints() == blackMage.getHealthPoints() &&
                getMana() == blackMage.getMana();
    }
    @Override
    public int hashCode(){
        return Objects.hashCode(BlackMage.class);
    }

    @Override
    public void equipWeapon(IWeapon weapon) {
        weapon.equippedByBlackMage(this);
    }

    public int getMana() {
        return mana;
    }
}