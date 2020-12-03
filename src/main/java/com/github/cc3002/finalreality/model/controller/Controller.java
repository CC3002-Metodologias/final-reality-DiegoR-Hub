package com.github.cc3002.finalreality.model.controller;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.player.*;
import com.github.cc3002.finalreality.model.weapon.*;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Controller {
    private List<Enemy> listaEnemy = new ArrayList<Enemy>();
    private List<IPlayerCharacter> party = new ArrayList<IPlayerCharacter>();
    private HashMap<Integer,IWeapon> inventory = new HashMap<>();
    private IPlayerCharacter jugadorActual;
    private final PropertyChangeListener PlayerCharacterDeathHandler = new PlayerCharacterDeathHandler(this);
    private final PropertyChangeListener EnemyDeathHandler = new EnemyCharacterDeathHandler(this);
    private final PropertyChangeListener turnsHandler = new TurnsHandler(this);
    private int totalPlayerMuertos = 0;
    private int totalEnemyMuertos = 0;
    private LinkedBlockingQueue<ICharacter> turns = new LinkedBlockingQueue<>();
    private int maxPlayerCharacter;
    private int maxEnemy;


    public Controller(int maxPlayerCharacter, int maxEnemy){
        this.maxEnemy = maxEnemy;
        this.maxPlayerCharacter = maxPlayerCharacter;
    }
    public WhiteMage createWhiteMage(String name, int defensePoints, int healthPoints, int mana) {
        WhiteMage whiteMage = new WhiteMage(this.turns, name, defensePoints, healthPoints, mana);
        this.addPlayerCharacter(whiteMage);
        return whiteMage;
    }

    public BlackMage createBlackMage(String name, int defensePoints, int healthPoints, int mana) {
        BlackMage blackMage = new BlackMage(this.turns, name, defensePoints, healthPoints, mana);
        this.addPlayerCharacter(blackMage);
        return blackMage;
    }

    public Thief createThief(String name, int defensePoints, int healthPoints) {
        Thief thief = new Thief(this.turns, name, defensePoints, healthPoints);
        this.addPlayerCharacter(thief);
        return thief;
    }

    public Engineer createEngineer(String name, int defensePoints, int healthPoints) {
        Engineer engineer = new Engineer(this.turns, name, defensePoints, healthPoints);
        this.addPlayerCharacter(engineer);
        return engineer;
    }
    public Knight createKnight(String name, int defensePoints, int healthPoints) {
        Knight knight = new Knight(this.turns, name, defensePoints, healthPoints);
        this.addPlayerCharacter(knight);
        return knight;
    }

    public Enemy createEnemy(String name, int weight, int defensePoints, int healthPoints, int attackPoints) {
        Enemy enemy = new Enemy(this.turns, name, weight, defensePoints, healthPoints, attackPoints);
        this.addEnemy(enemy);
        return enemy;
    }

    /**
     * pendiente demas clases de player character
     */
    public int createAxe(String name, int damage, int weight){
        Axe axe = new Axe(name, damage, weight);
        this.addToInventory(axe);
        return axe.hashCode();
    }

    public int createSword(String name, int damage, int weight){
        Sword sword = new Sword(name, damage, weight);
        this.addToInventory(sword);
        return sword.hashCode();
    }

    public int createBow(String name, int damage, int weight){
        Bow bow = new Bow(name, damage, weight);
        this.addToInventory(bow);
        return bow.hashCode();
    }
    public int createKnife(String name, int damage, int weight){
        Knife knife = new Knife(name, damage, weight);
        this.addToInventory(knife);
        return knife.hashCode();
    }

    public int createStaff(String name, int damage, int weight, int magicDamage){
        Staff staff = new Staff(name, damage, weight, magicDamage);
        this.addToInventory(staff);
        return staff.hashCode();
    }

    public void addToInventory(IWeapon weapon){
        this.inventory.put(weapon.hashCode(), weapon);
    }

    /**
     *
     * Remueve un arma del inventario del jugador y luego la retorna
     * revisar muchas branches en if
     *
     */
    public void removeFromInventory(int hashweapon){
            this.inventory.remove(hashweapon);
    }

    public IWeapon getWeapon(int hashweapon){
        return this.inventory.get(hashweapon);
    }

    public boolean inventoryIsEmpty(){
        return this.inventory.isEmpty();
    }
    /**
     * Agrega un character del jugador a la party
     *
     */
    public void addPlayerCharacter(IPlayerCharacter playerCharacter){
        if (party.size() < this.maxPlayerCharacter){
            party.add(playerCharacter);
            playerCharacter.addDeathListener(this.PlayerCharacterDeathHandler);
            playerCharacter.addTurnsListener(this.turnsHandler);
        }
    }
    public void removePlayerCharacter(IPlayerCharacter playerCharacter){
        party.remove(playerCharacter);
    }

    public void removeEnemy(Enemy enemy){
        listaEnemy.remove(enemy);
    }
    /**
     * Agrega un enemy a la lista de enemigos
     *
     */
    public void addEnemy(Enemy enemy){
        if (listaEnemy.size() < this.maxEnemy){
            listaEnemy.add(enemy);
            enemy.addDeathListener(this.EnemyDeathHandler);
            enemy.addTurnsListener(this.turnsHandler);
        }

    }

    /**
     *
     * Equipa un arma proveniente del inventario a un character de jugador, como resultado, esta arma
     * se remueve del inventario
     */
    public void equipFromInventory(int hashweapon, IPlayerCharacter playerCharacter){
        /**
         * demasiado grande??? rompe single responsibility???
         */
        if (playerCharacter.getEquippedWeapon() == null){
            this.putWeaponToPlayerCharacter(hashweapon, playerCharacter);
        }
        else {
            this.swapWeaponWithPlayerCharacter(hashweapon, playerCharacter);
        }
    }

    /**
     * listo
     */
    public void putWeaponToPlayerCharacter(int hashweapon, IPlayerCharacter playerCharacter){
        playerCharacter.equipWeapon(getWeapon(hashweapon));
        if (playerCharacter.getEquippedWeapon().equals(getWeapon(hashweapon))){
            this.removeFromInventory(hashweapon);
        }
    }

    /**
     *
     * listo
     */
    public void swapWeaponWithPlayerCharacter(int hashweapon, IPlayerCharacter playerCharacter){
        IWeapon playerPreviousWeapon = playerCharacter.getEquippedWeapon();
        playerCharacter.equipWeapon(getWeapon(hashweapon));
        if (playerCharacter.getEquippedWeapon().equals(getWeapon(hashweapon))){
            this.addToInventory(playerPreviousWeapon);
            this.removeFromInventory(hashweapon);
        }
    }

    public Boolean checkWeaponInInventory(int hash){
        return inventory.containsKey(hash);
    }

    /**
     * pendiente check enemy in lista, check player character in party para test
     */
    public void attackAnotherCharacter(ICharacter characteratk, ICharacter characterdef){
        characteratk.attack(characterdef);
        characteratk.waitTurn();
    }

    public void PlayerCharacterDeath(String name){
        System.out.println("Un Player Character ha muerto: " + name);
        this.totalPlayerMuertos ++;
        if (this.totalPlayerMuertos == party.size()){
            System.out.println("Player pierde, Enemy gana");
        }
    }

    public void EnemyDeath(String name){
        System.out.println("Un Enemy ha muerto: " + name);
        this.totalEnemyMuertos ++;
        if (this.totalEnemyMuertos == listaEnemy.size()){
            System.out.println("Enemy pierde, Player gana");
        }
    }

    public boolean checkPlayerCharacterInParty(IPlayerCharacter playerCharacter) {
        return this.party.contains(playerCharacter);
    }

    public boolean checkEnemyInListaEnemy(Enemy enemy) {
        return this.listaEnemy.contains(enemy);
    }

    public void turnMade(String name) {
        System.out.println(name + " ha jugado su turno");
    }

}
