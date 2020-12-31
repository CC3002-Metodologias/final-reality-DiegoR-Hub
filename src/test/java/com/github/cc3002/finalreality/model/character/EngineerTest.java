package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.Engineer;
import com.github.cc3002.finalreality.model.character.player.WhiteMage;
import com.github.cc3002.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.*;


/**
 * CLase que contiene los test asociados a la clase Engineer
 */
class EngineerTest {
    private Engineer testEngineer;
    private Engineer testEngineerDifferentName;
    private Engineer testEngineerDifferentHealthPoints;
    private Engineer testEngineerDifferentDefensePoints;
    private static final String ENGINEER_NAME = "Cid";
    private static final int DAMAGE = 15;
    private static final int WEIGHT = 20;
    private static final int DEFENSE_POINTS = 7;
    private static  final int HEALTH_POINTS = 17;
    private static final String AXE_NAME = "Test Axe";
    private static final String BOW_NAME = "Test Knife";
    private static final String WHITE_MAGE_NAME = "Eiko";
    private Axe testAxe;
    private Bow testBow;
    private WhiteMage testWhiteMage;
    private static final int MANA = 15;
    private static final String KNIFE_NAME = "Test Knife";
    private static final String STAFF_NAME = "Test Staff";
    private static final String SWORD_NAME = "Test Sword";
    private  static final int MAGIC_DAMAGE = 10;
    private Staff testStaff;
    private Knife testKnife;
    private Sword testSword;

    @BeforeEach
    void setUp(){
        testEngineer=new Engineer( ENGINEER_NAME, DEFENSE_POINTS, HEALTH_POINTS);
        testEngineerDifferentDefensePoints = new Engineer( ENGINEER_NAME, DEFENSE_POINTS+1, HEALTH_POINTS);
        testEngineerDifferentHealthPoints = new Engineer( ENGINEER_NAME, DEFENSE_POINTS, HEALTH_POINTS+1);
        testEngineerDifferentName =new Engineer( "hola", DEFENSE_POINTS, HEALTH_POINTS);
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
        var expectedEngineer= new Engineer( ENGINEER_NAME, DEFENSE_POINTS, HEALTH_POINTS);
        var prueba = testEngineer;
        assertEquals(expectedEngineer, testEngineer);
        assertEquals(expectedEngineer.hashCode(), testEngineer.hashCode());
        assertNotEquals(testEngineer,testWhiteMage);
        assertEquals(prueba,testEngineer);
        assertNotEquals(testEngineer, testEngineerDifferentDefensePoints);
        assertNotEquals(testEngineer, testEngineerDifferentHealthPoints);
        assertNotEquals(testEngineer, testEngineerDifferentName);
    }

    /**
     * Chequea que una instancia de la clase Engineer se pueda equipar correctamente una Axe
     */
    @Test
    public void equipAxeTest() {
        assertNull(testEngineer.getEquippedWeapon());
        testEngineer.equipWeapon(testAxe);
        assertEquals(testAxe, testEngineer.getEquippedWeapon());
    }

    /**
     * Chequea que una instancia de la clase Engineer se pueda equipar correctamente un Knife
     */
    @Test
    public void equipBowTest() {
        assertNull(testEngineer.getEquippedWeapon());
        testEngineer.equipWeapon(testBow);
        assertEquals(testBow, testEngineer.getEquippedWeapon());
    }

    /**
     * Chequea que una instancia de la clase Engineer no se pueda equipar un Sword
     */
    @Test
    public void equipSword(){
        assertNull(testEngineer.getEquippedWeapon());
        testEngineer.equipWeapon(testSword);
        assertNull(testEngineer.getEquippedWeapon());
    }
    /**
     * Chequea que una instancia de la clase Engineer no se pueda equipar un Knife
     */
    @Test
    void equipKnifeTest(){
        assertNull(testEngineer.getEquippedWeapon());
        testEngineer.equipWeapon(testKnife);
        assertNull(testEngineer.getEquippedWeapon());
    }
    /**
     * Chequea que una instancia de la clase Engineer no se pueda equipar un Staff
     */
    @Test
    void equipStaffTest(){
        assertNull(testEngineer.getEquippedWeapon());
        testEngineer.equipWeapon(testStaff);
        assertNull(testEngineer.getEquippedWeapon());
    }
}