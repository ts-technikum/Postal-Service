package com.postalservice.client;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.*;
import java.util.concurrent.CompletableFuture;
public class MainView {
  private final TableView<Map<String,String>> table = new TableView<>();
  private final ApiClient api = new ApiClient();
  public Parent root(){
    TextField name = new TextField(); name.setPromptText("Name");
    TextField country = new TextField(); country.setPromptText("Country (AT/DE/CH)");
    TextField weight = new TextField(); weight.setPromptText("Weight (kg)");
    Button sendLetter = new Button("Send Letter");
    sendLetter.setOnAction(e -> run(() -> api.sendLetter(country.getText(), name.getText())));
    Button sendParcel = new Button("Send Package");
    sendParcel.setOnAction(e -> run(() -> {
      try { double w = Double.parseDouble(weight.getText()); api.sendParcel(w, name.getText()); }
      catch(NumberFormatException nfe){ Platform.runLater(() -> new Alert(Alert.AlertType.ERROR, "Weight must be a number").showAndWait()); }
    }));
    Button refresh = new Button("Refresh");
    refresh.setOnAction(e -> run(this::loadStatus));
    TableColumn<Map<String,String>, String> idCol = new TableColumn<>("ID"); idCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().get("id")));
    TableColumn<Map<String,String>, String> typeCol = new TableColumn<>("Type"); typeCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().get("type")));
    TableColumn<Map<String,String>, String> nameCol = new TableColumn<>("Name"); nameCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().get("name")));
    TableColumn<Map<String,String>, String> extraCol = new TableColumn<>("Extra"); extraCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().get("extra")));
    TableColumn<Map<String,String>, String> statusCol = new TableColumn<>("Status"); statusCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().get("status")));
    table.getColumns().addAll(idCol, typeCol, nameCol, extraCol, statusCol);
    HBox inputs = new HBox(10, name, country, weight, sendLetter, sendParcel, refresh);
    VBox root = new VBox(10, inputs, table);
    root.setPadding(new Insets(12));
    return root;
  }
  private void run(ThrowingRunnable r){
    CompletableFuture.runAsync(() -> { try { r.run(); loadStatus(); } catch(Exception ex){ ex.printStackTrace(); } });
  }
  private void loadStatus() throws Exception {
    String json = api.status();
    var list = new ArrayList<Map<String,String>>();
    var mapper = new com.fasterxml.jackson.databind.ObjectMapper();
    var root = mapper.readTree(json).get("items");
    for (var n : root){
      Map<String,String> m = new HashMap<>();
      m.put("id", n.get("id").asText());
      m.put("type", n.get("type").asText());
      m.put("name", n.get("name").asText());
      m.put("extra", n.get("extra").asText());
      m.put("status", n.get("status").asText());
      list.add(m);
    }
    Platform.runLater(() -> { table.getItems().setAll(list); });
  }
  @FunctionalInterface interface ThrowingRunnable { void run() throws Exception; }
}
