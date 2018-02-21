package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sun.util.resources.cldr.lag.CalendarData_lag_TZ;

public class Main extends Application {
    private TableView<StudentRecord> students;


    public static void main(String[] args) { Application.launch(args); }


    @Override public void start(Stage primaryStage) {
        primaryStage.setTitle("Lab05");

        BorderPane layout = new BorderPane();

        TableColumn<StudentRecord, String> idCol = new TableColumn<>("SID");
        idCol.setPrefWidth(100);
        idCol.setCellValueFactory(new PropertyValueFactory<>("sid"));

        TableColumn<StudentRecord, Float> assCol = new TableColumn<>("Assignments");
        assCol.setPrefWidth(200);
        assCol.setCellValueFactory(new PropertyValueFactory<>("assignments"));

        TableColumn<StudentRecord, Float> midCol = new TableColumn<>("Midterm");
        midCol.setPrefWidth(100);
        midCol.setCellValueFactory(new PropertyValueFactory<>("midterm"));

        TableColumn<StudentRecord, Float> feCol = new TableColumn<>("Final Exam");
        feCol.setPrefWidth(100);
        feCol.setCellValueFactory(new PropertyValueFactory<>("FinalExam"));

        TableColumn<StudentRecord, Double> fmCol = new TableColumn<>("Final Mark");
        fmCol.setPrefWidth(100);
        fmCol.setCellValueFactory(new PropertyValueFactory<>("FinalMark"));

        TableColumn<StudentRecord, String> lgCol = new TableColumn<>("Letter Grade");
        lgCol.setPrefWidth(100);
        lgCol.setCellValueFactory(new PropertyValueFactory<>("LetterGrade"));


        this.students = new TableView<>();
        this.students.getColumns().add(idCol);
        this.students.getColumns().add(assCol);
        this.students.getColumns().add(midCol);
        this.students.getColumns().add(feCol);
        this.students.getColumns().add(fmCol);
        this.students.getColumns().add(lgCol);


        layout.setCenter(students);

        Scene scene = new Scene(layout, 700, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        this.students.setItems(DataSource.getAllStudents());


    }

}