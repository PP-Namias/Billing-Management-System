
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package billingmanagementsystem;

import databaseSQL.DatabaseManager;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

/**
 * FXML Controller class
 *
 * @author User
 */
public class DashboardController implements Initializable {

    @FXML
    private Label totalorders;
    @FXML
    private Label totalrevenue;
    @FXML
    private Label totalproducts;
    @FXML
    private Label totalcustomer;
    @FXML
    private LineChart<String, Number> chart1;
    @FXML
    private LineChart<Number, Number> chart2;
    @FXML
    private NumberAxis xAxisChart2;
    @FXML
    private CategoryAxis xAxisChart1;
    @FXML
    private PieChart pieChart;
    @FXML
    private AnchorPane dashboardPane;
    @FXML
    private Label msgLabel;
    @FXML
    private Label greetingText;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        COUNTTOTALPRODUCTS();
        COUNTTOTALCUSTOMER();
       PopulateChart1();
       SUMOFPRODUCTS();
       PopulateChart2();
       PopulatePieChart();
       COUNTTOTALORDERS();
    }    
    
    
    
    private int COUNTTOTALPRODUCTS() {
        int count = 0;
        try {
            Connection connection = DatabaseManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM products");

            if (resultSet.next()) {
                String total;
                total = resultSet.getString("COUNT(*)");
                totalproducts.setText(total);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
        }

        return count;
    }
    
     private int COUNTTOTALCUSTOMER() {
        int count = 0;
        try {
            Connection connection = DatabaseManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM customers");

            if (resultSet.next()) {
                String total;
                total = resultSet.getString("COUNT(*)");
                totalcustomer.setText(total);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
        }

        return count;
    }
    
    private int COUNTTOTALORDERS() {
        int count = 0;
        try {
            Connection connection = DatabaseManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM lineitems");

            if (resultSet.next()) {
                String total;
                total = resultSet.getString("COUNT(*)");
                totalorders.setText(total);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
        }

        return count;
    }
    
    
     private int SUMOFPRODUCTS() {
        int count = 0;
        try {
            Connection connection = DatabaseManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT SUM(lineItemTotal) FROM lineitems");

            if (resultSet.next()) {
                String total;
                total = resultSet.getString("SUM(lineItemTotal)");
                totalrevenue.setText(total);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
        }

        return count;
    }
    
    private XYChart.Series<String, Number> PopulateChart1() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        try {
             Connection connection = DatabaseManager.getConnection();

             Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT creationDate, COUNT(*) AS customerCount FROM customers GROUP BY creationDate");
            while (resultSet.next()) {
                Timestamp date = resultSet.getTimestamp("creationDate");
                String stringdate = new SimpleDateFormat("yyyy-MM-dd").format(date);
                int count = resultSet.getInt("customerCount");

                series.getData().add(new XYChart.Data<>(stringdate, count));
            }

            chart1.getData().add(series);
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return series;
    }

    private XYChart.Series<Number, Number> PopulateChart2() {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();

        try {
             Connection connection = DatabaseManager.getConnection();

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT billID AS billCount, lineItemTotal FROM lineitems GROUP BY billID");
            while (resultSet.next()) {
                int billcount = resultSet.getInt("billCount");
                int price = resultSet.getInt("lineItemTotal");

                series.getData().add(new XYChart.Data<>(billcount, price));
            }

            chart2.getData().add(series);
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return series;
    }
    
    private void PopulatePieChart() {
        try {
            Connection connection = DatabaseManager.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS productCount, Price FROM products GROUP BY Price");

            while (resultSet.next()) {
                int prodcount = resultSet.getInt("productCount");
                int price = resultSet.getInt("Price");

                PieChart.Data data = new PieChart.Data(String.valueOf(price), prodcount);
                pieChart.getData().add(data);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void PrintREPORT(MouseEvent event) {
     try {
    int pdfNumber = 1; 
    String baseFileName = "REPORT";
    LocalDate localDate = LocalDate.now();
    String dateNow = localDate.toString();
    

    while (Files.exists(Paths.get("C:\\Users\\User\\Documents\\bmsReport\\" + baseFileName + " " + pdfNumber +" " + dateNow + ".pdf"))) {
        pdfNumber++; 
    }

    String outputPath = "C:\\Users\\User\\Documents\\bmsReport\\" + baseFileName + " " + pdfNumber + " "+ dateNow + ".pdf";

    PDDocument document = new PDDocument();
    PDPage page = new PDPage(new PDRectangle(792, 612));
    document.addPage(page);

    PDPageContentStream contentStream = new PDPageContentStream(document, page);

   AnchorPane dashPane = dashboardPane;
    WritableImage snapshot = dashPane.snapshot(new SnapshotParameters(), null);
    BufferedImage bufferedImage = SwingFXUtils.fromFXImage(snapshot, null);
    PDImageXObject pdImage = LosslessFactory.createFromImage(document, bufferedImage);

    float pageWidth = 792;
    float pageHeight = 612;

    float imageWidth = 10f * 72;
    float imageHeight = 7.5f * 72;

    float x = (pageWidth - imageWidth) / 2;
    float y = (pageHeight - imageHeight) / 2;
    contentStream.drawImage(pdImage, x, y, imageWidth, imageHeight);

    contentStream.close();

    document.save(outputPath);
    document.close();

    msgLabel.setText("Report created and save!");
    
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    msgLabel.setText(null);
                }
            }));
            timeline.play();

} catch (IOException e) {
    e.printStackTrace();
}


    }
    
    
   

    
}


    

