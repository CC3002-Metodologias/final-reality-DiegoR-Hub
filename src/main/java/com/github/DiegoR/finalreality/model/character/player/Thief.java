package com.github.DiegoR.finalreality.model.character.player;

import com.github.DiegoR.finalreality.model.weapon.Bow;
import com.github.DiegoR.finalreality.model.weapon.Staff;
import com.github.DiegoR.finalreality.model.weapon.Sword;
import com.github.cc3002.finalreality.model.character.ICharacter;

import java.util.concurrent.BlockingQueue;

public class Thief extends AbstractCommonCharacter {

    /**
     * Creates a new character.
     *
     * @param name       the character's name
     * @param turnsQueue the queue with the characters waiting for their turn
     */
    public Thief(BlockingQueue<ICharacter> turnsQueue, String name) {
        super(turnsQueue,name);
    }
    public void equipSword(Sword newSword) {
        this.setEquippedWeapon(newSword);
    }
    public void equipStaff(Staff newStaff) {
        this.setEquippedWeapon(newStaff);
    }
    public void equipBow(Bow newBow) {
        this.setEquippedWeapon(newBow);
    }

}
/**Cambios:
 * Se agrega esta clase Thief:
 -Razon: *Especializa AbstractCommonCharacter en cuanto cumple Liskov al serle aplicables todos los metodos y atributos
 de AbstractCommonCharacter
 *Ademas permite extensibilidad para los thiefs del juego cumpliendo Open Closed Principle lo cual
 parece importante para implementar metodos y atributos unicos de thiefs en el futuro
 * Se agrega equipSword(), equipStaff() y equipBow(), armas equipables a thiefs
 -Razon: *Solucion a haber quitado equip() en ICharacter y AbstractCharacter lo cual rompia Liskov y Single Principle en
 cuanto un mismo metodo tiene por fin equipar un gran numero de armas. Lo que a su vez ademaaaaas rompe Open
 Closed Principle en cuanto si queremos agregar nuevas armas al juego tendriamos que modificar el metodo equip()
 */

