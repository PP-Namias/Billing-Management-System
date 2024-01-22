/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package billingmanagementsystem;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import billings.Bill;
import billings.Bill.PaymentType;
import billings.BillDAOImpl;
import customer.Customer;
import customer.CustomerDAOImpl;
import databaseSQL.DatabaseManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lineItems.LineItem;
import lineItems.LineItemDAOImpl;
import pdfGeneration.PDFGenerator;
/**
 * FXML Controller class
 *
 * @author User
 */


public class BillingsController implements Initializable {

	@FXML
	private ComboBox<Bill.DocType> docTypeComboBox;
	@FXML
	private ComboBox<Bill.PaymentType> paymentTypeComboBox;
	@FXML
	private DatePicker current_datepicker;
	@FXML
	private DatePicker due_datepicker;
	@FXML
	private AnchorPane product_pane;
	@FXML
	private TextField textField1;
	@FXML
	private TextField textField2;
	@FXML
	private TextField textField5;
	@FXML
	private TextField textField3;
	@FXML
	private TableView<OrderList> OrderListTable;
	@FXML
	private TableColumn<OrderList, Integer> order_product_id;
	@FXML
	private TableColumn<OrderList, String> order_product_name;
	@FXML
	private TableColumn<OrderList, Double> order_price;
	@FXML
	private TableColumn<OrderList, Double> order_qty;
	@FXML
	private TableColumn<OrderList, Double> order_amount;
	@FXML
	private Label totalamount;
	@FXML
	private TextField prodID;
	@FXML
	private Label warningtext;
	@FXML
	private TextField fname;
	@FXML
	private TextField lname;
	@FXML
	private TextField address;
	@FXML
	private TextField cnumber;
	@FXML
	private TextField email;
	@FXML
	private TextField town;
	@FXML
	private TextField country;
	@FXML
	private TextField postal;
	@FXML
	private TextField telephone, cashField, refField;
	@FXML
	private CheckBox VAT;
	@FXML
	private AnchorPane billingpane;
	@FXML
	private Button selectCustomerBtn, confirmButton, addOrder; 
	@FXML
	private Text confirmMessage;
	@FXML
	private Label changeText, paymentMessage;
	@FXML
	private Pane confirmMessagePane;
	@FXML
	private VBox cashPane;

	CustomerDAOImpl customerDAO = new CustomerDAOImpl();
	@FXML
	private TableColumn<ManageInvoiceData, Boolean> actionColumn;
	@FXML
	private TableColumn<ManageInvoiceData, Integer> customerIDColumn;
	@FXML
	private TableColumn<ManageInvoiceData, Integer> billIDColumn;
	@FXML
	private TableColumn<ManageInvoiceData, String> firstNameColumn;
	@FXML
	private TableColumn<ManageInvoiceData, String> lastNameColumn;
	@FXML
	private TableColumn<ManageInvoiceData, Double> totalAmountColumn;
	@FXML
	private TableColumn<ManageInvoiceData, String> noteColumn;
	@FXML
	private TableColumn<ManageInvoiceData, File> invoiceColumn;
	@FXML
	private TableView<ManageInvoiceData> InvoiceTable;

	private ManageInvoiceData manageInvoiceData;

	@FXML
	private ImageView productImageView;
	@FXML
	private TableColumn<OrderList, Boolean> actionColumnOrder;
	@FXML
	private TabPane tabPane;

	/**
	 * Initializes the controller class.
	 */
	boolean isPayValid= false;
	boolean isRefValid = false;
	 @Override
	 public void initialize(URL url, ResourceBundle rb) {
		 setCurrentDate();
		 setDueDate();

		 
		 
		 confirmButton.setDisable(true);
		 paymentTypeComboBox.setDisable(true);
		 due_datepicker.setVisible(false);
		 
		 docTypeComboBox.getItems().addAll(Bill.DocType.RECEIPT, Bill.DocType.INVOICE);
		 paymentTypeComboBox.getItems().addAll(Bill.PaymentType.CASH, Bill.PaymentType.CHECK, Bill.PaymentType.GCASH);
		 InvoiceTable();
		 
		 docTypeComboBox.setOnAction(event ->{
			 if (docTypeComboBox.getValue().equals(Bill.DocType.RECEIPT)) {
				 due_datepicker.setVisible(false);
			 }else due_datepicker.setVisible(true);
		 });
		 
		 paymentTypeComboBox.setOnAction(event -> {
			 if (paymentTypeComboBox.getValue().equals(Bill.PaymentType.CASH)) {
				 cashPane.setVisible(true);
				 refField.setVisible(false);
				 isRefValid = true;
			 }
			 else if (paymentTypeComboBox.getValue().equals(Bill.PaymentType.CHECK) || paymentTypeComboBox.getValue().equals(Bill.PaymentType.GCASH)) {
				 refField.setVisible(true) ;
				 cashPane.setVisible(false);
				 isPayValid = true;
			 }
			 else cashPane.setVisible(false);
		 });
		 
		 refField.setOnKeyTyped(event -> {
			 Bill.PaymentType paymentType = paymentTypeComboBox.getValue();
			 String refNumber = refField.getText();
			 if (paymentType.equals(Bill.PaymentType.CHECK) && (refNumber.length() < 20)) {
				 confirmMessage.setText("Reference Number is too short");
				 confirmMessage.setStyle("-fx-fill: #D33434;");
				 confirmMessagePane.setVisible(true);
				 isRefValid = true;
			 }
			 else if (paymentType.equals(Bill.PaymentType.GCASH) && (refNumber.length() < 8)) {
				 confirmMessage.setText("Reference Number is too short");
				 confirmMessage.setStyle("-fx-fill: #D33434;");
				 confirmMessagePane.setVisible(true);
				 isRefValid = true;
			 }else isRefValid = true;
		 });
		 
		 cashField.setOnKeyTyped(event -> {
			 BigDecimal cash = new BigDecimal(cashField.getText());
	            BigDecimal total = new BigDecimal(totalamount.getText());
	            BigDecimal one = new BigDecimal("1");
	            int lessResult = cash.compareTo(one);
	            int changeResult = cash.compareTo(total);
	            if (!isValidNumber(cashField.getText())) {
	            	paymentMessage.setText("Invalid Number");
	                event.consume(); 
	                isPayValid = false;
	            }
	            else if (lessResult < 0) {
	            	paymentMessage.setText("Invalid Number");
	            	isPayValid = false;
	            }
	            else if (changeResult < 0) {
	            	paymentMessage.setText("Insufficient Cash");
	            	isPayValid = false;
	            }
	            else {
	            	isPayValid = true;
	            	changeText.setText(String.valueOf(cash.subtract(total)));
	            }
	        });
		 
	 }    

	 //For current and due date
	 private void setCurrentDate(){
		 current_datepicker.setValue(LocalDate.now());
	 }
	 private void setDueDate(){
		 LocalDate currentDate = LocalDate.now();
		 LocalDate dueDate = currentDate.plusDays(30);
		 due_datepicker.setValue(dueDate);
	 }

	 private void InvoiceTable(){

		 try
		 {
			 InvoiceTable.getItems().setAll(retrieveProductsFromDatabase());
		 } catch (SQLException ex)
		 {
			 Logger.getLogger(BillingsController.class.getName()).log(Level.SEVERE, null, ex);
		 }

		 customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID")); 
		 billIDColumn.setCellValueFactory(new PropertyValueFactory<>("billID"));
		 firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		 lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		 totalAmountColumn.setCellValueFactory(new PropertyValueFactory<>("lineItemTotal"));
		 ButtonCellFactoryInvoice cellFactory = new ButtonCellFactoryInvoice(this);
		 actionColumn.setCellFactory(cellFactory);
	 }

	 //retrieve grouped data para maayos ang itsura sa table view
	 private ObservableList<ManageInvoiceData> retrieveProductsFromDatabase() throws SQLException {
		 ObservableList<ManageInvoiceData> InvoiceList = FXCollections.observableArrayList();

		 String sqlQuery = "SELECT * FROM Customers c JOIN bills b ON c.customerID = b.customerID JOIN lineItems l ON b.billID = l.billID GROUP BY b.billID";
		 try (Connection con = DatabaseManager.getConnection();
				 Statement st = con.createStatement();
				 ResultSet rs = st.executeQuery(sqlQuery)) {

			 while (rs.next()) {
				 InvoiceList.add(new ManageInvoiceData(
						 rs.getInt("customerID"),
						 rs.getTimestamp("creationDate"),
						 rs.getString("firstName"),
						 rs.getString("lastName"),
						 rs.getString("contactNumber"),
						 rs.getString("email"),
						 rs.getString("address"),
						 rs.getString("town"),
						 rs.getString("country"),
						 rs.getInt("postal"),
						 rs.getInt("billID"),
						 rs.getDate("issueDate"),
						 rs.getDate("dueDate"),
						 rs.getString("docType"),
						 rs.getTimestamp("transactionAdded"),
						 rs.getInt("lineItemID"),
						 rs.getInt("productID"),
						 rs.getString("productName"),
						 rs.getInt("quantity"),
						 rs.getDouble("unitPrice"),
						 rs.getDouble("lineItemTotal")
						 ));
			 }
		 }
		 return InvoiceList;
	 }

	 //to retrieve detailed or not grouped data 
	 private ObservableList<ManageInvoiceData> retrieveProductsFromDatabaseDetailed(int billID) throws SQLException {
		 ObservableList<ManageInvoiceData> InvoiceList = FXCollections.observableArrayList();

		 String sqlQuery = "SELECT * FROM Customers c JOIN bills b ON c.customerID = b.customerID JOIN lineItems l ON b.billID = l.billID WHERE b.billID = ?";
		 try (Connection con = DatabaseManager.getConnection();
				 PreparedStatement pst = con.prepareStatement(sqlQuery)) {
			 pst.setInt(1, billID);

			 try (ResultSet rs = pst.executeQuery()) {
				 while (rs.next()) {
					 InvoiceList.add(new ManageInvoiceData(
							 rs.getInt("customerID"),
							 rs.getTimestamp("creationDate"),
							 rs.getString("firstName"),
							 rs.getString("lastName"),
							 rs.getString("contactNumber"),
							 rs.getString("email"),
							 rs.getString("address"),
							 rs.getString("town"),
							 rs.getString("country"),
							 rs.getInt("postal"),
							 rs.getInt("billID"),
							 rs.getDate("issueDate"),
							 rs.getDate("dueDate"),
							 rs.getString("docType"),
							 rs.getTimestamp("transactionAdded"),
							 rs.getInt("lineItemID"),
							 rs.getInt("productID"),
							 rs.getString("productName"),
							 rs.getInt("quantity"),
							 rs.getDouble("unitPrice"),
							 rs.getDouble("lineItemTotal")
							 ));
				 }
			 }
		 }
		 // Now you can use InvoiceList as needed
		 for (ManageInvoiceData invoiceData : InvoiceList) {
			 System.out.println(invoiceData);
		 }
		 return InvoiceList;
	 }

	 /*private void InvoiceTableUpdate(){

            try
            {
                ManageInvoiceData selectedData = InvoiceTable.getSelectionModel().getSelectedItem();
                int billid = selectedData.getBillID();
                OrderListTable.getItems().setAll(retrieveProductsFromDatabaseDetailed(selectedData));

                order_product_id.setCellValueFactory(new PropertyValueFactory<>("productID"));
                order_product_name.setCellValueFactory(new PropertyValueFactory<>("productName"));
                order_price.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
                order_qty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                order_amount.setCellValueFactory(new PropertyValueFactory<>("lineItemTotal"));
                actionColumnOrder.setCellFactory(new ButtonFactoryOrderList(OrderListTable, totalamount));
            } catch (SQLException ex)
            {
                Logger.getLogger(BillingsController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }*/

	 
	 @FXML
	 private void opencustomertable(ActionEvent event) {
		 try {
			 FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerTable.fxml"));
			 Parent root = loader.load();
			 Scene scene = new Scene(root);

			 Stage stage = new Stage();
			 stage.setScene(scene);
			 stage.initModality(Modality.APPLICATION_MODAL);
			 stage.initStyle(StageStyle.UNDECORATED);
			 stage.show();

			 CustomerTableController customerTableController = loader.getController();
			 customerTableController.setBillingsController(this);
			 if (event.getSource() == selectCustomerBtn)customerTableController.customerFlag = true;
			 else customerTableController.customerFlag = false;

		 } catch (IOException e) {
			 e.printStackTrace();
		 }
	 }

	 public void setCustomerData(Customer customerData) {
		 fname.setText(customerData.getFirstName());
		 lname.setText(customerData.getLastName());
		 cnumber.setText(customerData.getContactNumber());
		 email.setText(customerData.getEmail());
		 address.setText(customerData.getAddress());
		 town.setText(customerData.getTown());
		 country.setText(customerData.getCountry());
		 postal.setText(customerData.getPostal());
	 }

	 public void setInvoiceData(ManageInvoiceData invoicedata){
		 try
		 {
			 retrieveProductsFromDatabaseDetailed(invoicedata.getBillID());

		 } catch (SQLException ex)
		 {
			 Logger.getLogger(BillingsController.class.getName()).log(Level.SEVERE, null, ex);
		 }

		 fname.setText(invoicedata.getFirstName());
		 lname.setText(invoicedata.getLastName());
		 cnumber.setText(invoicedata.getContactNumber());
		 email.setText(invoicedata.getEmail());
		 address.setText(invoicedata.getAddress());
		 town.setText(invoicedata.getTown());
		 country.setText(invoicedata.getCountry());
		 postal.setText(String.valueOf(invoicedata.getPostal()));

	 }


	 private void ClearFields(){
		 docTypeComboBox.setValue(null);
		 paymentTypeComboBox.setValue(null);
		 cashPane.setVisible(false);
		 refField.setVisible(false);
		 cashField.setText(null);
		 refField.setText(null);
		 fname.setText(null);
		 lname.setText(null);
		 cnumber.setText(null);
		 email.setText(null);
		 address.setText(null);
		 town.setText(null);
		 country.setText(null);
		 postal.setText(null);
		 telephone.setText(null);
		 OrderListTable.getItems().clear();
		 productImageView.setImage(null);
		 updateTotalAmountLabel();
	 }



	 //for tab pane
	 public TabPane getTabPane() {
		 return tabPane;
	 }

	 @FXML
	 private void enterprice(KeyEvent event) {
		 String price = textField2.getText();
		 String qty = textField3.getText();

		 if (!price.isEmpty() && !qty.isEmpty()) {
			 double subtotal = Double.parseDouble(price) * Double.parseDouble(qty);
			 textField5.setText(String.valueOf(subtotal));
			 warningtext.setText("");
		 }else if (!price.isEmpty() && qty.isEmpty()){
			 warningtext.setText("Enter Quantity");
		 } else {
			 warningtext.setText("Enter Price");
			 textField5.setText("");
		 }
	 }

	 @FXML
	 private void subtotal(KeyEvent event) {
		 String price = textField2.getText();
		 String qty = textField3.getText();

		 if (!price.isEmpty() && !qty.isEmpty()) {
			 double subtotal = Double.parseDouble(price) * Double.parseDouble(qty);
			 textField5.setText(String.valueOf(subtotal));
			 warningtext.setText("");
		 }else if (price.isEmpty() && !qty.isEmpty()){
			 warningtext.setText("Enter Price");
		 } else {
			 warningtext.setText("Enter Quantity");
			 textField5.setText("");
		 }
	 }

	 // when you sleect product then add it to order
	 private ObservableList<OrderList> tableItems = FXCollections.observableArrayList();

	 @FXML
	 private void addtoorder(ActionEvent event) {
		 String productID = prodID.getText();
		 String productName = textField1.getText();
		 String price = textField2.getText();
		 String quantity = textField3.getText();
		 String amount = textField5.getText();
		 
		 paymentTypeComboBox.setDisable(false);
		 
		 if(checkProductStocks()){
			 //		System.out.println("Adding to order - Product ID: " + productID + ", Product Name: " + productName +
			 //				", Price: " + price + ", Quantity: " + quantity + ", Amount: " + amount);

			 if (!productID.isEmpty() && !productName.isEmpty() && !price.isEmpty() && !quantity.isEmpty() && !amount.isEmpty()) {
				 OrderList orderlist = new OrderList(productID, productName, Double.parseDouble(price), Double.parseDouble(quantity), Double.parseDouble(amount));

				 order_product_id.setCellValueFactory(new PropertyValueFactory<>("productID"));
				 order_product_name.setCellValueFactory(new PropertyValueFactory<>("productName"));
				 order_price.setCellValueFactory(new PropertyValueFactory<>("price"));
				 order_qty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
				 order_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
				 actionColumnOrder.setCellFactory(new ButtonFactoryOrderList(OrderListTable, totalamount));

				 tableItems.add(orderlist);
				 OrderListTable.setItems(tableItems);

				 updateTotalAmountLabel();

				 warningtext.setText("Added to orderlist");

				 warningtext.setTextFill(Color.GREEN);

				 Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
					 @Override
					 public void handle(ActionEvent event) {
						 warningtext.setText(null);
					 }
				 }));
				 timeline.play();

				 prodID.clear();
				 textField1.clear();
				 textField2.clear();
				 textField3.clear();
				 textField5.clear();
				 productImageView.setImage(null);
				 confirmButton.setDisable(false);
			 } else {
				 System.out.println("One or more fields are empty. Not adding to order.");
				 warningtext.setText("Fill up necessary fields");
				 Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {
					 @Override
					 public void handle(ActionEvent event) {
						 warningtext.setText(null);
					 }
				 }));
				 timeline.play();
			 }
		 }
	 }

	 //total amount 
	 private void updateTotalAmountLabel() {
		 double totalAmount = 0.0;

		 for (OrderList orderItem : tableItems) {
			 totalAmount += orderItem.getAmount();
		 }

		 totalamount.setText(String.valueOf(totalAmount));
	 }


	 @FXML
	 private void openproducttable(ActionEvent event) {
		 try {
			 FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductTable.fxml"));
			 Parent root = loader.load();
			 Scene scene = new Scene(root);

			 Stage stage = new Stage();
			 stage.setScene(scene);
			 stage.initStyle(StageStyle.UNDECORATED);
			 stage.show();
			 ProductTableController productController = loader.getController();
			 productController.setBillingsController(this);

		 } catch (IOException e) {
			 e.printStackTrace();
		 }
	 }
	 public void setProductData(ProductData productData) {
		 // Update initial text fields
		 prodID.setText(String.valueOf(productData.getProductID()));
		 textField1.setText(productData.getProductName());
		 textField2.setText(String.valueOf(productData.getPrice()));
                 Image currentImage = new Image(productData.getImagePath());
       
		 productImageView.setImage(currentImage);

	 }

	 @FXML
	 private void VATButton(ActionEvent event) {
		 double vat = Double.parseDouble(totalamount.getText());
		 double tax = vat * 0.12;
		 if (VAT.isSelected()){ 
			 double taxes = vat - tax;
			 String taxed = String.valueOf(taxes);
			 totalamount.setText(taxed);

		 }else {
			 updateTotalAmountLabel();
		 }
	 }

	 private void customerDataChecker() {
		 Customer customer = new Customer(
				 fname.getText(),
				 lname.getText(),
				 cnumber.getText(),
				 email.getText(),
				 address.getText(),
				 town.getText(),
				 country.getText(),
				 postal.getText()
				 );

		 if (!customerDAO.checkIfCustomerExists(customer)) customerDAO.addCustomer(customer);

	 }

	 private Bill buildBill() {
		 //Getting data for new bill
		 Customer customer = customerDAO.getCustomerByName(fname.getText(), lname.getText());
		 Date issueDate = Date.valueOf(current_datepicker.getValue());
		 Date dueDate = Date.valueOf(due_datepicker.getValue());

		 //New Bill Constructor
		 Bill bill = new Bill(
				 customer.getCustomerId(),
				 issueDate,
				 dueDate,
				 docTypeComboBox.getValue(),
				 paymentTypeComboBox.getValue()
				 );

		 return bill;
	 }

	 private List<LineItem> lineItemListBuilder(Bill bill) {

		 List<LineItem> lineItemList = new ArrayList<>();

		 for (OrderList orderList : tableItems) {
			 int quantity= (int) (orderList.getQuantity());
			 LineItem lineItem = new LineItem(
					 bill.getBillID(),
					 Integer.valueOf(orderList.getProductID()),
					 orderList.getProductName(),
					 quantity,
					 BigDecimal.valueOf(orderList.getPrice()),
					 BigDecimal.valueOf(orderList.getAmount())
					 );
			 lineItemList.add(lineItem);
		 }

		 return lineItemList;
	 }

	 private void openFile(String path) {
		 try {

			 File pdfFile = new File(path);
			 if (pdfFile.exists()) {

				 if (Desktop.isDesktopSupported()) {
					 Desktop.getDesktop().open(pdfFile);
				 } else {
					 System.out.println("Awt Desktop is not supported!");
				 }

			 } else {
				 System.out.println("File does not exists!");
			 }


		 } catch (Exception ex) {
			 ex.printStackTrace();
		 }
	 }

	 @FXML
	 private void CREATEINVOICE(ActionEvent event) throws IOException {
		 BillDAOImpl billDAO = new BillDAOImpl();
		 LineItemDAOImpl lineItemDAO = new LineItemDAOImpl();

		 //Check if customers are already exists
		 customerDataChecker();
		 Bill bill = buildBill();
		
		 
		  
		 //Insert bill to database
		 if (docTypeComboBox.getValue() == null) {
			 confirmMessage.setText("Select Document Type");
			 confirmMessage.setStyle("-fx-fill: #D33434;");
			 confirmMessagePane.setVisible(true);
		 }
		 else if (paymentTypeComboBox.getValue() == null) {
			 confirmMessage.setText("Select Payment Type");
			 confirmMessage.setStyle("-fx-fill: #D33434;");
			 confirmMessagePane.setVisible(true);
		 }
		 else if (!isPayValid) {
			 confirmMessage.setText("Insufficient Cash");
			 confirmMessage.setStyle("-fx-fill: #D33434;");
			 confirmMessagePane.setVisible(true);
		 }
		 else if (!isRefValid) {
			 confirmMessage.setText("Invalid Reference Number");
			 confirmMessage.setStyle("-fx-fill: #D33434;");
			 confirmMessagePane.setVisible(true);
		 }
		 //(docTypeComboBox.getValue() != null && paymentTypeComboBox.getValue() != null)
		 else {
			 confirmMessage.setText(null);
			 billDAO.addBill(bill);

			 //Populate lineItemList for adding lineItems to database
			 Bill createdBill = billDAO.getLastBill();
			 List<LineItem> lineItemList = lineItemListBuilder(createdBill);

			 //Adding lineItems
			 lineItemDAO.addLineItems(lineItemList);
			 updateProductStocks(lineItemList);

			 Bill.PaymentType paymentType = paymentTypeComboBox.getValue(); 
			 String cash = cashField.getText();
			 String totalAmount = totalamount.getText();
			 PDFGenerator pdfGen = new PDFGenerator();
			 
			 if (paymentType.equals(Bill.PaymentType.CASH)) {
				 	pdfGen.setBill(createdBill);
				 	pdfGen.setBillCustomer(customerDAO.getCustomerByID(createdBill.getCustomerID())); 
				 	pdfGen.setLineItemList(lineItemList);
				 	pdfGen.setPaymentType(paymentType);
				 	pdfGen.setCash(cash);
				 	pdfGen.setTotal(totalAmount);
			 } 
			 else {
					pdfGen.setBill(createdBill);
				 	pdfGen.setBillCustomer(customerDAO.getCustomerByID(createdBill.getCustomerID())); 
				 	pdfGen.setLineItemList(lineItemList);
				 	pdfGen.setRefNumber(refField.getText());
				 	pdfGen.setPaymentType(paymentType);
				 	pdfGen.setTotal(totalAmount);
			 }
			 
			 pdfGen.createPDF();

			 confirmMessage.setStyle("-fx-fill: #435585;");
			 confirmMessage.setText(createdBill.getDoctype() + " Created");
			 confirmMessagePane.setVisible(true);
			 ClearFields();
			 openFile(pdfGen.getPath());



			 /*           FXMLLoader loader = new FXMLLoader(getClass().getResource("Invoice.fxml"));
        Parent root = loader.load();

        InvoiceController controller = loader.getController();
        controller.setInvoiceData(bill);

        Stage previewStage = new Stage();
        previewStage.setTitle("Preview");
        previewStage.setScene(new Scene(root));
        previewStage.initStyle(StageStyle.UNDECORATED);

        previewStage.show();*/




		 }
	 }


	 //for updating stocks in database

	private void updateProductStocks(List<LineItem> lineItemList) {
    try (Connection con = DatabaseManager.getConnection()) {
        for (LineItem lineItem : lineItemList) {
            int productId = lineItem.getProductID();
            int quantity = lineItem.getQuantity();

            // Ensure that prodID and textField3 are not null
            if (prodID != null && textField3 != null) {
                int currentStock = getCurrentStock(con, productId);

                if (quantity > currentStock) {
                    warningtext.setText(currentStock + " left");
                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> warningtext.setText(null)));
                    timeline.play();
                } else {
                    int updatedStock = currentStock - quantity;
                    updateStockInDatabase(con, productId, updatedStock);
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}




	 private int getCurrentStock(Connection con, int productId) throws SQLException {
    String query = "SELECT stocks FROM Products WHERE productID = ?";
    try (PreparedStatement pst = con.prepareStatement(query)) {
        pst.setInt(1, productId);
        try (ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                // Check if "stocks" column is not null
                int stocks = rs.getInt("stocks");
                if (!rs.wasNull()) {
                    return stocks;
                } else {
                    warningtext.setText("Insufficient Stocks");
                    return 0; // Return a default value or handle it as needed
                }
            }
        }
    }
    return 0;
}


	 private void updateStockInDatabase(Connection con, int productId, int updatedStock) throws SQLException {
		 String query = "UPDATE Products SET Stocks = ? WHERE ProductID = ?";
		 try (PreparedStatement pst = con.prepareStatement(query)) {
			 pst.setInt(1, updatedStock);
			 pst.setInt(2, productId);
			 pst.executeUpdate();
		 }
	 }



	 @FXML
	 private void changetoTab2(Event event) {
		 System.out.println(tabPane.getSelectionModel().getSelectedIndex());
		 InvoiceTable();
	 }


	 private boolean checkProductStocks() {
    boolean haveStock = false;

    try {
        DatabaseManager db = new DatabaseManager();
        Connection con = db.getConnection();

        String quantityText = textField3.getText();

        if (!quantityText.isEmpty()) {
            int quantity = Integer.parseInt(quantityText);

            int stocks = getCurrentStock(con, Integer.valueOf(prodID.getText()));

            if (quantity > stocks) {
                warningtext.setText(stocks + " stock/s left");
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), event -> warningtext.setText(null)));
                timeline.play();
                haveStock = false;
            } else {
                haveStock = true;
            }
        } else {
            warningtext.setText("Enter a valid quantity");
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> warningtext.setText(null)));
            timeline.play();
        }
    } catch (NumberFormatException | SQLException ex) {
        warningtext.setText("Enter a valid quantity");
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> warningtext.setText(null)));
        timeline.play();
    }

    return haveStock;
}



	 private void deductStocks(int productID){

		 try
		 {
			 Connection con = DatabaseManager.getConnection();
			 int quantity = Integer.valueOf(textField3.getText());

			 PreparedStatement ps = con.prepareStatement("Update products SET Stocks = " + quantity + " WHERE productID = " +productID );
			 ps.executeUpdate();
		 } catch (SQLException ex){
			 Logger.getLogger(BillingsController.class.getName()).log(Level.SEVERE, null, ex);
		 }

	 }
	 
	 private boolean isValidNumber(String input) {
	        try {
	            // Attempt to parse the input as a double
	            Double.parseDouble(input);
	            return true;
	        } catch (NumberFormatException e) {
	            // Show an error alert if parsing fails
	            System.out.println("Please enter a valid number.");
	            return false;
	        }
	 }
	 

}		
