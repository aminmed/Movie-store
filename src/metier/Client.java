package metier;

import java.io.Serializable;

public class Client implements Serializable {
	
  /**
	 * 
	 */
	private static final long serialVersionUID = -8550828925349296531L;
private long accountBalance;
  private String name;
  private long customerID;
  
public long getAccountBalance() {
	return accountBalance;
}
public void setAccountBalance(long accountBalance) {
	this.accountBalance = accountBalance;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public long getCustomerID() {
	return customerID;
}
public void setCustomerID(long customerID) {
	this.customerID = customerID;
} 

}
