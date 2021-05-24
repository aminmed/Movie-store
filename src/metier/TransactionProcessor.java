package metier;
import java.util.*;
import java.text.SimpleDateFormat;
import pipeAndFilter.*;
import org.json.*;
public class TransactionProcessor extends Filter {
	private Data data;

    public TransactionProcessor(Data data,Pipe _dataINPipe, Pipe _dataOUTPipe ) {
    	super(); 
    	this._dataINPipe = _dataINPipe; 
    	this._dataOUTPipe = _dataOUTPipe; 
    	this.data = data; 
    }
	public boolean CheckOut (long item, long client, Date dueDate) {

		if (IsCheckedOut(item)) return false;
		//checking client sold 
		long sold = Solde(client); 
		long rentalPrice = FindItemByID(item).getRentalPrice(); 
		if (rentalPrice > sold) return false; 
		UpdateClientSold(client,sold - rentalPrice); 
		//creating new rented item in rentedItems list 
		RentedItem newRentedItem = new RentedItem(); 
		newRentedItem.setCustomerID(client);
		newRentedItem.setItemID(item);
		newRentedItem.setDueDate(dueDate);
		return true; 
	}
	public boolean UpdateClientSold (long clientID, long amount) {
		Client client = this.data.getClients().get(clientID); 
		client.setAccountBalance(client.getAccountBalance() - amount);
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
	public boolean AddCustomer(String name, long initialSold, long id) {
	
		Client client = new Client(); 
		client.setName(name);
		client.setCustomerID(id);
		client.setAccountBalance(initialSold);
		if(this.data.getClients().containsKey(id)) return false; 
		this.data.getClients().putIfAbsent(id, client); 
		return true; 
	}
	public boolean AddStockItem(String type, String title, long rentalPrice, long itemID, String additional){
		
		if(this.data.getStock().containsKey(itemID)) return false; 
		if (type.contentEquals("film") ) {
			Film f = new Film(); 
			f.setItemID(itemID);
			f.setRentalPrice(rentalPrice);
			f.setTitle(title);
			f.setActeur(additional);
			this.data.getStock().put(itemID,f); 
		}else {
			Jeux j = new Jeux(); 
			j.setItemID(itemID);
			j.setRentalPrice(rentalPrice);
			j.setTitle(title);
			j.setPlateforme(additional);
			this.data.getStock().put(itemID,j); 
		}
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
	public ArrayList<String> NdByActor (String actor) {
		HashMap<Long,StockItem> stock = this.data.getStock(); // stock refers to stock hashmap
		ArrayList<String> output = new ArrayList<String>(); 
		StockItem item = null; 
		for(Map.Entry<Long, StockItem> entry : stock.entrySet()) {
			item = entry.getValue(); 
			if (item instanceof Film) {
				if ( ((Film) item).getActeur().contentEquals(actor) ) {
					output.add(Long.toString(item.getItemID()) + " - " + item.getTitle());    
				}
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
	public long Solde (long customerId) {
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
	public ArrayList <String> availableItems (String _type) {
		StockItem item = null;  
		HashMap<Long,StockItem> stock = this.data.getStock(); 
		String type; 
		ArrayList <String> listOfItems = new ArrayList<String>();
		for(Map.Entry<Long, StockItem> entry : stock.entrySet()) {
			item = entry.getValue(); 
			if(this.IsCheckedOut(item.getItemID()) == false) 
			{
				if( item instanceof Film) type = "Film"; 
				else type = "Jeu"; 
				if (type.contentEquals(_type)) listOfItems.add(type +" - "+ item.getTitle());
			}
				 
		}
		return listOfItems; 
	}
	public ArrayList <String> filmsRentedByClient (long clientID) {
		ArrayList <String> listOfItems = new ArrayList<String>();
		
		LinkedList<RentedItem> rentedItems = this.data.getRentedItems(); 
		RentedItem item = null; 
		for(int i=0; i< rentedItems.size(); i++) {
			item = rentedItems.get(i); 
			if ((item.getCustomerID() == clientID) && (this.data.getStock().get(item.getItemID()) instanceof Film )) {
				
				listOfItems.add(this.data.getStock().get(item.getItemID()).getTitle() +"  -  " +item.getDueDate().toString());   
			}
		}
		return listOfItems; 
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
		execute(); 
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		String input = "";
		JSONObject transaction = null;
		JSONObject answer = new JSONObject() ;
		boolean result  = false; 
		while(true) {
		    try {
		    	Thread.sleep(500);  
		    	input = this._dataINPipe.dataOUT(); 
		    	System.out.println("from transaction !"+ input); 
				try {
					transaction = new JSONObject(input);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					switch((String)transaction.get("method")) {
					case "AddCustomer":
						 result = AddCustomer((String)transaction.get("name"),
								Long.valueOf(transaction.get("initialSold").toString()),Long.valueOf(transaction.get("id").toString())); 
						 answer.put("response", result); 
						break; 
					case "AddStockItem":
						result = AddStockItem((String)transaction.get("type"),(String)transaction.get("title"),
								Long.valueOf(transaction.get("rentalPrice").toString()), Long.valueOf(transaction.get("itemID").toString()),
								(String)transaction.get("additional")); 
						answer.put("response", result); 
						break;
					case "CheckOut":
						Date date = new SimpleDateFormat("dd-mm-yyyy").parse((String)transaction.get("dueDate"));; 
						result = CheckOut(Long.valueOf(transaction.get("itemID").toString()),Long.valueOf(transaction.get("clientID").toString()),
								date); 
						answer.put("response", result); 
						break;	
					case "CheckIn": 
						result = CheckIn(Long.valueOf(transaction.get("itemID").toString())); 
						answer.put("response", result); 
						break;
					case "UpdateClientSold":
						result = UpdateClientSold(Long.valueOf(transaction.get("clientID").toString()),Long.valueOf(transaction.get("amount").toString()));
						answer.put("response", result); 
						break;
					case "availableItems":
						ArrayList<String> arr =  availableItems((String)transaction.get("type")); 
						JSONArray array = new JSONArray();
						array.put(arr); 
						answer.put("response",array); 
						break;
					case "NdByActor":
						ArrayList<String> arrfilms =  NdByActor((String)transaction.get("actor")); 
						JSONArray arrayfilms = new JSONArray();
						arrayfilms.put(arrfilms); 
						answer.put("response",arrayfilms); 
						break;
					case "filmsRentedByClient":
						ArrayList<String> clientfilms =  filmsRentedByClient(Long.valueOf(transaction.get("clientID").toString())); 
						JSONArray clientsarrayfilms = new JSONArray();
						clientsarrayfilms.put(clientfilms); 
						answer.put("response",clientsarrayfilms); 
						break;
						
					default:
						break; 
					}
					
				} catch (JSONException e) {e.printStackTrace();}
				System.out.println("from transaction to query !"+answer.toString() ); 
				this._dataOUTPipe.dataIN(answer.toString());	
		    	
		    }catch(Exception e) {System.out.print(e);} 
		}
		
	}
}
