package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.Enemy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EnemyTest {
    private static final String ENEMY_NAME = "Goblin";
    private static final int ENEMY_WEIGHT = 10;
    protected BlockingQueue<com.github.cc3002.finalreality.model.character.ICharacter> turns;
    private Enemy testEnemy;
    @BeforeEach
    void setUp(){
        testEnemy=new Enemy(turns, ENEMY_NAME, ENEMY_WEIGHT);
    }
    @Test
    void constructorTest(){
        var expectedEnemy= new Enemy(turns, ENEMY_NAME, ENEMY_WEIGHT);
        assertEquals(expectedEnemy, testEnemy);
        assertEquals(expectedEnemy.hashCode(), testEnemy.hashCode());
    }

}
