package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.Engineer;
import com.github.cc3002.finalreality.model.character.player.WhiteMage;
import com.github.cc3002.finalreality.model.weapon.Axe;
import com.github.cc3002.finalreality.model.weapon.Knife;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
     * Abstract class containing the tests for the Engineer.
     *
     */
class EngineerTest {
    private Engineer testEngineer;
    private static final String ENGINEER_NAME = "Cid";
    private static final BlockingQueue<com.github.cc3002.finalreality.model.character.ICharacter> turns = null;
    private static final int DAMAGE = 15;
    private static final int WEIGHT = 20;
    private static final String AXE_NAME = "Test Axe";
    private static final String KNIFE_NAME = "Test Knife";
    private static final String WHITE_MAGE_NAME = "Eiko";
    private Axe testAxe;
    private Knife testKnife;
    private WhiteMage testWhiteMage;
    private static final int MANA = 15;
    @BeforeEach
    void setUp(){
        testEngineer=new Engineer(turns, ENGINEER_NAME);
        testAxe=new Axe(AXE_NAME,DAMAGE,WEIGHT);
        testKnife=new Knife(KNIFE_NAME, DAMAGE,WEIGHT);
        testWhiteMage = new WhiteMage(turns,WHITE_MAGE_NAME,MANA);
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest(){
        var expectedEngineer= new Engineer(turns, ENGINEER_NAME);
        var prueba = testEngineer;
        assertEquals(expectedEngineer, testEngineer);
        assertEquals(expectedEngineer.hashCode(), testEngineer.hashCode());
        assertNotEquals(testEngineer,testWhiteMage);
        assertEquals(prueba,testEngineer);
    }

    /**
     * Chequea que una instancia de la clase Engineer se pueda equipar correctamente una Axe
     */
    @Test
    public void equipAxeTest() {
        assertNull(testEngineer.getEquippedWeapon());
        testEngineer.equipAxe(testAxe);
        assertEquals(testAxe, testEngineer.getEquippedWeapon());
    }

    /**
     * Chequea que una instancia de la clase Engineer se pueda equipar correctamente un Knife
     */
    @Test
    public void equipKnifeTest() {
        assertNull(testEngineer.getEquippedWeapon());
        testEngineer.equipKnife(testKnife);
        assertEquals(testKnife, testEngineer.getEquippedWeapon());
    }
}