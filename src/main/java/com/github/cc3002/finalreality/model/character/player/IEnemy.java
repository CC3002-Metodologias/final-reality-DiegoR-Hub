package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.ICharacter;

public interface IEnemy extends ICharacter{
    /**
     *  Ser atacado por una instancia de PlayerCharacter
     */
    void attackedByPlayerCharacter(IPlayerCharacter playerCharacter);

    /**
     * Atacar a una instancia de PlayerCharacter
     */
    void attack(IPlayerCharacter playerCharacter);

}
