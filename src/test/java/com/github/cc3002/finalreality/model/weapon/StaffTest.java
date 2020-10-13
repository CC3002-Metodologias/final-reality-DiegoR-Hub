package com.github.cc3002.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
     * Abstract class containing the tests for the Staff.
     *
     */
public class StaffTest {
    private  static final int MAGIC_DAMAGE=10;
    private static final int DAMAGE = 15;
    private static final int WEIGHT = 20;
    private static final String STAFF_NAME = "Test Staff";
    private Axe testAxe;
    private static final String AXE_NAME = "Test Axe";
    private Staff testStaff;
    private Staff testStaffDifferentName;
    private Staff testStaffDifferentWeight;
    private Staff testStaffDifferentDamage;
    private Staff testStaffDifferentMagicDamage;
    @BeforeEach
    void setUp(){
        testStaff=new Staff(STAFF_NAME,DAMAGE,WEIGHT,MAGIC_DAMAGE);
        testStaffDifferentName = new Staff("arco", DAMAGE, WEIGHT,MAGIC_DAMAGE);
        testStaffDifferentDamage = new Staff(STAFF_NAME, DAMAGE+1, WEIGHT,MAGIC_DAMAGE);
        testStaffDifferentWeight = new Staff(STAFF_NAME, DAMAGE, WEIGHT+1,MAGIC_DAMAGE );
        testStaffDifferentMagicDamage = new Staff(STAFF_NAME,DAMAGE,WEIGHT,MAGIC_DAMAGE+1);
        testAxe = new Axe(AXE_NAME,DAMAGE,WEIGHT);
    }

    /**
     *
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest(){
        var expectedBow = new Staff(STAFF_NAME, DAMAGE, WEIGHT, MAGIC_DAMAGE);
        assertEquals(expectedBow, testStaff);
        assertEquals(expectedBow, testStaff);
        assertEquals(expectedBow.hashCode(),testStaff.hashCode());

        var prueba = testStaff;
        assertEquals(prueba, testStaff);
        assertNotEquals(expectedBow.hashCode(), testAxe.hashCode());
        assertNotEquals(testStaff,testAxe);

        assertFalse(testStaff.equals(testStaffDifferentName));
        assertFalse(testStaff.equals(testStaffDifferentWeight));
        assertFalse(testStaff.equals(testStaffDifferentDamage));
        assertFalse(testStaff.equals(testStaffDifferentMagicDamage));
    }
}
