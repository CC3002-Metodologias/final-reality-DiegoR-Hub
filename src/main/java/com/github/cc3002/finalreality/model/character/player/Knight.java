package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.Axe;
import com.github.cc3002.finalreality.model.weapon.Knife;
import com.github.cc3002.finalreality.model.weapon.Sword;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

public class Knight extends PlayerCharacter {

    /**
     * Creates a new character.
     *
     * @param name       the character's name
     * @param turnsQueue the queue with the characters waiting for their turn
     */
    public Knight(BlockingQueue<ICharacter> turnsQueue, String name) {
        super(turnsQueue,name);
    }
    public  void equipSword(Sword newSword){ this.equippedWeapon=newSword;}
    public void equipAxe(Axe newAxe) {
        this.equippedWeapon=newAxe;
    }
    public void equipKnife(Knife newKnife) {
        this.equippedWeapon=newKnife;
    }
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Knight)) {
            return false;
        }
        final Knight knight = (Knight) o;
        return getName().equals(knight.getName());
    }
    @Override
    public int hashCode(){
        return Objects.hashCode(Knight.class);
    }

}
