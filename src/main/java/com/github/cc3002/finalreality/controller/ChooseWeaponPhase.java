package com.github.cc3002.finalreality.controller;

import java.util.*;
import java.util.ArrayList;

/**
 * Clase que implementa la fase de equipamiento de armas en el turno de un player character
 */

public class ChooseWeaponPhase extends Phase {
    /**
     * Transiciona estado del juego a la siguiente fase, elegir un enemy a atacar
     */
    public void nextPhase(){
        changePhase(new TargetPhase());
    }

    /**
     *
     * Indica en un string que estamos en la fase de equipamiento de arma
     */
    public String printPhase(){
        return "Equipamiento de armas";
    }

    /**
     *
     * Entrega una lista con los nombres de las armas a elegir con fin en darle nombre a los botones de la GUI
     */
    public List<String> nombreBotones(){
        List<String> listaNombreBotones = new ArrayList<String>();
        for (int i=0; i < controller.getInventory().size(); i++){
            listaNombreBotones.add(controller.getWeaponFromInventory(i).getName());
        }
        return listaNombreBotones;
    }

    /**
     * Dado el nombre de un arma, lo equipa al jugador actual
     */
    @Override
    public void action(String nombreButton) {
        for (int i=0; i<controller.getInventory().size(); i++){
            if (controller.getWeaponFromInventory(i).getName() == nombreButton){
                controller.equipFromInventory(i,controller.getParty().indexOf(controller.getJugadorActual()));
            }
        }
    }
}
