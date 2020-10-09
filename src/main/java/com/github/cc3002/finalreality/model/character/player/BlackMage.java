package com.github.cc3002.finalreality.model.character.player;


import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.Knife;
import com.github.cc3002.finalreality.model.weapon.Staff;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

public class BlackMage extends MagePlayerCharacter {
    /**
     * Creates a new character.
     *
     * @param name       the character's name
     * @param turnsQueue the queue with the characters waiting for their turn
     */
    public BlackMage(BlockingQueue<ICharacter> turnsQueue, String name, int mana) {
        super(turnsQueue,name,mana);
    }
    public void equipKnife(Knife newKnife) {
        this.equippedWeapon=newKnife;
    }

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