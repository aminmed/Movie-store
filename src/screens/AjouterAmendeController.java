package screens; 
import org.json.JSONException;
import org.json.JSONObject;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AjouterAmendeController {

    @FXML
    private TextField idAmendeClientID;

    @FXML
    private TextField idAmendeSolde;

    @FXML
    private JFXButton idAddAmende;

    @FXML
    void addAmende(ActionEvent event) {
    	long id = Long.parseLong(idAmendeClientID.getText()); 
    	long solde = Long.parseLong(idAmendeSolde.getText());
    	JSONObject dataToSend = new JSONObject(); 
    	try {
    		dataToSend.put("operation", "penalty");
			dataToSend.put("amount", solde);
			dataToSend.put("clientID", id);
			
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
