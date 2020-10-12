package com.github.cc3002.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

    /**
     * Abstract class containing the tests for the Staff.
     *
     */
public class StaffTest {
    private  static final int MAGIC_DAMAGE=10;
    private static final int DAMAGE = 15;
    private static final int WEIGHT = 20;
    private static final String STAFF_NAME = "Test Staff";
    private static final String AXE_NAME = "Test Axe";

    private Staff testStaff;
    @BeforeEach
    void setUp(){
        testStaff=new Staff(STAFF_NAME,DAMAGE,WEIGHT,MAGIC_DAMAGE);
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest(){
        var expectedStaff = new Staff(STAFF_NAME, DAMAGE, WEIGHT, MAGIC_DAMAGE);
        Axe testAxe = new Axe(AXE_NAME,DAMAGE,WEIGHT);
        assertEquals(expectedStaff, testStaff);
        assertEquals(expectedStaff.hashCode(), testStaff.hashCode());
        assertNotEquals(expectedStaff.hashCode(), testAxe.hashCode());
    }
}
