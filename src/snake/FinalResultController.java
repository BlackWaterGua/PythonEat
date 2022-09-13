package snake;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class FinalResultController extends MapController implements Initializable{
	@FXML
	Label finalScore;
	@FXML
	Label finalTime;
	@FXML
	Button menu;
	@FXML
	Button exit;
	@FXML
	Button leaderboard;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		finalScore.setText(Integer.toString(score));
		finalTime.setText(Double.toString(passTime/1000.000)+" seconds");
	}
	
	@FXML
	public void backMenu()
	{
		StartGame.currentStage.setScene(StartGame.menuScene);
		score=0;
	}
	@FXML
	public void exit()
	{
		StartGame.currentStage.close();
	}
	@FXML
	public void leaderboard()
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("leaderboard.fxml"));
		Parent temp;
		try {
			temp = loader.load();
			Scene a = new Scene(temp);
			StartGame.currentStage.setScene(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
