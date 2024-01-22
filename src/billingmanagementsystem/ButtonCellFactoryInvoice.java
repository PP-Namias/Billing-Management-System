package billingmanagementsystem;


import java.sql.*;
import databaseSQL.DatabaseManager;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Pair;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
public class ButtonCellFactoryInvoice implements Callback<TableColumn<ManageInvoiceData, Boolean>, TableCell<ManageInvoiceData, Boolean>> {

    private BillingsController billingsController;

    public ButtonCellFactoryInvoice(BillingsController billingsController) {
        this.billingsController = billingsController;
    }
    
    @Override
    public TableCell<ManageInvoiceData, Boolean> call(TableColumn<ManageInvoiceData, Boolean> param) {
         return new ButtonTableCell();
    }
    
    private class ButtonTableCell extends TableCell<ManageInvoiceData, Boolean> {
        final ImageView deleteIcon = new ImageView(new Image(getClass().getResourceAsStream("/Graphics/bin.png")));
        //final ImageView updateIcon = new ImageView(new Image(getClass().getResourceAsStream("/Graphics/pen.png")));

        ButtonTableCell() {
           
            deleteIcon.setOnMouseClicked(event -> {
                ManageInvoiceData invoicedata = getTableView().getItems().get(getIndex());
                
                handleDelete(invoicedata);
            
            });

            
            /*updateIcon.setOnMouseClicked(event -> {
                ManageInvoiceData invoicedata = getTableView().getItems().get(getIndex());
                
                handleUpdate(invoicedata);
            });*/

            // Set icons side by side
            HBox buttonHBox = new HBox(deleteIcon);
            HBox.setMargin(deleteIcon, new Insets(0, 5, 0, 0));
            setGraphic(buttonHBox);
        }

        @Override
        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                setGraphic(new HBox(deleteIcon));
            } else {
                setGraphic(null);
            }
        }

     private void handleDelete(ManageInvoiceData invoicedata) {
    try {
        int custID = invoicedata.getCustomerID();
        int billID = invoicedata.getBillID(); // Assume you have a method to get billID
        Connection connection = DatabaseManager.getConnection();


        try (PreparedStatement deleteLineItemsStatement = connection.prepareStatement("DELETE FROM lineItems WHERE billID = ?")) {
            deleteLineItemsStatement.setInt(1, billID);
            deleteLineItemsStatement.executeUpdate();
        }

        try (PreparedStatement deleteBillsStatement = connection.prepareStatement("DELETE FROM bills WHERE customerID = ? AND billID = ?")) {
            deleteBillsStatement.setInt(1, custID);
            deleteBillsStatement.setInt(2, billID);
            deleteBillsStatement.executeUpdate();
        }


        getTableView().getItems().remove(invoicedata);

        connection.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

private void handleUpdate(ManageInvoiceData invoicedata) {
           
                
                billingsController.setInvoiceData(invoicedata);

        
                TabPane tabPane = billingsController.getTabPane();
                 System.out.println("Before selecting tab: " + tabPane.getSelectionModel().getSelectedIndex());
              
                tabPane.getSelectionModel().select(0);
                System.out.println("After selecting tab: " + tabPane.getSelectionModel().getSelectedIndex());
                // ... other code
            
        }


     
    }
}
