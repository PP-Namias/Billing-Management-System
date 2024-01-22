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

import customer.Customer;
import customer.CustomerDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author User
 */
public class CustomerController implements Initializable {

	@FXML private TableView<Customer> customerTableView;
	@FXML private TableColumn<Customer, Integer> customerIdCol; 
	@FXML private TableColumn<Customer,String> creationDateCol;
	@FXML private TableColumn<Customer,String> customerFirstNameCol;
	@FXML private TableColumn<Customer,String> customerLastNameCol;
	@FXML private TableColumn<Customer,String> contactNumCol;
	@FXML private TableColumn<Customer,String> emailCol;
	@FXML private TableColumn<Customer,String> townCol;
	@FXML private TableColumn<Customer,String> addressCol;
	@FXML private TableColumn<Customer,String> countryCol;
	@FXML private TableColumn<Customer,Integer> postalCol;
	@FXML private TableColumn<Customer,Boolean> editCol;
	@FXML private ImageView addButton;
	@FXML private ImageView refreshButton;

	CustomerDAOImpl CustomerDAO = new CustomerDAOImpl(); 
    @FXML
    private TextField searchbox;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		refreshCustomerTableview();    	
	}    

	@FXML
	void buttonHover(MouseEvent event) {
		Image buttonAddHover = new Image("/Graphics/user-add-full.png");
		Image buttonRefreshHover = new Image("/Graphics/refresh-full.png");

		if (event.getSource() == addButton) {
			addButton.setImage(buttonAddHover);
		} else if (event.getSource() == refreshButton) {
			refreshButton.setImage(buttonRefreshHover);
		}

	}

	@FXML
	void buttonUnhover(MouseEvent event) {
		Image buttonAddUnhover = new Image("/Graphics/user-add.png");
		Image buttonRefreshUnhover = new Image("/Graphics/refresh.png");
		addButton.setImage(buttonAddUnhover);
		refreshButton.setImage(buttonRefreshUnhover);
	}

	@FXML
	void openCustomerDetails(MouseEvent event) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("CustomerDetails.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initStyle(StageStyle.UTILITY);
			stage.show();
			
		} catch (IOException ex) {
			Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	void refreshCustomerTable(MouseEvent event) {
		refreshCustomerTableview();
	}
	public void refreshCustomerTableview() {
		customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
		creationDateCol.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
		customerFirstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		customerLastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		contactNumCol.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
		townCol.setCellValueFactory(new PropertyValueFactory<>("town"));
		countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
		postalCol.setCellValueFactory(new PropertyValueFactory<>("postal"));
		editCol.setCellFactory(new ButtonTableCellFactory());
		editCol.setCellFactory(new ButtonTableCellFactory());


		CustomerDAO.fetchAllCustomers();
		customerTableView.setItems(CustomerDAO.getCustomersData());
	}

    @FXML
    private void SearchCustomer(KeyEvent event) {
        String input = searchbox.getText();
    if (!input.isEmpty()) {
        ObservableList<Customer> originalList = customerTableView.getItems();
        ObservableList<Customer> filteredList = FXCollections.observableArrayList();

        for (Customer product : originalList) {
            if (String.valueOf(product.getCustomerId()).contains(input)
                    || product.getFirstName().toLowerCase().contains(input.toLowerCase()) || product.getLastName().toLowerCase().contains(input.toLowerCase())) {
                filteredList.add(product);
            }
        }

        customerTableView.getItems().setAll(filteredList);
    } else {
        customerTableView.getItems().clear();
        refreshCustomerTableview();
    }
    }

}
