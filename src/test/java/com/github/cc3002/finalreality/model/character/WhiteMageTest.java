package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.BlackMage;
import com.github.cc3002.finalreality.model.character.player.WhiteMage;
import com.github.cc3002.finalreality.model.weapon.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
     * Abstract class containing the tests for the WhiteMage.
     *
     */
public class WhiteMageTest {
    private WhiteMage testWhiteMage;
    private Staff testStaff;
    private static final int MANA = 15;
    private static final String WHITE_MAGE_NAME = "Eiko";
    private static final BlockingQueue<com.github.cc3002.finalreality.model.character.ICharacter> turns = null;
    private static final int DAMAGE = 15;
    private static final int WEIGHT = 20;
    private static final String STAFF_NAME = "Test Staff";
    private  static final int MAGIC_DAMAGE=10;
    private static final String BLACK_MAGE_NAME = "Vivi";
    private BlackMage testBlackMage;


    @BeforeEach
    void setUp(){
        testWhiteMage=new WhiteMage(turns,WHITE_MAGE_NAME,MANA);
        testStaff=new Staff(STAFF_NAME,DAMAGE,WEIGHT,MAGIC_DAMAGE);
        testBlackMage = new BlackMage(turns,BLACK_MAGE_NAME,MANA);
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest(){
        var expectedWhiteMage = new WhiteMage(turns, WHITE_MAGE_NAME, MANA);
        var prueba = testWhiteMage;
        assertEquals(expectedWhiteMage, testWhiteMage);
        assertEquals(expectedWhiteMage.hashCode(), testWhiteMage.hashCode());
        assertNotEquals(testWhiteMage,testBlackMage);
        assertEquals(prueba,testWhiteMage);
    }

    /**
     * Chequea que una instancia de la clase WhiteMage se pueda equipar correctamente un Staff
     */
    @Test
    public void equipStaffTest(){
        assertNull(testWhiteMage.getEquippedWeapon());
        testWhiteMage.equipStaff(testStaff);
        assertEquals(testStaff, testWhiteMage.getEquippedWeapon());
    }
}
