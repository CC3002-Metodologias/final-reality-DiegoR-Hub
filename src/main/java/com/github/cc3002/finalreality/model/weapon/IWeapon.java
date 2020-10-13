package com.github.cc3002.finalreality.model.weapon;

/**
 * Esto representa un weapon del juego
 * Una weapon puede ser equipada por un PlayerCharacter
 * @author Ignacio Slater Muñoz.
 * @author Diego Ruiz R.
 */

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