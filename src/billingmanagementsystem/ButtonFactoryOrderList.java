/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package billingmanagementsystem;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import javafx.util.Callback;

/**
 *
 * @author User
 */
public class ButtonFactoryOrderList implements Callback<TableColumn<OrderList, Boolean>, TableCell<OrderList, Boolean>> {

    private final TableView<OrderList> orderListTable;
    private final Label totalAmountLabel;

    public ButtonFactoryOrderList(TableView<OrderList> orderListTable, Label totalAmountLabel) {
        this.orderListTable = orderListTable;
        this.totalAmountLabel = totalAmountLabel;
    }

    @Override
    public TableCell<OrderList, Boolean> call(TableColumn<OrderList, Boolean> param) {
        return new ButtonTableCell();
    }

    private class ButtonTableCell extends TableCell<OrderList, Boolean> {
        final ImageView deleteIcon = new ImageView(new Image(getClass().getResourceAsStream("/Graphics/bin.png")));

        ButtonTableCell() {

            deleteIcon.setOnMouseClicked(event -> {
                OrderList orderList = getTableView().getItems().get(getIndex());

                handleDelete(orderList);

            });

            // Set icons side by side
            HBox buttonHBox = new HBox(deleteIcon);
            HBox.setMargin(deleteIcon, new Insets(0, 0, 0, 0));
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

        private void handleDelete(OrderList orderList) {
            // Subtract the amount of the deleted item from the total amount
            double deletedAmount = orderList.getAmount();
            double currentTotalAmount = Double.parseDouble(totalAmountLabel.getText());
            double newTotalAmount = currentTotalAmount - deletedAmount;
            totalAmountLabel.setText(String.valueOf(newTotalAmount));

            // Remove the item from the table
            orderListTable.getItems().remove(orderList);
            orderListTable.refresh(); // Refresh the table view
        }
    }
}
    
