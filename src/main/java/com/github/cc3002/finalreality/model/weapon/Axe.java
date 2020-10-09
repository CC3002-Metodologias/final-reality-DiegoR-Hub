package com.github.cc3002.finalreality.model.weapon;

import java.util.Objects;

public class Axe extends AbstractWeapon {
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
        return getDamage() == axe.getDamage() &&
                getWeight() == axe.getWeight() &&
                getName().equals(axe.getName());
    }
    @Override
    public int hashCode(){
        return Objects.hashCode(Axe.class);
    }

}
