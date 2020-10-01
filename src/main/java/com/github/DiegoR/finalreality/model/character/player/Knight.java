package com.github.DiegoR.finalreality.model.character.player;

import com.github.DiegoR.finalreality.model.weapon.Axe;
import com.github.DiegoR.finalreality.model.weapon.Bow;
import com.github.DiegoR.finalreality.model.weapon.Sword;
import com.github.cc3002.finalreality.model.character.ICharacter;

import java.util.concurrent.BlockingQueue;

public class Knight extends AbstractCommonCharacter {

    /**
     * Creates a new character.
     *
     * @param name       the character's name
     * @param turnsQueue the queue with the characters waiting for their turn
     */
    public Knight(BlockingQueue<ICharacter> turnsQueue, String name) {
        super(turnsQueue,name);
    }
    public  void equipSword(Sword newSword){ this.setEquippedWeapon(newSword);}
    public void equipAxe(Axe newAxe {
        this.setEquippedWeapon(newAxe);
    }
    public void equipKnife(Bow newBow) {
        this.setEquippedWeapon(newBow);
    }
}
/**Cambios:
 * Se agrega esta clase Knight:
 -Razon: *Especializa AbstractCommonCharacter en cuanto cumple Liskov al serle aplicables todos los metodos y atributos
 de AbstractCommonCharacter
 *Ademas permite extensibilidad para los knights del juego cumpliendo Open Closed Principle lo cual
 parece importante para implementar metodos y atributos unicos de knights en el futuro
 * Se agrega equipSword(), equipAxe() y equipKnife(), armas equipables a knights
 -Razon: *Solucion a haber quitado equip() en ICharacter y AbstractCharacter lo cual rompia Liskov y Single Principle en
 cuanto un mismo metodo tiene por fin equipar un gran numero de armas. Lo que a su vez ademaaaaas rompe Open
 Closed Principle en cuanto si queremos agregar nuevas armas al juego tendriamos que modificar el metodo equip()
 */

