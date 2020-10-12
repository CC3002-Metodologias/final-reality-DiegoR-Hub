package com.github.cc3002.finalreality.model.weapon;

import java.util.Objects;

    /**
     * La clase que implementa las Sword del juego
     *
     */
public class Sword extends AbstractWeapon {
    /**
     * Creates a new Sword with a name, a damage, a weight
     *
     */
    public Sword(String name, int damage, int weight) {
        super(name, damage, weight);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sword)) {
            return false;
        }
        final Sword sword = (Sword) o;
        return getDamage() == sword.getDamage() &&
                getWeight() == sword.getWeight() &&
                getName().equals(sword.getName());
    }
    @Override
    public int hashCode(){
        return Objects.hashCode(Sword.class);
    }
}

