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
