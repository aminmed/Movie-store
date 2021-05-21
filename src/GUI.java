//import java.util.*; 
import pipeAndFilter.Filter;
import pipeAndFilter.Pipe;
import org.json.*;
public class GUI extends Filter{
	//requests :
	//
    public GUI(Pipe _dataINPipe, Pipe _dataOUTPipe ) {
    	super(); 
    	this._dataINPipe = _dataINPipe; 
    	this._dataOUTPipe = _dataOUTPipe; 
    }
    
	@Override
	public void run() {
		execute(); 
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		String output = ""; 
		while (true) {
			json = recieveRequestFromUser(); 
			if (json != null) {
				this._dataOUTPipe.dataIN(json.toString());
			}
			output = this._dataINPipe.dataOUT();
			try {
				json = new JSONObject(output);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			showResultsToUser(json); 
		}
	} 
	public JSONObject recieveRequestFromUser() {
		return null; 
	}
	public void showResultsToUser(JSONObject output) {
		
	}
	
}
