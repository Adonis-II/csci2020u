import javafx.scene.*;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.input.*;
import javafx.collections.*;
import javafx.event.*;

import java.io.*;
import java.net.*;

public class Sample1 extends Application {
  private BorderPane layout;
  //private TableView<Student> table;

  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("Adonis' Aplication ");

    // create our menu
    Menu fileMenu = new Menu("File");
    MenuItem newMenuItem = new MenuItem("New"/*, icon */);
    newMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
    fileMenu.getItems().add(newMenuItem);
    fileMenu.getItems().add(new SeparatorMenuItem());
    fileMenu.getItems().add(new MenuItem("Open"));
    fileMenu.getItems().add(new SeparatorMenuItem());
    fileMenu.getItems().add(new MenuItem("Save"));
    fileMenu.getItems().add(new MenuItem("Save As..."));
    fileMenu.getItems().add(new SeparatorMenuItem());

    MenuItem exitMenuItem = new MenuItem("Exit");
    newMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
    exitMenuItem.setOnAction( event -> System.exit(0) );
    fileMenu.getItems().add(exitMenuItem);

    MenuBar menuBar = new MenuBar();
    menuBar.getMenus().add(fileMenu);

    // main table
    // table = new TableView<>();
    // table.setItems(DataSource.getAllStudents());
    // table.setEditable(true);
    // TableColumn<Student, Integer> sidColumn = null;
    // sidColumn = new TableColumn<>("SID");
    // sidColumn.setMinWidth(100);
    // sidColumn.setCellValueFactory(new PropertyValueFactory<>("sid"));

    // initialize the border pane
    layout = new BorderPane();
    // TODO: Place UI elements
    layout.setTop(menuBar);

    Scene scene = new Scene(layout, 600, 600);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
