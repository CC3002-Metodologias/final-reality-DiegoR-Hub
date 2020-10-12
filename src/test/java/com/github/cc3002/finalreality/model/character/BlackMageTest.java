package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.BlackMage;
import com.github.cc3002.finalreality.model.weapon.Knife;
import com.github.cc3002.finalreality.model.weapon.Staff;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Abstract class containing the tests for the BlackMages.
 *
 */
public class BlackMageTest {
    private BlackMage testBlackMage;
    private static final int MANA = 15;
    private static final int DAMAGE = 15;
    private static final int WEIGHT = 20;
    private  static final int MAGIC_DAMAGE=10;
    private static final String BLACK_MAGE_NAME = "Vivi";
    private static final BlockingQueue<com.github.cc3002.finalreality.model.character.ICharacter> turns = null;
    private static final String KNIFE_NAME = "Test Knife";
    private static final String STAFF_NAME = "Test Staff";
    private Staff testStaff;
    private Knife testKnife;
    @BeforeEach
    void setUp(){
        testBlackMage=new BlackMage(turns,BLACK_MAGE_NAME,MANA);
        testKnife=new Knife(KNIFE_NAME, DAMAGE,WEIGHT);
        testStaff=new Staff(STAFF_NAME,DAMAGE,WEIGHT,MAGIC_DAMAGE);
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest(){
        var expectedBlackMage = new BlackMage(turns, BLACK_MAGE_NAME, MANA);
        assertEquals(expectedBlackMage, testBlackMage);
        assertEquals(expectedBlackMage.hashCode(), testBlackMage.hashCode());
    }

    /**
     * Chequea que una instancia de la clase BlackMage se pueda equipar correctamente un Knife
     */
    @Test
    void equipKnifeTest() {
        assertNull(testBlackMage.getEquippedWeapon());
        testBlackMage.equipKnife(testKnife);
        assertEquals(testKnife, testBlackMage.getEquippedWeapon());
    }
    /**
     * Chequea que una instancia de la clase BlackMage se pueda equipar correctamente un Staff
     */
    @Test
    void equipStaffTest(){
        assertNull(testBlackMage.getEquippedWeapon());
        testBlackMage.equipStaff(testStaff);
        assertEquals(testStaff, testBlackMage.getEquippedWeapon());
    }
    /**
     * Checks that the character waits the appropriate amount of time for it's turn.
     */
    @Test
    void waitTurnTest() {
        Assertions.assertTrue(turns.isEmpty());
        testBlackMage.waitTurn();
        try {
            // Thread.sleep is not accurate so this values may be changed to adjust the
            // acceptable error margin.
            // We're testing that the character waits approximately 1 second.
            Thread.sleep(900);
            Assertions.assertEquals(0, turns.size());
            Thread.sleep(200);
            Assertions.assertEquals(1, turns.size());
            Assertions.assertEquals(testBlackMage, turns.peek());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

