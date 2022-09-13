package snake;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class PauseMenuController extends MapController implements Initializable{
	
	@FXML
	Button Continue;
	
	@FXML
	Button Exit;
	
	@FXML
	Button Restart;
	
	@FXML
	Button Menu;
	
	private AnchorPane parentPane;
	private Node me;
	private Timeline tl;
	
	
	public void SetParent(AnchorPane ap, Node me, Timeline tl)
	{
		parentPane = ap;
		this.me = me;
		this.tl=tl;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	@FXML
	public void Continue()
	{
		startTime=System.currentTimeMillis();
		a=0;
		parentPane.requestFocus();
		tl.play();
		parentPane.getChildren().remove(me);
	}
	
	@FXML
	public void Restart() throws IOException
	{
		passTempTime = 0;
		score = 0;
		passTime = 0;
		startTime=System.currentTimeMillis();
		a=0;
		onStartPressed();
		parentPane.requestFocus();
		tl.play();
		parentPane.getChildren().remove(me);
	}
	
	@FXML
	public void Exit()
	{
		onExitPressed();
	}
	
	@FXML
	public void Menu()
	{
		a=0;
		StartGame.currentStage.setScene(StartGame.menuScene);
	}
}
