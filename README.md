<p align="center">
  <a href="https://github.com/Mon0629/BillingManagementSystem" target="_blank"><img src="images/banner.png" alt="Billing Management System Banner"/></a>
  <a href="https://github.com/Mon0629/BillingManagementSystem/blob/master/LICENSE" target="_blank"><img src="https://img.shields.io/badge/all_contributors-4-orange.svg?style=flat-square" alt="Billing Management System All Contributors" /></a>
  <a href="https://github.com/Mon0629/BillingManagementSystem/blob/master/LICENSE" target="_blank"><img src="https://img.shields.io/github/license/Mon0629/BillingManagementSystem?style=flat-square" alt="Billing Management System license" /></a>
  <a href="https://github.com/Mon0629/BillingManagementSystem/fork" target="_blank"><img src="https://img.shields.io/github/forks/Mon0629/BillingManagementSystem?style=flat-square" alt="Billing Management System forks"/></a>
  <a href="https://github.com/Mon0629/BillingManagementSystem/stargazers" target="_blank"><img src="https://img.shields.io/github/stars/Mon0629/BillingManagementSystem?style=flat-square" alt="Billing Management System stars"/></a>
  <a href="https://github.com/Mon0629/BillingManagementSystem/issues" target="_blank"><img src="https://img.shields.io/github/issues/Mon0629/BillingManagementSystem?style=flat-square" alt="Billing Management System issues"/></a>
  <a href="https://github.com/Mon0629/BillingManagementSystem/pulls" target="_blank"><img src="https://img.shields.io/github/issues-pr/Mon0629/BillingManagementSystem?style=flat-square" alt="Billing Management System pull-requests"/></a>
  <a href="https://github.com/Mon0629/BillingManagementSystem/releases" target="_blank"><img src="https://img.shields.io/github/downloads/Mon0629/BillingManagementSystem/total.svg?style=flat-square" alt="Billing Management System downloads"/></a>
</p>

<br />
<p align="center">
  <a href="https://github.com/Mon0629/BillingManagementSystem">
    <img src="./images/dashboardlogo.png" alt="Billing Management System Logo" width="200" height="200">
  </a>

  <h1 align="center"><b>Billing Management System</b> - <i>A System for Managing Billing, Invoices, and Financial Transactions</i></h1>

  <p align="center">
    Billing Management System is a software application or platform designed to facilitate the process of generating invoices, managing billing information, and handling financial transactions for businesses and organizations
    <br />
    <a href="https://github.com/Mon0629/BillingManagementSystem/issues">Report Bug</a>
    ·
    <a href="https://github.com/Mon0629/BillingManagementSystem/issues">Request Feature</a>
  </p>
</p>

## Table of Contents
- [Features](#features)
  - [Super Admin](#super-admin)
- [Flowchart](#flowchart)
- [Programming Paradigms & Principles Used](#programming-paradigms--principles-used)
- [How to Use](#how-to-use)
- [Pros](#pros)
- [Cons](#cons)
- [Technologies Used](#technologies-used)
- [Work-in-Progress](#work-in-progress)
  - [Upcoming Features](#upcoming-features)
- [License](#license)

## Features
Planned (✘) and currently working (✔) - but not necessarily fully completed - features:
### Super Admin
- ✔ Shop Management
  - ✔ Add Shop Data
  - ✔ Display Shop Data
  - ✔ Search Shop Data
  - ✔ Edit Shop Data
  - ✔ Delete Shop Data
- ✔ Product Management
  - ✔ Add Product Data
  - ✔ Display Product Data
  - ✔ Search Product Data
  - ✔ Edit Product Data
  - ✔ Delete Product Data
- ✔ Login Logs
  - ✔ Records Login Data
  - ✔ Display Login Data
  - ✔ Export Login Data into Excel
- ✔ Order Logs
  - ✔ Records Order Data
  - ✔ Display Order Data
  - ✔ Export Order Data into Excel
- ✔ Billing Logs
  - ✔ Records Billing Data
  - ✔ Display Billing Data
  - ✔ Export Billing Data into Excel
- ✔ About Us

## Flowchart
<p align="center">
  <img src="images/Flowchart.png" alt="Flowchart">
</p>

## Programming Paradigms & Principles Used
  - Object-Oriented Programming
  - Code Reusability
  - File Handling

## How to Use
### Prerequisites
Ensure you have the following installed:
- [Java Development Kit (JDK) 21](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Apache NetBeans IDE](https://netbeans.apache.org/)

### Step 1: Clone the Repository
```bash
git clone https://github.com/Mon0629/BillingManagementSystem.git
```

### Step 2: Open Project in NetBeans IDE
1. Open NetBeans IDE.
2. Select "File" > "Open Project" and choose the `BillingManagementSystem` folder.

### Step 3: Set Up Database
1. Create a MySQL database named `bms`.
2. Import SQL script (`sqlFiles/bms.sql`) into the `bms` database.

### Step 4: Configure Database Connection
1. Open `src/databaseSQL/DatabaseManager.java`.
2. Update `DB_URL`, `USER`, and `PASS` with your MySQL server details.

### Step 5: Run the Application
1. Right-click on the project in NetBeans.
2. Select "Run" to execute the Billing Management System.

### Step 6: Explore Features
1. Navigate through features like Shop Management, Product Management, Login Logs, Order Logs, Billing Logs, and About Us.
2. Add, display, search, edit, and delete data within each feature.

### Step 7: Export Data
1. Use export functionality for Login Logs, Order Logs, and Billing Logs to export data to Excel.

### Step 8: About Us
1. Visit the "About Us" section to learn more about the project and contributors.

### Step 9: Enjoy!
You've successfully set up and used the Billing Management System. Feel free to contribute, report issues, or explore additional functionalities.

This guide assumes a basic understanding of Java and MySQL. For any issues, refer to the [issues page](https://github.com/Mon0629/BillingManagementSystem/issues) or contribute by reporting problems or suggesting improvements.

Feel free to further customize this to match the specific details of your project.

## Pros:
- **Automation Efficiency:** Streamlines billing processes for accurate calculations, reducing the risk of errors.
- **Time and Resource Savings:** Saves time and resources for businesses through automated billing.

## Cons:
- **Maintenance Requirement:** Requires periodic maintenance and updates for optimal functionality.
- **Educational Use:** Intended primarily for educational purposes, with ongoing improvements and issue resolutions.

## Technologies Used
The following are the technologies that have been used in the development of this project. All of them are free to use.
- [JavaFX 21](https://openjfx.io/)
- [Apache NetBeans IDE](https://netbeans.apache.org/)
- [MySQL Server and Workbench](https://www.mysql.com/)
- [JDK 21](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)

## Work-in-Progress
This project is a work in progress and more features are yet to be added with new technologies.
### Upcoming Features
- Additional user roles and permissions
- Enhanced reporting and analytics
- Integration with external APIs for payment processing

## License
This project is licensed under the [MIT License](LICENSE).

> [!NOTE]  
> Some issues were not yet fixed thus I will be updating this in several upcoming weeks/months. And I hope that this project will be used for educational purposes and that the system itself maintains its copyrights as proposed.

Enjoy ;)