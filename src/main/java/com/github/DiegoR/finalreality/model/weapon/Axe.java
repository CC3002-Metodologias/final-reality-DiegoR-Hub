package com.github.DiegoR.finalreality.model.weapon;

public class Axe extends AbstractCommonWeapon {
    /**
     * Creates a weapon with a name, a base damage, speed and it's type.
     *
     * @param name
     * @param damage
     * @param weight
     */
    public Axe(String name, int damage, int weight) {
        super(name, damage, weight);
    }
}
/**Cambios:
 * Se agrega esta clase Axe:
 -Razon: *Especializa AbstractCommonWeapon en cuanto cumple Liskov al serle aplicables todos los metodos y atributos
 de AbstractCommonWeapon
 *Ademas permite extensibilidad para las armas axe del juego cumpliendo Open Closed Principle lo cual
 parece importante para implementar metodos y atributos unicos de axes en el futuro
 */