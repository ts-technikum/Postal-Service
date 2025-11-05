package com.postalservice.client;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
public class ClientApp extends Application {
  @Override public void start(Stage stage){
    MainView view = new MainView();
    stage.setTitle("Postal Service");
    stage.setScene(new Scene(view.root(), 900, 500));
    stage.show();
  }
  public static void main(String[] args){ launch(args); }
}
