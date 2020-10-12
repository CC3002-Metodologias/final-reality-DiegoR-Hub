package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.Thief;
import com.github.cc3002.finalreality.model.weapon.Bow;
import com.github.cc3002.finalreality.model.weapon.Staff;
import com.github.cc3002.finalreality.model.weapon.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

    /**
     * Abstract class containing the tests for the Thief.
     *
     */
public class ThiefTest {
    private Thief testThief;
    private Sword testSword;
    private Staff testStaff;
    private Bow testBow;
    private static final String THIEF_NAME = "Zidane";
    private static final BlockingQueue<com.github.cc3002.finalreality.model.character.ICharacter> turns = null;
    private static final int DAMAGE = 15;
    private static final int WEIGHT = 20;
    private static final String BOW_NAME = "Test Bow";
    private static final String STAFF_NAME = "Test Staff";
    private static final String SWORD_NAME = "Test Sword";
    private  static final int MAGIC_DAMAGE=10;

    @BeforeEach
    void setUp(){
        testThief=new Thief(turns, THIEF_NAME);
        testBow=new Bow(BOW_NAME,DAMAGE,WEIGHT);
        testStaff=new Staff(STAFF_NAME,DAMAGE,WEIGHT,MAGIC_DAMAGE);
        testSword=new Sword(SWORD_NAME,DAMAGE,WEIGHT);
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest(){
        var expectedThief= new Thief(turns, THIEF_NAME);
        assertEquals(expectedThief, testThief);
        assertEquals(expectedThief.hashCode(), testThief.hashCode());
    }

    /**
     * Chequea que una instancia de la clase Thief se pueda equipar correctamente una Sword
     */
    @Test
    public void equipSwordTest() {
        assertNull(testThief.getEquippedWeapon());
        testThief.equipSword(testSword);
        assertEquals(testSword, testThief.getEquippedWeapon());
    }

    /**
     * Chequea que una instancia de la clase Thief se pueda equipar correctamente un Staff
     */
    @Test
    public void equipStaffTest() {
        assertNull(testThief.getEquippedWeapon());
        testThief.equipStaff(testStaff);
        assertEquals(testStaff, testThief.getEquippedWeapon());
    }

    /**
     * Chequea que una instancia de la clase Thief se pueda equipar correctamente un Bow
     */
    @Test
    public void equipBowTest() {
        assertNull(testThief.getEquippedWeapon());
        testThief.equipBow(testBow);
        assertEquals(testBow, testThief.getEquippedWeapon());
    }
}
