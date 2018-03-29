package sample;
/*
    Code -stolen- used from Devon McGrath. Thanks <3 your code is always better
    and cleaner than mine
*/
import javafx.application.Application;
import javafx.collections.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.chart.*;

public class lab06 extends Application {

    private static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    private static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };
    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };


    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(

            new PieChart.Data(ageGroups[0],purchasesByAgeGroup[0]),
            new PieChart.Data(ageGroups[1],purchasesByAgeGroup[1]),
            new PieChart.Data(ageGroups[2],purchasesByAgeGroup[2]),
            new PieChart.Data(ageGroups[3],purchasesByAgeGroup[3]),
            new PieChart.Data(ageGroups[4],purchasesByAgeGroup[4]),
            new PieChart.Data(ageGroups[5],purchasesByAgeGroup[5])

    );


    private Canvas canvas;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 425, Color.WHITE);

        this.canvas = new Canvas();
        this.canvas.widthProperty().bind(primaryStage.widthProperty());
        this.canvas.heightProperty().bind(primaryStage.heightProperty());

        root.getChildren().add(canvas);
        final PieChart chart = new PieChart(pieChartData);
        chart.setLegendVisible(true);

        primaryStage.setTitle("Lab 06 pie graph");
        primaryStage.setScene(new Scene(chart));
        primaryStage.show();



        drawPieChart(0.0,0.0,0.0,0.0,new Series(pieChartData,ageGroups,purchasesByAgeGroup,pieColours) );


    }


    private void addColours(ObservableList<PieChart.Data> pieChartData, String... pieColors) {

        GraphicsContext g = canvas.getGraphicsContext2D();
        int i = 0;
        for (PieChart.Data data : pieChartData) {
            data.getNode().setStyle("-fx-pie-color: " + pieColors[i % pieColors.length] + ";");
            i++;
        }

    }

    private void drawPieChart(double cX, double cY, double rX, double rY, Series... series){

        addColours(pieChartData,"AQUA", "GOLD", "DARKORANGE","DARKSALMON", "LAWNGREEN", "PLUM");



    }

    public static class Series {
        public String[] ageGroups;
        public int[] values;
        public Color[] colour;
        public ObservableList<PieChart.Data> pieChartData;


        public Series() {
            this.values = new int[0];
            this.colour[0] = Color.YELLOW;
        }

        public Series(ObservableList<PieChart.Data> pieChartData,String[] ageGroups, int[] values, Color[] colour) {
            this.ageGroups = ageGroups;
            this.values = values;
            this.colour = colour;
            this.pieChartData = pieChartData;

        }




    }
}