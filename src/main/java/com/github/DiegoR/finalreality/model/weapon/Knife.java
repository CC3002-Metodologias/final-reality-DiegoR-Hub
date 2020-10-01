package com.github.DiegoR.finalreality.model.weapon;

public class Knife extends AbstractCommonWeapon {

    /**
     * Creates a weapon with a name, a base damage, speed and it's type.
     *
     * @param name
     * @param damage
     * @param weight
     */
    public Knife(String name, int damage, int weight) {
        super(name, damage, weight);
    }
}
/**Cambios:
 * Se agrega esta clase Knife:
 -Razon: *Especializa AbstractCommonWeapon en cuanto cumple Liskov al serle aplicables todos los metodos y atributos
 de AbstractCommonWeapon
 *Ademas permite extensibilidad para las armas knife del juego cumpliendo Open Closed Principle lo cual
 parece importante para implementar metodos y atributos unicos de knifes en el futuro
 */