package screens;
import java.util.*; 
import pipeAndFilter.Filter;
import pipeAndFilter.Pipe;
import org.json.*;
public class GUI extends Filter{
	//requests :
	//
	protected static Queue<JSONObject> requests = new LinkedList<JSONObject> (); 
	protected static Queue<JSONObject> answers = new LinkedList<JSONObject> ();
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
		while(true) {
            try  
            {  
                Thread.sleep(500);  
                if (requests.isEmpty() == false) {
                	json = recieveRequestFromUser(); 
                	System.out.println("from GUI !"+ json.toString()); 
                	this._dataOUTPipe.dataIN(json.toString());
                }
                if (this._dataINPipe.isEmpty() == false) {
                	try {
						showResultsToUser(new JSONObject(this._dataINPipe.dataOUT()));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
                }
                
            }catch(InterruptedException e){System.out.println(e);}  

		}
		
	} 
	public JSONObject recieveRequestFromUser() {
		
		return requests.poll(); 
	}
	public synchronized void showResultsToUser(JSONObject output) {
		answers.add(output); 
	}
	
}
