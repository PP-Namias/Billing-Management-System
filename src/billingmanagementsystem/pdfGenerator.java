package billingmanagementsystem;

import java.io.FileNotFoundException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class pdfGenerator {
	
	public void createPdf() throws FileNotFoundException {
	    // Create a PdfWriter
        PdfWriter writer = new PdfWriter("example.pdf");

        // Create a PdfDocument
        PdfDocument pdf = new PdfDocument(writer);

        // Create a Document
        Document document = new Document(pdf);

        // Add content to the document
        document.add(new Paragraph("Hello, iText!"));

        // Close the document
        document.close();

	}
}
