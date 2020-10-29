package com.github.cc3002.finalreality.model.weapon;


import com.github.cc3002.finalreality.model.character.player.*;

import java.util.Objects;
    /**
     * La clase que implementa las Axes del juego
     * @author Ignacio Slater Muñoz.
     * @author Diego Ruiz R.
     */
public class Axe extends AbstractWeapon {
    /**
     * Creates a new Axe with a name, a damage, a weight
     *
     */
    public Axe(String name, int damage, int weight) {
        super(name, damage, weight);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Axe)) {
            return false;
        }
        final Axe axe = (Axe) o;
        return  getDamage() == axe.getDamage() &&
                getWeight() == axe.getWeight() &&
                getName().equals(axe.getName());

    }
    @Override
    public int hashCode(){
        return Objects.hashCode(Axe.class);
    }

    /**
     *  Se equipa a si misma a una instancia de Engineer
     *
     */
    @Override
    public void equippedByEngineer(Engineer engineer) {
        engineer.setEquippedWeapon(this);
    }

    /**
     *  Se equipa a si misma a una instancia de Knight
     *
     */
    @Override
    public void equippedByKnight(Knight knight) {
        knight.setEquippedWeapon(this);
    }

}
