package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.Enemy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

    /**
     * Abstract class containing the tests for the Enemy.
     *
     */
public class EnemyTest {
    private static final String ENEMY_NAME = "Goblin";
    private static final int ENEMY_WEIGHT = 10;
    protected BlockingQueue<com.github.cc3002.finalreality.model.character.ICharacter> turns;
    private Enemy testEnemy;
    @BeforeEach
    void setUp(){
        testEnemy=new Enemy(turns, ENEMY_NAME, ENEMY_WEIGHT);
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */

    @Test
    void constructorTest(){
        var expectedEnemy= new Enemy(turns, ENEMY_NAME, ENEMY_WEIGHT);
        assertEquals(expectedEnemy, testEnemy);
        assertEquals(expectedEnemy.hashCode(), testEnemy.hashCode());
    }
    /**
     * Checks that the character waits the appropriate amount of time for it's turn.
     */
    @Test
    void waitTurnTest() {
        Assertions.assertTrue(turns.isEmpty());
        testEnemy.waitTurn();
        try {
            // Thread.sleep is not accurate so this values may be changed to adjust the
            // acceptable error margin.
            // We're testing that the character waits approximately 1 second.
            Thread.sleep(900);
            Assertions.assertEquals(0, turns.size());
            Thread.sleep(200);
            Assertions.assertEquals(1, turns.size());
            Assertions.assertEquals(testEnemy, turns.peek());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
