import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import textio.TextIO;

import java.util.ArrayList;
import java.util.Scanner;

import static javafx.application.Platform.exit;

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

        var years = new ArrayList<String>();

        //defining the axes
        CategoryAxis xAxis = new CategoryAxis();
        //x-axis with empty categories
        xAxis.setCategories(FXCollections.observableArrayList());
        xAxis.setLabel("year");
        //y-axis using mm as unit
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Units = mm");

        //creating the bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

        //define bar chart title
        try {
            var pathElements = TextIO.getInputFileName().trim().split("/");
            var filenameElements = pathElements[1].trim().split("\\.");
            var stationName = filenameElements[0].trim().split("_");
            barChart.setTitle(stationName[0] + " Rainfall Analysis");
        } catch (Exception e) {
            System.out.println("File name error!");
        }

        //creating data series using months
        //Jan Feb Mar Apr May June July Aug Sept Oct Nov Dec
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Jan");
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Feb");
        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("Mar");
        XYChart.Series<String, Number> series4 = new XYChart.Series<>();
        series4.setName("Apr");
        XYChart.Series<String, Number> series5 = new XYChart.Series<>();
        series5.setName("May");
        XYChart.Series<String, Number> series6 = new XYChart.Series<>();
        series6.setName("June");
        XYChart.Series<String, Number> series7 = new XYChart.Series<>();
        series7.setName("July");
        XYChart.Series<String, Number> series8 = new XYChart.Series<>();
        series8.setName("Aug");
        XYChart.Series<String, Number> series9 = new XYChart.Series<>();
        series9.setName("Sept");
        XYChart.Series<String, Number> series10 = new XYChart.Series<>();
        series10.setName("Oct");
        XYChart.Series<String, Number> series11 = new XYChart.Series<>();
        series11.setName("Nov");
        XYChart.Series<String, Number> series12 = new XYChart.Series<>();
        series12.setName("Dec");


        //generate bar chart using analysed data
        while (!TextIO.eof()) {
            var record = TextIO.getln().trim().split(",");

            //extract data
            var year = record[0];
            var month = record[1];
            var monthlyTotal = Double.parseDouble(record[2]);

            //axis year
            if (!years.contains(year)) {
                years.add(year);
            }

            //fill in data according to month
            switch (month) {
                case "1":
                    series1.getData().add(new XYChart.Data<>(year, monthlyTotal));
                    continue;
                case "2":
                    series2.getData().add(new XYChart.Data<>(year, monthlyTotal));
                    continue;
                case "3":
                    series3.getData().add(new XYChart.Data<>(year, monthlyTotal));
                    continue;
                case "4":
                    series4.getData().add(new XYChart.Data<>(year, monthlyTotal));
                    continue;
                case "5":
                    series5.getData().add(new XYChart.Data<>(year, monthlyTotal));
                    continue;
                case "6":
                    series6.getData().add(new XYChart.Data<>(year, monthlyTotal));
                    continue;
                case "7":
                    series7.getData().add(new XYChart.Data<>(year, monthlyTotal));
                    continue;
                case "8":
                    series8.getData().add(new XYChart.Data<>(year, monthlyTotal));
                    continue;
                case "9":
                    series9.getData().add(new XYChart.Data<>(year, monthlyTotal));
                    continue;
                case "10":
                    series10.getData().add(new XYChart.Data<>(year, monthlyTotal));
                    continue;
                case "11":
                    series11.getData().add(new XYChart.Data<>(year, monthlyTotal));
                    continue;
                case "12":
                    series12.getData().add(new XYChart.Data<>(year, monthlyTotal));
                    continue;
            }

            //XYChart.Series<String, Number> series = new XYChart.Series<>();
            //series.setName(month);
            //series.getData().add(new XYChart.Data<>(year, monthlyTotal));
            //barChart.getData().add(series);

        }



        //fill in categories
        xAxis.setCategories(FXCollections.observableArrayList(years));

        //fill in data
        barChart.getData().addAll(series1,series2,series3,series4,series5,series6,series7,series8,series9,series10,series11,series12);


        //barChart.setBarGap(5);
        //barChart.setCategoryGap(20);

        //barChart.setMinWidth(0);
        //barChart.setMaxWidth(1500);

        barChart.setBarGap(1);

        //Group root = new Group(barChart);

        //Creating a scene object
        Scene scene = new Scene(barChart, 1500, 500);


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

        var flag = true;

        while (flag) {

            System.out.print("Enter file path (enter exit to stop): ");
            Scanner sc = new Scanner(System.in);
            String path = sc.nextLine();
            //path = TextIO.getln();
            //String path = null;
            //try {


            //} catch (Exception e){
                //System.out.println("errorrr");
            //}

            if (path.equals("exit")) {
                flag = false;
                exit();
            } else {
                //var path = "src/data/MountSheridanStationCNS.csv";
                //use static methods to analyse rainfall data
                var processPath = RainfallAnalyser.processData(path);

                if (processPath == null || processPath.equals("fail")) {
                    continue;
                }

                TextIO.readFile(processPath);

                System.out.println("Statistical charts have been generated!");
                launch();
                flag = false;

            }
        }
    }

} // end SimpleGraphicsStarter
