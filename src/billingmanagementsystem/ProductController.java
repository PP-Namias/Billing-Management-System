/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package billingmanagementsystem;

import databaseSQL.DatabaseManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
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
public class ProductController implements Initializable {

	@FXML
	private TableView<ProductData> product_table;
	@FXML
	private TableColumn<ProductData, Integer> productID;
	@FXML
	private TableColumn<ProductData, String> productName;
	@FXML
	private TableColumn<ProductData, Double> price;
	@FXML
	private TableColumn<ProductData, String> Description;
	@FXML
	private TableColumn<ProductData, Boolean> editcolumn;
	@FXML
	private TextField searchbox;
	@FXML
	private TableColumn<ProductData, Integer> Stocks;
	@FXML
	private TableColumn<ProductData, String> parentType;
	@FXML
	private TableColumn<ProductData, String> type;
	@FXML
	private TableColumn<ProductData, String> Brand;
	@FXML
	private TableColumn<ProductData, String> othersAttributes;
	@FXML
	private TableColumn<ProductData, String> ImageCol;

	/**
	 * Initializes the controller class.
	 */
	 @Override
	 public void initialize(URL url, ResourceBundle rb) {
		 ProductTable();
	 }    


	 //loading prod into the table

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



	 private void ProductTable() {
		 try {
			 product_table.getItems().setAll(retrieveProductsFromDatabase());
			 productID.setCellValueFactory(new PropertyValueFactory<>("ProductID"));
			 productName.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
			 price.setCellValueFactory(new PropertyValueFactory<>("Price"));
			 Stocks.setCellValueFactory(new PropertyValueFactory<>("Stocks"));
			 Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
			 parentType.setCellValueFactory(new PropertyValueFactory("parentType"));
			 type.setCellValueFactory(new PropertyValueFactory("type"));
			 Brand.setCellValueFactory(new PropertyValueFactory("Brand"));
			 othersAttributes.setCellValueFactory(new PropertyValueFactory("otherAttributes"));
			 ImageCol.setCellValueFactory(new PropertyValueFactory("imagePath"));
			 editcolumn.setCellFactory(new ButtonCellFactory());
			 editcolumn.setCellFactory(new ButtonCellFactory()); 
		 } catch (SQLException ex) {
			 Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
		 }
	 }





	 void refresh(){
		 ProductTable();
	 }


	 @FXML
	 private void SearchProduct(KeyEvent event) {
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


	 @FXML
	 private void OpenProductDetails(MouseEvent event) {
		 try {
			 FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductDetails.fxml"));



			 Parent parent = loader.load();

			 ProductDetailsController controller = loader.getController();
			 controller.setProductController(this);

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
	 private void RefreshTable(MouseEvent event) {
		 refresh();
		 /* try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Invoice.fxml"));



        Parent parent = loader.load();


        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stage.show();

    } catch (IOException ex) {
        Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
    }
    } */


	 }  
}
