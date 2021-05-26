package metier;
//import java.util.*;
import pipeAndFilter.*; 
import org.json.*;
public class QueryProcessor extends Filter {
	protected Pipe dataToServer; 
	protected Pipe dataFromServer; 
    public QueryProcessor(Pipe _dataINPipe,Pipe _dataOUTPipe,Pipe dataToServer,Pipe dataFromServer ) {
    	super(); 
    	this._dataINPipe = _dataINPipe; 
    	this._dataOUTPipe = _dataOUTPipe; 
    	this.dataFromServer = dataFromServer; 
    	this.dataToServer = dataToServer; 
    }
    
	@Override
	public void run() {
		// TODO Auto-generated method stub
		execute(); 
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		String dataIn ="";
		JSONObject query = null;
		JSONObject transaction = null;
		JSONObject answerToUser = null; 
		JSONObject answerFromServer = null; 
		while(true) {
			
			try {
				Thread.sleep(500);
				if(!this._dataINPipe.isEmpty()) {
					
					dataIn= this._dataINPipe.dataOUT(); 
					try {
						query = new JSONObject(dataIn);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(mainApp.debug) System.out.println("recieved by query :"+query.toString());
					transaction = TransactionBuilder(query);
					this.dataToServer.dataIN(transaction.toString());
					
				}
				if(!this.dataFromServer.isEmpty()) {
					
					dataIn= this.dataFromServer.dataOUT(); 
					try {
						answerFromServer = new JSONObject(dataIn);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(mainApp.debug) System.out.println("from query to transaction :"+answerFromServer.toString());
					answerToUser = AnswerToUserBuilder(answerFromServer); 
					this._dataOUTPipe.dataIN(answerToUser.toString());
				}
			}catch(InterruptedException e){System.out.println(e);}  

		}
		
	}
	public JSONObject TransactionBuilder(JSONObject query) {
		String operation = "";
		JSONObject json = new JSONObject (); 
		try {
			 operation = (String) query.get("operation");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switch(operation) {
		case "add-client":
			try {
				json.put("method","AddCustomer");
				json.put("name",(String) query.get("name"));
				json.put("initialSold",((Number) query.get("initialSold")).longValue()); 
				json.put("id",((Number) query.get("id")).longValue());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			break; 
		case "add-item":
			try {
				json.put("method","AddStockItem");
				json.put("itemID",Long.valueOf(query.get("itemID").toString()));
				json.put("rentalPrice",Long.valueOf(query.get("rentalPrice").toString()));
				json.put("title",(String) query.get("title"));
				json.put("type",(String) query.get("type"));
				json.put("additional",(String) query.get("additional"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			break;
		case "rent-item":
			try {
				json.put("method","CheckOut");
				json.put("itemID",Long.valueOf(query.get("itemID").toString()));
				json.put("clientID",Long.valueOf(query.get("clientID").toString()));
				json.put("dueDate",(String) query.get("dueDate"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block 
				e.printStackTrace();
			}
			break;
		case "return-item":
			try {
				json.put("method","CheckIn");
				json.put("itemID",Long.valueOf(query.get("itemID").toString()));
				json.put("clientID",Long.valueOf(query.get("clientID").toString()));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "penalty":
			
			try {
				json.put("method","UpdateClientSold");
				json.put("clientID",((Number) query.get("clientID")).longValue());
				json.put("amount",((Number) query.get("amount")).longValue());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break; 
		case "search-items-location":
			
			try {
				json.put("method","availableItems");
				json.put("type",(String)query.get("type"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "search-filmsByActor": 
			
			try { 
				json.put("method","NdByActor");
				json.put("actor",(String)query.get("actor"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		break; 
		case "search-filmsByClient": 
		try {
			json.put("method","filmsRentedByClient");
			json.put("clientID",((Number) query.get("clientID")).longValue());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			break;
		default:
			json = null;
			break; 
		}
		return json; 
	}
	public JSONObject AnswerToUserBuilder(JSONObject answer) {
		return answer; 
	}
}
