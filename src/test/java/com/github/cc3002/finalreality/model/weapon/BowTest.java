package com.github.cc3002.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * CLase que contiene los test asociados a la clase Bow
 */
public class BowTest {
    private static final int DAMAGE = 15;
    private static final int WEIGHT = 20;
    private static final String BOW_NAME = "Test Bow";

    private Bow testBow;
    private Bow testBowDifferentName;
    private Bow testBowDifferentWeight;
    private Bow testBowDifferentDamage;
    private Axe testAxe;
    private static final String AXE_NAME = "Test Axe";
    @BeforeEach
    void setUp(){
        testBow=new Bow(BOW_NAME,DAMAGE,WEIGHT);
        testBowDifferentName = new Bow("arco", DAMAGE, WEIGHT);
        testBowDifferentDamage = new Bow(BOW_NAME, DAMAGE+1, WEIGHT);
        testBowDifferentWeight = new Bow(BOW_NAME, DAMAGE, WEIGHT+1 );
        testAxe = new Axe(AXE_NAME,DAMAGE,WEIGHT);
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest(){
        var expectedBow = new Bow(BOW_NAME, DAMAGE, WEIGHT);
        assertEquals(expectedBow, testBow);
        assertEquals(expectedBow, testBow);
        assertEquals(expectedBow.hashCode(),testBow.hashCode());

        var prueba = testBow;
        assertEquals(prueba, testBow);
        assertNotEquals(expectedBow.hashCode(), testAxe.hashCode());
        assertNotEquals(testBow,testAxe);

        assertFalse(testBow.equals(testBowDifferentName));
        assertFalse(testBow.equals(testBowDifferentWeight));
        assertFalse(testBow.equals(testBowDifferentDamage));
    }
}
