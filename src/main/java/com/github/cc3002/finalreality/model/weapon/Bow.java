package com.github.cc3002.finalreality.model.weapon;

import java.util.Objects;

public class Bow extends AbstractWeapon {
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

}