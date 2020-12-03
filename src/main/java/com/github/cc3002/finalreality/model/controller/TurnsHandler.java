package com.github.cc3002.finalreality.model.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TurnsHandler implements PropertyChangeListener {
    private final Controller controller;

    public TurnsHandler(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.turnMade((String) evt.getNewValue());
    }
}
