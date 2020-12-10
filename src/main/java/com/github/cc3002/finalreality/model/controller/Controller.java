package com.github.cc3002.finalreality.model.controller;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.player.*;
import com.github.cc3002.finalreality.model.weapon.*;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class Controller {
    private List<Enemy> listaEnemy = new ArrayList<Enemy>();
    private List<IPlayerCharacter> party = new ArrayList<IPlayerCharacter>();
    private HashMap<String,IWeapon> inventory = new HashMap<>();
    private ICharacter jugadorActual;
    private final PropertyChangeListener PlayerCharacterDeathHandler = new PlayerCharacterDeathHandler(this);
    private final PropertyChangeListener EnemyDeathHandler = new EnemyCharacterDeathHandler(this);
    private int totalPlayerMuertos = 0;
    private int totalEnemyMuertos = 0;
    private LinkedBlockingQueue<ICharacter> turns = new LinkedBlockingQueue<>();
    private LinkedBlockingQueue<Boolean> turnsIsPlayerCharacter = new LinkedBlockingQueue<>();
    private int maxPlayerCharacter;
    private int maxEnemy;
    private List<Integer> indicesPlayerCharacterVivos = new ArrayList<Integer>();

    public List<Enemy> getListaEnemy(){
        return  this.listaEnemy;
    }
    public List<IPlayerCharacter> getParty(){
        return  this.party;
    }
    public Controller(int maxPlayerCharacter, int maxEnemy){
        this.maxEnemy = maxEnemy;
        this.maxPlayerCharacter = maxPlayerCharacter;
    }
    /**
     * Dados los argumentos del constructor de WhiteMage, ingresa un nuevo WhiteMage a la party
     */
    public void createWhiteMage(String name, int defensePoints, int healthPoints, int mana) {
        WhiteMage whiteMage = new WhiteMage(this.turns, name, defensePoints, healthPoints, mana);
        this.addPlayerCharacter(whiteMage);
    }
    /**
     *
     * Dados los argumentos del constructor de BlackMage, ingresa un nuevo BlackMage a la party
     */
    public void createBlackMage(String name, int defensePoints, int healthPoints, int mana) {
        BlackMage blackMage = new BlackMage(this.turns, name, defensePoints, healthPoints, mana);
        this.addPlayerCharacter(blackMage);
    }
    /**
     *
     * Dados los argumentos del constructor de Thief, ingresa un nuevo Thief a la party
     */
    public void createThief(String name, int defensePoints, int healthPoints) {
        Thief thief = new Thief(this.turns, name, defensePoints, healthPoints);
        this.addPlayerCharacter(thief);
    }
    /**
     *
     * Dados los argumentos del constructor de Engineer, ingresa un nuevo Engineer a la party
     */
    public void createEngineer(String name, int defensePoints, int healthPoints) {
        Engineer engineer = new Engineer(this.turns, name, defensePoints, healthPoints);
        this.addPlayerCharacter(engineer);
    }
    /**
     *
     * Dados los argumentos del constructor de Knight, ingresa un nuevo enemigo a la party
     */
    public void createKnight(String name, int defensePoints, int healthPoints) {
        Knight knight = new Knight(this.turns, name, defensePoints, healthPoints);
        this.addPlayerCharacter(knight);
    }
    /**
     *
     * Dados los argumentos del constructor de Enemy, ingresa un nuevo enemigo a la lista de enemigos
     */
    public void createEnemy(String name, int weight, int defensePoints, int healthPoints, int attackPoints) {
        Enemy enemy = new Enemy(this.turns, name, weight, defensePoints, healthPoints, attackPoints);
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
        this.inventory.put(weapon.getName(), weapon);
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
    private void removeFromInventory(String weaponName){
            this.inventory.remove(weaponName);
    }

    /**
     *
     * Metodo auxiliar de metodo swapWeaponWithPlayerCharacter y putWeaponToPlayerCharacter
     * Getter de arma de Inventory dada su llave
     */
    private IWeapon getWeapon(String weaponName){
        return this.inventory.get(weaponName);
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
    public void equipFromInventory(String weaponName, int playerCharacterindex){
        if (this.party.get(playerCharacterindex).getEquippedWeapon() == null){
            this.putWeaponToPlayerCharacter(weaponName, this.party.get(playerCharacterindex));
        }
        else {
            this.swapWeaponWithPlayerCharacter(weaponName, this.party.get(playerCharacterindex));
        }
    }

    /**
     * Metodo auxiliar de equipFromInventory
     * Permite equipar un arma del inventario a un character del jugador que no tiene un arma equipada actualmente
     */
    private void putWeaponToPlayerCharacter(String weaponName, IPlayerCharacter playerCharacter){
        playerCharacter.equipWeapon(getWeapon(weaponName));
        if (playerCharacter.getEquippedWeapon().equals(getWeapon(weaponName))){
            this.removeFromInventory(weaponName);
        }
    }

    /**
     * Metodo auxiliar de equipFromInventory
     *  Permite equipar un arma del inventario a un character del jugador que ya tiene equipada un arma actualmente
     */
    private void swapWeaponWithPlayerCharacter(String weaponName, IPlayerCharacter playerCharacter){
        IWeapon playerPreviousWeapon = playerCharacter.getEquippedWeapon();
        playerCharacter.equipWeapon(getWeapon(weaponName));
        if (playerCharacter.getEquippedWeapon().equals(getWeapon(weaponName))){
            this.addToInventory(playerPreviousWeapon);
            this.removeFromInventory(weaponName);
        }
    }

    /**
     *
     * Metodo para testing, chequea que arma dada como argumento este presente en el inventario
     */
    public Boolean checkWeaponInInventory(String weaponName){
        return inventory.containsKey(weaponName);
    }

    /**
     * Dado un ICharacter definido atacante y un character definido defensor genera un ataque del atacante hacia el defensor
     */
    public void attackAnotherCharacter(ICharacter characteratk, ICharacter characterdef){
        characteratk.attack(characterdef);
    }
    /**
     * Metodo asociado al Listener, imprime el nombre de cada character del jugador cuando muere
     * Adicionalmente, si han muerto todos los character de a party, imprime que han ganado los enemigos
     */
    public void PlayerCharacterDeath(String name){
        System.out.println("Un Player Character ha muerto: " + name);
        this.totalPlayerMuertos ++;
        if (this.totalPlayerMuertos == party.size()){
            System.out.println("Player pierde, Enemy gana");
        }
    }

    /**
     * Metodo asociado al Listener, imprime el nombre de cada enemigo cuando muere
     * Adicionalmente, si han muerto todos los enemigos, imprime que ha ganado el jugador
     */
    public void EnemyDeath(String name){
        System.out.println("Un Enemy ha muerto: " + name);
        this.totalEnemyMuertos ++;
        if (this.totalEnemyMuertos == listaEnemy.size()){
            System.out.println("Enemy pierde, Player gana");
        }
    }
    /**
     *
     * Metodo para testing, getter player character en party dado indice
     */
    public IPlayerCharacter getPlayerCharacterInParty(int indice){
            return this.party.get(indice);
    }

    /**
     *
     * Metodo para testing, getter player character en party dado indice
     */
    public Enemy getEnemyCharacterInListaEnemy(int indice){
        return this.listaEnemy.get(indice);
    }
    /**
     * Hace wait turn de un enemy y ademas indica que se trata de un eneny en la queue paralela
     * turnsIsPlayerCharacter
     * Al extraer en turnsIsPlayerCharacter y turns al mismo tiempo, obtenemos la instancia del character de turns
     * y ademas si es enemy o character del player de turnsIsPlayerCharacter
     */
    private void waitTurnEnemy(ICharacter character){
        this.turnsIsPlayerCharacter.add(false);
        character.waitTurn();
    }

    /**
     * Hace wait turn de un player character y ademas indica que se trata de un player character en la queue paralela
     * turnsIsPlayerCharacter
     * Al extraer en turnsIsPlayerCharacter y turns al mismo tiempo, obtenemos la instancia del character
     * de turns y ademas si es enemy o character del player de turnsIsPlayerCharacter
     */
    private void waitTurnPlayerCharacter(ICharacter character){
        this.turnsIsPlayerCharacter.add(true);
        character.waitTurn();
    }

    /**
     *
     * Dado un conjunto de player characters en la party y un conjunto de enemys en la lista de enemigos, los
     * posiciona en la cola para partir el juego
     */
    public void iniciarQueue() throws InterruptedException {
        for (var playerCharacter: this.party){
            this.waitTurnPlayerCharacter(playerCharacter);
        }
        for (var enemy: this.listaEnemy){
            this.waitTurnEnemy(enemy);
        }
        Thread.sleep(6000);
    }
    /**
     * Inicia el turno del siguiente character en la cola, si es un character del player espera indicaciones
     * del usuario. Si es un enemigo realiza un ataque aleatorio a algun player character de la party
     * Se terminan los turnos con los metodos waitTurnPlayerCharacter y waitTurnEnemy
     */
    public void beginTurn(){
        this.jugadorActual = turns.poll();
        Boolean jugadorActualIsPlayerCharacter = turnsIsPlayerCharacter.poll();
        if (jugadorActualIsPlayerCharacter){
            this.usersAction();
            this.waitTurnPlayerCharacter(jugadorActual);
        }
        else{
            this.attackRandomPlayerCharacter(jugadorActual);
            this.waitTurnEnemy(jugadorActual);
        }
    }
    /**
     *
     * Recibe un character, quien genera un ataque a un character del playerCharacter en el party aleatorio
     */
    public void attackRandomPlayerCharacter(ICharacter characteratk){
        int randomPlayerCharacterIndex = new Random().nextInt(this.indicesPlayerCharacterVivos.size());
        IPlayerCharacter randomPlayerCharacter = this.party.get(randomPlayerCharacterIndex);
        this.attackAnotherCharacter(characteratk, randomPlayerCharacter);
        if (randomPlayerCharacter.isDead()){
            this.indicesPlayerCharacterVivos.remove(party.indexOf(randomPlayerCharacter));
        }
        this.waitTurnEnemy(characteratk);
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
}
