package com.github.cc3002.finalreality.model.character.player;


import com.github.cc3002.finalreality.model.character.ICharacter;

import java.util.concurrent.BlockingQueue;

public class MagePlayerCharacter extends PlayerCharacter {

    private int mana;


    /**
     * Creates a new character.
     *
     * @param name       the character's name
     * @param turnsQueue the queue with the characters waiting for their turn
     */
    public MagePlayerCharacter(BlockingQueue<ICharacter> turnsQueue, String name, int mana) {
        super(turnsQueue,name);
        this.mana=mana;
    }

}

