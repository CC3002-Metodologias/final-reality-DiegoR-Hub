package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.Enemy;
import com.github.cc3002.finalreality.model.character.player.Engineer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
     * Abstract class containing the tests for the Enemy.
     *
     */
public class EnemyTest {
    private static final String ENEMY_NAME = "Goblin";
    private static final int ENEMY_WEIGHT = 11;
    protected BlockingQueue<com.github.cc3002.finalreality.model.character.ICharacter> turns;
    private Enemy testEnemy;
    private Enemy testEnemy1;
    private Enemy testEnemy2;
    private Enemy testEnemy3;
    private Enemy testEnemy4;
    protected List<Enemy> listaEnemigos;
    private Engineer testEngineer;
    private static final String ENGINEER_NAME = "Cid";
    private Enemy testEnemyDifferentName;
    private Enemy testEnemyDifferentWeight;
    @BeforeEach
    void setUp(){
        testEnemy=new Enemy(turns, ENEMY_NAME, ENEMY_WEIGHT);
        testEnemy1 = new Enemy(turns, "bla", ENEMY_WEIGHT);
        testEnemy2 = new Enemy(turns, "bla", 15);
        testEnemy3 = new Enemy(turns, "hola", 10);
        testEnemy4 = new Enemy(turns, "chao", 20);
        listaEnemigos = new ArrayList<>();
        turns = new LinkedBlockingQueue<>();
        listaEnemigos.add(testEnemy);
        listaEnemigos.add(testEnemy1);
        listaEnemigos.add(testEnemy2);
        listaEnemigos.add(testEnemy3);
        listaEnemigos.add(testEnemy4);
        testEngineer = new Engineer(turns,ENGINEER_NAME);
        testEnemyDifferentName = new Enemy(turns,"hola", ENEMY_WEIGHT);
        testEnemyDifferentWeight = new Enemy(turns, ENEMY_NAME, ENEMY_WEIGHT+1 );
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */

    @Test
    void constructorTest(){
        var expectedEnemy = new Enemy(turns,ENEMY_NAME,ENEMY_WEIGHT);
        assertEquals(expectedEnemy, testEnemy);
        assertEquals(expectedEnemy.hashCode(),testEnemy.hashCode());

        var prueba = testEnemy;
        assertEquals(prueba, testEnemy);

        assertNotEquals(expectedEnemy.hashCode(), testEngineer.hashCode());
        assertNotEquals(testEnemy,testEngineer);

        assertFalse(testEnemy.equals(testEnemyDifferentName));
        assertFalse(testEnemy.equals(testEnemyDifferentWeight));
    }
    /**
     * Checks that the character waits the appropriate amount of time for it's turn.
     */
    @Test
    void waitTurnTest() {
        Assertions.assertTrue(turns.isEmpty());
        listaEnemigos.get(0).waitTurn();
        try {
            // Thread.sleep is not accurate so this values may be changed to adjust the
            // acceptable error margin.
            // We're testing that the character waits approximately 1 second.
            Thread.sleep(100);
            Assertions.assertEquals(0, turns.size());
            Thread.sleep(3000);
            Assertions.assertEquals(1, turns.size());
            Assertions.assertEquals(listaEnemigos.get(0), turns.peek());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
