package com.github.cc3002.finalreality.controller;

import java.util.*;
import java.util.ArrayList;

/**
 * Clase para la fase de eleccion de enemigo a atacar en el turno de un player character
 */
public class TargetPhase extends Phase {

    /**
     * Genera transicion de fase desde TargetPhase a un nuevo TurnBeginningPhase
     */
    @Override
    public void nextPhase() throws InterruptedException {
        controller.attackAnotherCharacter(controller.getPlayerCharacterInParty(controller.getParty().indexOf(controller.getJugadorActual())),
                controller.getEnemyCharacterInListaEnemy(controller.getListaEnemy().indexOf(controller.getTargetEnemy())));
        controller.setPhase(new TurnBeginningPhase());
    }
    /**
     *
     * Retorna un String caracteristico a la fase TargetPhase
     *
     */
    public String printPhase(){
        return "Eleccion de enemy a atacar";
    }

    /**
     * Retorna los nombres de los botones que debe mostrar la GUI para la TargetPhase
     *
     */
    public List<String> nombreBotones(){
        List<String> listaNombreBotones = new ArrayList<String>();
        for (int i=0; i<controller.getListaEnemy().size(); i++){
            listaNombreBotones.add(controller.getEnemyCharacterInListaEnemy(i).getName());
        }
        return listaNombreBotones;
    }

    /**
     * Dado el nombre de un enemigo, lo setea como target
     */
    @Override
    public void action(String nombreButton) {
        for(int i=0; i<controller.getListaEnemy().size(); i++){
            if(controller.getEnemyCharacterInListaEnemy(i).getName() == nombreButton){
                controller.targetEnemy(controller.getEnemyCharacterInListaEnemy(i));
                break;
            }
        }
    }
}
