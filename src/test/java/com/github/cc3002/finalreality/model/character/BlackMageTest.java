package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.BlackMage;
import com.github.cc3002.finalreality.model.weapon.Knife;
import com.github.cc3002.finalreality.model.weapon.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BlackMageTest {
    private BlackMage testBlackMage;
    private static final int MANA = 15;
    private static final int DAMAGE = 15;
    private static final int WEIGHT = 20;
    private  static final int MAGIC_DAMAGE=10;
    private static final String BLACK_MAGE_NAME = "Vivi";
    private static final BlockingQueue<com.github.cc3002.finalreality.model.character.ICharacter> turns = null;
    private static final String KNIFE_NAME = "Test Knife";
    private static final String STAFF_NAME = "Test Staff";
    private Staff testStaff;
    private Knife testKnife;
    @BeforeEach
    void setUp(){
        testBlackMage=new BlackMage(turns,BLACK_MAGE_NAME,MANA);
        testKnife=new Knife(KNIFE_NAME, DAMAGE,WEIGHT);
        testStaff=new Staff(STAFF_NAME,DAMAGE,WEIGHT,MAGIC_DAMAGE);
    }
    @Test
    void constructorTest(){
        var expectedBlackMage = new BlackMage(turns, BLACK_MAGE_NAME, MANA);
        assertEquals(expectedBlackMage, testBlackMage);
        assertEquals(expectedBlackMage.hashCode(), testBlackMage.hashCode());
    }
    @Test
    void equipKnifeTest() {
        assertNull(testBlackMage.getEquippedWeapon());
        testBlackMage.equipKnife(testKnife);
        assertEquals(testKnife, testBlackMage.getEquippedWeapon());
    }
    @Test
    void equipStaffTest(){
        assertNull(testBlackMage.getEquippedWeapon());
        testBlackMage.equipStaff(testStaff);
        assertEquals(testStaff, testBlackMage.getEquippedWeapon());
    }
}

