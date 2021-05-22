package screens; 
import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class HomeController {

    @FXML
    private JFXButton idSearchFilm;

    @FXML
    private JFXButton idItemForLocation;

    @FXML
    private JFXButton idFilmReservedByClient;

    @FXML
    private JFXButton idSoldeAndDelayClient;

    @FXML
    private JFXButton idAddClient;

    @FXML
    private JFXButton idPenaltyClient;

    @FXML
    private JFXButton idAddArticle;

    @FXML
    void addArticle(ActionEvent event) {

    }

    @FXML
    void addClient(ActionEvent event) {
    	Stage stage = (Stage) idAddClient.getScene().getWindow();
    	AnchorPane root = null ; 
    	try {
		 root = FXMLLoader.load(getClass().getResource("ajouter_client.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}; 
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void addPenaltyClient(ActionEvent event) {

    }

    @FXML
    void getClientSoldeAndDelayArticles(ActionEvent event) {

    }

    @FXML
    void searchFilmByActor(ActionEvent event) {

    }

    @FXML
    void searchFilmsReservedByClient(ActionEvent event) {

    }

    @FXML
    void searchItemForLocation(ActionEvent event) {

    }

}
