package screens; 
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text; 

public class SearchClientSoldeAndRetardController {

    @FXML
    private TextField searchByClientField;
    @FXML
    private Text idSoldeField;
    @FXML
    private JFXButton idSearchFilmButton;
    @FXML 
    private JFXListView<String> idListView;
    @FXML
    void searchSoldeAndRetardArticle(ActionEvent event) {
       	long clientID = Long.parseLong(searchByClientField.getText()); 
    	JSONObject dataToSend = new JSONObject(); 
    	try {
			dataToSend.put("operation", "search-ClientSoldAndOverDueItems");
			dataToSend.put("clientID", clientID);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();  
		} 
    	GUI.requests.add(dataToSend); 
    	dataToSend = retrieveAnswer(); 
    	try {
			JSONArray array = (JSONArray) dataToSend.get("overdueItems");
			long sold = dataToSend.getLong("sold"); 
			idSoldeField.setText(sold + "$");
			JSONArray arr = (JSONArray) array.get(0);
			idListView.getItems().clear(); 
			for (int i=0; i< arr.length(); i++) {
				idListView.getItems().add(arr.getString(i)); 
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	
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
