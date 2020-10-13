package com.github.cc3002.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
     * Abstract class containing the tests for the Sword.
     *
     */
public class SwordTest {
    private static final int DAMAGE = 15;
    private static final int WEIGHT = 20;
    private static final String SWORD_NAME = "Test Sword";
    private static final String AXE_NAME = "Test Axe";
    private Sword testSword;
    private Sword testSwordDifferentName;
    private Sword  testSwordDifferentWeight;
    private Sword testSwordDifferentDamage;
    private Axe testAxe;
    @BeforeEach
    void setUp(){
        testSword=new Sword(SWORD_NAME,DAMAGE,WEIGHT);
        testSwordDifferentName = new Sword("arco", DAMAGE, WEIGHT);
        testSwordDifferentDamage = new Sword(SWORD_NAME, DAMAGE+1, WEIGHT);
        testSwordDifferentWeight = new Sword(SWORD_NAME, DAMAGE, WEIGHT+1 );
        testAxe = new Axe(AXE_NAME,DAMAGE,WEIGHT);
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest(){

        var expectedSword = new Sword(SWORD_NAME, DAMAGE, WEIGHT);
        assertEquals(expectedSword, testSword);
        assertEquals(expectedSword, testSword);
        assertEquals(expectedSword.hashCode(),testSword.hashCode());

        var prueba = testSword;
        assertEquals(prueba, testSword);
        assertNotEquals(expectedSword.hashCode(), testAxe.hashCode());
        assertNotEquals(testSword,testAxe);

        assertFalse(testSword.equals(testSwordDifferentName));
        assertFalse(testSword.equals(testSwordDifferentWeight));
        assertFalse(testSword.equals(testSwordDifferentDamage));
    }
}
