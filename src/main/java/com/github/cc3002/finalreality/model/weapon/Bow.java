package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.player.*;

import java.util.Objects;
    /**
     * La clase que implementa los Bow del juego
     * @author Ignacio Slater Muñoz.
     * @author Diego Ruiz R.
     */
public class Bow extends AbstractWeapon {
    /**
     * Creates a new Bow with a name, a damage, a weight
     *
     */
    public Bow(String name, int damage, int weight) {
        super(name, damage, weight);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bow)) {
            return false;
        }
        final Bow bow = (Bow) o;
        return getDamage() == bow.getDamage() &&
                getWeight() == bow.getWeight() &&
                getName().equals(bow.getName());
    }
    @Override
    public int hashCode(){
        return Objects.hashCode(Bow.class);
    }


    @Override
    public void equippedByEngineer(Engineer engineer) {
        engineer.setEquippedWeapon(this);
    }


    @Override
    public void equippedByThief(Thief thief) {
        thief.setEquippedWeapon(this);
    }
}