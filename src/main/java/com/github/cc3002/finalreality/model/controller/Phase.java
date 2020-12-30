package com.github.cc3002.finalreality.model.controller;

public abstract class Phase implements IGameState{
    //protected para que las demas fases tambien puedan ingresas al controlador
    protected Controller controller;

    public void setController(Controller controller){
        this.controller = controller;
    }

    protected void changePhase(Phase phase){
        controller.setPhase(phase);
    }


}
