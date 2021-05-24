package screens; 
import org.json.*; 
import javafx.event.ActionEvent;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SearchFilmByActorController {

    @FXML
    private TextField searchByActorField;

    @FXML
    private JFXButton idSearchFilmButton;
    @FXML 
    private JFXListView<String> idListView;
    @FXML
    void searchFilmsByActor(ActionEvent event) {
    	String actor = searchByActorField.getText(); 
    	JSONObject dataToSend = new JSONObject(); 
    	try {
			dataToSend.put("operation", "search-filmsByActor");
			dataToSend.put("actor", actor);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	GUI.requests.add(dataToSend); 
    	dataToSend = retrieveAnswer(); 
    	try {
			JSONArray array = (JSONArray) dataToSend.get("response");
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
