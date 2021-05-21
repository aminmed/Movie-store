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
			if(!this._dataINPipe.isEmpty()) {
				
				dataIn= this._dataINPipe.dataOUT(); 
				try {
					query = new JSONObject(dataIn);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
				answerToUser = AnswerToUserBuilder(answerFromServer); 
				this._dataOUTPipe.dataIN(answerToUser.toString());
			}

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
				json.put("initialSold",(float) query.get("initialSold"));
				json.put("id",(long) query.get("id"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			break; 
		case "add-item":
			try {
				json.put("method","AddStockItem");
				json.put("itemID",(String) query.get("itemID"));
				json.put("rentalPrice",(float) query.get("rentalPrice"));
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
				json.put("itemID",(String) query.get("itemID"));
				json.put("clientID",(long) query.get("clientID"));
				json.put("dueDate",(String) query.get("dueDate"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "return-item":
			try {
				json.put("method","CheckIn");
				json.put("itemID",(String) query.get("itemID"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "penalty":
			
			try {
				json.put("method","UpdateClientSold");
				json.put("clientID",(String) query.get("clientID"));
				json.put("amount",(float) query.get("amount"));
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
