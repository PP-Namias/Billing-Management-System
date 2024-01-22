/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package billingmanagementsystem;

import java.sql.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import databaseSQL.DatabaseManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author User
 */
public class ProductTableController implements Initializable {

	@FXML
	private TableView<ProductData> product_table;
	@FXML
	private TableColumn<ProductData, Integer> productID;
	@FXML
	private TableColumn<ProductData, String> productName;
	@FXML
	private TableColumn<ProductData, Double> price;
	@FXML
	private TableColumn<ProductData, String> description;
	@FXML
	private TextField searchbox;
	@FXML
	private TableColumn<ProductData, Integer> stocks;
	@FXML
	private TableColumn<ProductData, String> swcategory;
	@FXML
	private TableColumn<ProductData, String> subcategory;
	@FXML
	private TableColumn<ProductData, String> brandcol;
	@FXML
	private TableColumn<ProductData, String> otherscol;
	@FXML
	private TableColumn<ProductData, String> imagecol;

	/**
	 * Initializes the controller class.
	 */
	 @Override
	 public void initialize(URL url, ResourceBundle rb) {
		 ProductTable();
	 }    



	 private void ProductTable(){
		 try
		 {
			 Connection con = DatabaseManager.getConnection();
			 Statement st = con.createStatement();
			 ResultSet rs = st.executeQuery("SELECT * FROM products JOIN productcategory on products.ProductID = productcategory.ProductID");

			 while (rs.next()) {
				 product_table.getItems().add(new ProductData(
						 rs.getInt("ProductID"),
						 rs.getString("ProductName"),
						 rs.getDouble("Price"),
						 rs.getInt("Stocks"),
						 rs.getString("Description"),
						 rs.getString("Image"),
						 rs.getString("parentType"),
						 rs.getString("type"),
						 rs.getString("Brand"),
						 rs.getString("otherAttributes")
						 ));
			 }


		 } catch (SQLException ex)
		 {
			 Logger.getLogger(ProductTableController.class.getName()).log(Level.SEVERE, null, ex);
		 }

		 productID.setCellValueFactory(new PropertyValueFactory<>("ProductID"));
		 productName.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
		 price.setCellValueFactory(new PropertyValueFactory<>("Price"));
		 description.setCellValueFactory(new PropertyValueFactory<>("Description"));
		 stocks.setCellValueFactory(new PropertyValueFactory<>("Stocks"));
		 swcategory.setCellValueFactory(new PropertyValueFactory<>("parentType"));
		 subcategory.setCellValueFactory(new PropertyValueFactory<>("Type"));
		 brandcol.setCellValueFactory(new PropertyValueFactory<>("Brand"));
		 otherscol.setCellValueFactory(new PropertyValueFactory<>("otherAttributes"));
		 imagecol.setCellValueFactory(new PropertyValueFactory<>("imagePath"));
		 
	 }

	 @FXML
	 private void tableclicked(MouseEvent event) {

		 if (event.getClickCount() == 1) {
			 ProductData product = product_table.getSelectionModel().getSelectedItem();
			 if (product != null && billingsController != null) {
				 billingsController.setProductData(product);
				 ((Node) (event.getSource())).getScene().getWindow().hide();
			 }
		 }
	 }

	 void refresh(){
		 ProductTable();
	 }

	 //para to sa billing, para makuha yung instance ng controller
	 private BillingsController billingsController;

	 public void setBillingsController(BillingsController controller) {
		 this.billingsController = controller;
	 }   

	 @FXML
	 private void searchproduct(KeyEvent event) {
		 String input = searchbox.getText().toLowerCase();

		 if (!input.isEmpty()) {
			 try {
				 ObservableList<ProductData> originalList = retrieveProductsFromDatabase();
				 ObservableList<ProductData> filteredList = FXCollections.observableArrayList();

				 for (ProductData product : originalList) {
					 if (matchesCriteria(product, input)) {
						 filteredList.add(product);
					 }
				 }

				 product_table.getItems().setAll(filteredList);
			 } catch (SQLException ex) {
				 Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
			 }
		 } else {
			 product_table.getItems().clear();
			 refresh();  

		 }
	 }

	 private boolean matchesCriteria(ProductData product, String input) {
		 String[] criteriaArray = input.split("\\s+");
		 boolean matchesAllCriteria = true;

		 for (String criterion : criteriaArray) {
			 matchesAllCriteria = matchesAllCriteria &&
					 (String.valueOf(product.getProductID()).contains(criterion)
							 || product.getProductName().toLowerCase().contains(criterion)
							 || product.getParentType().toLowerCase().contains(criterion)
							 || product.getType().toLowerCase().contains(criterion)
							 || product.getBrand().toLowerCase().contains(criterion)
							 || product.getOtherAttributes().toLowerCase().contains(criterion));
		 }

		 return matchesAllCriteria;
	 }

	 private ObservableList<ProductData> retrieveProductsFromDatabase() throws SQLException {
		 ObservableList<ProductData> productList = FXCollections.observableArrayList();

		 try (Connection con = DatabaseManager.getConnection();
				 Statement st = con.createStatement();
				 ResultSet rs = st.executeQuery("SELECT * FROM Products join ProductCategory on Products.ProductID = ProductCategory.ProductID")) {

			 while (rs.next()) {
				 productList.add(new ProductData(
						 rs.getInt("ProductID"),
						 rs.getString("ProductName"),
						 rs.getDouble("Price"),
						 rs.getInt("Stocks"),
						 rs.getString("Description"),
						 rs.getString("Image"),
						 rs.getString("parentType"),
						 rs.getString("type"),
						 rs.getString("Brand"),
						 rs.getString("otherAttributes")
						 ));
			 }
		 }

		 return productList;
	 }

	 @FXML
	 private void Close(MouseEvent event) {
		 Stage stage = (Stage) ((ImageView) event.getSource()).getScene().getWindow();
		 stage.close();
	 }

}

