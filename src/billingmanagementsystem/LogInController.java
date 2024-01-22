/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package billingmanagementsystem;

import databaseSQL.DatabaseManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import billingmanagementsystem.Admin_DashboardController;
import billingmanagementsystem.DashboardController;
/**
 * FXML Controller class
 *
 * @author User
 */
public class LogInController implements Initializable {

	@FXML
	private PasswordField passwordField;

	@FXML
	private ImageView showPasswordIcon;

	private Text showPasswordText;
	
	@FXML
	private Label forget_password;

	boolean isShow = false;
    @FXML
    private TextField usernameField;
    @FXML
    private Button loginButton;
    @FXML
    private Button signUpButton;
    @FXML
    private AnchorPane loginPane;
    @FXML
    private TextField usernameSignUp;
    @FXML
    private PasswordField passwordSignUp;
    @FXML
    private PasswordField confirmpasswordSignUp;
    @FXML
    private ComboBox<String> selectRole;
    @FXML
    private TextField usernameSignUp1;
    @FXML
    private PasswordField passwordSignUp1;
    @FXML
    private Label adminText;
    @FXML
    private AnchorPane signUpPane;
    @FXML
    private AnchorPane adminPane;
    @FXML
    private Label employeeSignUp;
    @FXML
    private Label warningtext;
    @FXML
    private Label confirmTxt;
    @FXML
    private Label warningtext1;
	/**
	 * Initializes the controller class.
	 */
	@Override

	public void initialize(URL url, ResourceBundle rb) {
            
         
            selectRole.getItems().addAll("Admin", "Employee");
            
	}
        
        
        private void setLoginStyle() {
         loginButton.setStyle(
            "-fx-background-color: linear-gradient(to bottom left, #F88379, #DE3163);" +
            "-fx-background-radius: 3 3 3 3;" +
            "-fx-border-color: black;" +
            "-fx-border-radius: 3 3 3 3;"
    );
         signUpButton.setStyle("-fx-background-color: white");
}

    private void setSignUpStyle() {
        signUpButton.setStyle(
            "-fx-background-color: linear-gradient(to bottom left, #F88379, #DE3163);" +
            "-fx-background-radius: 3 3 3 3;" +
            "-fx-border-color: black;" +
            "-fx-border-radius: 3 3 3 3;"
    );
        loginButton.setStyle("-fx-background-color: white");
}


	@FXML
	void showPassword(MouseEvent event) {
		
		if (!isShow) {
			showPasswordText.setText(passwordField.getText());
			passwordField.setVisible(false);
			isShow = true;
		}
		
		passwordField.setVisible(true);
		isShow = false;
		
	}

	

    @FXML
    private void login(ActionEvent event) {
    try {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String selectedUserType = selectRole.getValue();

        if (selectedUserType == null) {
            System.out.println("Please select user type");
            return;
        }

        String query = "SELECT * FROM Login WHERE username=? AND password=? AND userType=?";
        try (
            Connection connection = DatabaseManager.getConnection();
            PreparedStatement pst = connection.prepareStatement(query)) {

            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, selectedUserType);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                   
                    openDashboard(selectedUserType, username, event);
                   
                } else {
                    System.out.println("Incorrect username or password");
                    warningtext.setText("Invalid Credentials");
                    warningtext.setStyle("-fx-text-fill: red");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, "Error loading dashboard FXML", ex);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

   private void openDashboard(String userType, String username, ActionEvent event) throws IOException {
    String fxmlPath = null;

    switch (userType) {
        case "Admin":
            fxmlPath = "Admin_Dashboard.fxml";
            break;
        case "Employee":
            fxmlPath = "Admin_Dashboard.fxml";
            break;
        default:
            warningtext.setText("Unknown user type");
            warningtext.setStyle("-fx-text-fill:red");
            return;
    }

    FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
    Parent root = loader.load();
 
    if (userType.equals("Employee")) {
        Admin_DashboardController adminController = loader.getController();
        adminController.setEmployeeButtonVisibility(false);
    }

    Admin_DashboardController adminController = loader.getController();
    adminController.setGreetingText(username );

    warningtext.setText("Login Success!");
    Stage primaryStage = new Stage();
    Scene scene = new Scene(root);
    primaryStage.setTitle("Billing Management System");
    primaryStage.setScene(scene);
    primaryStage.initStyle(StageStyle.UNDECORATED);
    primaryStage.show();

    ((Node) (event.getSource())).getScene().getWindow().hide();
}






	@FXML
	private void forgetpassword(MouseEvent event) {

	}

         @FXML
        private void switchLoginPane(ActionEvent event) {
            setLoginStyle();
        loginPane.setVisible(true);
        adminPane.setVisible(false);
        signUpPane.setVisible(false);
       
    }

    @FXML
    private void switchSignUpPane(ActionEvent event) {
        setSignUpStyle();
        loginPane.setVisible(false);
        adminPane.setVisible(true);
        signUpPane.setVisible(false);
        setSignUpStyle();
    }

    @FXML
    private void checkAdminCredentials(ActionEvent event) {
        
        try {
        String username = usernameSignUp1.getText();
        String password = passwordSignUp1.getText();
        String admin = "Admin";
        String query = "SELECT * FROM Login WHERE username=? AND password=? AND userType=?";
        try (
            Connection connection = DatabaseManager.getConnection();
            PreparedStatement pst = connection.prepareStatement(query)) {

            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, admin);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                        warningtext1.setText("Sucess!");
                        warningtext1.setStyle("-fx-text-fill: #9ACD32");
                        loginPane.setVisible(false);
                        adminPane.setVisible(false);
                        signUpPane.setVisible(true);
                        usernameSignUp1.setText(null);
                        passwordSignUp1.setText(null);
                } else {
                    System.out.println("Incorrect username, password, or userType is not Admin");
                    warningtext1.setStyle("-fx-text-fill");
                    warningtext1.setText("Invalid Credentials");
                }
            }
        }    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    }

    @FXML
     private void signUp(ActionEvent event) {
    try {
        String username = usernameSignUp.getText();
        String password = passwordSignUp.getText();
        String confirmPassword = confirmpasswordSignUp.getText();
        String userType = employeeSignUp.getText(); 

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            confirmTxt.setText("Fill up all fields.");
            confirmTxt.setStyle("-fx-text-fill: red");
            return;
        }

        if (!password.equals(confirmPassword)) {
            confirmTxt.setText("Passwords do not match");
            confirmTxt.setStyle("-fx-text-fill: red");
            return;
        }

        String query = "INSERT INTO Login (username, password, userType) VALUES (?, ?, ?)";
        try (
            Connection connection = DatabaseManager.getConnection();
            PreparedStatement pst = connection.prepareStatement(query)) {

            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, userType);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                usernameSignUp.setText(null);
                passwordSignUp.setText(null);
                confirmpasswordSignUp.setText(null);
                confirmTxt.setText("Account Created");
                confirmTxt.setStyle("-fx-text-fill: #9ACD32");
            } else {
                System.out.println("Signup failed. No rows were affected.");
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

    @FXML
    private void exitLogin(MouseEvent event) {
        Stage stage = (Stage) ((ImageView) event.getSource()).getScene().getWindow();
        stage.close();
    }

    
    

}
