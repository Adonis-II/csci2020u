package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.PieChart;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;

import java.util.*;

public class lab07 extends Application {
    private Map<String,Integer> wordCounts;
    public lab07() {
        wordCounts = new TreeMap<>();
    }
    private static String[] Warning = {
            "FLASH FLOOD", "SPECIAL MARINE", "SEVERE THUNDERSTORM", "TORNADO"
    };
    private static int[] count = {
            4849, 4007, 18041, 2298
    };
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(

            new PieChart.Data(Warning[0],count[0]),
            new PieChart.Data(Warning[1],count[1]),
            new PieChart.Data(Warning[2],count[2]),
            new PieChart.Data(Warning[3],count[3])


    );

    private Canvas canvas;

    public static void main(String args[]) {

        getData("src/sample/weatherwarnings-2015.csv", ",");
        lab07 wordCounter = new lab07();
        File dataDir = new File("src/sample/weatherwarnings-2015.csv");
        File outFile = new File("src/sample/random.txt");
        try {
            wordCounter.processFile(dataDir);
            wordCounter.outputWordCounts(2, outFile);
        } catch (FileNotFoundException e) {
            System.err.println("Invalid input dir: " + dataDir.getAbsolutePath());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        primaryStage.setTitle("Lab 07");
        primaryStage.setScene(new Scene(chart));
        primaryStage.show();
    }



    public void processFile(File file) throws IOException {
        System.out.println("Processing " + file.getAbsolutePath() + "...");
        if (file.isDirectory()) {
            // process all the files in that directory
            File[] contents = file.listFiles();
            for (File current: contents) {
                processFile(current);
            }
        } else if (file.exists()) {
            // count the words in this file
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("\\s");//"[\s\.;:\?\!,]");//" \t\n.;,!?-/\\");
            while (scanner.hasNext()) {
                String word = scanner.next();
                if (isWord(word)) {
                    countWord(word);
                }
            }
        }
    }

    private boolean isWord(String word) {
        String pattern = "^[a-zA-Z]+$";
        if (word.matches(pattern)) {
            return true;
        } else {
            return false;
        }
    }

    private void countWord(String word) {
        if (wordCounts.containsKey(word)) {
            int oldCount = wordCounts.get(word);
            wordCounts.put(word, oldCount+1);
        } else {
            wordCounts.put(word, 1);
        }
    }

    public void outputWordCounts(int minCount, File outFile)
            throws IOException {
        System.out.println("Saving word counts to " + outFile.getAbsolutePath());
        System.out.println("# of words: " + wordCounts.keySet().size());
        if (!outFile.exists()) {
            outFile.createNewFile();
            if (outFile.canWrite()) {
                PrintWriter fileOut = new PrintWriter(outFile);

                Set<String> keys = wordCounts.keySet();
                Iterator<String> keyIterator = keys.iterator();

                while (keyIterator.hasNext()) {
                    String key = keyIterator.next();
                    int count = wordCounts.get(key);

                    if (count >= minCount) {
                        fileOut.println(key + ": " + count);
                    }
                }

                fileOut.close();
            } else {
                System.err.println("Error:  Cannot write to file: " + outFile.getAbsolutePath());
            }
        } else {
            System.err.println("Error:  File already exists: " + outFile.getAbsolutePath());
            System.out.println("outFile.exists(): " + outFile.exists());
            System.out.println("outFile.canWrite(): " + outFile.canWrite());
        }
    }

    public static void getData(String path, String delimiter) {
        List<String> srcList = new ArrayList<>();
        // Special Case
        if (path == null || path.length() == 0) {
            return;
        }
        if(delimiter == null || delimiter.length() == 0) {
            delimiter = ",";
        }
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            //Read the next line until end of file
            for (String line; (line = br.readLine()) != null;) {
                //Parse the line
                String[] values = line.split(delimiter);
                for(int i = 0 ; i < values.length; i++) {
                    System.out.print(values[i]);
                }
                System.out.print("\n");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
