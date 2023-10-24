/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package billingmanagementsystem;

import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class BillingManagementSystem extends Application {
    
    @Override
    public void start(Stage primaryStage){
        Parent root = null;
        
        String icon = getClass().getResource("/Graphics/capybara.png").toExternalForm();
        primaryStage.getIcons().add(new javafx.scene.image.Image(icon));       
        try {
            root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(BillingManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        Scene scene = new Scene(root);

        primaryStage.setTitle("Billing Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
}

