/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package billingmanagementsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class LogInController implements Initializable {
       
    /**
     * Initializes the controller class.
     */
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    
    
  

    @FXML
    private void login(ActionEvent event) {
        Parent root = null;
    try {
        root = FXMLLoader.load(getClass().getResource("Admin_Dashboard.fxml"));
    } catch (IOException ex) {
        Logger.getLogger(BillingManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
    }

    Stage primaryStage = new Stage(); 
    Scene scene = new Scene(root);
    
    
    primaryStage.setTitle("Billing Management System");
    primaryStage.setScene(scene);
    primaryStage.show();
    

    // Hide the current window if needed
    ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
}
