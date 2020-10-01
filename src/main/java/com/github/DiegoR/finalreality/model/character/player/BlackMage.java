package com.github.DiegoR.finalreality.model.character.player;


import com.github.DiegoR.finalreality.model.weapon.Knife;
import com.github.DiegoR.finalreality.model.weapon.Staff;
import com.github.cc3002.finalreality.model.character.ICharacter;

import java.util.concurrent.BlockingQueue;

public class BlackMage extends AbstractMageCharacter  {
    /**
     * Creates a new character.
     *
     * @param name       the character's name
     * @param turnsQueue the queue with the characters waiting for their turn
     */
    public BlackMage(BlockingQueue<ICharacter> turnsQueue, String name, int mana) {
        super(turnsQueue,name,mana);
    }
    public void equipKnife(Knife newKnife) {
        this.setEquippedWeapon(newKnife);
    }

    public void equipStaff(Staff newStaff){
        this.setEquippedWeapon(newStaff);
    }

}
/**Cambios:
 * Se agrega esta clase BlackMage:
 -Razon: *Especializa AbstractMageCharacter en cuanto cumple Liskov al serle aplicables todos los metodos y atributos
 de AbstractMageCharacter
 *Ademas permite extensibilidad para los black mages del juego cumpliendo Open Closed Principle lo cual
 parece importante para implementar uso de black magic en el futuro
 * Se agrega equipKnife() y equipStaff(), armas equipables a black mages
 -Razon: *Solucion a haber quitado equip() en ICharacter y AbstractCharacter lo cual rompia Liskov y Single Principle en
 cuanto un mismo metodo tiene por fin equipar un gran numero de armas. Lo que a su vez ademaaaaas rompe Open
 Closed Principle en cuanto si queremos agregar nuevas armas al juego tendriamos que modificar el metodo equip()
 */
