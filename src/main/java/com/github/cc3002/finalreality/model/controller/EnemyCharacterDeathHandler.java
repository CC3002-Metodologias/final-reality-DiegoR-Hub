package com.github.cc3002.finalreality.model.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EnemyCharacterDeathHandler implements PropertyChangeListener {
    private final Controller controller;

    public EnemyCharacterDeathHandler(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.EnemyDeath((String) evt.getNewValue());
    }
}
