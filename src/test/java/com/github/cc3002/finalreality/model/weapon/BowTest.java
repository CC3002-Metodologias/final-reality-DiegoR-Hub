package com.github.cc3002.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowTest {
    private static final int DAMAGE = 15;
    private static final int WEIGHT = 20;
    private static final String BOW_NAME = "Test Bow";
    private Bow testBow;
    @BeforeEach
    void setUp(){
        testBow=new Bow(BOW_NAME,DAMAGE,WEIGHT);
    }

    @Test
    void constructorTest(){
        var expectedBow = new Bow(BOW_NAME, DAMAGE, WEIGHT);
        assertEquals(expectedBow, testBow);
        assertEquals(expectedBow.hashCode(), testBow.hashCode());
    }
}
