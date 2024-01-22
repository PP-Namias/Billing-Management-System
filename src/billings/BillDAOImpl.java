package billings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import databaseSQL.DatabaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BillDAOImpl implements BillDAO{

	private ObservableList <Bill> billsData = FXCollections.observableArrayList();

	public ObservableList<Bill> getBillsData() {
		return billsData;
	}

	@Override
	public Bill createBill() {
		return null;
	}
	
	@Override
	public void addBill(Bill bill) {
		String insertQuery = "INSERT INTO bills (customerID, issueDate, dueDate, docType, paymentType) values(?,?,?,?,?)";
		try (
			Connection connection = DatabaseManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
			statement.setInt(1, bill.getCustomerID());
			statement.setDate(2, bill.getIssueDate());
			statement.setDate(3, bill.getDueDate());
			statement.setString(4, String.valueOf(bill.getDoctype()));
			statement.setString(5, String.valueOf(bill.getPaymentType()));

			statement.executeUpdate();

			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public Bill getLastBill() {
		Bill bill = new Bill();

		String fetchQuery = "SELECT * FROM bills ORDER BY billID DESC LIMIT 1";
		try {
			Connection connection = DatabaseManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(fetchQuery, PreparedStatement.RETURN_GENERATED_KEYS);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				bill.setBillID(resultSet.getInt("billID"));
				bill.setCustomerID(resultSet.getInt("customerID"));
				bill.setIssueDate(resultSet.getDate("issueDate"));
				bill.setDueDate(resultSet.getDate("dueDate"));
				bill.setDoctype(Bill.DocType.valueOf(resultSet.getString("docType")));
				bill.setPaymentType(Bill.PaymentType.valueOf(resultSet.getString("paymentType")));
			}
			resultSet.close();
			statement.close();
			connection.close();


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bill;
		
	}

	@Override
	public void deleteBill() {
		// TODO Auto-generated method stub
		
	}
	

}
