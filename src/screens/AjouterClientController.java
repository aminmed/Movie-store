package screens; 
import java.io.IOException;

import org.json.*;
import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
