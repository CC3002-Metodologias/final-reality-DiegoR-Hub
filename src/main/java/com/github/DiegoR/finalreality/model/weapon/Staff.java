package com.github.DiegoR.finalreality.model.weapon;

public class Staff extends AbstractMagicWeapon{
    /**
     * Creates a weapon with a name, a base damage, speed and it's type.
     *
     * @param name
     * @param damage
     * @param weight
     * @param magicDamage
     */
    public Staff(String name, int damage, int weight, int magicDamage) {
        super(name, damage, weight, magicDamage);
    }
}
/**Cambios:
 * Se agrega esta clase Staff:
 -Razon: *Especializa AbstractMagicWeapon en cuanto cumple Liskov al serle aplicables todos los metodos y atributos
 de AbstractMagicWeapon
 *Ademas permite extensibilidad para las armas staff del juego cumpliendo Open Closed Principle lo cual
 parece importante para implementar metodos y atributos unicos de staffs en el futuro
 */