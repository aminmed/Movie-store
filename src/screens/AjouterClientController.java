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
    	float soldinitial = Float.parseFloat(idClientSolde.getText());
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
    	System.out.print(dataToSend.toString());
    }

}
