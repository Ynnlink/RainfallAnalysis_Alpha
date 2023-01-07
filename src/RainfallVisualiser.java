import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.Group;
import javafx.stage.Stage;
import textio.TextIO;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This file can be used to draw a chart that effectively represents rainfall data.  Just fill in
 * the definition of drawPicture with the code that draws your picture.
 */
public class RainfallVisualiser extends Application {

    /**
     * Draws a picture.  The parameters width and height give the size
     * of the drawing area, in pixels.
     */

    public void drawPicture(GraphicsContext g, int width, int height) {
        // draw the x-axis and y-axis
        var margin = 20;
        var origin = new Point2D(margin, height - margin);

        g.setStroke(Color.BLACK);
        g.setLineWidth(1);
        g.setFont(Font.font(24));

        g.strokeLine(origin.getX(), origin.getY(), origin.getX(), margin);
        g.strokeLine(origin.getX(), origin.getY(), width - margin, origin.getY());

        TextIO.getln(); // ignore the header line

        var xAxisLength = width - 2.0 * margin;
        var yAxisLength = height - 2.0 * margin;
        var columnWidth = xAxisLength / 218.0;
        var maxMonthlyTotal = 1500.0;
        var currentX = origin.getX();

        // draw the monthly totals as a bar chart
        g.setLineWidth(1);
        g.setFill(Color.RED);
        while (!TextIO.eof()) {
            var record = TextIO.getln().trim().split(",");

            var monthlyTotal = Double.parseDouble(record[2]);

            var columnHeight = monthlyTotal / maxMonthlyTotal * yAxisLength;

            var x = currentX;
            var y = origin.getY() - columnHeight;
            g.fillRect(x, y, columnWidth, columnHeight);

            currentX += columnWidth;
        }
    } // end drawPicture()


    //------ Implementation details: DO NOT EDIT THIS ------
    /*
    public void start(Stage stage) {
        int width = 218 * 6 + 40;
        int height = 500;
        Canvas canvas = new Canvas(width, height);
        drawPicture(canvas.getGraphicsContext2D(), width, height);
        BorderPane root = new BorderPane(canvas);
        root.setStyle("-fx-border-width: 4px; -fx-border-color: #444");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Rainfall Visualiser");
        stage.show();
        stage.setResizable(false);
    }
     */



    public void start(Stage stage) {

        TextIO.getln(); // ignore the header line

        var year = new ArrayList<String>();

        //Defining the axes
        CategoryAxis xAxis = new CategoryAxis();
        //empty categories
        xAxis.setCategories(FXCollections.observableArrayList());
        xAxis.setLabel("year");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Units = mm");

        //Creating the Bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Rainfall Visualiser");

        //generate bar chart using analysed data
        while (!TextIO.eof()) {
            var record = TextIO.getln().trim().split(",");

            //axis year
            /*
            if (!year.contains(record[0])) {
                year.add(record[0]);
            } else {
                continue;
            }

             */

            //axis data
            XYChart.Series<String, Number> series = new XYChart.Series<>();

            var monthlyTotal = Double.parseDouble(record[2]);

            series.setName(record[0]);
            series.getData().add(new XYChart.Data<>(record[1], monthlyTotal));

            barChart.getData().add(series);


        }

        //fill in categories
        xAxis.setCategories(FXCollections.observableArrayList(year));





        Group root = new Group(barChart);

        //Creating a scene object
        Scene scene = new Scene(root, 218 * 6 + 40, 500);


        /*
        int width = 218 * 6 + 40;
        int height = 500;
        Canvas canvas = new Canvas(width, height);
        drawPicture(canvas.getGraphicsContext2D(), width, height);
        BorderPane root = new BorderPane(canvas);
        root.setStyle("-fx-border-width: 4px; -fx-border-color: #444");
        Scene scene = new Scene(root);

         */
        stage.setScene(scene);
        stage.setTitle("Rainfall Visualiser");
        stage.show();
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        System.out.print("Enter path: ");
        // var path = TextIO.getln();

        var path = "src/data/MountSheridanStationCNS.csv";

        //use static methods to analyse rainfall data
        TextIO.readFile(path);
        String savePath = RainfallAnalyser.generateSavePath(path);
        RainfallAnalyser.analyseDataset(savePath);

        //input generated rainfall data
        TextIO.readFile(savePath);

        launch();
    }

} // end SimpleGraphicsStarter
