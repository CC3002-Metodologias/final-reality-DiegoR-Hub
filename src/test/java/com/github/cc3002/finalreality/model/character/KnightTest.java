package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.Knight;
import com.github.cc3002.finalreality.model.character.player.WhiteMage;
import com.github.cc3002.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
     * Abstract class containing the tests for the Knight.
     *
     */
public class KnightTest {
    private Knight testKnight;
    private  Knight testKnightDifferentName;
    private  Knight testKnightDifferentHealthPoints;
    private  Knight testKnightDifferentDefensePoints;
    private Sword testSword;
    private Knife testKnife;
    private Axe testAxe;
    private WhiteMage testWhiteMage;
    private static final String WHITE_MAGE_NAME = "Eiko";
    private static final String KNIGHT_NAME = "Adelbert";
    private static final BlockingQueue<com.github.cc3002.finalreality.model.character.ICharacter> turns = null;
    private static final int DAMAGE = 15;
    private static final int WEIGHT = 20;
    private static final int DEFENSE_POINTS = 7;
    private static  final int HEALTH_POINTS = 17;
    private static final String AXE_NAME = "Test Axe";
    private static final String SWORD_NAME = "Test Sword";
    private static final int MANA = 15;
    private static final String KNIFE_NAME = "Test Knife";
    private static final String STAFF_NAME = "Test Staff";
    private static final String BOW_NAME = "Test Bow";
    private  static final int MAGIC_DAMAGE = 10;
    private Staff testStaff;
    private Bow testBow;

    @BeforeEach
    void setUp(){
        testKnight = new Knight(turns,KNIGHT_NAME, DEFENSE_POINTS, HEALTH_POINTS);
        testKnightDifferentDefensePoints = new Knight(turns, KNIGHT_NAME, DEFENSE_POINTS+1, HEALTH_POINTS);
        testKnightDifferentHealthPoints = new Knight(turns, KNIGHT_NAME, DEFENSE_POINTS, HEALTH_POINTS+1);
        testKnightDifferentName = new Knight(turns, "hola", DEFENSE_POINTS, HEALTH_POINTS);
        testKnife = new Knife(KNIFE_NAME, DAMAGE,WEIGHT);
        testStaff = new Staff(STAFF_NAME,DAMAGE,WEIGHT,MAGIC_DAMAGE);
        testAxe = new Axe(AXE_NAME, DAMAGE,WEIGHT);
        testSword = new Sword(SWORD_NAME, DAMAGE, WEIGHT);
        testBow = new Bow(BOW_NAME, DAMAGE, WEIGHT);
        testWhiteMage = new WhiteMage(turns,WHITE_MAGE_NAME, DEFENSE_POINTS, HEALTH_POINTS, MANA);
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest(){
        var expectedKnight= new Knight(turns, KNIGHT_NAME, DEFENSE_POINTS, HEALTH_POINTS);
        var prueba = testKnight;
        assertEquals(expectedKnight, testKnight);
        assertEquals(expectedKnight.hashCode(), testKnight.hashCode());
        assertNotEquals(testKnight,testWhiteMage);
        assertNotEquals(testKnight, testKnightDifferentDefensePoints);
        assertNotEquals(testKnight, testKnightDifferentHealthPoints);
        assertNotEquals(testKnight, testKnightDifferentName);
        assertEquals(prueba,testKnight);
    }

    /**
     * Chequea que una instancia de la clase Knight se pueda equipar correctamente una Sword
     */
    @Test
    public  void equipSwordTest(){
        assertNull(testKnight.getEquippedWeapon());
        testKnight.equipWeapon(testSword);
        assertEquals(testSword, testKnight.getEquippedWeapon());
    }

    /**
     * Chequea que una instancia de la clase Knight se pueda equipar correctamente una Axe
     */
    @Test
    public void equipAxeTest() {
        assertNull(testKnight.getEquippedWeapon());
        testKnight.equipWeapon(testAxe);
        assertEquals(testAxe, testKnight.getEquippedWeapon());
    }

    /**
     * Chequea que una instancia de la clase Knight se pueda equipar correctamente un Knife
     */
    @Test
    public void equipKnifeTest() {
        assertNull(testKnight.getEquippedWeapon());
        testKnight.equipWeapon(testKnife);
        assertEquals(testKnife, testKnight.getEquippedWeapon());
    }
    /**
     * Chequea que una instancia de la clase Knight no se pueda equipar un Bow
     */
    @Test
    void equipBowTest(){
        assertNull(testKnight.getEquippedWeapon());
        testKnight.equipWeapon(testBow);
        assertNull(testKnight.getEquippedWeapon());
    }
    /**
     * Chequea que una instancia de la clase Knight no se pueda equipar un Staff
     */
    @Test
    void equipStaffTest(){
        assertNull(testKnight.getEquippedWeapon());
        testKnight.equipWeapon(testStaff);
        assertNull(testKnight.getEquippedWeapon());
    }
}
