package screens;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.Stage; 
public class UserInterface extends Application{
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		AnchorPane root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	
}
