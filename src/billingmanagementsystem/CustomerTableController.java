/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package billingmanagementsystem;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import customer.Customer;
import customer.CustomerDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class CustomerTableController implements Initializable {

	@FXML
	private TableView<Customer> customer_table;
	@FXML
	private TableColumn<Customer, Integer> customerID;
	@FXML
	private TableColumn<Customer, String> firstName;
	@FXML
	private TableColumn<Customer, String> lastName;
	@FXML 
	private TableColumn<Customer,String> contactNumCol;
	@FXML
	private TableColumn<Customer, String> email;
	@FXML
	private TableColumn<Customer, String> address;
	@FXML
	private TableColumn<Customer, String> town;
	@FXML
	private TableColumn<Customer, String> country;
	@FXML
	private TableColumn<Customer, String> postal;
	@FXML
	private TextField searchbox;

	CustomerDAOImpl CustomerDAO = new CustomerDAOImpl();
	boolean customerFlag = true;
	/**
	 * Initializes the controller class.
	 */
	 @Override
	 public void initialize(URL url, ResourceBundle rb) {
		 customerTable();
	 }    
	 private BillingsController billingsController;
	 public void setBillingsController(BillingsController controller) {
		 this.billingsController = controller;
	 }



	 @FXML
	 private void tableclicked(MouseEvent event) {
		 if (event.getClickCount() == 1) {
			 Customer customer = customer_table.getSelectionModel().getSelectedItem();
			 if (customer != null && billingsController != null) {
				 billingsController.setCustomerData(customer);
				 
				 ((Node) (event.getSource())).getScene().getWindow().hide();
			 }
		 }
	 }

	 @FXML
	 private void searchcustomer(KeyEvent event) throws SQLException {
		 String input = searchbox.getText();
		 if (!input.isEmpty()) {
			 ObservableList<Customer> originalList = customer_table.getItems();
			 ObservableList<Customer> filteredList = FXCollections.observableArrayList();

			 for (Customer customer : originalList) {
				 if (String.valueOf(customer.getCustomerId()).contains(input)
						 || customer.getFirstName().toLowerCase().contains(input.toLowerCase())
						 || customer.getLastName().toLowerCase().contains(input.toLowerCase())) {
					 filteredList.add(customer);
				 }
			 }

			 customer_table.getItems().setAll(filteredList);
		 } else {
			 customer_table.getItems().clear();
			 customerTable(); // Load all data again
		 }
	 }



	 private void customerTable(){

		 customerID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
		 firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		 lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		 contactNumCol.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
		 email.setCellValueFactory(new PropertyValueFactory<>("email"));
		 address.setCellValueFactory(new PropertyValueFactory<>("address"));
		 town.setCellValueFactory(new PropertyValueFactory<>("town"));
		 country.setCellValueFactory(new PropertyValueFactory<>("country"));
		 postal.setCellValueFactory(new PropertyValueFactory<>("postal"));

		 CustomerDAO.fetchAllCustomers();
		 customer_table.setItems(CustomerDAO.getCustomersData());


	 }

    @FXML
    private void Close(MouseEvent event) {
        Stage stage = (Stage) ((ImageView) event.getSource()).getScene().getWindow();
        stage.close();
    }

}