package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.player.*;

import java.util.Objects;

    /**
     * La clase que implementa los Knife del juego
     * @author Ignacio Slater Muñoz.
     * @author Diego Ruiz R.
     */
public class Knife extends AbstractWeapon {
    /**
     * Creates a new Knife with a name, a damage, a weight
     *
     */
    public Knife(String name, int damage, int weight) {
        super(name, damage, weight);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Knife)) {
            return false;
        }
        final Knife knife = (Knife) o;
        return getDamage() == knife.getDamage() &&
                getWeight() == knife.getWeight() &&
                getName().equals(knife.getName());
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(Knife.class);
}

    /**
     *  Se equipa a si misma a una instancia de BlackMage
     *
     */
    @Override
    public void equippedByBlackMage(BlackMage blackMage) {
        blackMage.setEquippedWeapon(this);
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