/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package billingmanagementsystem;

/**
 *
 * @author User
 */
public class CustomerData {
     private int customerID, contactNumber, postal;
    private String firstName,lastName, email, address, town, country;
    
    public CustomerData(int customerID, String firstName, String lastName, int contactNumber, String email, String address, String town, String country, int postal){
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.email = email;
        this.address=address;
        this.town = town;
        this.country = country;
        this.postal = postal;
    }

   
    
    public int getContactNumber() {
		return contactNumber;
	}



	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}



	//getter
    public int getCustomerID(){
        return customerID;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getEmail(){
        return email;
    }
    public String getAddress(){
        return address;
    }
    public String getTown(){
        return town;
    }
    public String getCountry(){
        return country;
    }
    public int getPostal(){
        return postal;
    }
    
    
    //setter
    
    public void setCostumerID(int customerID){
    this.customerID = customerID;
    }
    public void setFirstName(String firstName){
    this.firstName = firstName;
    }
    public void setLastName(String lastName){
    this.lastName = lastName;
    }
    public void setEmail(String email){
    this.email = email;
    }
    public void setAddress(String address){
    this.address = address;
    }
    public void setTown(String town){
    this.town = town;
    }
    public void setCountry(String country){
    this.country = country;
    }
    public void setPostal(int postal){
    this.postal = postal;
    }
}

