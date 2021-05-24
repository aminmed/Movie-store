package metier;
import java.util.*;

public class Data {
	private HashMap<Long,Client> clients = null; 
	private HashMap<Long,StockItem> stock =  null; 
	private LinkedList<RentedItem> rentedItems = null;
	public Data () {
		this.clients = new HashMap <Long,Client>() ; 
		this.stock =  new HashMap <Long,StockItem>();
		this.rentedItems = new LinkedList<RentedItem>();
	}
	public HashMap<Long,Client> getClients() {
		return clients;
	}
	public void setClients(HashMap<Long,Client> clients) {
		this.clients = clients;
	}
	public LinkedList<RentedItem> getRentedItems() {
		return rentedItems;
	}
	public void setRentedItems(LinkedList<RentedItem> rentedItems) {
		this.rentedItems = rentedItems;
	}
	public HashMap<Long,StockItem> getStock() {
		return stock;
	}
	public void setStock(HashMap<Long,StockItem> stock) {
		this.stock = stock;
	} 
}
