//import java.util.*;
import pipeAndFilter.*; 
import org.json.*;
public class QueryProcessor extends Filter {
	
    public QueryProcessor(Pipe _dataINPipe, Pipe _dataOUTPipe ) {
    	super(); 
    	this._dataINPipe = _dataINPipe; 
    	this._dataOUTPipe = _dataOUTPipe; 
    }
    
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		String dataIn ="";
		JSONObject json = null; 
		while(true) {
			dataIn= this._dataINPipe.dataOUT(); 
			
		}
		
	}
}
