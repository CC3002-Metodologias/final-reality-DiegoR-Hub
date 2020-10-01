package com.github.DiegoR.finalreality.model.weapon;


public abstract class AbstractCommonWeapon extends AbstractWeapon {
    /**
     * Creates a weapon with a name, a base damage, speed and it's type.
     *
     * @param name
     * @param damage
     * @param weight
     */
    public AbstractCommonWeapon(String name, int damage, int weight) {
        super(name, damage, weight);
    }
}
/**Cambios:
 * Se agrega esta clase AbstractCommonWeapon:
 -Razon: *Especializa AbstractWeapon en cuanto cumple Liskov al serle aplicables todos los metodos de AbstractWeapon a la
 clase AbstractCommonWeapon y que AbstractCommonWeapon tenga todos los metodos de AbstractWeapon
 *Ademas permite extensibilidad para las weapon comunes del juego cumpliendo Open Closed Principle

 */