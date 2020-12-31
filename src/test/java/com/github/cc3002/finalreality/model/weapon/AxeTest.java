package com.github.cc3002.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CLase que contiene los test asociados a la clase Axe
 */
public class AxeTest {
    private static final int DAMAGE = 15;
    private static final int WEIGHT = 20;
    private static final String AXE_NAME = "Test Axe";
    private static final String KNIFE_NAME = "Test Knife";

    private Axe testAxe;
    private Axe testAxeDifferentName;
    private Axe testAxeDifferentWeight;
    private Axe testAxeDifferentDamage;
    private Knife testKnife;

    @BeforeEach
    void setUp(){
        testAxe=new Axe(AXE_NAME,DAMAGE,WEIGHT);
        testAxeDifferentName = new Axe("hola", DAMAGE, WEIGHT);
        testAxeDifferentWeight = new Axe(AXE_NAME, DAMAGE, WEIGHT+1);
        testAxeDifferentDamage = new Axe(AXE_NAME, DAMAGE+1, WEIGHT);
        testKnife = new Knife(KNIFE_NAME,DAMAGE,WEIGHT);
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest(){
        var expectedAxe = new Axe(AXE_NAME, DAMAGE, WEIGHT);
        assertEquals(expectedAxe, testAxe);
        assertEquals(expectedAxe.hashCode(), testAxe.hashCode());

        var prueba = testAxe;
        assertEquals(prueba,testAxe);


        assertNotEquals(expectedAxe.hashCode(), testKnife.hashCode());
        assertNotEquals(testAxe,testKnife);

        assertFalse(testAxe.equals(testAxeDifferentName));
        assertFalse(testAxe.equals(testAxeDifferentWeight));
        assertFalse(testAxe.equals(testAxeDifferentDamage));



    }

}

