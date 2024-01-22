/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package billingmanagementsystem;

/**
 *
 * @author User
 */
public class EmployeeData {
    
    private int userID;
    private String username;
    private String password;
    private String userType;
    
    public EmployeeData(int userID, String username, String password, String userType){
    this.userID = userID;
    this.username = username;
    this.password = password;
    this.userType = userType;
    
    }
    
    public int getUserID(){
    return userID;
    }
    public void setUserID(int userID){
    this.userID = userID;
    }
    
    public String getUsername(){
    return username;
    }
    public void setUsername(String username){
    this.username = username;
    }
    
    public String getPassword(){
    return password;
    }
    public void setPassword(String password){
    this.password = password;
    }
    
    public String getUserType(){
    return userType;
    }
    public void setUserType(String userType){
    this.userType = userType;
    }
    
}
