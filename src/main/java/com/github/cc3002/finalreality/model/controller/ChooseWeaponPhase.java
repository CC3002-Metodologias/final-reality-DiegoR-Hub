package com.github.cc3002.finalreality.model.controller;

public class ChooseWeaponPhase extends Phase {
    public void nextPhase(){
        changePhase(new TargetPhase());
    }
}
