package com.github.DiegoR.finalreality.model.weapon;

public class Sword extends AbstractCommonWeapon {
    /**
     * Creates a weapon with a name, a base damage, speed and it's type.
     *
     * @param name
     * @param damage
     * @param weight
     */
    public Sword(String name, int damage, int weight) {
        super(name, damage, weight);
    }
}
/**Cambios:
 * Se agrega esta clase Sword:
 -Razon: *Especializa AbstractCommonWeapon en cuanto cumple Liskov al serle aplicables todos los metodos y atributos
 de AbstractCommonWeapon
 *Ademas permite extensibilidad para las armas sword del juego cumpliendo Open Closed Principle lo cual
 parece importante para implementar metodos y atributos unicos de swords en el futuro
 */