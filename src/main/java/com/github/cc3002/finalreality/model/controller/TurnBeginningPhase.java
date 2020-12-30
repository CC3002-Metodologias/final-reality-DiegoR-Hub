package com.github.cc3002.finalreality.model.controller;

public class TurnBeginningPhase extends Phase{
    @Override
    public void nextPhase(){
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

}
