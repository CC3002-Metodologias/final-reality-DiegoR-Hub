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
Con fin en implementar la mecanica de ataque entre instancias ICharacter se realizacion las siguientes modificaciones:
* Double dispatch: Metodos attackedBy() y attack() en AbstractCharacter
Con fin en implementar la mecanica de equipamiento de armas a PlayerCharacter se realizacion las siguientes modificaciones:
* Double dispatch: Metodos equippedByEngineer(), equippedByBlackMage() y asi para toda subclase de AbstractPlayerCharacter en AbstractWeapon
que no realizan ninguna accion. En cada subclase de AbstractWeapon se hace override a estos metodos cuando si son equipables a la clase correspondiente
Con fin en implementar un controller se crea una clase Controller tal que:
* Tiene atributo listaEnemy y Party, dos List e Inventory un hashmap que almacena IWeapons dado su nombre como llave
* Metodos getter para Enemy y PlayerCharacter que dado un indice, retornan el valor de los atributos para aquella instancia de listaEnemy o Party correspondiente
* Metodos create para Enemy, cada subclase de IPlayerCharacter y cada subclase de IWeapon tal que reciben los atributos correspondientes y crean una instancia en listaEnemy,
 Party y Inventory respectivamente
* Metodos PlayerCharacterDeath EnemyDeath tal que dado un observer de trigger en metodo setDead() de AbstractCharacter detecta cuando muere una instancia Enemy de listaEnemy o cuando muere una instancia de IPlayerCharacter
de Party. Ademas, si han muerto todas las instancias de Party indica que ganan los Enemy y si han muerto
todas las instancia de listaEnemy indica que gana el jugador
* Metodo equipFromInventory() tal que dada una llave de inventory y un indice de Party, equipa un arma de Inventory a un PlayerCharacter de Party.
Si aquella instancia de Party tiene equipada un arma, hace un swap en inventory, sino, solo se la agrega
* Metodo waitTurnEnemy() y waitTurnPlayerCharacter(), indica a una blocking queue auxiliar si se agrega un Enemy o un IPlayerCharacter a la queue turns, con este metodo se tiene el final de turno
* Metodo beginTurn(), obtiene el siguiente ICharacter de la queue, gracias a la blocking queue auxiliar sabe si es PlayerCharacter o Enemy. En caso de ser Enemy realiza un ataque aleatorio a una instancia de 
Party con el metodo attackRandomPlayerCharacter(), sino, espera input del usuario con metodo usersAction() a implementar
  
Version Final, Entrega 3
-------
* Para EJECUTAR EL PROGRAMA, ejecutar la clase FinalReality dentro del package gui
Aparece una ventana con los stats del character en turno actual, el numero
de enemy aun vivos, el numero de player character aun vivos y un string que indica la fase
del turno en que se encuentra actualmente.
* Ademas se muestra un boton de siguiente fase, al pulsarlo, si el character en turno actual es un enemy, realiza un
ataque aleatorio a alguna instancia de la party y se genera el siguiente turno del juego, en cambio si es 
un player character de party, se va a la siguiente fase, eleccion de armas
* En la FASE DE ELECCION DE ARMAS se muestra un conjunto de botones con los nombres de las armas actualmente en inventario
* Al pulsar los botones se intenta equipar aquella arma al jugador en turno actual
* El equipamiento se puede hacer un numero indefinido y libre de veces
* Nota: El arma desequipada aparecera en inventario el siguiente turno
* Con el boton de siguiente fase, se transiciona a la fase de eleccion de target
* En la FASE DE ELECCION DE TARGET se muestra nuevamente otro conjunto de botones, esta vez con 
nombre de los enemys en el juego 
* Al pulsar los botones se elige al correspondiente enemigo como target
* Al pulsar siguiente fase el jugador actual realiza un ataque al target y se genera el siguiente turno
* Nota: Las instancias de armas, player character y enemy en juego son un ejemplo tipo de juego de Final Reality,
modificando el metodo basicSetup() en controller se pueden generar otras configuraciones