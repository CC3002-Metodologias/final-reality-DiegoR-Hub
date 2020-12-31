package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.controller.Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CLase que contiene los test asociados a la clase Phase y sus subclases
 */

public class PhaseTest {
    private Controller controller;

    @BeforeEach
    void setUp() throws InterruptedException {
        controller = new Controller(100,100);
        controller.tooBasicSetup();
    }

    /**
     * Teste la correcta transicion entre estados y el correcto funcinamiento de cada uno de los metodos
     * de cada estado
     */
    @Test
    void phasesTest() throws InterruptedException {
        //ahora en fase turn beginning
        assertEquals("Inicio del turno", controller.getPhase().printPhase());
        assertTrue(controller.getPhase().nombreBotones().isEmpty());
        //metodo que no hace nada
        controller.getPhase().action("nada");
        controller.getPhase().nextPhase();
        //ahora en fase choose weapon
        assertEquals("Equipamiento de armas", controller.getPhase().printPhase());
        assertTrue(controller.getPhase().nombreBotones().contains("axe c++2"));
        controller.getPhase().action("axe c++2");
        assertEquals("axe c++2", controller.getPartyMemberEquippedWeapon(0).getName());
        controller.getPhase().nextPhase();
        //ahora en fase target
        assertEquals("Eleccion de enemy a atacar",controller.getPhase().printPhase());
        assertTrue(controller.getPhase().nombreBotones().contains("Goblin"));
        controller.getPhase().action("Goblin");
        assertEquals("Goblin", controller.getTargetEnemy().getName());
        controller.getPhase().nextPhase();
        assertEquals("Inicio del turno", controller.getPhase().printPhase());
    }
}
