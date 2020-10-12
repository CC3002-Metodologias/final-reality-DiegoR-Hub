package com.github.cc3002.finalreality.model.character.player;


import com.github.cc3002.finalreality.model.character.ICharacter;

import java.util.concurrent.BlockingQueue;

/**
 * A class that holds the common behaviour of all the mages in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Diego Ruiz R.
 */
public class MagePlayerCharacter extends PlayerCharacter {

    private int mana;

    /**
     * El constructor que sera comun a todos los mages del juego
     *
     */
    public MagePlayerCharacter(BlockingQueue<ICharacter> turnsQueue, String name, int mana) {
        super(turnsQueue,name);
        this.mana=mana;
    }

}

