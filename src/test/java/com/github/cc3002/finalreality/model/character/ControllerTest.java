package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.*;
import com.github.cc3002.finalreality.model.controller.Controller;
import com.github.cc3002.finalreality.model.weapon.*;
import javafx.scene.chart.StackedAreaChart;
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
    private final String PLAYER_CHARACTER_NAME5 = "Player 5";
    private final String PLAYER_CHARACTER_NAME6 = "Player 6";
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
    private BlackMage testBlackMage;
    private Engineer testEngineer;
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
        /**
         * USAR INDICES EN LISTS Y SOLO CREAR INSTANCIAS EN EL CONTROLLER USANDO LOS CREATORS
         */
        turns = new LinkedBlockingQueue<ICharacter>();
        //Instanciandos los player characters
        testWhiteMage = new WhiteMage(turns, PLAYER_CHARACTER_NAME1, DEFENSE_POINTS, HEALTH_POINTS, MANA);
        testThief = new Thief(turns, PLAYER_CHARACTER_NAME2, DEFENSE_POINTS, HEALTH_POINTS);
        testKnight = new Knight(turns, PLAYER_CHARACTER_NAME3, DEFENSE_POINTS, HEALTH_POINTS);
        testEngineer = new Engineer(turns, PLAYER_CHARACTER_NAME4, DEFENSE_POINTS, HEALTH_POINTS);
        testBlackMage = new BlackMage(turns, PLAYER_CHARACTER_NAME5, DEFENSE_POINTS, HEALTH_POINTS, MANA);
        testEngineerAboutToDie = new Engineer(turns, PLAYER_CHARACTER_NAME4, DEFENSE_POINTS, 1);
        //Instanciando los Enemys
        testEnemy1 = new Enemy(turns, ENEMY1_NAME, ENEMY_WEIGHT, DEFENSE_POINTS, HEALTH_POINTS, ATTACK_POINTS);
        testEnemy2 = new Enemy(turns, ENEMY2_NAME, ENEMY_WEIGHT, DEFENSE_POINTS, HEALTH_POINTS, ATTACK_POINTS);
        testEnemy3 = new Enemy(turns, ENEMY3_NAME, ENEMY_WEIGHT, DEFENSE_POINTS, HEALTH_POINTS, ATTACK_POINTS);
        testEnemy4 = new Enemy(turns, ENEMY4_NAME, ENEMY_WEIGHT, DEFENSE_POINTS, HEALTH_POINTS, ATTACK_POINTS);
        testEnemyAboutToDie = new Enemy(turns, ENEMY5_NAME, ENEMY_WEIGHT, DEFENSE_POINTS, 1, ATTACK_POINTS);
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
        controller.createEnemy(testEnemy2.getName(), testEnemy2.getWeight(), testEnemy2.getDefensePoints(), testEnemy2.getHealthPoints(), testEnemy2.getDamage());
        controller.createEnemy(testEnemyAboutToDie.getName(), testEnemyAboutToDie.getWeight(), testEnemyAboutToDie.getDefensePoints(), testEnemyAboutToDie.getHealthPoints(), testEnemyAboutToDie.getDamage());

        controller.createKnight(testKnight.getName(), testKnight.getDefensePoints(), testKnight.getHealthPoints());
        controller.createAxe(testAxe.getName(), testAxe.getDamage(), testAxe.getWeight());
        controller.equipFromInventory(testAxe.getName(), 0);
        controller.attackAnotherCharacter(controller.getPlayerCharacterInParty(0), testEnemyAboutToDie);
        //enemy mata player character
        controller.createEngineer(testEngineerAboutToDie.getName(), testEngineerAboutToDie.getDefensePoints(), testEngineerAboutToDie.getHealthPoints());
        controller.createEnemy(testEnemy1.getName(), testEnemy1.getWeight(), testEnemy1.getDefensePoints(), testEnemy1.getHealthPoints(), testEnemy1.getDamage());
        controller.attackAnotherCharacter(controller.getEnemyCharacterInListaEnemy(2),controller.getPlayerCharacterInParty(1));
    }

    @Test
    void addPlayerCharacterWhenFullPartyFullListaEnemy(){
        Controller controllerChico = new Controller(1,1);
        //agregar enemys mas alla de maximo
        controllerChico.createEnemy(testEnemy1.getName(), testEnemy1.getWeight(), testEnemy1.getDefensePoints(), testEnemy1.getHealthPoints(), testEnemy1.getDamage());
        controllerChico.createEnemy(testEnemy2.getName(), testEnemy2.getWeight(), testEnemy2.getDefensePoints(), testEnemy2.getHealthPoints(), testEnemy2.getDamage());

        assertFalse(controller.getListaEnemy().size() == 2);
        //agregar player characters mas alla del maximo

        controllerChico.createKnight(testKnight.getName(), testKnight.getDefensePoints(), testKnight.getHealthPoints());
        controllerChico.createThief(testThief.getName(), testThief.getDefensePoints(), testThief.getHealthPoints());

        assertFalse(controller.getParty().size() == 2);
    }
    @Test
    void attackRandomPlayerCharacter() {
        controller.createThief(testThief.getName(), testThief.getDefensePoints(), testThief.getHealthPoints());
        controller.createEngineer(testEngineerAboutToDie.getName(), testEngineerAboutToDie.getDefensePoints(), testEngineerAboutToDie.getHealthPoints());
        controller.createEnemy(testEnemy1.getName(), testEnemy1.getWeight(), testEnemy1.getDefensePoints(), testEnemy1.getHealthPoints(), testEnemy1.getDamage());
        controller.attackRandomPlayerCharacter(controller.getEnemyCharacterInListaEnemy(0));
        assertTrue(controller.getPartyMemberHealthPoints(0) != HEALTH_POINTS ||
                controller.getPartyMemberHealthPoints(1) != 1);
        controller.attackRandomPlayerCharacter(testEnemy1);
    }

    @Test
    void beginTurnTest() throws InterruptedException {
        controller.createEnemy(testEnemy1.getName(), testEnemy1.getWeight(), testEnemy1.getDefensePoints(), testEnemy1.getHealthPoints(), testEnemy1.getDamage());
        controller.createEnemy(testEnemy2.getName(), testEnemy2.getWeight(), testEnemy2.getDefensePoints(), testEnemy2.getHealthPoints(), testEnemy2.getDamage());
        controller.createEnemy(testEnemy3.getName(), testEnemy3.getWeight(), testEnemy3.getDefensePoints(), testEnemy3.getHealthPoints(), testEnemy3.getDamage());;

        controller.createKnight(testKnight.getName(), testKnight.getDefensePoints(), testKnight.getHealthPoints());
        controller.createThief(testThief.getName(), testThief.getDefensePoints(), testThief.getHealthPoints());
        controller.createEngineer(testEngineer.getName(), testEngineer.getDefensePoints(), testEngineer.getHealthPoints());

        controller.createStaff(testStaff.getName(), testStaff.getDamage(), testStaff.getWeight(), testStaff.getmagicDamage());
        controller.createSword(testSword.getName(), testSword.getDamage(), testSword.getWeight());
        controller.createAxe(testAxe.getName(), testAxe.getDamage(), testAxe.getWeight());

        controller.equipFromInventory(STAFF_NAME, 1);
        controller.equipFromInventory(SWORD_NAME, 0);
        controller.equipFromInventory(AXE_NAME, 2);
        controller.iniciarQueue();

        for (int i = 0; i < 6; i++){
            controller.beginTurn();
        }
    }


    @Test
    void playerCharacterWinsTest(){
        controller.createKnight(testKnight.getName(), testKnight.getDefensePoints(), testKnight.getHealthPoints());
        controller.createAxe(testAxe.getName(), testAxe.getDamage(), testAxe.getWeight());
        controller.equipFromInventory(testAxe.getName(), 0);
        controller.createEnemy(testEnemyAboutToDie.getName(), testEnemyAboutToDie.getWeight(), testEnemyAboutToDie.getDefensePoints(), testEnemyAboutToDie.getHealthPoints(), testEnemyAboutToDie.getDamage());
        controller.attackAnotherCharacter(controller.getPlayerCharacterInParty(0), controller.getEnemyCharacterInListaEnemy(0));
    }

    @Test
    void characterGettersTest(){
        controller.createEnemy(testEnemy1.getName(), testEnemy1.getWeight(), testEnemy1.getDefensePoints(), testEnemy1.getHealthPoints(), testEnemy1.getDamage());
        assertEquals(testEnemy1.getDamage(),controller.getListaEnemyMemberAttackPoints(0));
        assertEquals(testEnemy1.getWeight(),controller.getListaEnemyMemberWeight(0));
        assertEquals(testEnemy1.getDefensePoints(), controller.getListaEnemyMemberDefensePoints(0));
        assertEquals(testEnemy1.getHealthPoints(), controller.getListaEnemyMemberHealthPoints(0));
        assertEquals(testEnemy1.getName(), controller.getListaEnemyMemberName(0));

        controller.createThief(testThief.getName(), testThief.getDefensePoints(), testThief.getHealthPoints());
        controller.createStaff(testStaff.getName(), testStaff.getDamage(), testStaff.getWeight(), testStaff.getmagicDamage());

        controller.equipFromInventory(testStaff.getName(), 0);
        assertEquals(testThief.getDefensePoints(),controller.getPartyMemberDefensePoints(0));
        assertEquals(testStaff,controller.getPartyMemberEquippedWeapon(0));
        assertEquals(testThief.getHealthPoints(), controller.getPartyMemberHealthPoints(0));
        assertEquals(testThief.getName(), controller.getPartyMemberName(0));
    }

    @Test
    void enemyWinsTest(){
        controller.createEngineer(testEngineerAboutToDie.getName(), testEngineerAboutToDie.getDefensePoints(), testEngineerAboutToDie.getHealthPoints());
        controller.createEnemy(testEnemy1.getName(), testEnemy1.getWeight(), testEnemy1.getDefensePoints(), testEnemy1.getHealthPoints(), testEnemy1.getDamage());
        controller.attackAnotherCharacter(controller.getEnemyCharacterInListaEnemy(0),controller.getPlayerCharacterInParty(0));

    }

    @Test
    void removePlayerCharacterTest(){
        controller.createKnight(testKnight.getName(), testKnight.getDefensePoints(), testKnight.getHealthPoints());
        controller.removePlayerCharacter(0);
        assertTrue(controller.checkPartyIsEmpty());

        //Re
    }

    @Test
    void removeEnemyTest(){
        controller.createEnemy(testEnemy1.getName(), testEnemy1.getWeight(), testEnemy1.getDefensePoints(), testEnemy1. getHealthPoints(), testEnemy1. getDamage());
        controller.removeEnemy(0);
        assertTrue(controller.checkListaEnemyIsEmpty());
    }

    @Test
    void attackRandomPlayerCharacterTest(){

    }
    /**
     * chequea que con funciones create de cada subclase de player character sea posible crearlos y agregalos a la party
     */
    @Test
    void creatorICharacterTest(){
        //crear player characters
        controller.createBlackMage(this.PLAYER_CHARACTER_NAME5, DEFENSE_POINTS, HEALTH_POINTS, MANA);
        assertEquals(controller.getPlayerCharacterInParty(0), testBlackMage);
        controller.createWhiteMage(this.PLAYER_CHARACTER_NAME1, DEFENSE_POINTS, HEALTH_POINTS, MANA);
        assertEquals(controller.getPlayerCharacterInParty(1), testWhiteMage);
        controller.createEngineer(this.PLAYER_CHARACTER_NAME4, DEFENSE_POINTS, HEALTH_POINTS);
        assertEquals(controller.getPlayerCharacterInParty(2), testEngineer);
        controller.createKnight(this.PLAYER_CHARACTER_NAME3, DEFENSE_POINTS, HEALTH_POINTS);
        assertEquals(controller.getPlayerCharacterInParty(3), testKnight);
        controller.createThief(this.PLAYER_CHARACTER_NAME2, DEFENSE_POINTS, HEALTH_POINTS);
        assertEquals(controller.getPlayerCharacterInParty(4), testThief);
        //crear enemigos
        controller.createEnemy(testEnemy1.getName(), testEnemy1.getWeight(), testEnemy1.getDefensePoints(), testEnemy1.getHealthPoints(), testEnemy1.getDamage());
        assertEquals(controller.getEnemyCharacterInListaEnemy(0), testEnemy1);

    }
    @Test
    void creatorIWeaponTest(){
        controller.createAxe(AXE_NAME, DAMAGE, WEIGHT);
        assertTrue(controller.checkWeaponInInventory(AXE_NAME));
        controller.createBow(BOW_NAME, DAMAGE, WEIGHT);
        assertTrue(controller.checkWeaponInInventory(BOW_NAME));
        controller.createKnife(KNIFE_NAME, DAMAGE, WEIGHT);
        assertTrue(controller.checkWeaponInInventory(KNIFE_NAME));
        controller.createSword(SWORD_NAME, DAMAGE, WEIGHT);
        assertTrue(controller.checkWeaponInInventory(SWORD_NAME));
        controller.createStaff(STAFF_NAME, DAMAGE, WEIGHT, MAGIC_DAMAGE);
        assertTrue(controller.checkWeaponInInventory(STAFF_NAME));
    }
    /**
     *
     */
    @Test
    void inventoryUsageTest(){
        //chequear que inventario parte vacio
        assertTrue(controller.inventoryIsEmpty());
        //chequear que agrega correctamente un axe, un staff y una sword al inventario
        controller.createAxe(testAxe.getName(), testAxe.getDamage(), testAxe.getWeight());
        assertTrue(controller.checkWeaponInInventory(testAxe.getName()));
        controller.createSword(testSword.getName(), testSword.getDamage(), testSword.getWeight());

        assertTrue(controller.checkWeaponInInventory(testSword.getName()));
        controller.createStaff(testStaff.getName(), testStaff.getDamage(), testStaff.getWeight(), testStaff.getmagicDamage());

        assertTrue(controller.checkWeaponInInventory(testStaff.getName()));

        //agrega un player character para equipar un arma desde el inventario
        controller.createKnight(testKnight.getName(), testKnight.getDefensePoints(), testKnight.getHealthPoints());
        //chequea equipar armas a personajes sin armas
        controller.equipFromInventory(testAxe.getName(), 0);
        //chequea que el arma equipada ya no este en inventario
        assertTrue(!controller.checkWeaponInInventory(testAxe.getName()));
        //chequea que en efecto se haya equipado el arma el player character
        assertEquals(controller.getPlayerCharacterInParty(0).getEquippedWeapon(), testAxe);

        //chequea equipar armas a personajes con armas
        controller.equipFromInventory(testSword.getName(), 0);
        //chequea que arma antigua de player character ahora este en inventario
        assertTrue(controller.checkWeaponInInventory(testAxe.getName()));
        //chequea que arma nueva se haya equipado correctamente
        assertEquals(controller.getPlayerCharacterInParty(0).getEquippedWeapon(), testSword);
        //chequea que arma nueva ya no este en inventario
        assertTrue(!controller.checkWeaponInInventory(testSword.getName()));

        //chequea equipar arma en swap a un player character que no la puede equipar
        controller.equipFromInventory(testStaff.getName(), 0);
    }


}
