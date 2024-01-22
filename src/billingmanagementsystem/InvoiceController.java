/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package billingmanagementsystem;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Worker;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.pdmodel.PDDocument;
import pdfGeneration.PDFGenerator;
import customer.Customer;
import billings.Bill;
import java.sql.Date;
import lineItems.LineItem;
/**
 * FXML Controller class
 *
 * @author User
 */
public class InvoiceController implements Initializable {

    private AnchorPane InvoicePane;
    @FXML
    private Label previewID;
    @FXML
    private Label dueDate;
    @FXML
    private Label dateIssued;
    @FXML
    private Label firsName;
    @FXML
    private Label email;
    @FXML
    private Label contactNumber;
    @FXML
    private TableView<?> previewTable;
    @FXML
    private TableColumn<?, ?> productName;
    @FXML
    private TableColumn<?, ?> quantity;
    @FXML
    private TableColumn<?, ?> price;
    @FXML
    private TableColumn<?, ?> subtotal;
    @FXML
    private Label totalamount;
    @FXML
    private Label address;
    @FXML
    private Label country;
    @FXML
    private Label city;
    @FXML
    private Label postal;
    @FXML
    private Label shipfirstName;
    @FXML
    private Label shipemail;
    @FXML
    private Label shipContactNumber;
    @FXML
    private Label shipAddress;
    @FXML
    private Label shipCountry;
    @FXML
    private Label shipCity;
    @FXML
    private Label shipPostal;
    @FXML
    private Label lastName;
    @FXML
    private Label shiplastName;
   
    private Bill bill;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
   public void setInvoiceData(Bill bill) {
        this.bill = bill;

        // Now you can access the non-static method on the instance
        previewID.setText(String.valueOf(bill.getBillID()));
        // Set other labels using bill instance as needed
    }

 

    @FXML
    private void Close(MouseEvent event) {
        Stage stage = (Stage) ((ImageView) event.getSource()).getScene().getWindow();
        stage.close();
    }
    
}
