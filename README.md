Final Reality
=============

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a 
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)

Context
-------

This project's goal is to create a (simplified) clone of _Final Fantasy_'s combat, a game developed
by [_Square Enix_](https://www.square-enix.com)
Broadly speaking for the combat the player has a group of characters to control and a group of 
enemies controlled by the computer.

Version Entrega 1
---
Con fin en respetar los principios de diseño SOLID se realizaron las siguientes modificaciones:
* Se elimina tanto clase ENUM CharacterClass como ENUM WeaponType ya que rompe Single Responsibility
* Se divide el metodo waitTurn() de AbstractCharacter a Enemy y PlayerCharacter ya que rompe Single Responsibility
* Se elimina el metodo equip() ya que rompe Single Responsibility al usarse para equipar un conjunto de armas distintas. A su vez
que tambien se elimina por romper Liskov en cuanto un enemigo no puede equipar armas
* Se mueve el metodo getEquippedWeapon() desde AbstractCharacter a Player Character ya que rompe Liskov en cuanto 
un enemigo no debe tener una equippedWeapon
Para mayor detalle de los cambios realizados dirigirse a ChangeLog

Version Entrega 2
---
 