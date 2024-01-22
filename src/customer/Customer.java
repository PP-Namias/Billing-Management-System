package customer;

public class Customer {
	private int customerId;
	private String creationDate;
	private String firstName;
	private String lastName;
	private String contactNumber;
	private String email;
	private String address;
	private String town;
	private String country;
	private String postal;
	
	public Customer(int customerId, String creationDate, String firstName, String lastName, String contactNumber, String email, String address,   String town,
			String country, String postal) {
		this.customerId = customerId;
		this.creationDate = creationDate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNumber = contactNumber; 
		this.email = email;
		this.address = address;
		this.town = town;
		this.country = country;
		this.postal = postal;
	}
	
	public Customer(String creationDate, String firstName, String lastName, String contactNumber, String email, String address, String town,
			String country, String postal) {
		this.creationDate = creationDate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNumber = contactNumber; 
		this.email = email;
		this.address = address;
		this.town = town;
		this.country = country;
		this.postal = postal;
	}
	
	public Customer(String firstName, String lastName, String contactNumber, String email, String address, String town,
			String country, String postal) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNumber = contactNumber; 
		this.email = email;
		this.address = address;
		this.town = town;
		this.country = country;
		this.postal = postal;
	}
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostal() {
		return postal;
	}
	public void setPostal(String postal) {
		this.postal = postal;
	}
	
	
	
	
	
	
}
