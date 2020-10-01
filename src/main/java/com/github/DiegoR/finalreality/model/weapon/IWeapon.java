package com.github.DiegoR.finalreality.model.weapon;

public interface IWeapon {
    String getName();

    int getDamage() ;

    int getWeight() ;

    boolean equals(final Object o);

    int hashCode();
}
/**Cambios:
 * Se agrega interfaz IWeapon
 -Razon: Que sirva de contrato para toda arma a crear. Toma arma tiene un name, un damage y un weight
 *
 */
