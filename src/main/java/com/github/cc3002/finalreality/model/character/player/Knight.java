package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.Axe;
import com.github.cc3002.finalreality.model.weapon.Knife;
import com.github.cc3002.finalreality.model.weapon.Sword;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * La clase que implementa los Knight del juego
 * @author Ignacio Slater Muñoz.
 * @author Diego Ruiz R.
 */
public class Knight extends PlayerCharacter {

    /**
     * Creates a new Knight with a name and the queue
     * @author Ignacio Slater Muñoz.
     * @author <Your name>
     */
    public Knight(BlockingQueue<ICharacter> turnsQueue, String name) {
        super(turnsQueue,name);
    }

    /**
     * Actualiza el atributo equippedWeapon del Knight a la Sword newSword
     * @param newSword
     */
    public  void equipSword(Sword newSword){ this.equippedWeapon=newSword;}

    /**
     * Actualiza el atributo equippedWeapon del Knight a la Axe newAxe
     * @param newAxe
     */
    public void equipAxe(Axe newAxe) {
        this.equippedWeapon=newAxe;
    }

    /**
     * Actualiza el atributo equippedWeapon del Knight a la Knife newKnife
     * @param newKnife
     */
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
