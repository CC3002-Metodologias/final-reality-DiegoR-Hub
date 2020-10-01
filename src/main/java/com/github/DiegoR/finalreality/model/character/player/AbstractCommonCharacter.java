package com.github.DiegoR.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.ICharacter;

import java.util.concurrent.BlockingQueue;

public abstract class AbstractCommonCharacter extends com.github.cc3002.finalreality.model.character.player.AbstractPlayerCharacter {
    /**
     * Creates a new character.
     *
     * @param name       the character's name
     * @param turnsQueue the queue with the characters waiting for their turn
     */
    public AbstractCommonCharacter(BlockingQueue<ICharacter> turnsQueue, String name) {
        super(turnsQueue,name);
    }

}
/**Cambios:
 * Se agrega esta clase AbstractCommonCharacter:
 -Razon: *Especializa AbstractCharacter en cuanto cumple Liskov al serle aplicables todos los metodos
 *Ademas permite extensibilidad para los character comunes del juego cumpliendo Open Closed Principle

 */

