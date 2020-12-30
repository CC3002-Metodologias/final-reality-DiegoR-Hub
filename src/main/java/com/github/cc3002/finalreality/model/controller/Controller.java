package com.github.cc3002.finalreality.model.controller;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.player.*;
import com.github.cc3002.finalreality.model.weapon.*;

import java.beans.PropertyChangeListener;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controller {

    private List<Enemy> listaEnemy = new ArrayList<Enemy>();
    private List<IPlayerCharacter> party = new ArrayList<IPlayerCharacter>();
    private List<IWeapon> inventory = new ArrayList<IWeapon>();
    private ICharacter jugadorActual = new Engineer("Hola", 10, 15);;
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



    public Controller(int maxPlayerCharacter, int maxEnemy){
        this.maxEnemy = maxEnemy;
        this.maxPlayerCharacter = maxPlayerCharacter;
        //phase con la que parte el juego
        setPhase(new TurnBeginningPhase());
    }
    //todo hacer metodo basic setup de controller para crear un ejemplo de partida

    public LinkedBlockingQueue<ICharacter> getTurns(){
        return this.turns;
    }

    public void printParty(){
        for (IPlayerCharacter playerCharacter : party){
            System.out.println(playerCharacter);
        }
    }
    public void printQueue(){
        System.out.println("este es el tamano de turns: " + turns.size());
        System.out.println(turns);
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
    public void addToQueue(){
        turns.add(this.jugadorActual);
        scheduledExecutor.shutdown();
    }

    /**
     * ingresa un ICharacter a la Blocking queue de turnos
     */
    public void waitTurn(ICharacter character){
        this.jugadorActual = character;
        scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor.schedule(this::addToQueue, character.getWeight()/10, TimeUnit.SECONDS);
    }

    public List<Enemy> getListaEnemy(){
        return  this.listaEnemy;
    }

    public List<IPlayerCharacter> getParty(){
        return  this.party;
    }

    public List<IWeapon> getInventory() {
        return this.inventory;
    }

    public ICharacter getJugadorActual(){
        return  this.jugadorActual;
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
     * Remueve un arma de Inventory dada su llave
     */
    private void removeFromInventory(int weaponIndex){
            this.inventory.remove(weaponIndex);
    }

    /**
     *
     * Metodo auxiliar de metodo swapWeaponWithPlayerCharacter y putWeaponToPlayerCharacter
     * Getter de arma de Inventory dada su llave
     */
    public IWeapon getWeaponFromInventory(int weaponindex){
        return this.inventory.get(weaponindex);
    }

    /**
     * Metodo para testing, chequea si el inventario esta vacio
     */
    public boolean inventoryIsEmpty(){
        return this.inventory.isEmpty();
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
     * Dada una IWeapon de Inventory determinada por su llave y un IPlayerCharacter de Party dado  indice en ella,
     * equipa esta IWeapon a ese IPlayerCharacter si este es capaz de equiparla
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
     * Permite equipar un arma del inventario a un character del jugador que no tiene un arma equipada actualmente
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
     *  Permite equipar un arma del inventario a un character del jugador que ya tiene equipada un arma actualmente
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
     * todos los ataques pasan por este metodo
     * Dado un ICharacter definido atacante y un character definido defensor genera un ataque del atacante hacia el defensor
     */
    public void attackAnotherCharacter(ICharacter characteratk, ICharacter characterdef){
        characteratk.attack(characterdef);
        //ingresamos nuevamente el jugador actual a la turns queue
        if (party.contains(characteratk)){
            this.waitTurn(this.getPlayerCharacterInParty(party.indexOf(characteratk)));
        }
        //caso que es enemigo, metodo usado en attackrandom player character
        else{
            this.waitTurn(this.getEnemyCharacterInListaEnemy(listaEnemy.indexOf(characteratk)));
        }

    }
    /**
     * Metodo asociado al Listener, imprime el nombre de cada character del jugador cuando muere
     * Adicionalmente, si han muerto todos los character de a party, imprime que han ganado los enemigos
     */
    public void PlayerCharacterDeath(IPlayerCharacter playerCharacter){
        System.out.println("Un Player Character ha muerto: " + playerCharacter.getName());
        if (party.isEmpty()){
            enemyWin = true;
        }
    }

    /**
     * Metodo asociado al Listener, imprime el nombre de cada enemigo cuando muere
     * Adicionalmente, si han muerto todos los enemigos, imprime que ha ganado el jugador
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
     * Recibe un character, quien genera un ataque a un character del playerCharacter en el party aleatorio
     */
    public void attackRandomPlayerCharacter(ICharacter characteratk){
        int randomPlayerCharacterIndex = new Random().nextInt(this.party.size());
        IPlayerCharacter randomPlayerCharacter = this.getPlayerCharacterInParty(randomPlayerCharacterIndex);
        this.attackAnotherCharacter(characteratk, randomPlayerCharacter);
    }

    /**
     * Metodo para interactuar con el usuario durante su turno
     * Cuando usuario ataque, sale de este metodo, sino, se mantiene dentro
     */
    public void usersAction(){
        return;
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
     * chequea si no hay enemigos en el juego
     */
    public boolean checkListaEnemyIsEmpty() {
        return this.listaEnemy.isEmpty();
    }

    /**
     *
     * chequea si no hay characters del player en juego
     */
    public boolean checkPartyIsEmpty() {
        return this.party.isEmpty();
    }


    public IPlayerCharacter getPlayerCharacterInParty(int index) {
        return this.party.get(index);
    }

    public Enemy getEnemyCharacterInListaEnemy(int index){
        return this.listaEnemy.get(index);
    }

    public boolean isEnemyWin() {
        return enemyWin;
    }

    public boolean isPlayerCharacterWin() {
        return playerCharacterWin;
    }

    public void imprimirPrueba() {
        System.out.println(this.contador);
    }

    public void aumentarContador(){
        this.contador++;
    }

    public int getContador(){
        return this.contador;
    }

    public void setPhase(Phase phase) {
        //setea la fase actual en que se encuentra el juego
        this.phase = phase;
        //le pasa el controlador a la phase para que esta guie el flujo del juego
        phase.setController(this);
    }

    public void nextPhase() throws InterruptedException {
        phase.nextPhase();
    }

    public Phase getPhase() {
        return phase;
    }

}
