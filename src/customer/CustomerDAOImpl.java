package customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import databaseSQL.DatabaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class CustomerDAOImpl implements CustomerDAO {
	
	private ObservableList <Customer> customersData = FXCollections.observableArrayList();

	public ObservableList<Customer> getCustomersData() {
		return customersData;
	}

	@Override
	public Customer createCustomer(String firstName, String lastName, String contactNumber, String email, String address, String town, String country, String postal) {
		Customer customer = new Customer(
				firstName,
				lastName,
				contactNumber,
				email,
				address,
				town,
				country,
				postal
				);
		return customer;
	}

	@Override
	public void fetchAllCustomers() {
		customersData.clear();
		try {
			Connection connection = DatabaseManager.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * from customers");
	        ResultSet resultSet = statement.executeQuery();
	        
	        while(resultSet.next()) {
	        	Customer customer = new Customer(
	        		resultSet.getInt("customerID"),
	        		resultSet.getString("creationDate"),
	        		resultSet.getString("firstName"),
	        		resultSet.getString("lastName"),
	        		resultSet.getString("contactNumber"),
	        		resultSet.getString("email"),
	        		resultSet.getString("address"),
	        		resultSet.getString("town"),
	        		resultSet.getString("country"),
	        		resultSet.getString("postal")
	        		);
	        	customersData.add(customer);
	        }
	        
	        resultSet.close();
	        statement.close();
	        connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Customer getCustomerByID(int customerID) {
		Customer customer = new Customer();

		String fetchQuery = "SELECT * from customers WHERE customerID = ?";
		try {
			Connection connection = DatabaseManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(fetchQuery, PreparedStatement.RETURN_GENERATED_KEYS);
	        
	        statement.setInt(1, customerID);

	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
		        customer.setCustomerId(resultSet.getInt("customerID"));
		        customer.setCreationDate(resultSet.getString("creationDate"));
		        customer.setFirstName(resultSet.getString("firstName"));
		        customer.setLastName(resultSet.getString("lastName"));
		        customer.setContactNumber(resultSet.getString("contactNumber"));
		        customer.setEmail(resultSet.getString("email"));
		        customer.setTown(resultSet.getString("town"));
		        customer.setAddress(resultSet.getString("address"));
		        customer.setCountry(resultSet.getString("country"));
		        customer.setPostal(resultSet.getString("postal"));
	        }
	        resultSet.close();
	        statement.close();
	        connection.close();
	        
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customer;
	}
	
	@Override
	public Customer getCustomerByName(String firstName, String lastName) {
		
		Customer customer = new Customer();
		
		String fetchQuery = "SELECT * from customers WHERE firstName = ? AND lastName = ?";
		try {
			Connection connection = DatabaseManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(fetchQuery, PreparedStatement.RETURN_GENERATED_KEYS);
	        
	        statement.setString(1, firstName);
	        statement.setString(2, lastName);

	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
		        customer.setCustomerId(resultSet.getInt("customerID"));
		        customer.setCreationDate(resultSet.getString("creationDate"));
		        customer.setFirstName(resultSet.getString("firstName"));
		        customer.setLastName(resultSet.getString("lastName"));
		        customer.setContactNumber(resultSet.getString("contactNumber"));
		        customer.setEmail(resultSet.getString("email"));
		        customer.setTown(resultSet.getString("town"));
		        customer.setAddress(resultSet.getString("address"));
		        customer.setCountry(resultSet.getString("country"));
		        customer.setPostal(resultSet.getString("postal"));
	        }
	        resultSet.close();
	        statement.close();
	        connection.close();
	        
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customer;

	}
	
	@Override
	public boolean checkIfCustomerExists(Customer customer) {
		
		boolean customerExists = true;
		String checkQuery = "SELECT * from customers WHERE firstName = ? AND lastName = ? AND country = ?";
		try {
			Connection connection = DatabaseManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(checkQuery, PreparedStatement.RETURN_GENERATED_KEYS);
	        
	        statement.setString(1, customer.getFirstName());
	        statement.setString(2, customer.getLastName());
	        statement.setString(3, customer.getCountry());

	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) customerExists = true;
	        else customerExists = false;
	        
	        
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		 return customerExists;
	}

	@Override
	public void updateCustomer(Customer customer) {
		String updateQuery = "UPDATE customers SET "
			+ "creationDate = ?,"
			+ "firstName = ?,"
			+ "lastName = ?,"
			+ "contactNumber = ?, "
			+ "email = ?,"
			+ "address = ?,"
			+ "town = ?,"
			+ "country = ?,"
			+ "postal = ? "
			+ "WHERE customerID = ?";
		try (
			Connection connection = DatabaseManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(updateQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
			statement.setTimestamp(1, Timestamp.valueOf(customer.getCreationDate()));
			statement.setString(2, customer.getFirstName());
			statement.setString(3, customer.getLastName());
			statement.setString(4, customer.getContactNumber());
			statement.setString(5, customer.getEmail());
			statement.setString(6, customer.getAddress());
			statement.setString(7, customer.getTown());
			statement.setString(8, customer.getCountry());
			statement.setString(9, customer.getPostal());
			statement.setInt(10, customer.getCustomerId());
			
			statement.executeUpdate();

			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

	@Override
	public void deleteCustomer(int customerID) {
		
		try {
			Connection connection = DatabaseManager.getConnection();
			PreparedStatement statement = connection.prepareStatement("DELETE FROM customers WHERE customerID = " + customerID);
			statement.executeUpdate();
			
			statement.close();
	        connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void addCustomer(Customer customer) {
		String insertQuery = "INSERT INTO customers (creationDate,firstName,lastName,contactNumber,email,address,town,country,postal) values(?,?,?,?,?,?,?,?,?)";
		try (
			Connection connection = DatabaseManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
			statement.setTimestamp(1, Timestamp.valueOf(customer.getCreationDate()));
			statement.setString(2, customer.getFirstName());
			statement.setString(3, customer.getLastName());
			statement.setString(4, customer.getContactNumber());
			statement.setString(5, customer.getEmail());
			statement.setString(6, customer.getAddress());
			statement.setString(7, customer.getTown());
			statement.setString(8, customer.getCountry());
			statement.setString(9, customer.getPostal());

			statement.executeUpdate();

			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
