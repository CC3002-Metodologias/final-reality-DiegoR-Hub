package com.github.cc3002.finalreality.model.weapon;

public interface IWeapon {
    /**
     *
     * Returns this weapon's name.
     */
    String getName();

    /**
     *
     * Returns this weapon's damage.
     */
    int getDamage() ;

    /**
     *
     * Returns this weapon's weight.
     */
    int getWeight() ;

}