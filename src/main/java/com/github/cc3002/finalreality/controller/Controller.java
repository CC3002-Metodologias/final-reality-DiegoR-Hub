package com.github.cc3002.finalreality.controller;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.player.*;
import com.github.cc3002.finalreality.model.weapon.*;

import java.beans.PropertyChangeListener;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Clase controlador del MVC de Final Reality
 */

public class Controller {

    private List<Enemy> listaEnemy = new ArrayList<Enemy>();
    private List<IPlayerCharacter> party = new ArrayList<IPlayerCharacter>();
    private List<IWeapon> inventory = new ArrayList<IWeapon>();
    private ICharacter jugadorActual = new Enemy("Goblin", 10, 10, 20, 10);
    private final PropertyChangeListener PlayerCharacterDeathHandler = new PlayerCharacterDeathHandler(this);
    private final PropertyChangeListener EnemyDeathHandler = new EnemyCharacterDeathHandler(this);
    private int totalPlayerMuertos = 0;
    private int totalEnemyMuertos = 0;
    private LinkedBlockingQueue<ICharacter> turns = new LinkedBlockingQueue<>();
    private ScheduledExecutorService scheduledExecutor;
    private LinkedBlockingQueue<Boolean> turnsIsPlayerCharacter = new LinkedBlockingQueue<>();
    private int maxPlayerCharacter;
    private int maxEnemy;
    private List<Integer> indicesPlayerCharacterVivos = new ArrayList<Integer>();
    private boolean enemyWin = false;
    private boolean playerCharacterWin = false;
    private int contador = 0;
    private Phase phase;
    private Enemy target;


    /**
     * Constructor de un controlador, recibe el numero maximo de enemigos y characters que puede tener el player
     */
    public Controller(int maxPlayerCharacter, int maxEnemy){
        this.maxEnemy = maxEnemy;
        this.maxPlayerCharacter = maxPlayerCharacter;
        //phase con la que parte el juego
        setPhase(new TurnBeginningPhase());
    }

    /**
     *
     * Setup simple de un juego tipo de Final Reality
     */
    public void basicSetup() throws InterruptedException {
        createEngineer("Engineer", 5, 30);
        createKnight("Knight", 7, 70);
        createThief("Thief", 3, 40);
        createEnemy("Goblin", 10, 4, 50, 15);
        createEnemy("Mateu", 10, 7, 50, 21);
        //instancia las armas
        createAxe("axe c++", 16, 20);
        createAxe("Hacha", 15, 22);
        createStaff("Staff python", 22, 20, 999);
        createAxe("axe Vicho", 18, 20);
        createAxe("Hacha Perro", 15, 22);
        createStaff("Staff python Valu", 22, 20, 999);
        //equipa armas a cada player character para poder equiparlos
        equipFromInventory(0,0);
        equipFromInventory(0,1);
        equipFromInventory(0,2);
        //ingresa los character a la turns queue
        iniciarQueue();
    }

    /**
     * Setup super simple de un juego tipo Final Reality
     *
     */
    public void tooBasicSetup() throws InterruptedException {
        createEngineer("Engineer", 5, 30);
        createAxe("axe c++", 16, 20);
        createAxe("axe c++2", 16, 20);
        createEnemy("Goblin", 10, 4, 50, 15);
        equipFromInventory(0,0);
        iniciarQueue();
    }
    /**
     * Getter del enemy actualmente en target
     */
    public Enemy getTargetEnemy() {
        return this.target;
    }

    /**
     * Getter de la turns queue
     */
    public LinkedBlockingQueue<ICharacter> getTurns(){
        return this.turns;
    }
    /**
     * Getter de la lista de enemigos en juego
     */
    public List<Enemy> getListaEnemy(){
        return  this.listaEnemy;
    }

    /**
     * Getter de la party en juego
     */
    public List<IPlayerCharacter> getParty(){
        return  this.party;
    }

    /**
     * Getter del inventario del juego
     */
    public List<IWeapon> getInventory() {
        return this.inventory;
    }

    /**
     * Getter del jugador en turno actual
     */
    public ICharacter getJugadorActual(){
        return  this.jugadorActual;
    }

    /**
     * Getter del estado actual en que se encuentra el turno
     *
     */
    public Phase getPhase() {
        return phase;
    }

    /**
     * Ingresa las instancias en Party y Lista Enemy a la turns queue y ademas settea el primer jugador
     */
    public void iniciarQueue() throws InterruptedException {
        for (int i=0; i<party.size(); i++){
            this.waitTurn(this.getPlayerCharacterInParty(i));
            Thread.sleep(3000);
        }
        for (int j=0; j<listaEnemy.size(); j++){
            this.waitTurn(this.getEnemyCharacterInListaEnemy(j));
            Thread.sleep(3000);
        }
        jugadorActual = turns.poll();
    }

    /**
     * Ingresa el jugador en turno actual a la turns queue
     */
    public void addToQueue(){
        turns.add(this.jugadorActual);
        scheduledExecutor.shutdown();
    }

    /**
     * ingresa un ICharacter a la Blocking queue de turnos y ejecuta un scheduledExecutor
     */
    public void waitTurn(ICharacter character){
        this.jugadorActual = character;
        scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor.schedule(this::addToQueue, character.getWeight()/10, TimeUnit.SECONDS);
    }


    /**
     * Dados los argumentos del constructor de WhiteMage, ingresa un nuevo WhiteMage a la party
     */
    public void createWhiteMage(String name, int defensePoints, int healthPoints, int mana) {
        WhiteMage whiteMage = new WhiteMage(name, defensePoints, healthPoints, mana);
        this.addPlayerCharacter(whiteMage);
    }
    /**
     *
     * Dados los argumentos del constructor de BlackMage, ingresa un nuevo BlackMage a la party
     */
    public void createBlackMage(String name, int defensePoints, int healthPoints, int mana) {
        BlackMage blackMage = new BlackMage(name, defensePoints, healthPoints, mana);
        this.addPlayerCharacter(blackMage);
    }
    /**
     *
     * Dados los argumentos del constructor de Thief, ingresa un nuevo Thief a la party
     */
    public void createThief(String name, int defensePoints, int healthPoints) {
        Thief thief = new Thief( name, defensePoints, healthPoints);
        this.addPlayerCharacter(thief);
    }
    /**
     *
     * Dados los argumentos del constructor de Engineer, ingresa un nuevo Engineer a la party
     */
    public void createEngineer(String name, int defensePoints, int healthPoints) {
        Engineer engineer = new Engineer( name, defensePoints, healthPoints);
        this.addPlayerCharacter(engineer);
    }
    /**
     *
     * Dados los argumentos del constructor de Knight, ingresa un nuevo Knight a la party
     */
    public void createKnight(String name, int defensePoints, int healthPoints) {
        Knight knight = new Knight(name, defensePoints, healthPoints);
        this.addPlayerCharacter(knight);
    }
    /**
     *
     * Dados los argumentos del constructor de Enemy, ingresa un nuevo enemigo a la lista de enemigos
     */
    public void createEnemy(String name, int weight, int defensePoints, int healthPoints, int attackPoints) {
        Enemy enemy = new Enemy(name, weight, defensePoints, healthPoints, attackPoints);
        this.addEnemy(enemy);
    }

    /**
     *
     * Dados los argumentos del constructor de Axe, ingresa un nuevo Axe al inventario
     */
    public void createAxe(String name, int damage, int weight){
        Axe axe = new Axe(name, damage, weight);
        this.addToInventory(axe);
    }
    /**
     *
     * Dados los argumentos del constructor de Sword, ingresa un nuevo Sword al inventario
     */
    public void createSword(String name, int damage, int weight){
        Sword sword = new Sword(name, damage, weight);
        this.addToInventory(sword);
    }
    /**
     *
     * Dados los argumentos del constructor de Bow, ingresa un nuevo Bow al inventario
     */
    public void createBow(String name, int damage, int weight){
        Bow bow = new Bow(name, damage, weight);
        this.addToInventory(bow);
    }

    /**
     *
     * Dados los argumentos del constructor de Knife, ingresa un nuevo Knife al inventario
     */
    public void createKnife(String name, int damage, int weight){
        Knife knife = new Knife(name, damage, weight);
        this.addToInventory(knife);
    }

    /**
     *
     * Dados los argumentos del constructor de Staff, ingresa un nuevo Staff al inventario
     */
    public void createStaff(String name, int damage, int weight, int magicDamage){
        Staff staff = new Staff(name, damage, weight, magicDamage);
        this.addToInventory(staff);
    }

    /**
     * Metodo auxiliar de metodos create para cada arma
     * Ingresa una instancia de IWeapon al inventario
     */
    private void addToInventory(IWeapon weapon){
        this.inventory.add(weapon);
    }

    /**
     * Metodo auxiliar de metodos create de subclases de IPlayerCharacter
     * Ingresa una instancia de IPlayerCharacter a la Party
     */
    private void addPlayerCharacter(IPlayerCharacter playerCharacter){
        if (party.size() < this.maxPlayerCharacter){
            this.indicesPlayerCharacterVivos.add(this.party.size());
            party.add(playerCharacter);
            playerCharacter.addDeathListener(this.PlayerCharacterDeathHandler);
        }
    }
    /**
     * Metodo auxiliar de metodo createEnemy
     * Ingresa una instancia de Enemy a listaEnemy
     */
    private void addEnemy(Enemy enemy){
        if (listaEnemy.size() < this.maxEnemy){
            listaEnemy.add(enemy);
            enemy.addDeathListener(this.EnemyDeathHandler);
        }
    }
    /**
     *
     * Metodo auxiliar de metodo swapWeaponWithPlayerCharacter
     * Remueve un arma de Inventory dado su indice en la lista Inventario
     */
    private void removeFromInventory(int weaponIndex){
            this.inventory.remove(weaponIndex);
    }
    /**
     *
     * Remueve una instancia IPlayerCharacter de Party dado su indice en ella
     */
    public void removePlayerCharacter(int playerCharacterIndex){
        party.remove(playerCharacterIndex);
    }

    /**
     *
     * Remueve una instancia de Enemy de listaEnemy dado su indice en ella
     */
    public void removeEnemy(int enemyIndex){
        listaEnemy.remove(enemyIndex);
    }


    /**
     *
     * Metodo auxiliar de metodo swapWeaponWithPlayerCharacter y putWeaponToPlayerCharacter
     * Getter de arma de Inventory dado su indice en a lista Inventario
     */
    public IWeapon getWeaponFromInventory(int weaponindex){
        return this.inventory.get(weaponindex);
    }


    /**
     * Dado el indice de un player character en la party, lo retorna
     *
     */
    public IPlayerCharacter getPlayerCharacterInParty(int index) {
        return this.party.get(index);
    }


    /**
     * Dado el indice de un enemy en la lista Enemy, lo retorna
     */
    public Enemy getEnemyCharacterInListaEnemy(int index){
        return this.listaEnemy.get(index);
    }

    /**
     *
     * Dado el indice de un arma en Inventory y el indice de un player character en Party,
     * equipa esa arma a ese player character si este es capaz de equiparla
     */
    public void equipFromInventory(int weaponIndex, int playerCharacterindex){
        if (this.party.get(playerCharacterindex).getEquippedWeapon() == null){
            this.putWeaponToPlayerCharacter(weaponIndex, playerCharacterindex);
        }
        else {
            this.swapWeaponWithPlayerCharacter(weaponIndex, playerCharacterindex);
        }
    }

    /**
     * Metodo auxiliar de equipFromInventory
     * Permite equipar un arma del inventario a un character del player character que no tiene un
     * arma equipada actualmente
     */
    private void putWeaponToPlayerCharacter(int weaponIndex, int playerCharacterIndex){
        getPlayerCharacterInParty(playerCharacterIndex).equipWeapon(getWeaponFromInventory(weaponIndex));
        //si el arma fue equipada exitosamente, la saca del inventario, sino, nada ha pasado
        if (getPlayerCharacterInParty(playerCharacterIndex).getEquippedWeapon() != null){
            this.removeFromInventory(weaponIndex);
        }
        else{
            return;
        }
    }

    /**
     * Metodo auxiliar de equipFromInventory
     *  Permite equipar un arma del inventario a un player character que ya tiene equipada un arma actualmente
     */
    private void swapWeaponWithPlayerCharacter(int weaponIndex, int playerCharacterIndex){
        IWeapon playerPreviousWeapon = getPlayerCharacterInParty(playerCharacterIndex).getEquippedWeapon();
        getPlayerCharacterInParty(playerCharacterIndex).equipWeapon(getWeaponFromInventory(weaponIndex));
        if (getPlayerCharacterInParty(playerCharacterIndex).getEquippedWeapon().equals(getWeaponFromInventory(weaponIndex))){
            this.removeFromInventory(weaponIndex);
            this.addToInventory(playerPreviousWeapon);
        }
    }

    /**
     *
     * Dado un character definido atacante y un character definido defensor genera un ataque
     * del atacante hacia el defensor
     */
    public void attackAnotherCharacter(ICharacter characteratk, ICharacter characterdef) throws InterruptedException {
        characteratk.attack(characterdef);
        //ingresamos nuevamente el jugador actual a la turns queue
        if (party.contains(characteratk)){
            this.waitTurn(this.getPlayerCharacterInParty(party.indexOf(characteratk)));
            Thread.sleep(3000);
            jugadorActual = turns.poll();
        }
        //caso que es enemigo, metodo usado en attackrandom player character
        else{
            this.waitTurn(this.getEnemyCharacterInListaEnemy(listaEnemy.indexOf(characteratk)));
            Thread.sleep(3000);
            jugadorActual = turns.poll();
        }
    }
    /**
     *
     * Recibe un character, luego genera un ataque de este hacia un player character aleatorio de la party
     */
    public void attackRandomPlayerCharacter(ICharacter characteratk) throws InterruptedException {
        int randomPlayerCharacterIndex = new Random().nextInt(this.party.size());
        IPlayerCharacter randomPlayerCharacter = this.getPlayerCharacterInParty(randomPlayerCharacterIndex);
        this.attackAnotherCharacter(characteratk, randomPlayerCharacter);
    }

    /**
     * Metodo asociado al Listener, imprime el nombre de cada player character cuando muere
     * Adicionalmente, si han muerto todos los player character de la party, indica que han ganado los enemy
     */
    public void PlayerCharacterDeath(IPlayerCharacter playerCharacter){
        System.out.println("Un Player Character ha muerto: " + playerCharacter.getName());
        if (party.isEmpty()){
            enemyWin = true;
        }
    }

    /**
     * Metodo asociado al Listener, imprime el nombre de cada enemy cuando muere
     * Adicionalmente, si han muerto todos los enemy, indica que ha ganado el jugador
     */
    public void EnemyDeath(Enemy enemy){
        System.out.println("Un Enemy ha muerto: " + enemy.getName());
        this.totalEnemyMuertos ++;
        if (listaEnemy.isEmpty()){
            playerCharacterWin = true;
        }
    }


    /**
     *
     * getter para los puntos de vida de un player character en juego dado su indice en la party
     */
    public int getPartyMemberHealthPoints(int playerCharacterIndex){
        return party.get(playerCharacterIndex).getHealthPoints();
    }
    /**
     *
     * getter para el arma equipara de un player character en juego dado su indice en la party
     */
    public IWeapon getPartyMemberEquippedWeapon(int playerCharacterIndex){
        return party.get(playerCharacterIndex).getEquippedWeapon();
    }
    /**
     *
     * getter para el nombre de un character del player en juego dado su indice en la party
     */
    public String getPartyMemberName(int playerCharacterIndex){
        return party.get(playerCharacterIndex).getName();
    }
    /**
     *
     * getter para los puntos de defensa de un character del player en juego dado su indice en la party
     */
    public int getPartyMemberDefensePoints(int playerCharacterIndex){
        return party.get(playerCharacterIndex).getDefensePoints();
    }
    /**
     *
     * getter para los puntos de vida de un enemigo en juego dado su indice en la lista de enemigos
     */
    public int getListaEnemyMemberHealthPoints(int enemyIndex){
        return listaEnemy.get(enemyIndex).getHealthPoints();
    }
    /**
     *
     * getter para el nombre de un enemigo en juego dado su indice en la lista de enemigos
     */
    public String getListaEnemyMemberName(int enemyIndex){
        return listaEnemy.get(enemyIndex).getName();
    }
    /**
     *
     * getter para los puntos de defensa de un enemigo en juego dado su indice en la lista de enemigos
     */
    public int getListaEnemyMemberDefensePoints(int enemyIndex){
        return listaEnemy.get(enemyIndex).getDefensePoints();
    }
    /**
     *
     * getter para el peso de un enemigo en juego dado su indice en la lista de enemigos
     */
    public int getListaEnemyMemberWeight(int enemyIndex){
        return listaEnemy.get(enemyIndex).getWeight();
    }

    /**
     *
     * getter para los puntos de ataque de un enemigo en juego dado su indice en la lista de enemigos
     */
    public int getListaEnemyMemberAttackPoints(int enemyIndex){
        return listaEnemy.get(enemyIndex).getDamage();
    }

    /**
     * Metodo para testing, chequea si el inventario esta vacio
     */
    public boolean inventoryIsEmpty(){
        return this.inventory.isEmpty();
    }


    /**
     * Metodo de testing, chequea si la lista de enemigos esta vacia
     */
    public boolean checkListaEnemyIsEmpty() {
        return this.listaEnemy.isEmpty();
    }

    /**
     *
     * Metodo de testing, chequea si la party esta vacia
     */
    public boolean checkPartyIsEmpty() {
        return this.party.isEmpty();
    }


    /**
     * chequea si han ganado los enemy
     */
    public boolean isEnemyWin() {
        return enemyWin;
    }

    /**
     * Chequea si ha ganado el jugador
     */
    public boolean isPlayerCharacterWin() {
        return playerCharacterWin;
    }

    /**
     * Setea el estado actual del turno en que se encuentra el controlador
     *
     */
    public void setPhase(Phase phase) {
        //setea la fase actual en que se encuentra el juego
        this.phase = phase;
        //le pasa el controlador a la phase para que esta guie el flujo del juego
        phase.setController(this);
    }


    /**
     * Metodo para el Observer, quita a un player character de la turns queue cuando este muere
     *
     */
    public void removePlayerCharacterFromTurnsQueue(IPlayerCharacter playerCharacter) {
        turns.remove(playerCharacter);
    }

    /**
     * Metodo para el Observer, quita a un enemy de la lista Enemy cuando este muere
     *
     */
    public void removeEnemyCharacterFromTurnsQueue(Enemy enemy) {
        turns.remove(enemy);
    }

    /**
     * Metodo para la fase de target Enemy, dado un enemy, lo fija como target del siguiente ataque
     *
     */
    public void targetEnemy(Enemy enemy) {
        this.target = enemy;
    }


}
