package com.github.cc3002.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AxeTest {
    private static final int DAMAGE = 15;
    private static final int WEIGHT = 20;
    private static final String AXE_NAME = "Test Axe";

    private Axe testAxe;
    @BeforeEach
    void setUp(){
        testAxe=new Axe(AXE_NAME,DAMAGE,WEIGHT);
    }
    @Test
    void constructorTest(){
        var expectedAxe = new Axe(AXE_NAME, DAMAGE, WEIGHT);
        assertEquals(expectedAxe, testAxe);
        assertEquals(expectedAxe.hashCode(), testAxe.hashCode());
    }

}

