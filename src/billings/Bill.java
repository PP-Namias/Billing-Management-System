package billings;

import java.sql.Date;
import java.sql.Timestamp;

public class Bill {
	
	public enum DocType{
		RECEIPT, INVOICE, BILL
	}
	
	public enum PaymentType{
		CASH, GCASH, CHECK
	}	
	
	private int billID;
	private int customerID;
	private Date issueDate;
	private Date dueDate;
	private Timestamp transactionAdded;
	private DocType doctype;
	private PaymentType paymentType;
	
	public Bill(int customerID, Date issueDate, Date dueDate, DocType doctype, PaymentType paymentType) {
		this.customerID = customerID;
		this.issueDate = issueDate;
		this.dueDate = dueDate;
		this.doctype = doctype;
		this.paymentType = paymentType;
	}
	
	public Bill() {
		// TODO Auto-generated constructor stub
	}
	
	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Timestamp getTransactionAdded() {
		return transactionAdded;
	}

//	public void setTransactionAdded(Timestamp transactionAdded) {
//		this.transactionAdded = transactionAdded;
//	}

	public DocType getDoctype() {
		return doctype;
	}
	public void setDoctype(DocType doctype) {
		this.doctype = doctype;
	}
	public int getBillID() {
		return billID;
	}
	public void setBillID(int billID) {
		this.billID = billID;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
}
