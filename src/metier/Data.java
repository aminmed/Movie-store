package metier;
import java.util.*;

public class Data {
	private HashMap<Long,Client> clients = new HashMap <Long,Client>() ; 
	private HashMap<Long,StockItem> stock =  new HashMap <Long,StockItem>(); 
	private LinkedList<RentedItem> rentedItems = new LinkedList<RentedItem>();
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
