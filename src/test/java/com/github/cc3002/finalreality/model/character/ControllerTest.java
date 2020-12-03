package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.*;
import com.github.cc3002.finalreality.model.controller.Controller;
import com.github.cc3002.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;


public class ControllerTest {
    //Queue comun para cada ICharacter
    private LinkedBlockingQueue<ICharacter> turns;
    //Nombre de cada enemy
    private final String ENEMY1_NAME = "Goblin";
    private final String ENEMY2_NAME = "Orco";
    private final String ENEMY3_NAME = "Duende";
    private final String ENEMY4_NAME = "Polilla";
    private final String ENEMY5_NAME = "Zancudo";
    //Atributos comunes a cada character
    private final int DEFENSE_POINTS = 7;
    private final int HEALTH_POINTS = 17;
    //Atributos comunes a cada enemy
    private final int ENEMY_WEIGHT = 11;
    private final int ATTACK_POINTS = 12;
    //Atributos comunes a cada IWeapon
    private final int DAMAGE = 15;
    private final int WEIGHT = 20;
    private final String AXE_NAME = "Test Axe";
    private final String BOW_NAME = "Test Bow";
    private final String KNIFE_NAME = "Test Knife";
    private final String STAFF_NAME = "Test Staff";
    private final String SWORD_NAME = "Test Sword";
    //magic damage para staffs
    private  final int MAGIC_DAMAGE = 10;
    //Nombre de cada PlayerCharacter
    private final String PLAYER_CHARACTER_NAME1 = "Player 1";
    private final String PLAYER_CHARACTER_NAME2 = "Player 2";
    private final String PLAYER_CHARACTER_NAME3 = "Player 3";
    private final String PLAYER_CHARACTER_NAME4 = "Player 4";
    //Mana para magos
    private final int MANA = 15;
    //Enemigos individualmente
    private  Enemy testEnemy1 ;
    private  Enemy testEnemy2 ;
    private  Enemy testEnemy3 ;
    private  Enemy testEnemy4 ;
    private  Enemy testEnemyAboutToDie;
    //PlayerCharacters individualmente
    private  WhiteMage testWhiteMage ;
    private  Thief testThief ;
    private  Knight testKnight ;
    private Engineer testEngineerAboutToDie;
    //IWeapons individualmente
    private Axe testAxe;
    private Bow testBow;
    private Knife testKnife;
    private Staff testStaff;
    private Sword testSword;
    //Controller
    private Controller controller;


    @BeforeEach
    void setUp() {
        turns = new LinkedBlockingQueue<>();
        //Instanciandos los player characters
        testWhiteMage = new WhiteMage(turns, "hola hola", DEFENSE_POINTS, HEALTH_POINTS, MANA);
        testThief = new Thief(turns, "ladron", DEFENSE_POINTS, HEALTH_POINTS);
        testKnight = new Knight(turns, "peso", DEFENSE_POINTS, HEALTH_POINTS);
        //Instanciando los Enemys
        testEnemy1 = new Enemy(turns, ENEMY1_NAME, ENEMY_WEIGHT, DEFENSE_POINTS, HEALTH_POINTS, ATTACK_POINTS);
        testEnemy2 = new Enemy(turns, ENEMY2_NAME, ENEMY_WEIGHT, DEFENSE_POINTS, HEALTH_POINTS, ATTACK_POINTS);
        testEnemy3 = new Enemy(turns, ENEMY3_NAME, ENEMY_WEIGHT, DEFENSE_POINTS, HEALTH_POINTS, ATTACK_POINTS);
        testEnemy4 = new Enemy(turns, ENEMY4_NAME, ENEMY_WEIGHT, DEFENSE_POINTS, HEALTH_POINTS, ATTACK_POINTS);
        testEnemyAboutToDie = new Enemy(turns, ENEMY5_NAME, ENEMY_WEIGHT, DEFENSE_POINTS, 1, ATTACK_POINTS);
        //Instanciando los PlayerCharacters
        testWhiteMage = new WhiteMage(turns, PLAYER_CHARACTER_NAME1, DEFENSE_POINTS, HEALTH_POINTS, MANA);
        testThief = new Thief(turns, PLAYER_CHARACTER_NAME2, DEFENSE_POINTS, HEALTH_POINTS);
        testKnight = new Knight(turns, PLAYER_CHARACTER_NAME3, DEFENSE_POINTS, HEALTH_POINTS);
        testEngineerAboutToDie = new Engineer(turns, PLAYER_CHARACTER_NAME4, DEFENSE_POINTS, 1);
        //Instanciando las IWeapons
        testAxe = new Axe(AXE_NAME, DAMAGE, WEIGHT);
        testBow = new Bow(BOW_NAME, DAMAGE, WEIGHT);
        testKnife = new Knife(KNIFE_NAME, DAMAGE, WEIGHT);
        testStaff = new Staff(STAFF_NAME, DAMAGE, WEIGHT, MAGIC_DAMAGE);
        testSword = new Sword(SWORD_NAME, DAMAGE, WEIGHT);
        //Instanciando el controller
        controller = new Controller(100, 100);
    }

    @Test
    void attackTest(){
        //observer ve que player character mata enemigo
        controller.addEnemy(testEnemy2);
        controller.addEnemy(testEnemyAboutToDie);
        controller.addPlayerCharacter(testKnight);
        controller.addToInventory(testAxe);
        controller.equipFromInventory(testAxe.hashCode(), testKnight);
        controller.attackAnotherCharacter(testKnight, testEnemyAboutToDie);
        //enemy mata player character
        controller.addPlayerCharacter(testEngineerAboutToDie);
        controller.addEnemy(testEnemy1);
        controller.attackAnotherCharacter(testEnemy1,testEngineerAboutToDie);
    }

    @Test
    void addPlayerCharacterWhenFullPartyFullListaEnemy(){
        Controller controllerChico = new Controller(1,1);
        //agregar enemys mas alla de maximo
        controllerChico.addEnemy(testEnemy1);
        controllerChico.addEnemy(testEnemy2);
        assertFalse(controller.checkEnemyInListaEnemy(testEnemy2));
        //agregar player characters mas alla del maximo
        controllerChico.addPlayerCharacter(testKnight);
        controllerChico.addPlayerCharacter(testThief);
        assertFalse(controller.checkPlayerCharacterInParty(testThief));
    }

    @Test
    void playerCharacterWinsTest(){
        controller.addPlayerCharacter(testKnight);
        controller.addToInventory(testAxe);
        controller.equipFromInventory(testAxe.hashCode(), testKnight);
        controller.addEnemy(testEnemyAboutToDie);
        controller.attackAnotherCharacter(testKnight, testEnemyAboutToDie);
    }

    @Test
    void enemyWinsTest(){
        controller.addPlayerCharacter(testEngineerAboutToDie);
        controller.addEnemy(testEnemy1);
        controller.attackAnotherCharacter(testEnemy1,testEngineerAboutToDie);

    }

    @Test
    void removePlayerCharacterTest(){
        controller.addPlayerCharacter(testKnight);
        controller.removePlayerCharacter(testKnight);
        assertTrue(!controller.checkPlayerCharacterInParty(testKnight));

        //Re
    }

    @Test
    void removeEnemyTest(){
        controller.addEnemy(testEnemy1);
        controller.removeEnemy(testEnemy1);
        assertTrue(!controller.checkEnemyInListaEnemy(testEnemy1));
    }

    /**
     * chequea que con funciones create de cada subclase de player character sea posible crearlos y agregalos a la party
     */
    @Test
    void creatorICharacterTest(){
        //crear player characters
        BlackMage blackMage = controller.createBlackMage(this.PLAYER_CHARACTER_NAME1, DEFENSE_POINTS, HEALTH_POINTS, MANA);
        assertTrue(controller.checkPlayerCharacterInParty(blackMage));
        WhiteMage whiteMage = controller.createWhiteMage(this.PLAYER_CHARACTER_NAME1, DEFENSE_POINTS, HEALTH_POINTS, MANA);
        assertTrue(controller.checkPlayerCharacterInParty(whiteMage));
        Engineer engineer = controller.createEngineer(this.PLAYER_CHARACTER_NAME1, DEFENSE_POINTS, HEALTH_POINTS);
        assertTrue(controller.checkPlayerCharacterInParty(engineer));
        Knight knight = controller.createKnight(this.PLAYER_CHARACTER_NAME1, DEFENSE_POINTS, HEALTH_POINTS);
        assertTrue(controller.checkPlayerCharacterInParty(knight));
        Thief thief = controller.createThief(this.PLAYER_CHARACTER_NAME1, DEFENSE_POINTS, HEALTH_POINTS);
        assertTrue(controller.checkPlayerCharacterInParty(thief));
        //crear enemigos
        Enemy enemy = controller.createEnemy(ENEMY1_NAME, WEIGHT, DEFENSE_POINTS, HEALTH_POINTS, ATTACK_POINTS);
        assertTrue(controller.checkEnemyInListaEnemy(enemy));
    }
    @Test
    void creatorIWeaponTest(){
        int axeHash = controller.createAxe(AXE_NAME, DAMAGE, WEIGHT);
        assertTrue(controller.checkWeaponInInventory(axeHash));
        int bowHash = controller.createBow(BOW_NAME, DAMAGE, WEIGHT);
        assertTrue(controller.checkWeaponInInventory(bowHash));
        int knifeHash = controller.createKnife(KNIFE_NAME, DAMAGE, WEIGHT);
        assertTrue(controller.checkWeaponInInventory(knifeHash));
        int swordHash = controller.createSword(SWORD_NAME, DAMAGE, WEIGHT);
        assertTrue(controller.checkWeaponInInventory(swordHash));
        int staffHash = controller.createStaff(STAFF_NAME, DAMAGE, WEIGHT, MAGIC_DAMAGE);
        assertTrue(controller.checkWeaponInInventory(staffHash));
    }
    /**
     *
     */
    @Test
    void inventoryUsageTest(){
        //chequear que inventario parte vacio
        assertTrue(controller.inventoryIsEmpty());
        //chequear que agrega correctamente un axe, un staff y una sword al inventario
        controller.addToInventory(testAxe);
        assertTrue(controller.checkWeaponInInventory(testAxe.hashCode()));
        controller.addToInventory(testSword);
        assertTrue(controller.checkWeaponInInventory(testSword.hashCode()));
        controller.addToInventory(testStaff);
        assertTrue(controller.checkWeaponInInventory(testStaff.hashCode()));

        //agrega un player character para equipar un arma desde el inventario
        controller.addPlayerCharacter(testKnight);

        //chequea equipar incorrectamente
        /**
         * pendiente sgte linea
         */
        //chequea equipar arma en put a un player character que no la puede equipar
        //controller.equipFromInventory(testStaff.hashCode(), testKnight);

        //chequea equipar armas a personajes sin armas
        controller.equipFromInventory(testAxe.hashCode(), testKnight);
        //chequea que el arma equipada ya no este en inventario
        assertTrue(!controller.checkWeaponInInventory(testAxe.hashCode()));
        //chequea que en efecto se haya equipado el arma el player character
        assertEquals(testKnight.getEquippedWeapon(), testAxe);

        //chequea equipar armas a personajes con armas
        controller.equipFromInventory(testSword.hashCode(), testKnight);
        //chequea que arma antigua de player character ahora este en inventario
        assertTrue(controller.checkWeaponInInventory(testAxe.hashCode()));
        //chequea que arma nueva se haya equipado correctamente
        assertEquals(testKnight.getEquippedWeapon(), testSword);
        //chequea que arma nueva ya no este en inventario
        assertTrue(!controller.checkWeaponInInventory(testSword.hashCode()));

        //chequea equipar arma en swap a un player character que no la puede equipar
        controller.equipFromInventory(testStaff.hashCode(), testKnight);
    }


}
