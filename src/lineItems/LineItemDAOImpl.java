package lineItems;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import databaseSQL.DatabaseManager;

public class LineItemDAOImpl implements LineItemDAO{

	@Override
	public void addLineItems(List<LineItem> lineItemList) {
		String insertQuery = "INSERT INTO lineitems (billID, productID, productName, quantity, unitPrice, lineItemTotal) values(?,?,?,?,?,?)";
		try (
			Connection connection = DatabaseManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
			
			for (LineItem lineItem : lineItemList) {
				statement.setInt(1, lineItem.getBillID());
				statement.setInt(2, lineItem.getProductID());
				statement.setString(3, lineItem.getProductName());
				statement.setInt(4, lineItem.getQuantity());
				statement.setBigDecimal(5, lineItem.getUnitPrice());
				statement.setBigDecimal(6, lineItem.getLineItemTotal());
				
				statement.executeUpdate();
			}

			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

	@Override
	public void deleteLineItem() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateLineItem() {
		// TODO Auto-generated method stub
		
	}
	
}
