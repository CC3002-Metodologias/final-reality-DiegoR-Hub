package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.*;
import com.github.cc3002.finalreality.model.weapon.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PlayerCharacterTest {
    protected BlockingQueue<ICharacter> turns;
    private Engineer testEngineer;
    private WhiteMage testWhiteMage;
    private BlackMage testBlackMage;
    private Thief testThief;
    private Knight testKnight;
    private List<PlayerCharacter> listaPlayerCharacter;
    private Axe testAxe;
    private Sword testSword;
    private Staff testStaff;
    private Knife testKnife;
    private Bow testBow;

    @BeforeEach
    void setUp(){
        turns = new LinkedBlockingQueue<>();
        listaPlayerCharacter = new ArrayList<PlayerCharacter>();
        testBlackMage = new BlackMage(turns, "cosa",10);
        testEngineer = new Engineer(turns, "casa");
        testKnight = new Knight(turns, "peso");
        testThief = new Thief(turns, "ladronql");
        testWhiteMage = new WhiteMage(turns, "hola hola", 20);
        listaPlayerCharacter.add(testBlackMage);
        listaPlayerCharacter.add(testWhiteMage);
        listaPlayerCharacter.add(testThief);
        listaPlayerCharacter.add(testKnight);
        listaPlayerCharacter.add(testEngineer);
        testAxe=new Axe("prueba", 15, 10);
        testBow = new Bow("caco", 20, 13);
        testKnife = new Knife("hola", 20, 12);
        testSword = new Sword("otaku", 14, 18);
        testStaff = new Staff("jiji", 15, 16, 34);
        testWhiteMage.equipStaff(testStaff);
        testThief.equipBow(testBow);
        testKnight.equipAxe(testAxe);
        testEngineer.equipAxe(testAxe);
        testBlackMage.equipKnife(testKnife);


    }
    @Test
    void waitTurnTest() {
        Assertions.assertTrue(turns.isEmpty());
        listaPlayerCharacter.get(0).waitTurn();
        try {
            // Thread.sleep is not accurate so this values may be changed to adjust the
            // acceptable error margin.
            // We're testing that the character waits approximately 1 second.
            Thread.sleep(900);
            Assertions.assertEquals(0, turns.size());
            Thread.sleep(200);
            Assertions.assertEquals(1, turns.size());
            Assertions.assertEquals(listaPlayerCharacter.get(0), turns.peek());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
