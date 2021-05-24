package screens; 
import org.json.*;
import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AjouterClientController {

    @FXML
    private TextField idClientName;

    @FXML
    private TextField idClientID;

    @FXML
    private TextField idClientSolde;

    @FXML
    private JFXButton idAddClient;

    @FXML
    void addClient(ActionEvent event) {
    	long id = Long.parseLong(idClientID.getText()); 
    	long soldinitial = Long.parseLong(idClientSolde.getText());
    	String name = idClientName.getText(); 
    	JSONObject dataToSend = new JSONObject(); 
    	try {
    		dataToSend.put("operation", "add-client");
			dataToSend.put("name", name);
			dataToSend.put("initialSold", soldinitial);
			dataToSend.put("id", id);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	GUI.requests.add(dataToSend);
    	dataToSend = retrieveAnswer(); 
    	System.out.println(dataToSend.toString()); 
    }
    public  JSONObject retrieveAnswer () {
    	while(GUI.answers.isEmpty()) {
 			try {
 				Thread.sleep(1); 
 			} catch (InterruptedException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
    	}
		return GUI.answers.poll();
    	
    }


}
