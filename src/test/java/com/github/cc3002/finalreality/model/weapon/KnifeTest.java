package com.github.cc3002.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnifeTest {
    private static final int DAMAGE = 15;
    private static final int WEIGHT = 20;
    private static final String KNIFE_NAME = "Test Knife";
    private Knife testKnife;
    @BeforeEach
    void setUp(){
        testKnife=new Knife(KNIFE_NAME,DAMAGE,WEIGHT);
    }

    @Test
    void constructorTest(){
        var expectedKnife = new Knife(KNIFE_NAME, DAMAGE, WEIGHT);
        assertEquals(expectedKnife, testKnife);
        assertEquals(expectedKnife.hashCode(), testKnife.hashCode());
    }
}
