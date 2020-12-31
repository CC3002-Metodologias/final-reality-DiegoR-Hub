package com.github.cc3002.finalreality.gui;

import com.github.cc3002.finalreality.controller.Controller;
import com.github.cc3002.finalreality.model.character.player.Enemy;
import com.github.cc3002.finalreality.model.character.player.IPlayerCharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Main entry point for the application.
 * Permanente: indica los stats del character en turno actual, la cantidad de player character vivos
 * la cantidad de enemy vivos
 * Variante: Se compone de 3 fases, inicio, equipamiento de armas y ataque, cada una cambia
 * los botones, su cantidad, nombre y funcionalidad. Estos se encuentran en la parte derecha de la
 * Stage
 *
 * @author Ignacio Slater Muñoz.
 * @author Diego Ruiz R.
 */
public class FinalReality extends Application {
  //gui muestra el estado actual del controller, asi que crea uno y bajo el se basa el juego
  //Notas grales: queremos que controlador esconda el modelo, no trabajar con clases del modelo, ni en listas ni en nada
  //trabajar en cambio con enteros, strings, etc
  Controller controller = new Controller(100,100);
  Label nombreActual = new Label();
  Label vidaActual = new Label();
  Label defensaActual = new Label();
  Label ataqueActual = new Label();
  Label phaseActualLabel = new Label();
  Label cantidadEnemyVivo = new Label();
  Label cantidadPlayerCharacterVivo = new Label();
  Group buttonGroup = new Group();
  List<String> nombresButtons = new ArrayList<String>();
  Group endSceneGroup = new Group();
  Scene endGameScene;
  Stage principalStage;


  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws InterruptedException {
    //definicion de endGameScene
    this.endGameScene = new Scene(this.endSceneGroup, 800,600);

    principalStage = primaryStage;
    controller.basicSetup();
    principalStage.setTitle("Final Reality");
    principalStage.setResizable(false);
    //nodo padre
    Group root = new Group();
    //escena
    Scene scene = new Scene(root, 800, 600);
    //definimos las posiciones de los label del character actual
    nombreActual.setLayoutX(25);
    nombreActual.setLayoutY(250);
    vidaActual.setLayoutX(25);
    vidaActual.setLayoutY(275);
    defensaActual.setLayoutX(25);
    defensaActual.setLayoutY(300);
    ataqueActual.setLayoutX(25);
    ataqueActual.setLayoutY(325);
    cantidadPlayerCharacterVivo.setLayoutY(20);
    cantidadEnemyVivo.setLayoutY(40);

    //ingreamos los nodos al scene graph
    root.getChildren().add(nombreActual);
    root.getChildren().add(vidaActual);
    root.getChildren().add(defensaActual);
    root.getChildren().add(ataqueActual);
    root.getChildren().add(phaseActualLabel);
    root.getChildren().add(cantidadPlayerCharacterVivo);
    root.getChildren().add(cantidadEnemyVivo);

    //Lista con los nombres de los botones en la fase actual
    this.nombresButtons = controller.getPhase().nombreBotones();
    //Crea los botones a partir de la lista de nombres de botones
    //this.setterNombreBotones(this.nombresButtons, this.buttonGroup);
    //agrega los botones al nodo root
    root.getChildren().add(buttonGroup);


    //Crea boton para pasar a la siguiente fase y lo agrega a root
    Button buttonCambioPhase = new Button("next phase");
    buttonCambioPhase.setLayoutX(75);
    buttonCambioPhase.setLayoutY(500);
    buttonCambioPhase.setOnAction(event -> {
      try {
        controller.getPhase().nextPhase();
        nombresButtons = controller.getPhase().nombreBotones();
        this.setterNombreBotones(this.nombresButtons, this.buttonGroup);

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    root.getChildren().add(buttonCambioPhase);



  //timer para el refresco de pantalla
    setupTimer();
    //asignamos el scene graph al stage
    principalStage.setScene(scene);

    //muestra la ventada, se hace al final
    principalStage.show();

  }

  /**
   * Metodo que designa los nombres a los botones
   */
  public void setterNombreBotones(List<String> listaNombreBotones, Group buttons ){
    buttons.getChildren().clear();
    int posx = 300;
    int posy = 100;
    for (String nombreButton : listaNombreBotones){
      Button boton = new Button(nombreButton);
      boton.setLayoutX(posx);
      boton.setLayoutY(posy);
      boton.setOnAction(event -> controller.getPhase().action(nombreButton));
      buttons.getChildren().add(boton);
      if (posx == 700){
        posy = 100;
        posx += 200;
      }
      else{
        posy+= 100;
      }
    }
  }

  /**
   * Metodo para el refresco de imagen en la Stage
   * actualiza los stats del jugador en turno actual, la cantidad de enemy vivos, la cantidad de
   * player character vivos y los botones segun la fase en que se encuentra el turno/juego
   * Ademas genera una stage en caso que los enemy ganen o los player character ganen indicando su
   * victoria
   *
   */
  private void setupTimer(){
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        if(controller.isEnemyWin()){
          Label gameEndLabel = new Label("GANAN LOS ENEMIGOS");
          gameEndLabel.setLayoutX(250);
          gameEndLabel.setLayoutY(400);
          endSceneGroup.getChildren().add(gameEndLabel);
          principalStage.setScene(endGameScene);
        }
        else if(controller.isPlayerCharacterWin()){
          Label gameEndLabel = new Label("GANAN LOS PLAYER CHARACTER");
          gameEndLabel.setLayoutX(250);
          gameEndLabel.setLayoutY(400);
          endSceneGroup.getChildren().add(gameEndLabel);
          principalStage.setScene(endGameScene);
        }
        else {
          phaseActualLabel.setText("Phase actual: " + controller.getPhase().printPhase());
          cantidadEnemyVivo.setText("Enemigos vivos restantes: " + String.valueOf(controller.getListaEnemy().size()));
          cantidadPlayerCharacterVivo.setText("Player character vivos restantes: " + String.valueOf(controller.getParty().size()));
          nombreActual.setText("Nombre del jugador actual: " + controller.getJugadorActual().getName());
          vidaActual.setText("Vida de jugador actual: " + controller.getJugadorActual().getHealthPoints());
          defensaActual.setText("Defensa del jugador actual: " + controller.getJugadorActual().getDefensePoints());
          ataqueActual.setText("Ataque del jugador actual: " + controller.getJugadorActual().getDamage());
        }
      }
    };
    //con esto si se actualiza la vista
    timer.start();
  }

}

