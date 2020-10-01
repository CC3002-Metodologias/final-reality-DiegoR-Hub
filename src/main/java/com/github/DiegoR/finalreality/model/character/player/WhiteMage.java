package com.github.DiegoR.finalreality.model.character.player;

import com.github.DiegoR.finalreality.model.weapon.Staff;
import com.github.cc3002.finalreality.model.character.ICharacter;

import java.util.concurrent.BlockingQueue;

public class WhiteMage extends AbstractMageCharacter  {
    /**
     * Creates a new character.
     *
     * @param name       the character's name
     * @param turnsQueue the queue with the characters waiting for their turn
     */
    public WhiteMage(BlockingQueue<ICharacter> turnsQueue, String name, int mana) {
        super(turnsQueue,name,mana);
    }
    public void equipStaff(Staff newStaff){
        this.setEquippedWeapon(newStaff);
    }

}
/**Cambios:
 * Se agrega esta clase WhiteMage:
 -Razon: *Especializa AbstractMageCharacter en cuanto cumple Liskov al serle aplicables todos los metodos y atributos
 de AbstractMageCharacter
 *Ademas permite extensibilidad para los white mages del juego cumpliendo Open Closed Principle lo cual
 parece importante para implementar uso de white magic en el futuro
 * Se agrega equipStaff(), arma equipable a white mages
 -Razon: *Solucion a haber quitado equip() en ICharacter y AbstractCharacter lo cual rompia Liskov y Single Principle en
 cuanto un mismo metodo tiene por fin equipar un gran numero de armas. Lo que a su vez ademaaaaas rompe Open
 Closed Principle en cuanto si queremos agregar nuevas armas al juego tendriamos que modificar el metodo equip()
 */
