package pdfGeneration;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

import billings.Bill;
import billings.Bill.DocType;
import billings.Bill.PaymentType;
import customer.Customer;
import lineItems.LineItem;

public class PDFGenerator {
	
	private Bill bill;
	private Customer customer;
	private List<LineItem> lineItemList;
	private String path;
	private String refNumber;
	private Bill.PaymentType paymentType;
	private String cash;
	private String total;
	
	public PDFGenerator(Bill bill, Customer customer, List<LineItem> lineItemList, String refNumber,
			PaymentType paymentType, String total) {
		super();
		this.bill = bill;
		this.customer = customer;
		this.lineItemList = lineItemList;
		this.refNumber = refNumber;
		this.paymentType = paymentType;
		this.total = total;
	}

	public PDFGenerator(Bill bill, Customer customer, List<LineItem> lineItemList, PaymentType paymentType,
			String cash, String total) {
		super();
		this.bill = bill;
		this.customer = customer;
		this.lineItemList = lineItemList;
		this.paymentType = paymentType;
		this.cash = cash;
		this.total = total;
	}

	
	public PDFGenerator() {
		// TODO Auto-generated constructor stub
	}

	public String getRefNumber() {
		return refNumber;
	}

	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}

	public Bill.PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Bill.PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public String getCash() {
		return cash;
	}

	public void setCash(String cash) {
		this.cash = cash;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Customer getBillCustomer() {
		return customer;
	}

	public void setBillCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<LineItem> getLineItemList() {
		return lineItemList;
	}

	public void setLineItemList(List<LineItem> lineItemList) {
		this.lineItemList = lineItemList;
	}

	public void createPDF() throws FileNotFoundException  {
		
		path = "C:\\bmsPdf\\DOC" + bill.getBillID()+ bill.getIssueDate() + ".pdf";
		PdfWriter pdfWriter = new PdfWriter(path);
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		pdfDocument.setDefaultPageSize(PageSize.LEGAL);
		Document document = new Document(pdfDocument);

		Border separator = new SolidBorder(1.5f);
		DeviceRgb blue = new DeviceRgb(67, 85, 133);
		DeviceRgb white = new DeviceRgb(255, 255, 255);
		DeviceRgb black = new DeviceRgb(10, 10, 10);
		
		Style header1 = new Style()
				.setFontSize(28)
				.setBold()
				.setBorder(Border.NO_BORDER);
		
		Style docInfoStyle = new Style()
				.setFontSize(11)
				.setBorder(Border.NO_BORDER);
		
		Style header2 = new Style()
				.setFontSize(12)
				.setBold()
				.setBorder(Border.NO_BORDER);
		
		Style header3 = new Style()
				.setFontSize(11)
				.setBold()
				.setBorder(Border.NO_BORDER);
		
		Style paragraph = new Style()
				.setFontSize(11)
				.setBorder(Border.NO_BORDER);
		
		Style cellColumn = new Style()
				.setFontSize(11)
				.setFontColor(white)
				.setBackgroundColor(blue)
				.setBorder(Border.NO_BORDER);	
		
		Style cell = new Style()
				.setFontSize(11)
				.setBorderLeft(Border.NO_BORDER)
				.setBorderTop(Border.NO_BORDER)
				.setBorderRight(Border.NO_BORDER);
		
		Style cellTotal = new Style()
				.setFontSize(11)
				.setFontColor(white)
				.setBackgroundColor(black)
				.setBorder(Border.NO_BORDER);
//		Style bottomBorder = new Style()
//				.setBorder(Border.);
		
        Table docTypeTable = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();
        docTypeTable.addCell(new Cell().add(new Paragraph(String.valueOf(bill.getDoctype()))).addStyle(header1));
        
        Table docInfoTable = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();
        docInfoTable.addCell(new Cell().add( new Paragraph("DocumentID: ")).addStyle(docInfoStyle));
        docInfoTable.addCell(new Cell().add( new Paragraph(String.valueOf(bill.getBillID()))).addStyle(docInfoStyle));
        docInfoTable.addCell(new Cell().add( new Paragraph("Issue Date: ")).addStyle(docInfoStyle));
        docInfoTable.addCell(new Cell().add( new Paragraph(String.valueOf(bill.getIssueDate()))).addStyle(docInfoStyle));
        docInfoTable.addCell(new Cell().add( new Paragraph("Due Date: ")).addStyle(docInfoStyle));
        docInfoTable.addCell(new Cell().add( new Paragraph(String.valueOf(bill.getDueDate()))).addStyle(docInfoStyle));
        docInfoTable.addCell(new Cell().add( new Paragraph("Payment Mode: ")).addStyle(docInfoStyle));
        docInfoTable.addCell(new Cell().add( new Paragraph(String.valueOf(bill.getPaymentType()))).addStyle(docInfoStyle));
        if (!(paymentType.equals(PaymentType.CASH))) {
        	docInfoTable.addCell(new Cell().add( new Paragraph("Reference Number: ")).addStyle(docInfoStyle));
            docInfoTable.addCell(new Cell().add( new Paragraph(refNumber)).addStyle(docInfoStyle));
            
        }
        docTypeTable.addCell(new Cell().add(docInfoTable).setBorder(Border.NO_BORDER));
        
        document.add(docTypeTable.setBorderBottom(separator));
        
        Table customerTable = new Table(UnitValue.createPercentArray(new float[] {25,75})).useAllAvailableWidth();
        customerTable.addCell(new Cell(1,2).add( new Paragraph("Customer Information")).addStyle(header2));
        customerTable.addCell(new Cell().add( new Paragraph("Name")).addStyle(header3));
        customerTable.addCell(new Cell().add( new Paragraph(String.valueOf(customer.getFirstName()) + " " + String.valueOf(customer.getLastName()))).addStyle(paragraph));
        customerTable.addCell(new Cell().add( new Paragraph("Contact Number")).addStyle(header3));
        customerTable.addCell(new Cell().add( new Paragraph(String.valueOf(customer.getContactNumber()))).addStyle(paragraph));
        customerTable.addCell(new Cell().add( new Paragraph("Email")).addStyle(header3));
        customerTable.addCell(new Cell().add( new Paragraph(String.valueOf(customer.getEmail()))).addStyle(paragraph));
        customerTable.addCell(new Cell().add( new Paragraph("Address")).addStyle(header3));
        customerTable.addCell(new Cell().add( new Paragraph(String.valueOf(customer.getAddress()))).addStyle(paragraph));
        customerTable.addCell(new Cell().add( new Paragraph("City")).addStyle(header3));
        customerTable.addCell(new Cell().add( new Paragraph(String.valueOf(customer.getTown()))).addStyle(paragraph));
        customerTable.addCell(new Cell().add( new Paragraph("Country")).addStyle(header3));
        customerTable.addCell(new Cell().add( new Paragraph(String.valueOf(customer.getCountry()))).addStyle(paragraph));
        customerTable.addCell(new Cell().add( new Paragraph("Postal")).addStyle(paragraph));
        customerTable.addCell(new Cell().add( new Paragraph(String.valueOf(customer.getPostal()))).addStyle(paragraph));
        
        document.add(customerTable.setBorderBottom(separator));
        document.add(new Paragraph("Products").addStyle(header2));
        
        Table productsTable = new Table(UnitValue.createPercentArray(new float[] {44, 16, 20, 20})).useAllAvailableWidth();
        productsTable.addCell(new Cell().add( new Paragraph("Product Name")).addStyle(cellColumn));
        productsTable.addCell(new Cell().add( new Paragraph("Quantity")).addStyle(cellColumn));
        productsTable.addCell(new Cell().add( new Paragraph("Price")).addStyle(cellColumn));
        productsTable.addCell(new Cell().add( new Paragraph("Subtotal")).addStyle(cellColumn));
        
        BigDecimal totalAmount = new BigDecimal("0.00");
        
        for (LineItem lineItem : lineItemList) {
        	productsTable.addCell(new Cell().add( new Paragraph(lineItem.getProductName())).addStyle(cell));
            productsTable.addCell(new Cell().add( new Paragraph(String.valueOf(lineItem.getQuantity()))).addStyle(cell));
            productsTable.addCell(new Cell().add( new Paragraph("PHP " + String.valueOf(lineItem.getUnitPrice()))).addStyle(cell));
            productsTable.addCell(new Cell().add( new Paragraph("PHP " + String.valueOf(lineItem.getLineItemTotal()))).addStyle(cell));
            totalAmount = totalAmount.add(lineItem.getLineItemTotal());
        }
        
        
        if (paymentType.equals(PaymentType.CASH)) {

            BigDecimal recCash = new BigDecimal(cash);
            BigDecimal recTotal = new BigDecimal(total);
        	productsTable.addCell(new Cell(1,2).add( new Paragraph("")).addStyle(paragraph));
        	productsTable.addCell(new Cell().add( new Paragraph("Total Amount:")).addStyle(cellTotal));
            productsTable.addCell(new Cell().add( new Paragraph("PHP " + String.valueOf(totalAmount))).addStyle(cellTotal));
            productsTable.addCell(new Cell(1,2).add( new Paragraph("")).addStyle(paragraph));
        	productsTable.addCell(new Cell().add( new Paragraph("Cash:")).addStyle(cellTotal));
            productsTable.addCell(new Cell().add( new Paragraph("PHP " + String.valueOf(cash))).addStyle(cellTotal));
            productsTable.addCell(new Cell(1,2).add( new Paragraph("")).addStyle(paragraph));
        	productsTable.addCell(new Cell().add( new Paragraph("Change:")).addStyle(cellTotal));
            productsTable.addCell(new Cell().add( new Paragraph("PHP " + String.valueOf(recCash.subtract(recTotal)))).addStyle(cellTotal));
        } else if (bill.getDoctype().equals(DocType.RECEIPT)) {
        	productsTable.addCell(new Cell(1,2).add( new Paragraph("")).addStyle(paragraph));
        	productsTable.addCell(new Cell().add( new Paragraph("Total Amount:")).addStyle(cellTotal));
            productsTable.addCell(new Cell().add( new Paragraph("PHP " + String.valueOf(totalAmount))).addStyle(cellTotal));
            
        }
        else {
        	productsTable.addCell(new Cell(1,2).add( new Paragraph("Note: Pay the amount on or before the Due Date.")).addStyle(paragraph));
        	productsTable.addCell(new Cell().add( new Paragraph("Total Amount:")).addStyle(cellTotal));
            productsTable.addCell(new Cell().add( new Paragraph("PHP " + String.valueOf(totalAmount))).addStyle(cellTotal));
            
        }
        
        
        document.add(productsTable);
        
        document.close();
		
	}
        
        
        
	
	
	
//	public void createPDF() throws FileNotFoundException {
//		
//		float twoCol = 285f;
//		float twoCol = 
//	}
}
