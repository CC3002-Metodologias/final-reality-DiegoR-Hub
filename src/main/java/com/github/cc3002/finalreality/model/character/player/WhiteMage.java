package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.Staff;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

public class WhiteMage extends MagePlayerCharacter {
    /**
     * Creates a new character.
     *
     * @param name       the character's name
     * @param turnsQueue the queue with the characters waiting for their turn
     */
    public WhiteMage(BlockingQueue<ICharacter> turnsQueue, String name, int mana) {
        super(turnsQueue,name,mana);
    }
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