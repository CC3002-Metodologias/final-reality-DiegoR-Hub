package com.github.cc3002.finalreality.controller;

import com.github.cc3002.finalreality.model.character.player.Enemy;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * CLase observer de la muerte de instacias de enemy en el juego
 */

public class EnemyCharacterDeathHandler implements PropertyChangeListener {
    private final Controller controller;

    /**
     *
     * Crea un nuevo observer de muerte de enemy, setteando el controller al cual observa
     */
    public EnemyCharacterDeathHandler(Controller controller) {
        this.controller = controller;
    }

    /**
     *
     * Se ejecuta al recibir la señal el observer
     * Remueve al enemigo muerto de la turns queue
     * Remueve al enemigo muerto de la lista Enemy
     * Chequea si han ganado los player character tras esta muerte del enemy
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.removeEnemy(controller.getListaEnemy().indexOf(evt.getNewValue()));
        controller.EnemyDeath((Enemy) evt.getNewValue());
        controller.removeEnemyCharacterFromTurnsQueue((Enemy) evt.getNewValue());
    }
}
