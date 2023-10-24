/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package billingmanagementsystem;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */

public class Admin_DashboardController implements Initializable {

    @FXML
    private Button dashboard_btn;
    @FXML
    private Button product_btn;
    @FXML
    private Button billing_btn;
    @FXML
    private Button about_us_btn;
    @FXML
    private Button sign_out_btn;
    @FXML
    private AnchorPane customer_pane;
    @FXML
    private AnchorPane product_pane;
    @FXML
    private AnchorPane billing_pane;
    @FXML
    private Button customer_btn;
    @FXML
    private AnchorPane dashboard_pane;
    @FXML
    private ImageView icon1;
    @FXML
    private ImageView icon2;
    @FXML
    private ImageView icon3;
    @FXML
    private ImageView icon4;
    @FXML
    private ImageView icon5;
    @FXML
    private AnchorPane about_us_pane;
    @FXML
    private ImageView icon51;

    /**
     * Initializes the controller class.
     */
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    

    
    private Button lastClickedButton = null;
    @FXML
    private void Switch_Pane(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();

        if (clickedButton == lastClickedButton) {
            // Ignore the click if the same button was clicked twice in a row
            return;
        }

        if (clickedButton == dashboard_btn) {
           
        }

        // Update the last clicked button
        lastClickedButton = clickedButton;
        
        if (clickedButton == dashboard_btn) {
            dashboard_pane.setVisible(true);
            customer_pane.setVisible(false);
            product_pane.setVisible(false);
            billing_pane.setVisible(false);
            about_us_pane.setVisible(false);


        } else if (clickedButton == customer_btn) {
            dashboard_pane.setVisible(false);
            customer_pane.setVisible(true);
            product_pane.setVisible(false);
            billing_pane.setVisible(false);
            about_us_pane.setVisible(false);

        } else if (clickedButton == product_btn) {
            dashboard_pane.setVisible(false);
            customer_pane.setVisible(false);
            product_pane.setVisible(true);
            billing_pane.setVisible(false);
            about_us_pane.setVisible(false);
            
        } else if (clickedButton == billing_btn) {
            dashboard_pane.setVisible(false);
            customer_pane.setVisible(false);
            product_pane.setVisible(false);
            billing_pane.setVisible(true);
            about_us_pane.setVisible(false);

        } else if (clickedButton == about_us_btn) {
            dashboard_pane.setVisible(false);
            customer_pane.setVisible(false);
            product_pane.setVisible(false);
            billing_pane.setVisible(false);
            about_us_pane.setVisible(true);
        } 
    }
    
    /*private void setButtonColor(Button button, boolean isSelected) {
        if (isSelected) {
            button.getStyleClass().add("selected-button");
        } else {
            button.getStyleClass().remove("selected-button");
        }
    }*/

@FXML
private void changestyle(MouseEvent event) {
    dashboard_btn.pseudoClassStateChanged(PseudoClass.getPseudoClass("active"), event.getSource() == dashboard_btn);
    customer_btn.pseudoClassStateChanged(PseudoClass.getPseudoClass("active"), event.getSource() == customer_btn);
    product_btn.pseudoClassStateChanged(PseudoClass.getPseudoClass("active"), event.getSource() == product_btn);
    billing_btn.pseudoClassStateChanged(PseudoClass.getPseudoClass("active"), event.getSource() == billing_btn);
    about_us_btn.pseudoClassStateChanged(PseudoClass.getPseudoClass("active"), event.getSource() == about_us_btn);
  
}

    @FXML
    private void logout(ActionEvent event) {
    
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Logout Confirmation");
    alert.setHeaderText(null);
    alert.setContentText("Are you sure you want to log out?");

    // Get the user's response
    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LogIn.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }
    

    }
    
    
    }
    
    
