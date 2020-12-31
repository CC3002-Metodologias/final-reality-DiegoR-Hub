package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.BlackMage;
import com.github.cc3002.finalreality.model.character.player.WhiteMage;
import com.github.cc3002.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CLase que contiene los test asociados a la clase BlackMage
 */
public class BlackMageTest {
    private BlackMage testBlackMage;
    private BlackMage testBlackMageDifferentDefensePoints;
    private BlackMage testBlackMageDifferentHealthPoints;
    private  BlackMage testBlackMageDifferentName;
    private BlackMage testBlackMageDifferentMana;
    private static final int MANA = 15;
    private static final int DAMAGE = 15;
    private static final int WEIGHT = 20;
    private  static final int MAGIC_DAMAGE = 10;
    private static final int DEFENSE_POINTS = 7;
    private static  final int HEALTH_POINTS = 17;
    private static final String BLACK_MAGE_NAME = "Vivi";
    private static final String KNIFE_NAME = "Test Knife";
    private static final String STAFF_NAME = "Test Staff";
    private static final String SWORD_NAME = "Test Sword";
    private static final String AXE_NAME = "Test Axe";
    private static final String BOW_NAME = "Test Bow";
    private static final String WHITE_MAGE_NAME = "Eiko";
    private Staff testStaff;
    private Knife testKnife;
    private Axe testAxe;
    private Sword testSword;
    private Bow testBow;
    private WhiteMage testWhiteMage;
    @BeforeEach
    void setUp(){
        testBlackMage=new BlackMage( BLACK_MAGE_NAME, DEFENSE_POINTS, HEALTH_POINTS, MANA);
        testBlackMageDifferentDefensePoints = new BlackMage(BLACK_MAGE_NAME, DEFENSE_POINTS + 1, HEALTH_POINTS, MANA );
        testBlackMageDifferentHealthPoints = new BlackMage(BLACK_MAGE_NAME, DEFENSE_POINTS, HEALTH_POINTS +1, MANA);
        testBlackMageDifferentName = new BlackMage("hola", DEFENSE_POINTS, HEALTH_POINTS, MANA);
        testBlackMageDifferentMana = new BlackMage(BLACK_MAGE_NAME, DEFENSE_POINTS, HEALTH_POINTS, MANA+1);
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
        var expectedBlackMage = new BlackMage(BLACK_MAGE_NAME, DEFENSE_POINTS, HEALTH_POINTS, MANA);
        var prueba = testBlackMage;
        assertEquals(expectedBlackMage, testBlackMage);
        assertEquals(expectedBlackMage.hashCode(), testBlackMage.hashCode());
        assertNotEquals(testBlackMage,testWhiteMage);
        assertNotEquals(testBlackMage, testBlackMageDifferentDefensePoints);
        assertNotEquals(testBlackMage, testBlackMageDifferentHealthPoints);
        assertNotEquals(testBlackMage, testBlackMageDifferentName);
        assertNotEquals(testBlackMage, testBlackMageDifferentMana);
        assertEquals(prueba,testBlackMage);
    }

    /**
     * Chequea que una instancia de la clase BlackMage se pueda equipar correctamente un Knife
     */
    @Test
    void equipKnifeTest() {
        assertNull(testBlackMage.getEquippedWeapon());
        testBlackMage.equipWeapon(testKnife);
        assertEquals(testKnife, testBlackMage.getEquippedWeapon());
    }

    /**
     * Chequea que una instancia de la clase BlackMage se pueda equipar correctamente un Staff
     */
    @Test
    void equipStaffTest(){
        assertNull(testBlackMage.getEquippedWeapon());
        testBlackMage.equipWeapon(testStaff);
        assertEquals(testStaff, testBlackMage.getEquippedWeapon());
    }

    /**
     * Chequea que una instancia de la clase BlackMage no se pueda equipar un Sword
     */
    @Test
    void equipSwordTest(){
        assertNull(testBlackMage.getEquippedWeapon());
        testBlackMage.equipWeapon(testSword);
        assertNull(testBlackMage.getEquippedWeapon());
    }
    /**
     * Chequea que una instancia de la clase BlackMage no se pueda equipar un Axe
     */
    @Test
    void equipAxeTest(){
        assertNull(testBlackMage.getEquippedWeapon());
        testBlackMage.equipWeapon(testAxe);
        assertNull(testBlackMage.getEquippedWeapon());
    }
    /**
     * Chequea que una instancia de la clase BlackMage no se pueda equipar un Bow
     */
    @Test
    void equipBow(){
        assertNull(testBlackMage.getEquippedWeapon());
        testBlackMage.equipWeapon(testBow);
        assertNull(testBlackMage.getEquippedWeapon());
    }
}

