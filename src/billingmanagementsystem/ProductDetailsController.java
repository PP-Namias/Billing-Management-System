/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package billingmanagementsystem;

import java.sql.*;
import databaseSQL.DatabaseManager;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ProductDetailsController implements Initializable {

    @FXML
    private TextField prodID;
    @FXML
    private TextField prodName;
    @FXML
    private TextField prodPrice;
    @FXML
    private TextArea prodDesc;
    @FXML
    private ImageView prodImage;
    @FXML
    private Label msgconfirmation;
    @FXML
    private Spinner<Integer> NoOfStocks;
    @FXML
    private ComboBox<String> prodParentType;
    @FXML
    private ComboBox<String> prodSubcategory;
    @FXML
    private ComboBox<String> prodBrand;
    @FXML
    private ComboBox<String> otherAtt;
    @FXML
    private TextField pathFile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       otherAttributes();
       Spinner();
       handleBrand();
        ObservableList<String> parentTypeOptions = FXCollections.observableArrayList("Footwear", "Clothing", "Accessories");
    prodParentType.setItems(parentTypeOptions);

    prodParentType.valueProperty().addListener((observable, oldValue, newValue) -> {
        handleParentTypeChange();
    });

    
       
    }    
    
    
    
    
    private boolean isEditMode = false;
    private ProductData existingProductData;

    
    
    
    
    
  public void setProductData(ProductData productData) {
    if (productData != null) {
        existingProductData = productData;
        isEditMode = true;

        prodID.setText(String.valueOf(productData.getProductID()));
        prodName.setText(productData.getProductName());
        prodPrice.setText(String.valueOf(productData.getPrice()));
        NoOfStocks.getValueFactory().setValue(productData.getStocks());
        prodDesc.setText(productData.getDescription());
        prodParentType.setValue(productData.getParentType());
        prodSubcategory.setValue(productData.getType());
        prodBrand.setValue(productData.getBrand());
        otherAtt.setValue(productData.getOtherAttributes());

        Image currentImage = new Image(productData.getImagePath());
        prodImage.setImage(currentImage);
        pathFile.setText(productData.getImagePath());
        
    } else {

        ClearFields();
        isEditMode = false;
    }
}

   
     private int insertProductInDatabase(String ProductName, double Price, int Stocks, String Description, String parentType, String type, String Brand, String otherAttributes) throws SQLException {
    Connection connection = DatabaseManager.getConnection();

    String query = "INSERT INTO products (ProductName, Price, Stocks, Description, Image) VALUES (?, ?, ?, ?, ?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
        preparedStatement.setString(1, ProductName);
        preparedStatement.setDouble(2, Price);
        preparedStatement.setInt(3, Stocks);
        preparedStatement.setString(4, Description);
        preparedStatement.setString(5, pathFile.getText()); 

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows > 0) {
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedProductID = generatedKeys.getInt(1);

                    String query1 = "INSERT INTO productcategory (ProductID, parentType, type, Brand, otherAttributes) VALUES (?, ?, ?, ?, ?)";
                    try (PreparedStatement preparedStatement1 = connection.prepareStatement(query1)) {
                        preparedStatement1.setInt(1, generatedProductID);
                        preparedStatement1.setString(2, parentType);
                        preparedStatement1.setString(3, type);
                        preparedStatement1.setString(4, Brand);
                        preparedStatement1.setString(5, otherAttributes);

                        preparedStatement1.executeUpdate();
                    }
                    ClearFields();
                    msgconfirmation.setText("Product Saved");
                    msgconfirmation.setStyle("-fx-text-fill: green;");
                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            msgconfirmation.setText(null);
                        }
                    }));
                    timeline.play();
                    return generatedProductID;
                } else {
                    throw new SQLException("Failed to retrieve auto-generated ProductID.");
                }
            }
        } else {
            throw new SQLException("Inserting product failed, no rows affected.");
        }
    }
}


private void updateProductInDatabase(int productId, String productName, double price, int Stocks,String imagePath, String description, String parentType, String type, String brand, String otherAttributes) throws SQLException {
    Connection connection = DatabaseManager.getConnection();


    String query = "UPDATE products SET ProductName=?, Price=?, Stocks=?, Description=?, Image=? WHERE ProductID=?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setString(1, productName);
        preparedStatement.setDouble(2, price);
        preparedStatement.setDouble(3, Stocks);
        preparedStatement.setString(4, description);

        preparedStatement.setString(5, (imagePath));

        preparedStatement.setInt(6, productId);

        preparedStatement.executeUpdate();

        String query1 = "UPDATE productcategory SET parentType=?, type=?, Brand=?, otherAttributes=? WHERE ProductID=?";
        try (PreparedStatement preparedStatement1 = connection.prepareStatement(query1)) {
            preparedStatement1.setString(1, parentType);
            preparedStatement1.setString(2, type);
            preparedStatement1.setString(3, brand);
            preparedStatement1.setString(4, otherAttributes);
            preparedStatement1.setInt(5, productId);

            preparedStatement1.executeUpdate();
        }

        ClearFields();
        msgconfirmation.setText("Product Updated");
        msgconfirmation.setStyle("-fx-text-fill: green;");
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                msgconfirmation.setText(null);
            }
        }));
        timeline.play();
    }
}

     private void ClearFields(){
         prodID.setText(null);
         prodName.setText(null);
         prodPrice.setText(null);
         prodDesc.setText(null);
         prodImage.setImage(null);
         NoOfStocks.getValueFactory().setValue(0);
         prodParentType.setValue(null);
         prodSubcategory.setValue(null);
         prodBrand.setValue(null);
         otherAtt.setValue(null);
     }
     
    private boolean isImageViewEmpty(ImageView imageView) {
    return imageView.getImage() == null;
    }
   
    private ProductController productController;
    
    public void setProductController(ProductController productController) {
        this.productController = productController;
    }
    
    
    
    private File file;
    @FXML
private void imageChooser(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files", "*.png", "*.jpg", "*.jpeg", "*.gif");
    fileChooser.getExtensionFilters().add(extFilter);

    File selectedFile = fileChooser.showOpenDialog(new Stage());

    if (selectedFile != null) {
        String imagePath = selectedFile.toURI().toString();

        Image image = new Image(imagePath);
        prodImage.setImage(image);

        file = selectedFile;

        pathFile.setText(imagePath);
    }
}


    

    @FXML
private void saveproduct(ActionEvent event) {
    try {
        // Extract values from UI components
        String productName = prodName.getText();
        String priceText = prodPrice.getText();
        int spinnervalue = NoOfStocks.getValue();
        String description = prodDesc.getText();
        String pt = prodParentType.getValue();
        String sb = prodSubcategory.getValue();
        String brand = prodBrand.getValue();
        String oa = otherAtt.getValue();
        String imagePath = pathFile.getText();

        if (productName.isEmpty() || priceText.isEmpty()  || description.isEmpty() || isImageViewEmpty(prodImage)) {
            msgconfirmation.setText("Please fill up all fields");
            msgconfirmation.setStyle("-fx-text-fill: red;");
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    msgconfirmation.setText(null);
                }
            }));
            timeline.play();
        } else {
            double price = Double.parseDouble(priceText);

            if (isEditMode) {
                updateProductInDatabase(existingProductData.getProductID(), productName, price, spinnervalue, imagePath, description, pt, sb, brand, oa);
                msgconfirmation.setText(" Product updated successfully");
                msgconfirmation.setStyle("-fx-text-fill: green;");
                ClearFields();

            } else {
                insertProductInDatabase(productName, price, spinnervalue, description,  pt, sb, brand, oa);
                msgconfirmation.setText(productName + " added successfully");
                msgconfirmation.setStyle("-fx-text-fill: green;");
                ClearFields();
                productController.refresh();
            }

            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    msgconfirmation.setText(null);
                }
            }));
            timeline.play();
        }

    } catch (SQLException | NumberFormatException ex) {
        msgconfirmation.setText("Error: Invalid input");
        msgconfirmation.setStyle("-fx-text-fill: red;");
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                msgconfirmation.setText(null);
            }
        }));
        timeline.play();
        Logger.getLogger(ProductDetailsController.class.getName()).log(Level.SEVERE, null, ex);
    }
}


    @FXML
    private void clearfields(ActionEvent event) {
        ClearFields();
    }
    
    

        private void Spinner(){
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        NoOfStocks.setValueFactory(valueFactory);
        NoOfStocks.setOnScroll(event -> {
            int currentValue = NoOfStocks.getValue();
            if (event.getDeltaY() > 0) {
                NoOfStocks.getValueFactory().setValue(currentValue + 1);
            } else {
                NoOfStocks.getValueFactory().setValue(currentValue - 1);
            }
        });
        
        }

    
        private void handleBrand(){
        ObservableList<String> others = FXCollections.observableArrayList("Nike","Adidas", "Vans");
        prodBrand.setItems(others);
        }
        private void otherAttributes(){
        ObservableList<String> others = FXCollections.observableArrayList("Men","Women", "Kids");
        otherAtt.setItems(others);

}


   
    private void handleParentTypeChange() {
        String selectedParentType = prodParentType.getValue();

        ObservableList<String> subcategoryOptions = getSubcategory(selectedParentType);
        prodSubcategory.setItems(subcategoryOptions);

        prodSubcategory.getSelectionModel().selectFirst();
    }

   

    private ObservableList<String> getSubcategory(String parentType) {
    if (parentType == null) {
        return FXCollections.observableArrayList();  // or return an appropriate default value
    }

    switch (parentType) {
        case "Footwear":
            return FXCollections.observableArrayList("Shoes", "Flops", "Sneakers");
        case "Clothing":
            return FXCollections.observableArrayList("Shorts", "Tops", "Underwear");
        case "Accessories":
            return FXCollections.observableArrayList("Pads", "Hats", "Gloves");
        default:
            return FXCollections.observableArrayList();
    }
}

    
   
    
}
