package com.github.cc3002.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SwordTest {
    private static final int DAMAGE = 15;
    private static final int WEIGHT = 20;
    private static final String SWORD_NAME = "Test Sword";
    private Sword testSword;
    @BeforeEach
    void setUp(){
        testSword=new Sword(SWORD_NAME,DAMAGE,WEIGHT);
    }

    @Test
    void constructorTest(){
        var expectedSword = new Sword(SWORD_NAME, DAMAGE, WEIGHT);
        assertEquals(expectedSword, testSword);
        assertEquals(expectedSword.hashCode(), testSword.hashCode());

    }
}
