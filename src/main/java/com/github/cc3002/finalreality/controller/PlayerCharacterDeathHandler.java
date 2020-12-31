package com.github.cc3002.finalreality.controller;
import com.github.cc3002.finalreality.model.character.player.IPlayerCharacter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PlayerCharacterDeathHandler implements PropertyChangeListener {
    private final Controller controller;

    /**
     *
     * Crea un nuevo observer de muerte de player character, setteando el controller al cual observa
     */
    public PlayerCharacterDeathHandler(Controller controller) {
        this.controller = controller;
    }


    /**
     *
     * Se ejecuta al recibir la señal el observer
     * Remueve al player character muerto de la turns queue
     * Remueve al player character muerto de la party
     * Chequea si han ganado los enemy tras esta muerte del player character
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.removePlayerCharacter(controller.getParty().indexOf(evt.getNewValue()));
        controller.PlayerCharacterDeath((IPlayerCharacter) evt.getNewValue());
        controller.removePlayerCharacterFromTurnsQueue((IPlayerCharacter) evt.getNewValue());
    }
}
