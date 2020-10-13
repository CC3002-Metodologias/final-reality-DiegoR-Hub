package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.Bow;
import com.github.cc3002.finalreality.model.weapon.Staff;
import com.github.cc3002.finalreality.model.weapon.Sword;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

    /**
     * La clase que implementa los Thief del juego
     * @author Ignacio Slater Muñoz.
     * @author Diego Ruiz R.
     */
public class Thief extends PlayerCharacter {

    /**
     * Creates a new character.
     *
     * @param name       the character's name
     * @param turnsQueue the queue with the characters waiting for their turn
     */
    public Thief(BlockingQueue<ICharacter> turnsQueue, String name) {
        super(turnsQueue,name);
    }

    /**
     * Actualiza el atributo equippedWeapon del Thief a la Sword newSword
     * @param newSword
     */
    public void equipSword(Sword newSword) {
        this.equippedWeapon=newSword;
    }

    /**
     * Actualiza el atributo equippedWeapon del thief al Staff newStaff
     * @param newStaff
     */
    public void equipStaff(Staff newStaff) {
        this.equippedWeapon=newStaff;
    }

    /**
     * Actualiza el atributo equippedWeapon del thief al Bow newBow
     * @param newBow
     */
    public void equipBow(Bow newBow) {
        this.equippedWeapon=newBow;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Thief)) {
            return false;
        }
        final Thief thief = (Thief) o;
        return getName().equals(thief.getName());
    }
    @Override
    public int hashCode(){
        return Objects.hashCode(Thief.class);
    }

}
