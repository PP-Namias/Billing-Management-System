package lineItems;

import java.util.List;

public interface LineItemDAO {
	
	void addLineItems(List<LineItem> lineItemList);
	void deleteLineItem();
	void updateLineItem();
}
