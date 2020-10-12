package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.Axe;
import com.github.cc3002.finalreality.model.weapon.Knife;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * La clase que implementa los Engineer del juego
 *
 */
public class Engineer extends PlayerCharacter {

    /**
     * Creates a new Engineer with a name, a weight and the queue
     *
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

    /**
     * Actualiza el atributo equippedWeapon del Engineer a la Axe newAxe
     * @param newAxe
     */
    public void equipAxe(Axe newAxe) {
        this.equippedWeapon=newAxe;
    }

    /**
     * Actualiza el atributo equippedWeapon del Engineer a la Knife newKnife
     * @param newKnife
     */
    public void equipKnife(Knife newKnife) {
        this.equippedWeapon=newKnife;
    }
}
