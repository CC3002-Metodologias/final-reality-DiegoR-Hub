package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.Staff;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * La clase que implementa los WhiteMage del juego
 * @author Ignacio Slater Muñoz.
 * @author Diego Ruiz R.
 */
public class WhiteMage extends PlayerCharacter {
    private int mana;
    /**
     * Creates a new WhiteMage with a turnsQueue, a name, a mana
     *
     */
    public WhiteMage(BlockingQueue<ICharacter> turnsQueue, String name, int mana) {
        super(turnsQueue,name);
        this.mana=mana;
    }

    /**
     * Actualiza el atributo equippedWeapon de WhiteMage a un Staff dado por newStaff
     *
     * @param newStaff
     *      El staff a equipar al WhiteMage
     */
    public void equipStaff(Staff newStaff){
        this.equippedWeapon=newStaff;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WhiteMage)) {
            return false;
        }
        final WhiteMage whiteMage = (WhiteMage) o;
        return getName().equals(whiteMage.getName());
    }
    @Override
    public int hashCode(){
        return Objects.hashCode(WhiteMage.class);
    }

}