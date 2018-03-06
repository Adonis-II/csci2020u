package sample;
/*
    Code -stolen- used from Devon McGrath. Thanks <3 your code is always better
    and cleaner than mine
*/
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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

        primaryStage.setTitle("Lab 06 pie graph");
        primaryStage.setScene(scene);
        primaryStage.show();



    }




    public static class Series {
        public double[] values;
        public Color colour;
        public String label;

        public Series() {
            this.values = new double[0];
            this.colour = Color.YELLOW;
        }

        public Series(double[] values, Color colour) {
            this.values = values;
            this.colour = colour;
        }

        public Series(double[] values, Color color, String label) {
            this.values = values;
            this.colour = colour;
            this.label = label;
        }

        public double[] getValues() {
            return values;
        }
        public void setValues(double[] values) {
            this.values = values;
        }

        public Color getColour() {
            return colour;
        }
        public void setColour(Color colour) {
            this.colour = colour;
        }

        public String getLabel() {
            return label;
        }
        public void setLabel(String label) {
            this.label = label;
        }
    }
}