package com.github.cc3002.finalreality.model.weapon;

public class MagicWeapon extends AbstractWeapon {

    private int magicDamage;

    public MagicWeapon(String name, int damage, int weight, int magicDamage) {
        super(name, damage, weight);
        this.magicDamage=magicDamage;
    }
}