package snake;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class SnakeEatShitVideoController extends MapController implements Initializable, EventHandler<KeyEvent>{
	@FXML
	AnchorPane videoArea;
	
	MediaPlayer player = null;
	Label guide = null;
	//Bloom bloom = null;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		player = new MediaPlayer(new Media(getClass().getResource("snakeEat.mp4").toExternalForm()));
		MediaView mediaView = new MediaView(player);
		videoArea.getChildren().add(mediaView);
		player.play();
		guide = new Label("Press SPACE to the final result!!");
		guide.setFont(Font.font("Calibri",28));
		//bloom.setThreshold(0.3);
		guide.setEffect(bloom);
		guide.setLayoutX(450);
		guide.setLayoutY(740);
		videoArea.getChildren().add(guide);
		FadeTransition fade = new FadeTransition();
		fade.setNode(guide);
		fade.setAutoReverse(true);
		fade.setCycleCount(Timeline.INDEFINITE);
		fade.setDuration(Duration.seconds(0.5));
		fade.setFromValue(1.0);
		fade.setToValue(0.1);
		fade.play();
	}
	@Override
	public void handle(KeyEvent e) {
		if(e.getCode()==KeyCode.SPACE) {
			player.pause();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("finalResult.fxml"));
			Parent root;
			try {
				root = loader.load();
				Scene FR = new Scene(root);
				StartGame.currentStage.setScene(FR);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
