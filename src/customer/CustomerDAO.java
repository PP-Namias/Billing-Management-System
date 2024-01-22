package customer;

public interface CustomerDAO {
	Customer createCustomer(
		String firstName, 
		String lastName, 
		String contactNumber,
		String email, 
		String address, 
		String town, 
		String country, 
		String postal);
	void fetchAllCustomers();
	void addCustomer(Customer customer);
	Customer getCustomerByID(int customerID);
	void updateCustomer(Customer customer);
	void deleteCustomer(int customerID);
	boolean checkIfCustomerExists(Customer customer);
	Customer getCustomerByName(String firstName, String lastName);
}
