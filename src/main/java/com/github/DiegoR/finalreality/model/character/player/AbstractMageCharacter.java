package com.github.DiegoR.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.ICharacter;

import java.util.concurrent.BlockingQueue;

public abstract class AbstractMageCharacter extends com.github.cc3002.finalreality.model.character.player.AbstractPlayerCharacter {

    private int mana;


    /**
     * Creates a new character.
     *
     * @param name       the character's name
     * @param turnsQueue the queue with the characters waiting for their turn
     */
    public AbstractMageCharacter(BlockingQueue<ICharacter> turnsQueue, String name, int mana) {
        super(turnsQueue,name);
        this.mana=mana;
    }


}
/**Cambios:
 * Se agrega esta clase AbstractMageCharacter:
 -Razon: *Especializa AbstractCharacter en cuanto cumple Liskov al serle aplicables todos los metodos y atributos
 de AbstractCharacter y a la vez agrega un atributo nuevo mana
 *Ademas permite extensibilidad para los magos del juego cumpliendo Open Closed Principle

 */

