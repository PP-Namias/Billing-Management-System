package lineItems;

import java.math.BigDecimal;

public class LineItem {
	
	private int lineItemID;
	private int billID;
	private int productID;
	private String productName;
	private int quantity;
	private BigDecimal unitPrice;
	private BigDecimal lineItemTotal;

	public LineItem(int billID, int productID, String productName, int quantity, BigDecimal unitPrice,
			BigDecimal lineItemTotal) {
		this.billID = billID;
		this.productID = productID;
		this.productName = productName;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.lineItemTotal = lineItemTotal;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getLineItemID() {
		return lineItemID;
	}
	public void setLineItemID(int lineItemID) {
		this.lineItemID = lineItemID;
	}
	public int getBillID() {
		return billID;
	}
	public void setBillID(int billID) {
		this.billID = billID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public BigDecimal getLineItemTotal() {
		return lineItemTotal;
	}
	public void setLineItemTotal(BigDecimal lineItemTotal) {
		this.lineItemTotal = lineItemTotal;
	}
	
	
}