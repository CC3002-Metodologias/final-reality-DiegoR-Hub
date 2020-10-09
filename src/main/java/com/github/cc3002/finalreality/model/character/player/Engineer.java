package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.Axe;
import com.github.cc3002.finalreality.model.weapon.Knife;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

public class Engineer extends PlayerCharacter {

    /**
     * Creates a new character.
     *
     * @param name       the character's name
     * @param turnsQueue the queue with the characters waiting for their turn
     */
    public Engineer(BlockingQueue<ICharacter> turnsQueue, String name) {
        super(turnsQueue,name);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Engineer)) {
            return false;
        }
        final Engineer engineer = (Engineer) o;
        return getName().equals(engineer.getName());
    }
    @Override
    public int hashCode(){
        return Objects.hashCode(Engineer.class);
    }

    public void equipAxe(Axe newAxe) {
        this.equippedWeapon=newAxe;
    }
    public void equipKnife(Knife newKnife) {
        this.equippedWeapon=newKnife;
    }
}
