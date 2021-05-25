package metier;
import java.io.Serializable;
import java.util.*; 
public class RentedItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7188653574867742256L;
	private long customerID; 
	private long itemID;
	private Date dueDate;
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public long getItemID() {
		return itemID;
	}
	public void setItemID(long itemID) {
		this.itemID = itemID;
	}
	public long getCustomerID() {
		return customerID;
	}
	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	} 
	
}
