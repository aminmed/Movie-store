import java.util.*;
import pipeAndFilter.Filter;
import pipeAndFilter.Pipe; 
public class TransactionProcessor extends Filter {
	private Data data;
    public TransactionProcessor(Pipe _dataINPipe, Pipe _dataOUTPipe ) {
    	super(); 
    	this._dataINPipe = _dataINPipe; 
    	this._dataOUTPipe = _dataOUTPipe; 
    }
    
    public TransactionProcessor(Data data,Pipe _dataINPipe, Pipe _dataOUTPipe ) {
    	super(); 
    	this._dataINPipe = _dataINPipe; 
    	this._dataOUTPipe = _dataOUTPipe; 
    }
	public boolean CheckOut (long item, long client, Date dueDate) {

		if (IsCheckedOut(item)) return false;
		//checking client sold 
		float sold = Solde(client); 
		float rentalPrice = FindItemByID(item).getRentalPrice(); 
		if (rentalPrice > sold) return false; 
		UpdateClientSold(client,sold - rentalPrice); 
		//creating new rented item in rentedItems list 
		RentedItem newRentedItem = new RentedItem(); 
		newRentedItem.setCustomerID(client);
		newRentedItem.setItemID(item);
		newRentedItem.setDueDate(dueDate);
		return true; 
	}
	public boolean UpdateClientSold (long clientID, float newSold) {
		
		return true; 
	}
	public boolean CheckIn(long itemID) {
		LinkedList<RentedItem> rentedItems = this.data.getRentedItems(); 
		RentedItem item = null; 
		for(int i=0; i< rentedItems.size(); i++) {
			item = rentedItems.get(i); 
			if (item.getItemID() == itemID) rentedItems.remove(item);  
		}
		return true; 
	}
	public boolean AddCustomer(String name, float initialSold, long id) {
	
		Client client = new Client(); 
		client.setName(name);
		client.setCustomerID(id);
		client.setAccountBalance(initialSold);
		this.data.getClients().putIfAbsent(id, client); 
		return true; 
	}
	public boolean AddStockItem(){
		
		return true; 
	}
	public StockItem FindByTitle(String title) {
		
		HashMap<Long,StockItem> stock = this.data.getStock(); // stock refers to stock hashmap 
		for(Map.Entry<Long, StockItem> entry : stock.entrySet()) {
			if(title.equals(entry.getValue().getTitle()) == true) return entry.getValue(); 
		}
		return null; 
	}
	public StockItem FindItemByID(long id) {
		
		HashMap<Long,StockItem> stock = this.data.getStock(); // stock refers to stock hashmap 
		for(Map.Entry<Long, StockItem> entry : stock.entrySet()) {
			if(id == entry.getValue().getItemID()) return entry.getValue(); 
		}
		return null; 
	}
	public Set<Film> NdByActor (String actor) {
		HashMap<Long,StockItem> stock = this.data.getStock(); // stock refers to stock hashmap
		HashSet<Film> output = new HashSet<Film>(); 
		StockItem item = null; 
		for(Map.Entry<Long, StockItem> entry : stock.entrySet()) {
			item = entry.getValue(); 
			if (item instanceof Film) {
				if ( ((Film) item).getActeur() == actor) output.add((Film) item);    
			}
		}
		return output; 
	}
	public boolean IsCheckedOut(long itemId) {
		LinkedList<RentedItem> rentedItems = this.data.getRentedItems(); 
		RentedItem item = null; 
		for(int i=0; i< rentedItems.size(); i++) {
			item = rentedItems.get(i); 
			if (item.getItemID() == itemId) return true; 
		}
		return false; 
	}
	public float Solde (long customerId) {
		HashMap<Long,Client> clients = this._getData().getClients(); 
		Client clnt = clients.get(customerId);
		return clnt.getAccountBalance(); 
	}
	public Set<RentedItem> OverdueItems (Date currentDate) {
		LinkedList<RentedItem> rentedItems = this.data.getRentedItems();
		HashSet<RentedItem> overdueItems = new HashSet<RentedItem>(); 
		RentedItem item; 
		for(int i=0; i< rentedItems.size(); i++) {
			item = rentedItems.get(i); 
			if(item.getDueDate().before(currentDate)) overdueItems.add(item); 
		}
		return overdueItems; 
	}
	
	public Data _getData() {
		return data;
	}
	public void _setData(Data data) {
		this.data = data;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
