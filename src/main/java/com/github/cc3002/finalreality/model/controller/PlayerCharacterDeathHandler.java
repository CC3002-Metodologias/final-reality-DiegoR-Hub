package com.github.cc3002.finalreality.model.controller;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PlayerCharacterDeathHandler implements PropertyChangeListener {
    private final Controller controller;

    public PlayerCharacterDeathHandler(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.PlayerCharacterDeath((String) evt.getNewValue());
    }
}
