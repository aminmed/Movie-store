package screens; 
import org.json.JSONException;
import org.json.JSONObject;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class AjouterArticleController {

    @FXML
    private TextField idArticleTtire;

    @FXML
    private TextField idArticleID;

    @FXML
    private TextField idPrix;

    @FXML
    private JFXButton idAddArticle;

    @FXML
    private TextField idActeurOrPlateform;

    @FXML
    private JFXRadioButton idJeux;

    @FXML
    private ToggleGroup type;

    @FXML
    private JFXRadioButton idFilm;

    @FXML
    void addClient(ActionEvent event) {
    	long id = Long.parseLong(idArticleID.getText()); 
    	long rentalPrice = Long.parseLong(idPrix.getText());
    	String title = idArticleTtire.getText(); 
    	String additional = idActeurOrPlateform.getText(); 
    	String type; 
    	if(idFilm.isSelected())  type = "film"; 
    	else type = "jeu"; 
    	JSONObject dataToSend = new JSONObject();
    	try {
			dataToSend.put("operation", "add-item");
			dataToSend.put("itemID", id);
			dataToSend.put("rentalPrice", rentalPrice);
			dataToSend.put("title", title);
			dataToSend.put("additional", additional);
			dataToSend.put("type", type);
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
 				Thread.sleep(100); 
 			} catch (InterruptedException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
    	}
		return GUI.answers.poll();
    	
    }

}
