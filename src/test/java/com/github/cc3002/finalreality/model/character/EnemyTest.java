package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.Enemy;
import com.github.cc3002.finalreality.model.character.player.Engineer;
import com.github.cc3002.finalreality.model.weapon.*;
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
    private static final int DEFENSE_POINTS = 7;
    private static  final int HEALTH_POINTS = 17;
    private static final int ATTACK_POINTS = 12;
    private static BlockingQueue<com.github.cc3002.finalreality.model.character.ICharacter> turns;
    private Enemy testEnemyDead;
    private Engineer testEngineerDead;
    private Engineer testEngineerAboutToDie;
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
    private Enemy testEnemyDifferentAttackPoints;
    private Enemy testEnemyDifferentHealthPoints;
    private Enemy testEnemyDifferentDefensePoints;
    private static final String KNIFE_NAME = "Test Knife";
    private static final String STAFF_NAME = "Test Staff";
    private static final String SWORD_NAME = "Test Sword";
    private static final String AXE_NAME = "Test Axe";
    private static final String BOW_NAME = "Test Bow";
    private static final int DAMAGE = 15;
    private static final int WEIGHT = 20;
    private  static final int MAGIC_DAMAGE = 10;
    private Staff testStaff;
    private Knife testKnife;
    private Axe testAxe;
    private Sword testSword;
    private Bow testBow;
    @BeforeEach
    void setUp(){
        testEnemyDead = new Enemy(turns, ENEMY_NAME, ENEMY_WEIGHT, DEFENSE_POINTS, 0, ATTACK_POINTS);
        testEngineerDead = new Engineer(turns, ENGINEER_NAME, DEFENSE_POINTS, 0);
        testEngineerAboutToDie = new Engineer(turns, ENGINEER_NAME, DEFENSE_POINTS, ATTACK_POINTS - (DEFENSE_POINTS + 1));
        testEnemy=new Enemy(turns, ENEMY_NAME, ENEMY_WEIGHT, DEFENSE_POINTS, HEALTH_POINTS, ATTACK_POINTS);
        testEnemy1 = new Enemy(turns, "bla", 16, DEFENSE_POINTS, HEALTH_POINTS, ATTACK_POINTS);
        testEnemy2 = new Enemy(turns, "bla", 13, DEFENSE_POINTS, HEALTH_POINTS, ATTACK_POINTS);
        testEnemy3 = new Enemy(turns, "hola", 10,  DEFENSE_POINTS, HEALTH_POINTS, ATTACK_POINTS);
        testEnemy4 = new Enemy(turns, "chao", 10,  DEFENSE_POINTS, HEALTH_POINTS, ATTACK_POINTS);
        listaEnemigos = new ArrayList<Enemy>();
        turns = new LinkedBlockingQueue<>();
        listaEnemigos.add(testEnemy);
        listaEnemigos.add(testEnemy1);
        listaEnemigos.add(testEnemy2);
        listaEnemigos.add(testEnemy3);
        listaEnemigos.add(testEnemy4);
        testEngineer = new Engineer(turns,ENGINEER_NAME, DEFENSE_POINTS, HEALTH_POINTS);
        testEnemyDifferentName = new Enemy(turns,"hola", ENEMY_WEIGHT, DEFENSE_POINTS, HEALTH_POINTS, ATTACK_POINTS);
        testEnemyDifferentWeight = new Enemy(turns, ENEMY_NAME, ENEMY_WEIGHT+1 , DEFENSE_POINTS, HEALTH_POINTS, ATTACK_POINTS);
        testEnemyDifferentAttackPoints = new Enemy(turns, ENEMY_NAME, ENEMY_WEIGHT, DEFENSE_POINTS, HEALTH_POINTS, ATTACK_POINTS+1);
        testEnemyDifferentDefensePoints = new Enemy(turns, ENEMY_NAME, ENEMY_WEIGHT, DEFENSE_POINTS+1, HEALTH_POINTS, ATTACK_POINTS);
        testEnemyDifferentHealthPoints = new Enemy(turns, ENEMY_NAME, ENEMY_WEIGHT, DEFENSE_POINTS, HEALTH_POINTS+1, ATTACK_POINTS);
        testKnife = new Knife(KNIFE_NAME, DAMAGE,WEIGHT);
        testStaff = new Staff(STAFF_NAME,DAMAGE,WEIGHT,MAGIC_DAMAGE);
        testAxe = new Axe(AXE_NAME, DAMAGE,WEIGHT);
        testSword = new Sword(SWORD_NAME, DAMAGE, WEIGHT);
        testBow = new Bow(BOW_NAME, DAMAGE, WEIGHT);
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */

    @Test
    void constructorTest(){
        var expectedEnemy = new Enemy(turns,ENEMY_NAME,ENEMY_WEIGHT, DEFENSE_POINTS, HEALTH_POINTS, ATTACK_POINTS);
        assertEquals(expectedEnemy, testEnemy);
        assertEquals(expectedEnemy.hashCode(),testEnemy.hashCode());

        var prueba = testEnemy;
        assertEquals(prueba, testEnemy);

        assertNotEquals(expectedEnemy.hashCode(), testEngineer.hashCode());
        assertNotEquals(testEnemy,testEngineer);

        assertFalse(testEnemy.equals(testEnemyDifferentName));
        assertFalse(testEnemy.equals(testEnemyDifferentWeight));
        assertFalse(testEnemy.equals(testEnemyDifferentDefensePoints));
        assertFalse(testEnemy.equals(testEnemyDifferentAttackPoints));
        assertFalse(testEnemy.equals(testEnemyDifferentHealthPoints));
    }
    /***
     * Chequea que un enemigo no pueda atacar a otro enemigo y que si pueda atacar a un player character
     */
    @Test
    void attackTest() {

        testEnemy.attack(testEngineer);
        assertTrue(testEngineer.getHealthPoints() == HEALTH_POINTS - (ATTACK_POINTS -  DEFENSE_POINTS));

        assertTrue(testEngineerDead.isDead());
        testEnemy.attack(testEngineerDead);
        assertTrue(testEngineerDead.getHealthPoints() == 0);

        assertFalse(testEngineerAboutToDie.isDead());
        testEnemy.attack(testEngineerAboutToDie);
        assertTrue(testEngineerAboutToDie.getHealthPoints() == 0);
        assertTrue(testEngineerAboutToDie.isDead());
    }

    /**
     * Checks that the character waits the appropriate amount of time for it's turn.
     */
    @Test
    void waitTurnTest() {
        Assertions.assertTrue(turns.isEmpty());
        Enemy prueba = new Enemy(turns, "hola", 16, 7, 17, 12 );
        prueba.waitTurn();
        try {
            // Thread.sleep is not accurate so this values may be changed to adjust the
            // acceptable error margin.
            // We're testing that the character waits approximately 1 second.
            Thread.sleep(900);
            Assertions.assertEquals(0, turns.size());
            Thread.sleep(200);
            Assertions.assertEquals(1, turns.size());
            Assertions.assertEquals(prueba, turns.peek());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
