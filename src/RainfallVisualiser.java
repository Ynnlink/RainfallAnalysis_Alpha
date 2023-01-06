import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import textio.TextIO;

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

    public static void main(String[] args) {
        System.out.print("Enter path: ");
        //var path = TextIO.getln();
        //var path = "src/main/resources/MountSheridanStationCNS.csv";
        var path = "src/MountSheridanStationCNS_analysed.csv";
        TextIO.readFile(path);
        launch();
    }

} // end SimpleGraphicsStarter
