package com.github.DiegoR.finalreality.model.weapon;

public abstract class AbstractMagicWeapon extends AbstractWeapon {
    private int magicDamage;
    /**
     * Creates a weapon with a name, a base damage, speed and it's type.
     *
     * @param name
     * @param damage
     * @param weight
     */
    public AbstractMagicWeapon(String name, int damage, int weight, int magicDamage) {
        super(name, damage, weight);
        this.magicDamage=magicDamage;
    }
}
/**Cambios:
 * Se agrega esta clase AbstractMagicWeapon:
 -Razon: *Especializa AbstractWeapon en cuanto cumple Liskov al serle aplicables todos los metodos de AbstractWeapon a la
 clase AbstractMagicWeapon y que AbstractMagicWeapon tenga todos los metodos de AbstractWeapon y a la vez agrega un
 nuevo atributo magicDamage
 *Ademas permite extensibilidad para las weapon magic del juego cumpliendo Open Closed Principle

 */