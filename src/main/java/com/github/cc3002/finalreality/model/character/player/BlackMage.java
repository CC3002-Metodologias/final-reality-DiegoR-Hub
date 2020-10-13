package com.github.cc3002.finalreality.model.character.player;


import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.Knife;
import com.github.cc3002.finalreality.model.weapon.Staff;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * La clase que implementa los BlackMage del juego
 * @author Ignacio Slater Muñoz.
 * @author Diego Ruiz R.
 *
 */
public class BlackMage extends PlayerCharacter {
    private int mana;
    /**
     * Creates a new BlackMage with a turnsQueue, a name, a mana
     *
     */
    public BlackMage(BlockingQueue<ICharacter> turnsQueue, String name, int mana) {
        super(turnsQueue,name);
        this.mana=mana;
    }
    /**
     * Actualiza el atributo equippedWeapon del BlackMage a la Knife newKnife
     * @param newKnife
     */
    public void equipKnife(Knife newKnife) {
        this.equippedWeapon=newKnife;
    }

    /**
     * Actualiza el atributo equippedWeapon del BlackMage al Staff newStaff
     * @param newStaff
     */
    public void equipStaff(Staff newStaff){
        this.equippedWeapon=newStaff;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BlackMage)) {
            return false;
        }
        final BlackMage blackMage = (BlackMage) o;
        return getName().equals(blackMage.getName());
    }
    @Override
    public int hashCode(){
        return Objects.hashCode(BlackMage.class);
    }
}