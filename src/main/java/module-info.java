module finalreality.main {
  /**
   * queremos que este paquete
   */
  exports com.github.cc3002.finalreality.gui;
  /**
   * sea visible para java fx
   */
  requires javafx.controls;
  requires org.jetbrains.annotations;
    requires java.desktop;
}
