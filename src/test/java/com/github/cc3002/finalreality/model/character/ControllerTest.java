package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.*;
import com.github.cc3002.finalreality.controller.Controller;
import com.github.cc3002.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ControllerTest {
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
        //Instanciandos los player characters
        testWhiteMage = new WhiteMage( PLAYER_CHARACTER_NAME1, DEFENSE_POINTS, HEALTH_POINTS, MANA);
        testThief = new Thief( PLAYER_CHARACTER_NAME2, DEFENSE_POINTS, HEALTH_POINTS);
        testKnight = new Knight( PLAYER_CHARACTER_NAME3, DEFENSE_POINTS, HEALTH_POINTS);
        testEngineer = new Engineer( PLAYER_CHARACTER_NAME4, DEFENSE_POINTS, HEALTH_POINTS);
        testBlackMage = new BlackMage(PLAYER_CHARACTER_NAME5, DEFENSE_POINTS, HEALTH_POINTS, MANA);
        testEngineerAboutToDie = new Engineer(PLAYER_CHARACTER_NAME4, DEFENSE_POINTS, 1);
        //Instanciando los Enemys
        testEnemy1 = new Enemy(ENEMY1_NAME, ENEMY_WEIGHT, DEFENSE_POINTS, HEALTH_POINTS, ATTACK_POINTS);
        testEnemy2 = new Enemy(ENEMY2_NAME, ENEMY_WEIGHT, DEFENSE_POINTS, HEALTH_POINTS, ATTACK_POINTS);
        testEnemy3 = new Enemy(ENEMY3_NAME, ENEMY_WEIGHT, DEFENSE_POINTS, HEALTH_POINTS, ATTACK_POINTS);
        testEnemy4 = new Enemy( ENEMY4_NAME, ENEMY_WEIGHT, DEFENSE_POINTS, HEALTH_POINTS, ATTACK_POINTS);
        testEnemyAboutToDie = new Enemy( ENEMY5_NAME, ENEMY_WEIGHT, DEFENSE_POINTS, 1, ATTACK_POINTS);
        //Instanciando las IWeapons
        testAxe = new Axe(AXE_NAME, DAMAGE, WEIGHT);
        testBow = new Bow(BOW_NAME, DAMAGE, WEIGHT);
        testKnife = new Knife(KNIFE_NAME, DAMAGE, WEIGHT);
        testStaff = new Staff(STAFF_NAME, DAMAGE, WEIGHT, MAGIC_DAMAGE);
        testSword = new Sword(SWORD_NAME, DAMAGE, WEIGHT);
        //Instanciando el controller
        controller = new Controller(100, 100);

    }


    /**
     *
     * Testea que la morir un player character, este salga de la turns queue
     * COMPROBADO QUE FUNCIONA Y LISTO EL TEST
     */
    @Test
    void quitarPlayerCharacterMuertoTurnsQueue() throws InterruptedException {
        controller.createEnemy(testEnemy1.getName(), testEnemy1.getWeight(), testEnemy1.getDefensePoints(), testEnemy1.getHealthPoints(), testEnemy1.getDamage());
        controller.createKnight(testKnight.getName(), testKnight.getDefensePoints(), testKnight.getHealthPoints());
        controller.createEngineer(testEngineerAboutToDie.getName(), testEngineerAboutToDie.getDefensePoints(), testEnemyAboutToDie.getHealthPoints());
        controller.createAxe(testAxe.getName(), testAxe.getDamage(), testAxe.getWeight());
        controller.createAxe(testAxe.getName(), testAxe.getDamage(), testAxe.getWeight());
        controller.equipFromInventory(0,0);
        controller.equipFromInventory(0,1);
        controller.iniciarQueue();
        //antes de matar al engineer about to die
        assertTrue(controller.getTurns().contains(testEngineerAboutToDie));
        assertEquals(2, controller.getTurns().size());
        //despues de matar al engineer about to die
        controller.attackAnotherCharacter(controller.getEnemyCharacterInListaEnemy(0), controller.getPlayerCharacterInParty(1));
        assertFalse(controller.getTurns().contains(testEngineerAboutToDie));
        assertEquals(1, controller.getTurns().size());
    }

    /**
     *
     * Testea que la morir un enemy, este salga de la turns queue
     * COMPROBADO QUE FUNCIONA Y LISTO EL TEST
     */
    @Test
    void quitarEnemyMuertoTurnsQueue() throws InterruptedException {
        controller.createEngineer(testEngineer.getName(), testEngineer.getDefensePoints(), testEngineer.getHealthPoints());
        controller.createKnight(testKnight.getName(), testKnight.getDefensePoints(), testKnight.getHealthPoints());
        controller.createEnemy(testEnemyAboutToDie.getName(), testEnemyAboutToDie.getWeight(), testEnemyAboutToDie.getDefensePoints(), testEnemyAboutToDie.getHealthPoints(), testEnemyAboutToDie.getDamage());
        controller.createAxe(testAxe.getName(), testAxe.getDamage(), testAxe.getWeight());
        controller.createAxe(testAxe.getName(), testAxe.getDamage(), testAxe.getWeight());
        controller.equipFromInventory(0,0);
        controller.equipFromInventory(0,1);
        controller.iniciarQueue();
        //caso, muere un enemy
        assertTrue(controller.getTurns().contains(testEnemyAboutToDie));
        assertEquals(2,controller.getTurns().size());
        controller.attackAnotherCharacter(controller.getPlayerCharacterInParty(0), controller.getEnemyCharacterInListaEnemy(0));
        assertFalse(controller.getTurns().contains(testEnemyAboutToDie));
        assertEquals(1, controller.getTurns().size());



    }

    /**
     *
     * testea que tras atacar se actualice el jugador actual al siguiente en la turns queue
     * testea que el tamano de la turns queue se mantenga constante en el tiempo tras cada turno
     * testea que los character sean extraidos en orden correcto de la turns queue
     * testea que los character sean ingresados en orden correcto a la turns queue
     * COMPROBADO QUE FUNCIONA Y LISTO EL TEST
     */
    @Test
    void GameFlowTest() throws InterruptedException {
        assertTrue(controller.getTurns().isEmpty());
        //instancias player character en party
        controller.createEngineer(testEngineer.getName(), testEngineer.getDefensePoints(), testEngineer.getHealthPoints());
        controller.createKnight(testKnight.getName(), testKnight.getDefensePoints(), testKnight.getHealthPoints());
        controller.createThief(testThief.getName(), testThief.getDefensePoints(), testThief.getHealthPoints());
        controller.createBlackMage(testBlackMage.getName(), testBlackMage.getDefensePoints(), testBlackMage.getHealthPoints(), testBlackMage.getMana());
        controller.createWhiteMage(testWhiteMage.getName(), testWhiteMage.getDefensePoints(), testWhiteMage.getHealthPoints(), testWhiteMage.getMana());
        //instancias enemy en lista Enemy
        controller.createEnemy(testEnemy1.getName(), testEnemy1.getWeight(), testEnemy1.getDefensePoints(), testEnemy1.getHealthPoints(), testEnemy1.getDamage());
        controller.createEnemy(testEnemy2.getName(), testEnemy2.getWeight(), testEnemy2.getDefensePoints(), testEnemy2.getHealthPoints(), testEnemy2.getDamage());
        controller.createEnemy(testEnemy3.getName(), testEnemy3.getWeight(), testEnemy3.getDefensePoints(), testEnemy3.getHealthPoints(), testEnemy3.getDamage());
        controller.createEnemy(testEnemy4.getName(), testEnemy4.getWeight(), testEnemy4.getDefensePoints(), testEnemy4.getHealthPoints(), testEnemy4.getDamage());
        //instancia las armas
        controller.createAxe(testAxe.getName(), 1, testAxe.getWeight());
        controller.createAxe(testAxe.getName(), 1, testAxe.getWeight());
        controller.createStaff(testStaff.getName(), 1, testStaff.getWeight(), testStaff.getmagicDamage());
        controller.createStaff(testStaff.getName(), 1, testStaff.getWeight(), testStaff.getmagicDamage());
        controller.createStaff(testStaff.getName(), 1, testStaff.getWeight(), testStaff.getmagicDamage());
        //equipa armas a cada player character para poder equiparlos
        controller.equipFromInventory(0,0);
        controller.equipFromInventory(0,1);
        controller.equipFromInventory(0,2);
        controller.equipFromInventory(0,3);
        controller.equipFromInventory(0,4);
        //ingresa los character a la turns queue
        controller.iniciarQueue();

        //primer turno
        assertEquals(8,controller.getTurns().size());
        assertEquals(testEngineer, controller.getJugadorActual());
        controller.attackAnotherCharacter(controller.getJugadorActual(),controller.getEnemyCharacterInListaEnemy(0));

        //segundo turno
        assertEquals(8,controller.getTurns().size());
        assertEquals(testKnight, controller.getJugadorActual());
        controller.attackAnotherCharacter(controller.getJugadorActual(),controller.getEnemyCharacterInListaEnemy(0));

        //tercer turno
        assertEquals(8,controller.getTurns().size());
        assertEquals(testThief, controller.getJugadorActual());
        controller.attackAnotherCharacter(controller.getJugadorActual(),controller.getEnemyCharacterInListaEnemy(0));

        //cuarto turno
        assertEquals(8,controller.getTurns().size());
        assertEquals(testBlackMage, controller.getJugadorActual());
        controller.attackAnotherCharacter(controller.getJugadorActual(),controller.getEnemyCharacterInListaEnemy(0));

        //quinto turno
        assertEquals(8,controller.getTurns().size());
        assertEquals(testWhiteMage, controller.getJugadorActual());
        controller.attackAnotherCharacter(controller.getJugadorActual(),controller.getEnemyCharacterInListaEnemy(0));
    }

    /**
     * Testea el correcto funcionamiento de ataques entre dos instancias de enemy en Lista enemy
     * COMPROBADO QUE FUNCIONA Y LISTO EL TEST
     */
    @Test
    void attackBetweenEnemies() throws InterruptedException {
        controller.basicSetup();
        int initialEnemyHealthPoints = controller.getListaEnemyMemberHealthPoints(1);
        controller.attackAnotherCharacter(controller.getEnemyCharacterInListaEnemy(0), controller.getEnemyCharacterInListaEnemy(1));
        assertEquals(controller.getListaEnemyMemberHealthPoints(1),initialEnemyHealthPoints+controller.getListaEnemyMemberDefensePoints(1)-controller.getListaEnemyMemberAttackPoints(0));
    }
    /**
     * Testea el correcto funcionamiento de ataques entre dos instancias de player character en party
     * COMPROBADO QUE FUNCIONA Y LISTO EL TEST
     */
    @Test
    void attackBetweenPlayerCharacters() throws InterruptedException {
        controller.createEngineer(testEngineer.getName(), testEngineer.getDefensePoints(), testEngineer.getHealthPoints());
        controller.createThief(testThief.getName(), testThief.getDefensePoints(), testThief.getHealthPoints());
        controller.createAxe(testAxe.getName(), 1, testAxe.getWeight());
        controller.equipFromInventory(0,0);
        int initialThiefHealthPoints = controller.getPartyMemberHealthPoints(1);
        controller.attackAnotherCharacter(controller.getPlayerCharacterInParty(0), controller.getPlayerCharacterInParty(1));
        assertEquals(controller.getPartyMemberHealthPoints(1), controller.getPartyMemberDefensePoints(1)+initialThiefHealthPoints- controller.getPlayerCharacterInParty(0).getDamage());
    }

    /**
     * Testea el correcto ataque entre playerCharacter y Enemy
     * COMPROBADO QUE FUNCIONA Y LISTO EL TEST
     */
    @Test
    void attackTest() throws InterruptedException {
        //ataque de player character a enemy
        controller.createEngineer(testEngineer.getName(), testEngineer.getDefensePoints(), testEngineer.getHealthPoints());
        controller.createAxe(testAxe.getName(), testAxe.getDamage(), testAxe.getWeight());
        controller.createEnemy(testEnemy1.getName(), testEnemy1.getWeight(), testEnemy1.getDefensePoints(), testEnemy1.getHealthPoints(), testEnemy1.getDamage());
        controller.equipFromInventory(0,0);
        int initialEnemyHealthPoints = controller.getListaEnemyMemberHealthPoints(0);
        controller.attackAnotherCharacter(controller.getPlayerCharacterInParty(0), controller.getEnemyCharacterInListaEnemy(0));
        assertEquals(controller.getListaEnemyMemberHealthPoints(0), controller.getListaEnemyMemberDefensePoints(0) + initialEnemyHealthPoints - controller.getPlayerCharacterInParty(0).getDamage());
        //ataque de enemy a player character
        int initialPlayerCharacterHealthPoints = controller.getPartyMemberHealthPoints(0);
        controller.attackAnotherCharacter(controller.getEnemyCharacterInListaEnemy(0), controller.getPlayerCharacterInParty(0));
        assertEquals(controller.getPartyMemberHealthPoints(0), controller.getPartyMemberDefensePoints(0)+initialPlayerCharacterHealthPoints-controller.getListaEnemyMemberAttackPoints(0));
    }

    /**
     * Testea que no se puedan agregar instancias de player character a party cuando esta llena
     * ni que se puedan agregar instancias de enemy a lista eemy cuando esta llena
     * COMPROBADO QUE FUNCIONA Y LISTO EL TEST
     */
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
    /**
     * Testea que instancias de enemy salgan de Lista Enemy al morir
     * COMPROBADO QUE FUNCIONA Y LISTO EL TEST
     */
    @Test
    void listaEnemyVaciaAlMorir() throws InterruptedException {
        controller.createKnight(testKnight.getName(), testKnight.getDefensePoints(), testKnight.getHealthPoints());
        controller.createAxe(testAxe.getName(), testAxe.getDamage(), testAxe.getWeight());
        controller.createEnemy(testEnemyAboutToDie.getName(), testEnemyAboutToDie.getWeight(), testEnemyAboutToDie.getDefensePoints(), testEnemyAboutToDie.getHealthPoints(), testEnemyAboutToDie.getDamage());
        controller.equipFromInventory(0,0);
        assertFalse(controller.getListaEnemy().isEmpty());
        controller.attackAnotherCharacter(controller.getPlayerCharacterInParty(0), controller.getEnemyCharacterInListaEnemy(0));
        assertTrue(controller.getListaEnemy().isEmpty());
    }
    /**
     * Testea que instancias de player character salgan de Party al morir
     * COMPROBADO QUE FUNCIONA Y LISTO EL TEST
     */
    @Test
    void partyVaciaAlMorir() throws InterruptedException {
        controller.createEngineer(testEngineerAboutToDie.getName(), testEngineerAboutToDie.getDefensePoints(), testEngineerAboutToDie.getHealthPoints());
        controller.createEnemy(testEnemy1.getName(), testEnemy1.getWeight(), testEnemy1.getDefensePoints(), testEnemy1.getHealthPoints(), testEnemy1.getDamage());
        assertFalse(controller.getParty().isEmpty());
        controller.attackAnotherCharacter(controller.getEnemyCharacterInListaEnemy(0),controller.getPlayerCharacterInParty(0));
        assertTrue(controller.getParty().isEmpty());
    }

    /**
     * Testea el ataque aleatorio que reliza un enemy sobre la party
     * COMPROBADO QUE FUNCIONA Y LISTO EL TEST
     */
    @Test
    void attackRandomPlayerCharacterTest() throws InterruptedException {
        controller.createEngineer(testEngineer.getName(), testEngineer.getDefensePoints(), testEngineer.getHealthPoints());
        controller.createThief(testThief.getName(), testThief.getDefensePoints(), testThief.getHealthPoints());
        controller.createEnemy(testEnemy1.getName(), testEnemy1.getWeight(), testEnemy1.getDefensePoints(), testEnemy1.getHealthPoints(), 1);
        assertFalse(controller.getPlayerCharacterInParty(0).getHealthPoints() != testEngineer.getHealthPoints()
                || controller.getPlayerCharacterInParty(1).getHealthPoints() != testThief.getHealthPoints());
        controller.attackRandomPlayerCharacter(controller.getEnemyCharacterInListaEnemy(0));
        assertTrue(controller.getPlayerCharacterInParty(0).getHealthPoints() != testEngineer.getHealthPoints()
        || controller.getPlayerCharacterInParty(1).getHealthPoints() != testThief.getHealthPoints());

    }

    /**
     * Testea el caso en que muere el ultimo enemy y gana player character
     * COMPROBADO QUE FUNCIONA Y LISTO EL TEST
     */
    @Test
    void playerCharacterWinsTest() throws InterruptedException {
        controller.createKnight(testKnight.getName(), testKnight.getDefensePoints(), testKnight.getHealthPoints());
        controller.createEnemy(testEnemyAboutToDie.getName(), testEnemyAboutToDie.getWeight(), testEnemyAboutToDie.getDefensePoints(), testEnemyAboutToDie.getHealthPoints(), testEnemyAboutToDie.getDamage());
        controller.createEnemy(testEnemyAboutToDie.getName(), testEnemyAboutToDie.getWeight(), testEnemyAboutToDie.getDefensePoints(), testEnemyAboutToDie.getHealthPoints(), testEnemyAboutToDie.getDamage());
        controller.createAxe(testAxe.getName(), testAxe.getDamage(), testAxe.getWeight());
        controller.equipFromInventory(0,0);
        controller.attackAnotherCharacter(controller.getPlayerCharacterInParty(0),controller.getEnemyCharacterInListaEnemy(0));
        assertFalse(controller.isPlayerCharacterWin());
        controller.attackAnotherCharacter(controller.getPlayerCharacterInParty(0),controller.getEnemyCharacterInListaEnemy(0));
        assertTrue(controller.isPlayerCharacterWin());
    }

    /**
     * Testea el caso en que muere el ultimo player character de la party y gana enemy
     * COMPROBADO QUE FUNCIONA Y LISTO EL TEST
     */
    @Test
    void enemyWinsTest() throws InterruptedException {
        controller.createEngineer(testEngineerAboutToDie.getName(), testEngineerAboutToDie.getDefensePoints(), testEngineerAboutToDie.getHealthPoints());
        controller.createEngineer(testEngineerAboutToDie.getName(), testEngineerAboutToDie.getDefensePoints(), testEngineerAboutToDie.getHealthPoints());
        controller.createEnemy(testEnemy1.getName(), testEnemy1.getWeight(), testEnemy1.getDefensePoints(), testEnemy1.getHealthPoints(), testEnemy1.getDamage());
        controller.attackAnotherCharacter(controller.getEnemyCharacterInListaEnemy(0),controller.getPlayerCharacterInParty(0));
        assertFalse(controller.isEnemyWin());
        controller.attackAnotherCharacter(controller.getEnemyCharacterInListaEnemy(0),controller.getPlayerCharacterInParty(0));
        assertTrue(controller.isEnemyWin());
    }

    /**
     * Testea el funcionamiento de la turns queue y el metodo waitTurn
     * COMPROBADO QUE FUNCIONA Y LISTO EL TEST
     */
    @Test
    void turnsQueueTest() throws InterruptedException {
        //testea agregar un player character a la turns queue
        controller.createEngineer(testEngineer.getName(), testEngineer.getDefensePoints(), testEngineer.getHealthPoints());
        controller.createAxe(testAxe.getName(), testAxe.getDamage(), testAxe.getWeight());
        controller.equipFromInventory(0,0);
        assertTrue(controller.getTurns().isEmpty());
        controller.waitTurn(controller.getPlayerCharacterInParty(0));
        Thread.sleep(3000);
        assertEquals(controller.getTurns().size(),1);
        //chequea quitar el player character de la turns queue, que se retorne correctamente
        assertEquals(controller.getTurns().poll(),testEngineer);
        assertEquals(controller.getTurns().size(),0);
        //chequea agregar un enemy a la turns queue
        controller.createEnemy(testEnemy1.getName(), testEnemy1.getWeight(), testEnemy1.getDefensePoints(), testEnemy1.getHealthPoints(), testEnemy1.getDamage());
        controller.waitTurn(controller.getEnemyCharacterInListaEnemy(0));
        Thread.sleep(3000);
        assertEquals(controller.getTurns().size(),1);
        //chequea vaciar la turns queue
        controller.getTurns().poll();
        assertTrue(controller.getTurns().isEmpty());
    }

    /**
     * Testea los metodos getters aplicados a instancia de player charcater en party
     * COMPROBADO QUE FUNCIONA Y LISTO EL TEST
     */
    @Test
    void gettersPlayerCharacterTest(){
        controller.createThief(testThief.getName(), testThief.getDefensePoints(), testThief.getHealthPoints());
        controller.createStaff(testStaff.getName(), testStaff.getDamage(), testStaff.getWeight(), testStaff.getmagicDamage());
        controller.equipFromInventory(0, 0);
        assertEquals(testThief.getDefensePoints(),controller.getPartyMemberDefensePoints(0));
        assertEquals(testStaff,controller.getPartyMemberEquippedWeapon(0));
        assertEquals(testThief.getHealthPoints(), controller.getPartyMemberHealthPoints(0));
        assertEquals(testThief.getName(), controller.getPartyMemberName(0));
    }

    /**
     * Testea los metodos getters aplicados a instancia de enemy en Lista Enemy
     * COMPROBADO QUE FUNCIONA Y LISTO EL TEST
     */
    @Test
    void gettersEnemyTest(){
        controller.createEnemy(testEnemy1.getName(), testEnemy1.getWeight(), testEnemy1.getDefensePoints(), testEnemy1.getHealthPoints(), testEnemy1.getDamage());
        assertEquals(testEnemy1.getDamage(),controller.getListaEnemyMemberAttackPoints(0));
        assertEquals(testEnemy1.getWeight(),controller.getListaEnemyMemberWeight(0));
        assertEquals(testEnemy1.getDefensePoints(), controller.getListaEnemyMemberDefensePoints(0));
        assertEquals(testEnemy1.getHealthPoints(), controller.getListaEnemyMemberHealthPoints(0));
        assertEquals(testEnemy1.getName(), controller.getListaEnemyMemberName(0));
    }

    /**
     * Testea la remocion de instancia de player character de la party dado su indice en ella
     * COMPROBADO QUE FUNCIONA Y LISTO EL TEST
     */
    @Test
    void removePlayerCharacterTest(){
        controller.createKnight(testKnight.getName(), testKnight.getDefensePoints(), testKnight.getHealthPoints());
        controller.removePlayerCharacter(0);
        assertTrue(controller.checkPartyIsEmpty());
    }

    /**
     * Testea la remocion de instancia de enemy de la Lista Enemy dado su indice en ella
     * COMPROBADO QUE FUNCIONA Y LISTO EL TEST
     */
    @Test
    void removeEnemyTest(){
        controller.createEnemy(testEnemy1.getName(), testEnemy1.getWeight(), testEnemy1.getDefensePoints(), testEnemy1. getHealthPoints(), testEnemy1. getDamage());
        controller.removeEnemy(0);
        assertTrue(controller.checkListaEnemyIsEmpty());
    }

    /**
     * Testea la creacion de instancias de player character en la party
     * COMPROBADO QUE FUNCIONA Y LISTO EL TEST
     */
    @Test
    void IPlayerCharacterCreatorTest(){
        controller.createEngineer(testEngineer.getName(), testEngineer.getDefensePoints(), testEngineer.getHealthPoints());
        controller.createThief(testThief.getName(), testThief.getDefensePoints(), testThief.getHealthPoints());
        controller.createKnight(testKnight.getName(), testKnight.getDefensePoints(), testKnight.getHealthPoints());
        controller.createBlackMage(testBlackMage.getName(), testBlackMage.getDefensePoints(), testBlackMage.getHealthPoints(), testBlackMage.getMana());
        controller.createWhiteMage(testWhiteMage.getName(), testWhiteMage.getDefensePoints(), testWhiteMage.getHealthPoints(), testWhiteMage.getMana());
        assertTrue(controller.getParty().contains(testEngineer));
        assertTrue(controller.getParty().contains(testThief));
        assertTrue(controller.getParty().contains(testKnight));
        assertTrue(controller.getParty().contains(testBlackMage));
        assertTrue(controller.getParty().contains(testWhiteMage));
    }


    /**
     * Testea la creacion de armas en el inventario
     * COMPROBADO QUE FUNCIONA Y LISTO EL TEST
     */
    @Test
    void IWeaponCreatorTest(){
        controller.createAxe(testAxe.getName(), testAxe.getDamage(), testAxe.getWeight());
        controller.createStaff(testStaff.getName(), testStaff.getDamage(), testStaff.getWeight(), testStaff.getmagicDamage());
        controller.createBow(testBow.getName(), testBow.getDamage(), testBow.getWeight());
        controller.createKnife(testKnife.getName(), testKnife.getDamage(), testKnife.getWeight());
        controller.createSword(testSword.getName(), testSword.getDamage(), testSword.getWeight());
        assertEquals(controller.getWeaponFromInventory(0), testAxe);
        assertEquals(controller.getWeaponFromInventory(1), testStaff);
        assertEquals(controller.getWeaponFromInventory(2), testBow);
        assertEquals(controller.getWeaponFromInventory(3), testKnife);
        assertEquals(controller.getWeaponFromInventory(4), testSword);
        assertTrue(controller.getInventory().contains(testAxe));
        assertTrue(controller.getInventory().contains(testStaff));
        assertTrue(controller.getInventory().contains(testBow));
        assertTrue(controller.getInventory().contains(testKnife));
        assertTrue(controller.getInventory().contains(testSword));
        assertTrue(controller.getInventory().size()==5);
    }

    /**
     *
     * Testea equipar:
     * arma no equipable a player character sin arma
     * arma no equipable a player character con arma
     * arma equipable a player character sin arma
     * arma equipable a player character con arma
     *
     * Testea que arma salga del inventario al ser equipada y retorna al inventario el arma antigua equipada
     * COMPROBADO QUE FUNCIONA Y LISTO EL TEST
     */
    @Test
    void inventoryUsageTest(){
        //chequear que inventario parte vacio
        assertTrue(controller.inventoryIsEmpty());
        //chequear que agrega correctamente un axe, un staff y una sword al inventario
        controller.createAxe(testAxe.getName(), testAxe.getDamage(), testAxe.getWeight());
        controller.createSword(testSword.getName(), testSword.getDamage(), testSword.getWeight());
        controller.createStaff(testStaff.getName(), testStaff.getDamage(), testStaff.getWeight(), testStaff.getmagicDamage());
        //agrega un knight para equiparle el axe
        controller.createKnight(testKnight.getName(), testKnight.getDefensePoints(), testKnight.getHealthPoints());
        //intenta equipar un arma inequipable por knight sin arma
        controller.equipFromInventory(2,0);
        //chequea que no se haya equipado el arma
        assertEquals(controller.getPlayerCharacterInParty(0).getEquippedWeapon(), null);
        //equipa axe testaxe al knight
        controller.equipFromInventory(0, 0);
        //chequea correcto equipamiento de testaxe en knight
        assertEquals(controller.getPlayerCharacterInParty(0).getEquippedWeapon(), testAxe);
        //chequea que testaxe haya salido el inventario al equiparse en knight
        assertFalse(controller.getInventory().contains(testAxe));
        //chequea equipar armas a personajes con armas, sword al knight
        controller.equipFromInventory(0, 0);
        //chequea correcto equipamiento de testsword en knight
        assertEquals(controller.getPlayerCharacterInParty(0).getEquippedWeapon(), testSword);
        //chequea que testSword haya salido el inventario al equiparse en knight
        assertFalse(controller.getInventory().contains(testSword));
        //chequea que testAxe vuelve al inventario
        assertTrue(controller.getInventory().contains(testAxe));
        //chequea equipar arma en swap a un player character que no la puede equipar
        controller.equipFromInventory(0, 0);
        //intenta equipar un arma inequipable por knight con arma
        controller.equipFromInventory(0,0);
        //chequea que no se haya equipado el arma inequipable
        assertNotEquals(controller.getPlayerCharacterInParty(0).getEquippedWeapon(),testStaff);
    }

}
