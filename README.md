<p align="center">
<img src="Banner.png" alt="Banner">
</p>

# Billing Management System
A billing management system is a software application or platform designed to facilitate the process of generating invoices, managing billing information, and handling financial transactions for businesses and organizations

## Features
Planned (✘) and currently working (✘) - but not necessarily fully completed - features:

### Super Admin
  - ✘ Shop Management `[All Shops]`
      - ✘ Add Shop Data
      - ✘ Display Shop Data
      - ✘ Search Shop Data
      - ✘ Edit Shop Data
      - ✘ Delete Shop Data
  - ✘ Product Management `[All Shops]`
      - ✘ Add Product Data
      - ✘ Display Product Data
      - ✘ Search Product Data
      - ✘ Edit Product Data
      - ✘ Delete Product Data
  - ✘ Login Logs
      - ✘ Records Login Data
            `LogID,Username,Name,Access,Date,Time`
      - ✘ Display Login Data
      - ✘ Export Login Data into Excel
  - ✘ Order Logs
      - ✘ Records Order Data
            `OrderID,Username,Name,Access,LogID,Date,Time`
      - ✘ Display Order Data
      - ✘ Export Order Data into Excel
  - ✘ Billing Logs
      - ✘ Records Billing Data
            `BillingID,Username,Name,Access,OrderID,Date,Time`
      - ✘ Display Billing Data
      - ✘ Export Billing Data into Excel
  - ✘ About Us



### Admin [ Shop ]
  - ✘ Shop Management `[Own Shops]`
      - ✘ Add Shop Data
      - ✘ Display Shop Data
      - ✘ Search Shop Data
      - ✘ Edit Shop Data
      - ✘ Delete Shop Data
  - ✘ Product Management `[Own Shops]`
      - ✘ Add Product Data
      - ✘ Display Product Data
      - ✘ Search Product Data
      - ✘ Edit Product Data
      - ✘ Delete Product Data
  - ✘ Order Logs `[Own Shops]`
      - ✘ Records Order Data
            `OrderID,Username,Name,Access,LogID,Date,Time`
      - ✘ Display Order Data
      - ✘ Export Order Data into Excel
  - ✘ Billing Logs `[Own Shops]`
      - ✘ Records Billing Data
            `BillingID,Username,Name,Access,OrderID,Date,Time`
      - ✘ Display Billing Data
      - ✘ Export Billing Data into Excel
  - ✘ About Us



### Super User [ User with Account ]
  - ✘ Product Order `[Own Order(s)]`
      - ✘ Display Product Data
      - ✘ Search Product Data
      - ✘ Delete Product Data
  - ✘ Order Logs `[Own Order(s)]`
      - ✘ Records Order Data
            `OrderID,Username,Name,Access,LogID,Date,Time`
      - ✘ Display Order Data
      - ✘ Export Order Data into Excel
  - ✘ Billing Logs `[Own Order(s)]`
      - ✘ Records Billing Data
            `BillingID,Username,Name,Access,OrderID,Date,Time`
      - ✘ Display Billing Data
      - ✘ Export Billing Data into Excel
  - ✘ About Us

### User [ Guest ]
  - ✘ Product Order `[Own Order(s)]`
      - ✘ Display Product Data
      - ✘ Search Product Data
      - ✘ Delete Product Data
  - ✘ About Us


## Flowchart
<p align="center">
<img src="Flowchart.png" alt="Flowchart">
</p>

## Programming Paradigms & Principles Used
  - Object-Oriented Programming
  - Code Reusability
  - File Handling

## How to Use
lorem epsum 

## Pros: 
It automates the billing process, ensuring accurate calculations of charges, taxes, and fees. This reduces the risk of errors and helps businesses save time and resources. 

## Cons: 
Need Maintenance and updates most of the time, necessary siya to keep the BMS functioning correctly

## Technologies Used
The following are the technologies that have been used in the development of this project. All of them are free to use.
- JetBrains IntelliJ IDE
- JavaFx
- Apache NetBeans IDE (for the GUI designer)
- MySQL Server and Workbench
- JDK 21

## Source Code
The software code has been divided into four different packages:
- Data Access Object (DAO): Contains the data access layer of the software that interacts directly with the database and its tables. Used for retrieval and modification of data.
- Data Transfer Object (DTO): Contains the data transfer layer that allows the data to be transferred between the data access layer and the UI layer.
- Database: Contains the ConnectionFactory class that retrieves the database connection and verifies user credentials for the application.
- User Interface (UI): Contains all the GUI classes making up the interface layer of the software.

Click [here](src/com/inventory/) to skip directly to the source code.

## Work-in-Progress
This project is a work in progress and more features are yet to be added with new technologies.


> [!NOTE]  
> Some issues were not yet fixed thus I will be updating this in several upcoming weeks/months. And I hope that this project will be used for educational purposes and that the system itself maintains its copyrights as proposed.

Enjoy :)
