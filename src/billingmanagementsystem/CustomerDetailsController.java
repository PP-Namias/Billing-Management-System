package billingmanagementsystem;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import customer.Customer;
import customer.CustomerDAOImpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class CustomerDetailsController implements Initializable {

    @FXML
    private TextField countryFid, firstNameFid, lastNameFid, postalFid, townFid, emailFid, contactFid, addressFid;

    @FXML
    private Button clearButton, saveButton;

    @FXML
    private Text messageBox, customerIDText, creationDateText, customerIDLabel, creationDateLabel;
    
    
    boolean isUpdate = false;
    CustomerDAOImpl CustomerDAO = new CustomerDAOImpl();
    
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	customerIDLabel.setVisible(false); 
		creationDateLabel.setVisible(false);
	
	}
     
    public void setCustomerInfo(Customer customer) {
    	customerIDText.setText(String.valueOf(customer.getCustomerId()));
    	creationDateText.setText(String.valueOf(customer.getCreationDate()));
    	firstNameFid.setText(customer.getFirstName());
    	lastNameFid.setText(customer.getLastName());
    	contactFid.setText(customer.getContactNumber());
    	emailFid.setText(customer.getEmail());
    	addressFid.setText(customer.getAddress());
    	townFid.setText(customer.getTown());
    	countryFid.setText(customer.getCountry());
    	postalFid.setText(customer.getPostal());
    	isUpdate = true;
    	customerIDLabel.setVisible(true); 
		creationDateLabel.setVisible(true);
    }
    
    @FXML
    void clear(MouseEvent event) {
    	clearTextFields(firstNameFid, lastNameFid, contactFid, emailFid, addressFid, townFid, countryFid, postalFid);
    }
    
    

    @FXML
    void save(MouseEvent event) throws IOException {
    	boolean canSave = validateTextFields(firstNameFid, lastNameFid, contactFid, emailFid, addressFid, townFid, countryFid, postalFid);
    	
    	if (canSave) {
    		
    		if (isUpdate) {
    			System.out.println(creationDateText.getText());
    			Customer customer = new Customer(
    					Integer.parseInt(customerIDText.getText()),
    					String.valueOf(creationDateText.getText()),
            			firstNameFid.getText(),
            			lastNameFid.getText(),
            			contactFid.getText(),
            			emailFid.getText(),
            			addressFid.getText(), 
            			townFid.getText(), 
            			countryFid.getText(), 
            			postalFid.getText()
            			);
            	
    			CustomerDAO.updateCustomer(customer);
    			messageBox.setText("Successfully updated "+ customer.getFirstName() +" "+ customer.getLastName());
    			isUpdate = false;
    		} else {
    			Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String creationDate = String.valueOf(dateFormat.format(currentTimestamp));
    			System.out.println(creationDate);
    			Customer customer = new Customer(
    					creationDate,
            			firstNameFid.getText(),
            			lastNameFid.getText(),
            			contactFid.getText(),
            			emailFid.getText(),
            			addressFid.getText(), 
            			townFid.getText(), 
            			countryFid.getText(), 
            			postalFid.getText()
            			);
    			CustomerDAO.addCustomer(customer);
            	messageBox.setText("Successfully added "+ customer.getFirstName() +" "+ customer.getLastName());
    		}
        	
        	clearTextFields(firstNameFid, lastNameFid, contactFid, emailFid, addressFid, townFid, countryFid, postalFid);
    	}
    }

    private void clearTextFields(TextField... textFields) {
    	for (TextField textField : textFields) {
    		textField.clear();
        }
    }
    
    private boolean validateTextFields(TextField... textFields) {
        boolean allFieldsFilled = true;

        for (TextField textField : textFields) {
            if (textField.getText().trim().isBlank()) {
                allFieldsFilled = false;
                break;
            }
        }

        if(!allFieldsFilled){
			messageBox.setText("Please fill All Fields");
			return false;
        }
        return true;
    }

}
