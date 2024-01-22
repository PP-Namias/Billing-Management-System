/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package billingmanagementsystem;

/**
 *
 * @author User
 */
public class OrderList implements DisplayableItem{
    
    private int billID;
     private String productID;
    private String productName;
    private double price;
    private double quantity;
    private double amount;

    // Constructors

    public OrderList(String productID, String productName, double price, double quantity, double amount) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.amount = amount;
    }

    // Getters

    public String getProductID() {
        return productID;
    }
 
    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getAmount() {
        return amount;
    }

    // Setters

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String getDisplayText() {
    return String.format("Product ID: %s, Product Name: %s, Price: %.2f, Quantity: %.0f, Amount: %.2f",
            productID, productName, price, quantity, amount);
}

    
}

