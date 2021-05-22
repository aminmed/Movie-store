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
    	Stage stage = new Stage();
    	AnchorPane root = null ; 
    	try {
		 root = FXMLLoader.load(getClass().getResource("ajouter_article.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}; 
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void addClient(ActionEvent event) {
    	Stage stage = new Stage();
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
    	Stage stage = new Stage();
    	AnchorPane root = null ; 
    	try {
		 root = FXMLLoader.load(getClass().getResource("ajouter_amende.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}; 
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void getClientSoldeAndDelayArticles(ActionEvent event) {
    	Stage stage = new Stage();
    	AnchorPane root = null ; 
    	try {
		 root = FXMLLoader.load(getClass().getResource("search_client_solde_retard.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}; 
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void searchFilmByActor(ActionEvent event) {
    	Stage stage = new Stage();
    	AnchorPane root = null ; 
    	try {
		 root = FXMLLoader.load(getClass().getResource("search_film_by_actor.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}; 
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void searchFilmsReservedByClient(ActionEvent event) {
    	Stage stage = new Stage();
    	AnchorPane root = null ; 
    	try {
		 root = FXMLLoader.load(getClass().getResource("search_film_loue_par_client.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}; 
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void searchItemForLocation(ActionEvent event) {
    	Stage stage = new Stage();
    	AnchorPane root = null ; 
    	try {
		 root = FXMLLoader.load(getClass().getResource("search_articles_for_location.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}; 
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
