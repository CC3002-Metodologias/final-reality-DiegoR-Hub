package com.github.cc3002.finalreality.model.weapon;

import java.util.Objects;

public class Knife extends AbstractWeapon {
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
}