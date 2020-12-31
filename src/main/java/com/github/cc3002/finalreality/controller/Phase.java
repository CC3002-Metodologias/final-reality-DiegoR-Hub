package com.github.cc3002.finalreality.controller;

import java.util.List;


/**
 * Clase abstract, superclase de las fases del juego
 */

public abstract class Phase implements IGameState{
    protected Controller controller;

    /**
     * Settea que las fases se apliquen sobre este controller
     */
    public void setController(Controller controller){
        this.controller = controller;
    }

    /**
     * Metodo auxiliar de nextPhase(), dada una Phase, genera una transicion hacia ella
     *
     */
    public void changePhase(Phase phase){
        controller.setPhase(phase);
    }

    /**
     * Retorna un String caracteristico segun la fase en que se encuentra
     *
     */
    public abstract String printPhase();

    /**
     * Metodo auxiliar para la GUI, retorna una lista con los nombres que deben tener los botones
     * en la correspondiente fase
     *
     */
    public abstract List<String> nombreBotones();

    /**
     * Dado el nombre de un boton, realiza la accion correspondiente a este
     *
     */
    public abstract void action(String nombreButton);
}
