package billings;

public interface BillDAO {
	
	Bill createBill();
	void addBill(Bill bill);
//	void updateBill();
	void deleteBill();
	Bill getLastBill();
	
}
