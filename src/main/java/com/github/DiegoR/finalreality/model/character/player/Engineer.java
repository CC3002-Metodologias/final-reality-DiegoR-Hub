package com.github.DiegoR.finalreality.model.character.player;

import com.github.DiegoR.finalreality.model.weapon.Axe;
import com.github.DiegoR.finalreality.model.weapon.Bow;
import com.github.cc3002.finalreality.model.character.ICharacter;

import java.util.concurrent.BlockingQueue;

public class Engineer extends AbstractCommonCharacter {

    /**
     * Creates a new character.
     *
     * @param name       the character's name
     * @param turnsQueue the queue with the characters waiting for their turn
     */
    public Engineer(BlockingQueue<ICharacter> turnsQueue, String name) {
        super(turnsQueue,name);
    }

    public void equipAxe(Axe newAxe {
        this.setEquippedWeapon(newAxe);
    }
    public void equipKnife(Bow newBow) {
        this.setEquippedWeapon(newBow);
    }
}
/**Cambios:
 * Se agrega esta clase Engineer:
 -Razon: *Especializa AbstractCommonCharacter en cuanto cumple Liskov al serle aplicables todos los metodos y atributos
 de AbstractCommonCharacter
 *Ademas permite extensibilidad para los engineers del juego cumpliendo Open Closed Principle lo cual
 parece importante para implementar metodos y atributos unicos de engineers en el futuro
 * Se agrega equipAxe() y equipKnife(), armas equipables a engineers
 -Razon: *Solucion a haber quitado equip() en ICharacter y AbstractCharacter lo cual rompia Liskov y Single Principle en
 cuanto un mismo metodo tiene por fin equipar un gran numero de armas. Lo que a su vez ademaaaaas rompe Open
 Closed Principle en cuanto si queremos agregar nuevas armas al juego tendriamos que modificar el metodo equip()
 */

