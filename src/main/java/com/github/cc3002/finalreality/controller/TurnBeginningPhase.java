package com.github.cc3002.finalreality.controller;

import java.util.*;
import java.util.ArrayList;

/**
 * Clase que implementa el inicio de un turno en el juego
 */
public class TurnBeginningPhase extends Phase{


    @Override
    public void nextPhase() throws InterruptedException {
        //caso en que jugador actual es enemy
        if (controller.getListaEnemy().contains(controller.getJugadorActual())){
            controller.attackRandomPlayerCharacter(controller.getJugadorActual());
            controller.setPhase(new TurnBeginningPhase());
        }
        //caso en que jugador actual es player character
        else{
            changePhase(new ChooseWeaponPhase());
        }
    }

    /**
     *
     * Indica en un string que estamos en la fase de inicio de turno
     */
    public String printPhase(){
        return "Inicio del turno";
    }


    /**
     *
     * Entrega una lista vacia con los nombres de botones a configurar en la GUI,
     * vacia porque en inicio de turno no hay botones asociados a esta fase
     */
    @Override
    public List<String> nombreBotones() {
        List<String> listaNombreBotones = new ArrayList<String>();
        return listaNombreBotones;
    }

    /**
     * No realiza ninguna accion ya que no hay botones asociados a esta fase en la GUI
     */
    @Override
    public void action(String nombreButton) {
        return;
    }


}
