package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.BlackMage;
import com.github.cc3002.finalreality.model.character.player.WhiteMage;
import com.github.cc3002.finalreality.model.weapon.*;
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
    private  WhiteMage testWhiteMageDifferentDefensePoints;
    private  WhiteMage testWhiteMageDifferentHealthPoints;
    private  WhiteMage testWhiteMageDifferentName;
    private  WhiteMage testWhiteMageDifferentMana;
    private Staff testStaff;
    private static final int MANA = 15;
    private static final String WHITE_MAGE_NAME = "Eiko";
    private static final BlockingQueue<com.github.cc3002.finalreality.model.character.ICharacter> turns = null;
    private static final int DAMAGE = 15;
    private static final int WEIGHT = 20;
    private static final int DEFENSE_POINTS = 7;
    private static  final int HEALTH_POINTS = 17;
    private static final String STAFF_NAME = "Test Staff";
    private  static final int MAGIC_DAMAGE=10;
    private static final String BLACK_MAGE_NAME = "Vivi";
    private BlackMage testBlackMage;
    private static final String KNIFE_NAME = "Test Knife";
    private static final String SWORD_NAME = "Test Sword";
    private static final String AXE_NAME = "Test Axe";
    private static final String BOW_NAME = "Test Bow";
    private Knife testKnife;
    private Axe testAxe;
    private Sword testSword;
    private Bow testBow;


    @BeforeEach
    void setUp(){
        testWhiteMage = new WhiteMage(turns,WHITE_MAGE_NAME, DEFENSE_POINTS, HEALTH_POINTS, MANA);
        testWhiteMageDifferentDefensePoints = new WhiteMage(turns, WHITE_MAGE_NAME, DEFENSE_POINTS+1, HEALTH_POINTS, MANA);
        testWhiteMageDifferentHealthPoints = new WhiteMage(turns, WHITE_MAGE_NAME, DEFENSE_POINTS, HEALTH_POINTS+1, MANA);
        testWhiteMageDifferentMana = new WhiteMage(turns, WHITE_MAGE_NAME, DEFENSE_POINTS, HEALTH_POINTS, MANA+1);
        testWhiteMageDifferentName = new WhiteMage(turns, "hola", DEFENSE_POINTS, HEALTH_POINTS, MANA);
        testKnife = new Knife(KNIFE_NAME, DAMAGE,WEIGHT);
        testStaff = new Staff(STAFF_NAME,DAMAGE,WEIGHT,MAGIC_DAMAGE);
        testAxe = new Axe(AXE_NAME, DAMAGE,WEIGHT);
        testSword = new Sword(SWORD_NAME, DAMAGE, WEIGHT);
        testBow = new Bow(BOW_NAME, DAMAGE, WEIGHT);
        testBlackMage = new BlackMage(turns,BLACK_MAGE_NAME,DEFENSE_POINTS, HEALTH_POINTS, MANA);
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest(){
        var expectedWhiteMage = new WhiteMage(turns, WHITE_MAGE_NAME, DEFENSE_POINTS, HEALTH_POINTS, MANA);
        var prueba = testWhiteMage;
        assertEquals(expectedWhiteMage, testWhiteMage);
        assertEquals(expectedWhiteMage.hashCode(), testWhiteMage.hashCode());
        assertNotEquals(testWhiteMage,testBlackMage);
        assertNotEquals(testWhiteMage, testWhiteMageDifferentDefensePoints);
        assertNotEquals(testWhiteMage, testWhiteMageDifferentMana);
        assertNotEquals(testWhiteMage, testWhiteMageDifferentName);
        assertNotEquals(testWhiteMage, testWhiteMageDifferentHealthPoints);
        assertEquals(prueba,testWhiteMage);
    }

    /**
     * Chequea que una instancia de la clase WhiteMage se pueda equipar correctamente un Staff
     */
    @Test
    public void equipStaffTest(){
        assertNull(testWhiteMage.getEquippedWeapon());
        testWhiteMage.equipWeapon(testStaff);
        assertEquals(testStaff, testWhiteMage.getEquippedWeapon());
    }
    /**
     * Chequea que una instancia de la clase WhiteMage no se pueda equipar un Sword
     */
    @Test
    void equipSwordTest(){
        assertNull(testWhiteMage.getEquippedWeapon());
        testWhiteMage.equipWeapon(testSword);
        assertNull(testWhiteMage.getEquippedWeapon());
    }
    /**
     * Chequea que una instancia de la clase WhiteMage no se pueda equipar un Axe
     */
    @Test
    void equipAxeTest(){
        assertNull(testWhiteMage.getEquippedWeapon());
        testWhiteMage.equipWeapon(testAxe);
        assertNull(testWhiteMage.getEquippedWeapon());
    }
    /**
     * Chequea que una instancia de la clase WhiteMage no se pueda equipar un Knife
     */
    @Test
    void equipKnifeTest(){
        assertNull(testWhiteMage.getEquippedWeapon());
        testWhiteMage.equipWeapon(testKnife);
        assertNull(testWhiteMage.getEquippedWeapon());
    }
    /**
     * Chequea que una instancia de la clase WhiteMage no se pueda equipar un Bow
     */
    @Test
    void equipBowTest(){
        assertNull(testWhiteMage.getEquippedWeapon());
        testWhiteMage.equipWeapon(testBow);
        assertNull(testWhiteMage.getEquippedWeapon());
    }
}
