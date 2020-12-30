package com.github.cc3002.finalreality.gui;

import com.github.cc3002.finalreality.model.controller.Controller;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
/**
 * Main entry point for the application.
 * <p>
 * <Complete here with the details of the implemented application>
 *
 * @author Ignacio Slater Muñoz.
 * @author <Your name>
 */
public class FinalReality extends Application {
  //gui muestra el estado actual del controller, asi que crea uno y bajo el se basa el juego
  //Notas grales: queremos que controlador esconda el modelo, no trabajar con clases del modelo, ni en listas ni en nada
  //trabajar en cambio con enteros, strings, etc
  Controller controller = new Controller(100,100);
  //dejamos como atributos los nodos vacios que queramos mutar constantemente
  //en el futuro los cambiamos con sus metodos setters
  Label contadorLabel = new Label();
  Label phaseActualLabel = new Label();

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    //timer para el refresco de pantalla
    setupTimer();
    //le pone titulo a la ventana
    primaryStage.setTitle("Prueba de JavaFX");
    //tamano de la ventana puede ser problematico ya que habria que redefinir tamano de los componentes
    primaryStage.setResizable(false);
    //hacemos nodo padre del scene graph usando group, hay varias formas group es simple,
    // cuidado con import group que no sea swing
    //aparte de group esta label,horizontalbox o verticalbox que tienen layout a diferencia de group que no lo tiene
    //los anteriores tambien son nodos
    Group root = new Group();
    //creamos la escena a partir del scene graph pasandole el nodo padre de todos
    Scene scene = new Scene(root, 800, 600);
    //podemos agregar hijos en el orden que queramos, la cosa es que esten antes de show y que e padre exista previamente
    //label, un texto en pantalla, tambien es un nodo
    Label label = new Label("Prueba de label");
    //definimos posicion del label en x e y donde parte la primera letra
    //modificaciones antes de agregarlos al scene graph
    label.setLayoutX(400);
    label.setLayoutY(300);
    //otro label
    Label label2 = new Label("Otro label");
    //modificacion de la posicion
    label2.setLayoutX(400);
    label2.setLayoutY(310);
    //simplemente lo hacemos hijo del nodo padre de el scene graph
    root.getChildren().add(label2);
    //lo asignamos como hijo del nodo padre del scene graph
    root.getChildren().add(label);
    //asignamos la escena a la ventana(stage), cada stage puede tener una sola escena(scene)
    //podemos hacer que muestre dos escena creando otro scene graph e intercambiar el setScene
    primaryStage.setScene(scene);

    //definimos un boton, otro nodo del scene graph
    Button button1 = new Button("Imprimir la cuenta");
    //posicionamiento del boton igual que cualquier nodo
    button1.setLayoutX(100);
    button1.setLayoutY(100);
    //definimos accion del boton, siempre es setOnAction(new... se autocompleta
    button1.setOnAction(event -> controller.imprimirPrueba());
    //lo agregamos al scene graph
    root.getChildren().add(button1);

    Button buttonAumentador = new Button("Aumentar la cuenta");
    buttonAumentador.setLayoutX(700);
    buttonAumentador.setLayoutY(400);
    buttonAumentador.setOnAction(event -> controller.aumentarContador());
    root.getChildren().add(buttonAumentador);
    //otro boton para cambiar de phase
    Button buttonCambioPhase = new Button("Siguiente phase");
    buttonAumentador.setLayoutX(800);
    buttonAumentador.setLayoutY(600);
    buttonAumentador.setOnAction(event -> controller.aumentarContador());
    root.getChildren().add(buttonAumentador);


    //ingresamos al scene graph las label variables
    contadorLabel.setLayoutX(100);
    contadorLabel.setLayoutY(20);
    root.getChildren().add(contadorLabel);
    root.getChildren().add(phaseActualLabel);

    //muestra la ventada, se hace al final
    primaryStage.show();

  }

  /**
   * metodo para la actualizacion de la imagen en ventana
   * aca setteamos los nodos atributos
   * recomendaciones, dejar como atributos clase los nodos modificables, ver atributos arriba
   * metodo para animation timer, usa Animation Timer de java fx
   */
  private void setupTimer(){
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        contadorLabel.setText("Contador actual: " + controller.getContador());
        phaseActualLabel.setText("Phase actual: " + controller.getPhase().toString());
      }
    };
    //con esto si se actualiza la vista
    timer.start();
  }

}

