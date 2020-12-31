ChangeLog
=========
Version 3.0
----------


Version 2.0
-----------
CAMBIOS EN PACKAGE CHARACTER
- (B.1) Se elimina ENUM CharacterClass
- (B.2) Se crean cinco clases que extienden PlayerCharacter, a ser BlackMage, 
WhiteMage, Knight, Thief, Engineer.
- (B.3) A cada una de las clases del punto B.2 se implementa un metodo equals y hashCode
- (B.4) Se incluye atributo mana tanto a WhiteMage como BlackMage
- (B.5) Se elimina metodo waitTurn de AbstractCharacter y en respuesta se implementa por separado
en Enemy y PlayerCharacter
- (B.6) Se elimina el metodo equip y en cambio se implementan equip especificos a cada arma, a ser
equipAxe, equipStaff, equipSword, equipKnife, equipBow en cada clase correspondiente que pueda equiparlas. 
- (B.7) Se mueve el metodo getEquippedWeapon desde AbstractCharacter a Player Character

CAMBIOS EN PACKAGE WEAPON
- (B.1) Se elimina ENUM WeaponType
- (B.2) Se crean cinco clases que extienden AbstractWeapon, a ser Axe, Staff, Knife, Sword, Bow
- (B.3) A cada una de las clases del punto B.2 se implementa un metodo equals y hashCode
- (B.4) Se crea interfaz IWeapon con los metodos getters
- (B.5) Se incluye atributo magicDamage a Staff

Version 1.0
-----------
- (RC.1) Implemented missing tests
- (B.5) Updated License
- (B.4) Implementation and testing of Enemy class (ensured 100% branch coverage)
- (B.3) Created .gitignore
- (B.2) Implementation of most base elements of the model
- (B.1) Created project

A note on the version naming
----------------------------
- B.n: Version ``x.y`` _beta x_, alternative to ``x.y-b.n``.
  For example: ``v1.0-b.3``.
- RC.n: Release candidate x, alternative to ``x.y-rc.n``.
  For example: ``v1.0-rc.2``.