package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.Knight;
import com.github.cc3002.finalreality.model.weapon.Axe;
import com.github.cc3002.finalreality.model.weapon.Knife;
import com.github.cc3002.finalreality.model.weapon.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

    /**
     * Abstract class containing the tests for the Knight.
     *
     */
public class KnightTest {
    private Knight testKnight;
    private Sword testSword;
    private Knife testKnife;
    private Axe testAxe;
    private static final String KNIGHT_NAME = "Adelbert";
    private static final BlockingQueue<com.github.cc3002.finalreality.model.character.ICharacter> turns = null;
    private static final int DAMAGE = 15;
    private static final int WEIGHT = 20;
    private static final String AXE_NAME = "Test Axe";
    private static final String SWORD_NAME = "Test Sword";


    @BeforeEach
    void setUp(){
        testKnight=new Knight(turns,KNIGHT_NAME);
        testSword=new Sword(SWORD_NAME,DAMAGE,WEIGHT);
        testAxe=new Axe(AXE_NAME,DAMAGE,WEIGHT);
        testKnife=new Knife(KNIGHT_NAME,DAMAGE,WEIGHT);
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest(){
        var expectedKnight= new Knight(turns, KNIGHT_NAME);
        assertEquals(expectedKnight, testKnight);
        assertEquals(expectedKnight.hashCode(), testKnight.hashCode());
    }

    /**
     * Chequea que una instancia de la clase Knight se pueda equipar correctamente una Sword
     */
    @Test
    public  void equipSwordTest(){
        assertNull(testKnight.getEquippedWeapon());
        testKnight.equipSword(testSword);
        assertEquals(testSword, testKnight.getEquippedWeapon());
    }

    /**
     * Chequea que una instancia de la clase Knight se pueda equipar correctamente una Axe
     */
    @Test
    public void equipAxeTest() {
        assertNull(testKnight.getEquippedWeapon());
        testKnight.equipAxe(testAxe);
        assertEquals(testAxe, testKnight.getEquippedWeapon());
    }

    /**
     * Chequea que una instancia de la clase Knight se pueda equipar correctamente un Knife
     */
    @Test
    public void equipKnifeTest() {
        assertNull(testKnight.getEquippedWeapon());
        testKnight.equipKnife(testKnife);
        assertEquals(testKnife, testKnight.getEquippedWeapon());
    }
}
