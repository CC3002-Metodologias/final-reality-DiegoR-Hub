package com.github.cc3002.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

    /**
     * Abstract class containing the tests for the Bow
     *
     */
public class BowTest {
    private static final int DAMAGE = 15;
    private static final int WEIGHT = 20;
    private static final String BOW_NAME = "Test Bow";
    private static final String AXE_NAME = "Test Axe";
    private Bow testBow;
    @BeforeEach
    void setUp(){
        testBow=new Bow(BOW_NAME,DAMAGE,WEIGHT);
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest(){
        var expectedBow = new Bow(BOW_NAME, DAMAGE, WEIGHT);
        Axe testAxe = new Axe(AXE_NAME,DAMAGE,WEIGHT);
        assertEquals(expectedBow, testBow);
        assertEquals(expectedBow.hashCode(), testBow.hashCode());
        assertNotEquals(expectedBow.hashCode(), testAxe.hashCode());
    }
}
