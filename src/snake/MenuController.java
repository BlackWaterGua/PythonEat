package snake;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class MenuController {
	//declare exit and start button
	@FXML
	Button onExitPressed;
	
	@FXML
	Button onStartPressed;
	
	//actions of two buttons
	@FXML
	public void onStartPressed() throws IOException {
		Parent map = FXMLLoader.load(getClass().getResource("map.fxml"));
		Scene mapScene = new Scene(map);
		mapScene.getRoot().requestFocus();
		StartGame.currentStage.setScene(mapScene);
		StartGame.currentStage.setResizable(false);
		
	}
	
	@FXML
	public void onExitPressed() {
		StartGame.currentStage.close();
	}
	
}