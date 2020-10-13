package com.github.cc3002.finalreality.model.weapon;

import java.util.Objects;

    /**
     * La clase que implementa los Staff del juego
     * @author Ignacio Slater Muñoz.
     * @author Diego Ruiz R.
     */
public class Staff extends AbstractWeapon {
    private int magicDamage;
    /**
     * Creates a new Staff with a name, a damage, a weight and a magicDamage
     *
     */
    public Staff(String name, int damage, int weight, int magicDamage) {
        super(name, damage, weight);
        this.magicDamage=magicDamage;
    }
    /**
     * getter de atributo magicDamage
     * @return
     */
    public int getmagicDamage(){
        return this.magicDamage;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Staff)) {
            return false;
        }
        final Staff staff = (Staff) o;
        return getDamage() == staff.getDamage() &&
                getWeight() == staff.getWeight() &&
                getName().equals(staff.getName()) &&
                getmagicDamage()==staff.getmagicDamage();
    }
    @Override
    public int hashCode(){
        return Objects.hashCode(Staff.class);
    }
}