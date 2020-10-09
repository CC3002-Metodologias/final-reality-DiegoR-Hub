package com.github.cc3002.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StaffTest {
    private  static final int MAGIC_DAMAGE=10;
    private static final int DAMAGE = 15;
    private static final int WEIGHT = 20;
    private static final String STAFF_NAME = "Test Staff";

    private Staff testStaff;
    @BeforeEach
    void setUp(){
        testStaff=new Staff(STAFF_NAME,DAMAGE,WEIGHT,MAGIC_DAMAGE);
    }

    @Test
    void constructorTest(){
        var expectedStaff = new Staff(STAFF_NAME, DAMAGE, WEIGHT, MAGIC_DAMAGE);
        assertEquals(expectedStaff, testStaff);
        assertEquals(expectedStaff.hashCode(), testStaff.hashCode());
    }
}
