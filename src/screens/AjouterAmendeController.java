package screens; 
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    	Stage stage = new Stage();
    	AnchorPane root = null ; 
    	try {
    		try {
				if ((boolean)dataToSend.getBoolean("response"))
					root = FXMLLoader.load(getClass().getResource("dialog.fxml"));
				else root = FXMLLoader.load(getClass().getResource("dialog2.fxml"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}; 
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
