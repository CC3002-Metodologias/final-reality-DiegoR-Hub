package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;

import java.beans.PropertyChangeListener;

public interface IPlayerCharacter extends ICharacter{
    /**
     *  sets this player character's equipped weapon
     */
    void equipWeapon(IWeapon weapon);

    /**
     *
     * Retorna el arma equipada por este Player Character
     */
    IWeapon getEquippedWeapon();

    /**
     *
     * sets this player character's equipped weapon
     */
    void setEquippedWeapon(IWeapon weapon);

    void addTurnsListener(PropertyChangeListener turnsHandler);

}
