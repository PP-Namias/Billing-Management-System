/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package billingmanagementsystem;

import customer.Customer;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import databaseSQL.DatabaseManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class ButtonCellFactory implements Callback<TableColumn<ProductData, Boolean>, TableCell<ProductData, Boolean>> {
    
    
    @Override
    public TableCell<ProductData, Boolean> call(TableColumn<ProductData, Boolean> param) {
        return new ButtonTableCell();
    }

    private class ButtonTableCell extends TableCell<ProductData, Boolean> {
        final ImageView deleteIcon = new ImageView(new Image(getClass().getResourceAsStream("/Graphics/bin.png")));
        final ImageView updateIcon = new ImageView(new Image(getClass().getResourceAsStream("/Graphics/pen.png")));

        ButtonTableCell() {
           
            deleteIcon.setOnMouseClicked(event -> {
                ProductData productData = getTableView().getItems().get(getIndex());
                
                handleDelete(productData);
            
            });

            
            updateIcon.setOnMouseClicked(event -> {
                ProductData productData = getTableView().getItems().get(getIndex());
                
                handleUpdate(productData);
            });

            // Set icons side by side
            HBox buttonHBox = new HBox(deleteIcon, updateIcon);
            HBox.setMargin(deleteIcon, new Insets(0, 5, 0, 0));
            setGraphic(buttonHBox);
        }

        @Override
        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                setGraphic(new HBox(deleteIcon, updateIcon));
            } else {
                setGraphic(null);
            }
        }

        private void handleDelete(ProductData productData) {
            try {
                        int prodID = productData.getProductID();
			Connection connection = DatabaseManager.getConnection();
			PreparedStatement statement = connection.prepareStatement("DELETE FROM products WHERE ProductID = " + prodID);
			statement.executeUpdate();
			
			statement.close();
                        getTableView().getItems().remove(productData);
                        
	        connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        }

      private void handleUpdate(ProductData productData) {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("ProductDetails.fxml"));

    try {
        Parent parent = loader.load();
        ProductDetailsController productDetailsController = loader.getController();
        productDetailsController.setProductData(productData);
        

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
    }
}






        
    }
    
    
    
}

