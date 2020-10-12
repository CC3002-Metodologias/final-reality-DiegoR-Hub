package com.github.cc3002.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

    /**
     * Abstract class containing the tests for the Axe.
     *
     */
public class AxeTest {
    private static final int DAMAGE = 15;
    private static final int WEIGHT = 20;
    private static final String AXE_NAME = "Test Axe";
    private static final String KNIFE_NAME = "Test Knife";

    private Axe testAxe;

    @BeforeEach
    void setUp(){
        testAxe=new Axe(AXE_NAME,DAMAGE,WEIGHT);
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest(){
        var expectedAxe = new Axe(AXE_NAME, DAMAGE, WEIGHT);
        Knife testKnife = new Knife(KNIFE_NAME,DAMAGE,WEIGHT);
        assertEquals(expectedAxe, testAxe);
        assertEquals(expectedAxe.hashCode(), testAxe.hashCode());
        assertNotEquals(expectedAxe.hashCode(), testKnife.hashCode());
    }

}

