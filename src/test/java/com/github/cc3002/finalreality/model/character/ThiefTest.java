package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.Thief;
import com.github.cc3002.finalreality.model.character.player.WhiteMage;
import com.github.cc3002.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
     * Abstract class containing the tests for the Thief.
     *
     */
public class ThiefTest {
    private Thief testThief;
    private  Thief testThiefDifferentName;
    private  Thief testThiefDifferentHealthPoints;
    private  Thief testThiefDifferentDefensePoints;
    private Sword testSword;
    private Staff testStaff;
    private Bow testBow;
    private WhiteMage testWhiteMage;
    private static final String THIEF_NAME = "Zidane";
    private static final int DAMAGE = 15;
    private static final int WEIGHT = 20;
    private static final int DEFENSE_POINTS = 7;
    private static  final int HEALTH_POINTS = 17;
    private static final String BOW_NAME = "Test Bow";
    private static final String STAFF_NAME = "Test Staff";
    private static final String SWORD_NAME = "Test Sword";
    private  static final int MAGIC_DAMAGE=10;
    private static final String WHITE_MAGE_NAME = "Eiko";
    private static final int MANA = 15;
    private static final String KNIFE_NAME = "Test Knife";
    private static final String AXE_NAME = "Test Axe";
    private Knife testKnife;
    private Axe testAxe;

    @BeforeEach
    void setUp(){
        testThief=new Thief( THIEF_NAME, DEFENSE_POINTS, HEALTH_POINTS);
        testThiefDifferentDefensePoints = new Thief(THIEF_NAME, DEFENSE_POINTS+1, HEALTH_POINTS);
        testThiefDifferentHealthPoints = new Thief( THIEF_NAME, DEFENSE_POINTS, HEALTH_POINTS+1);
        testThiefDifferentName = new Thief( "hola", DEFENSE_POINTS, HEALTH_POINTS);
        testKnife = new Knife(KNIFE_NAME, DAMAGE,WEIGHT);
        testStaff = new Staff(STAFF_NAME,DAMAGE,WEIGHT,MAGIC_DAMAGE);
        testAxe = new Axe(AXE_NAME, DAMAGE,WEIGHT);
        testSword = new Sword(SWORD_NAME, DAMAGE, WEIGHT);
        testBow = new Bow(BOW_NAME, DAMAGE, WEIGHT);
        testWhiteMage = new WhiteMage(WHITE_MAGE_NAME, DEFENSE_POINTS, HEALTH_POINTS, MANA);
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest(){
        var expectedThief= new Thief( THIEF_NAME, DEFENSE_POINTS, HEALTH_POINTS);
        var prueba = testThief;
        assertEquals(expectedThief, testThief);
        assertEquals(expectedThief.hashCode(), testThief.hashCode());
        assertNotEquals(testThief,testWhiteMage);
        assertNotEquals(testThief, testThiefDifferentDefensePoints);
        assertNotEquals(testThief, testThiefDifferentHealthPoints);
        assertNotEquals(testThief, testThiefDifferentName);
        assertEquals(prueba,testThief);
    }

    /**
     * Chequea que una instancia de la clase Thief se pueda equipar correctamente una Sword
     */
    @Test
    public void equipSwordTest() {
        assertNull(testThief.getEquippedWeapon());
        testThief.equipWeapon(testSword);
        assertEquals(testSword, testThief.getEquippedWeapon());
    }

    /**
     * Chequea que una instancia de la clase Thief se pueda equipar correctamente un Staff
     */
    @Test
    public void equipStaffTest() {
        assertNull(testThief.getEquippedWeapon());
        testThief.equipWeapon(testStaff);
        assertEquals(testStaff, testThief.getEquippedWeapon());
    }

    /**
     * Chequea que una instancia de la clase Thief se pueda equipar correctamente un Bow
     */
    @Test
    public void equipBowTest() {
        assertNull(testThief.getEquippedWeapon());
        testThief.equipWeapon(testBow);
        assertEquals(testBow, testThief.getEquippedWeapon());
    }
    /**
     * Chequea que una instancia de la clase Thief no se pueda equipar un Axe
     */
    @Test
    void equipAxeTest(){
        assertNull(testThief.getEquippedWeapon());
        testThief.equipWeapon(testKnife);
        assertNull(testThief.getEquippedWeapon());
    }
    /**
     * Chequea que una instancia de la clase Thief no se pueda equipar un Knife
     */
    @Test
    void equipKnifeTest(){
        assertNull(testThief.getEquippedWeapon());
        testThief.equipWeapon(testKnife);
        assertNull(testThief.getEquippedWeapon());
    }
}
