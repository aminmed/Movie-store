package screens; 
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RemettreArticleController {

    @FXML
    private TextField idArticleID;
    @FXML
    private TextField idClientID;
    @FXML
    private TextField idOverDue; 
    @FXML
    private JFXButton idRemettreAllocation;
    @FXML
    private JFXRadioButton idAllocation; 
    @FXML
    private JFXRadioButton idRemise;
    @FXML
    private ToggleGroup radio; 

    @FXML
    void addRemsieOuAllocation(ActionEvent event) {

    	long clientID = Long.parseLong(idClientID.getText()); 
    	long itemID = Long.parseLong(idArticleID.getText());
    	String overdue = idOverDue.getText(); 
    	JSONObject dataToSend = new JSONObject();
    	try {
        	if (idRemise.isSelected()) dataToSend.put("operation", "return-item");
            else {
            	dataToSend.put("operation", "rent-item");
            	dataToSend.put("dueDate", overdue);
            }
			dataToSend.put("itemID", itemID);
			dataToSend.put("clientID", clientID);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    	GUI.requests.add(dataToSend); 
    	dataToSend = retrieveAnswer(); 
    	System.out.println(dataToSend.toString()); 
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
 				Thread.sleep(0); 
 			} catch (InterruptedException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
    	}
		return GUI.answers.poll();
    	
    }

}
