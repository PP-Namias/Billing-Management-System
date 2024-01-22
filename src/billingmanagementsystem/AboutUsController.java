/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package billingmanagementsystem;

import databaseSQL.DatabaseManager;
import java.sql.*;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AboutUsController implements Initializable {

    @FXML
    private TextField searchbox;
    @FXML
    private TextField employeeUsername;
    @FXML
    private TextField employeePassword;
    @FXML
    private Label confirmTxt;
    @FXML
    private TableView<EmployeeData> employeeTable;
    @FXML
    private TableColumn<EmployeeData, Integer> userIDCol;
    @FXML
    private TableColumn<EmployeeData, String> usernameCol;
    @FXML
    private TableColumn<EmployeeData, String> passwordCol;
    @FXML
    private TableColumn<EmployeeData, String> userTypeCol;
    @FXML
    private Label userIDLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        employeeTable();
    }    

    @FXML
    private void SearchCustomer(KeyEvent event) {
     String input = searchbox.getText().toLowerCase();

    if (!input.isEmpty()) {
        try {
            ObservableList<EmployeeData> originalList = retrieveEmployeesFromDatabase();
            ObservableList<EmployeeData> filteredList = FXCollections.observableArrayList();

            for (EmployeeData employee : originalList) {
                if (matchesCriteria(employee, input)) {
                    filteredList.add(employee);
                }
            }

            employeeTable.getItems().setAll(filteredList);
        } catch (SQLException ex) {
            Logger.getLogger(AboutUsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else {
        employeeTable.getItems().clear();
        refreshEmployeeTable(); 
    }
}


    private ObservableList<EmployeeData> retrieveEmployeesFromDatabase() throws SQLException {
    String emp = "Employee";
    Connection con = DatabaseManager.getConnection();
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery("SELECT * FROM login WHERE userType = '" + emp + "'");

        ObservableList<EmployeeData> employeeList = FXCollections.observableArrayList();

    while (rs.next()) {
        employeeList.add(new EmployeeData(
                rs.getInt("userID"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("userType")
        ));
    }

    return employeeList;
}

    private boolean matchesCriteria(EmployeeData employee, String filter) {
    return employee.getUsername().toLowerCase().contains(filter);
    }

    private void refreshEmployeeTable() {
    try {
        employeeTable.getItems().clear();
        employeeTable.getItems().addAll(retrieveEmployeesFromDatabase());
    } catch (SQLException ex) {
        Logger.getLogger(AboutUsController.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    @FXML
    private void addEmployee(MouseEvent event) {
         try {
        String username = employeeUsername.getText();
        String password = employeePassword.getText();
        String userType = "Employee";
        // Check if username and password fields are empty
        if (username.isEmpty() || password.isEmpty()) {
            confirmTxt.setText("Please fill both fields");
            confirmTxt.setStyle("-fx-text-fill: red");
            return;
        }

        if (isUsernameExists(username)) {
             confirmTxt.setText("Username already exists");
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
                employeeTable();
                confirmTxt.setText("Account "+username+ " created!");
                employeeUsername.setText(null);
                employeePassword.setText(null);
            } else {
                confirmTxt.setText("Error try again!");
                confirmTxt.setStyle("-fx-text-fill: red");
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

       private boolean isUsernameExists(String username) throws SQLException {
        String query = "SELECT * FROM Login WHERE username=?";
        try (
        Connection connection = DatabaseManager.getConnection();
        PreparedStatement pst = connection.prepareStatement(query)) {

        pst.setString(1, username);

        try (ResultSet rs = pst.executeQuery()) {
            return rs.next();
        }
    }
        
    }

    @FXML
    private void editEmployee(MouseEvent event) {
    try {
        String username = employeeUsername.getText();
        String password = employeePassword.getText();
        int id = Integer.parseInt(userIDLabel.getText());

        if (username.isEmpty() || password.isEmpty()) {
            confirmTxt.setText("Please fill both fields");
            confirmTxt.setStyle("-fx-text-fill: red");
            return;
        }

        String updateQuery = "UPDATE Login SET username=?, password=? WHERE userID=?";
        try (
            Connection connection = DatabaseManager.getConnection();
            PreparedStatement updatePst = connection.prepareStatement(updateQuery)) {

            updatePst.setString(1, username);
            updatePst.setString(2, password);
            updatePst.setInt(3, id);

            int rowsAffected = updatePst.executeUpdate();

            if (rowsAffected > 0) {
                employeeTable();
                confirmTxt.setText("Account Updated!");
                employeeUsername.setText(null);
                employeePassword.setText(null);
            } else {
                confirmTxt.setText("Error! Try Again!");
                confirmTxt.setStyle("-fx-text-fill: red");
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}


    @FXML
    private void deleteEmployee(MouseEvent event) {
        
        try {
        int userID = Integer.parseInt(userIDLabel.getText());

        String deleteQuery = "DELETE FROM Login WHERE userID=?";
        try (
            Connection connection = DatabaseManager.getConnection();
            PreparedStatement deletePst = connection.prepareStatement(deleteQuery)) {

            deletePst.setInt(1, userID);

            int rowsAffected = deletePst.executeUpdate();

            if (rowsAffected > 0) {
                employeeTable();
                confirmTxt.setText("Account Deleted");
                employeeUsername.setText(null);
                employeePassword.setText(null);
            } else {
               confirmTxt.setText("Error! Try Again!");
            confirmTxt.setStyle("-fx-text-fill: red");
            }
        }
    } catch (NumberFormatException | SQLException ex) {
             confirmTxt.setText("Error! Try Again!");
            confirmTxt.setStyle("-fx-text-fill: red");
        ex.printStackTrace();
    }
    }
    
   
    @FXML
    private void tableClick(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {

        EmployeeData selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();

        if (selectedEmployee != null) {

            userIDLabel.setText(String.valueOf(selectedEmployee.getUserID()));
            employeeUsername.setText(selectedEmployee.getUsername());
            employeePassword.setText(selectedEmployee.getPassword());
        }
    }
    }
    
    
    private void employeeTable(){
		 try
		 {
                        String emp = "Employee";
			 Connection con = DatabaseManager.getConnection();
			 Statement st = con.createStatement();
			 ResultSet rs = st.executeQuery("SELECT * FROM login WHERE userType = '" + emp + "'");
                          employeeTable.getItems().clear();
			 while (rs.next()) {
				 employeeTable.getItems().add(new EmployeeData(
						 rs.getInt("userID"),
						 rs.getString("username"),
                                                 rs.getString("password"),
                                                 rs.getString("userType")
						 
						 ));
			 }
		 } catch (SQLException ex)
		 {
			 Logger.getLogger(ProductTableController.class.getName()).log(Level.SEVERE, null, ex);
		 }
		 userIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
		 usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
		 passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
		 userTypeCol.setCellValueFactory(new PropertyValueFactory<>("userType"));
	 }

}
