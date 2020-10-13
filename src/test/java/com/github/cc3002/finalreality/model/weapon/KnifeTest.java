package com.github.cc3002.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
     * Abstract class containing the tests for the Knife.
     *
     */
public class KnifeTest {
    private static final int DAMAGE = 15;
    private static final int WEIGHT = 20;
    private static final String KNIFE_NAME = "Test Knife";
    private Knife testKnife;
    private Knife testKnifeDifferentName;
    private Knife testKnifeDifferentWeight;
    private Knife testKnifeDifferentDamage;
    private Axe testAxe;
    private static final String AXE_NAME = "Test Axe";
    @BeforeEach
    void setUp(){
        testKnife=new Knife(KNIFE_NAME,DAMAGE,WEIGHT);
        testKnifeDifferentName = new Knife("arco", DAMAGE, WEIGHT);
        testKnifeDifferentDamage = new Knife(KNIFE_NAME, DAMAGE+1, WEIGHT);
        testKnifeDifferentWeight = new Knife(KNIFE_NAME, DAMAGE, WEIGHT+1 );
        testAxe = new Axe(AXE_NAME,DAMAGE,WEIGHT);
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest(){
        var expectedKnife = new Knife(KNIFE_NAME, DAMAGE, WEIGHT);
        assertEquals(expectedKnife, testKnife);
        assertEquals(expectedKnife, testKnife);
        assertEquals(expectedKnife.hashCode(),testKnife.hashCode());

        var prueba = testKnife;
        assertEquals(prueba, testKnife);
        assertNotEquals(expectedKnife.hashCode(), testAxe.hashCode());
        assertNotEquals(testKnife,testAxe);

        assertFalse(testKnife.equals(testKnifeDifferentName));
        assertFalse(testKnife.equals(testKnifeDifferentWeight));
        assertFalse(testKnife.equals(testKnifeDifferentDamage));
    }
}
