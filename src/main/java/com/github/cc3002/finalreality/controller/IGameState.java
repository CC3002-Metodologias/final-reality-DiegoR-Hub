package com.github.cc3002.finalreality.controller;

import java.util.List;

/**
 * Interfaz que rige el comportamiento comun de toda fase del juego
 */

public interface IGameState {

    /**
     * Transiciona a la siguiente fase del turno/juego
     */
    void nextPhase() throws InterruptedException;

    /**
     * Dado el nombre de un boton, realiza la accion correspondiente a este
     *
     */
    void action(String nombreBoton);

    /**
     * Settea que las fases se apliquen sobre este controller
     */
    void setController(Controller controller);

    /**
     * Metodo auxiliar de nextPhase(), dada una Phase, genera una transicion hacia ella
     *
     */
    void changePhase(Phase phase);

    /**
     * Retorna un String caracteristico segun la fase en que se encuentra
     *
     */
    String printPhase();

    /**
     * Metodo auxiliar para la GUI, retorna una lista con los nombres que deben tener los botones
     * en la correspondiente fase
     *
     */
    List<String> nombreBotones();
}
