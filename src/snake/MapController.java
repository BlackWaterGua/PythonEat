package snake;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;


public class MapController extends MenuController implements Initializable, EventHandler<KeyEvent>{
	@FXML
	AnchorPane map;
	
	@FXML
	GridPane background;
	
	@FXML
	Rectangle snake;
	
	@FXML
	StackPane mainMap;
	
	@FXML
	Rectangle fruit;
	
	@FXML
	Label Score;
	
	@FXML
	Label trap;
	
	static long startTime = 0;
	long currentTime = 0;
	static long passTime = 0;
	String direction = "up";
	double delta = 3;
	Duration snakeMoveTime = Duration.millis(1000/60);
	Random ran = new Random();
	int fruitX = ran.nextInt(900-100+1)+100;
	int fruitY = ran.nextInt(600-100+1)+100;
	public static int score = 0;
	Bloom bloom = new Bloom();
	public static long passTempTime=0;
	public static int a = 0;
	
	
	@FXML
	ArrayList<Rectangle> snakes= new ArrayList<Rectangle>();
	
	ArrayList<Double> positionX = new ArrayList<Double>();
	ArrayList<Double> positionY = new ArrayList<Double>();
	
	EventHandler snakeMove = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {	
			//蛇頭移動
			if(direction.equals("up"))
			{
				timeFunction();
				AddPythonLength();
				snake.setLayoutY(snake.getLayoutY()-delta);
				snakeBodyMove();
				RemovePythonLength();
			}
			if(direction.equals("down"))
			{
				timeFunction();
				AddPythonLength();
				snake.setLayoutY(snake.getLayoutY()+delta);
				snakeBodyMove();
				RemovePythonLength();
			}
			if(direction.equals("left"))
			{
				timeFunction();
				AddPythonLength();
				snake.setLayoutX(snake.getLayoutX()-delta);
				snakeBodyMove();
				RemovePythonLength();
			}
			if(direction.equals("right"))
			{
				timeFunction();
				AddPythonLength();
				snake.setLayoutX(snake.getLayoutX()+delta);
				snakeBodyMove();
				RemovePythonLength();
			}
			if(snake.getLayoutX()>995)
			{
				snake.setLayoutX(0);
			}
			if(snake.getLayoutX()<0)
			{
				snake.setLayoutX(995);
			}
			if(snake.getLayoutY()<0)
			{
				snake.setLayoutY(695);
			}
			if(snake.getLayoutY()>695)
			{
				snake.setLayoutY(0);
			}
				
			if(snake.getLayoutX()+10>trap.getLayoutX()&&snake.getLayoutX()+10<trap.getLayoutX()+30&&snake.getLayoutY()+10>trap.getLayoutY()&&snake.getLayoutY()+10<trap.getLayoutY()+38)
			{
				snakeTimeline.pause();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("snakeEatShit.fxml"));
				Parent root;
				try {
					root = loader.load();
					Scene FR = new Scene(root);
					FR.getRoot().requestFocus();
					StartGame.currentStage.setScene(FR);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			for(int i = 0;i<positionX.size();i++)
			{
				if(snake.getLayoutX()==positionX.get(i)&&snake.getLayoutY()==positionY.get(i))
				{
					snakeTimeline.pause();
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
			
			if(snake.getLayoutX()+25>=fruit.getLayoutX()+10&&snake.getLayoutY()<=fruit.getLayoutY()+15&&snake.getLayoutX()<=fruit.getLayoutX()+10&&snake.getLayoutY()+30>=fruit.getLayoutY()+5)
			{
				delta+=0.2;
				trapMove();
				fruit.setLayoutX(fruitX);
				fruit.setLayoutY(fruitY);
				fruitX = ran.nextInt(700-50+1)+50;
				fruitY = ran.nextInt(450-10+1)+10;
				score+=1;
				Score.setText(Integer.toString(score));
				Rectangle temp = new Rectangle(20, 20);
				temp.setFill(Color.rgb(30, 144, 255));
				bloom.setThreshold(0.3);
				temp.setEffect(bloom);
				snakes.add(0,temp);
				map.getChildren().add(snakes.get(0));
			}
		}
	};
	
	@FXML
	public void trapMove()
	{
		if(score%5==1)
		{
			trap.setVisible(true);
			Random a = new Random(); 
			int x = a.nextInt(700-50+1)+50;
			int y = a.nextInt(450-10+1)+10;
			trap.setLayoutX(x);
			trap.setLayoutY(y);
		}
	}
	
	private void timeFunction()
	{
		currentTime = System.currentTimeMillis();
		passTime = currentTime-startTime+passTempTime;
	}
	
	@FXML
	private void snakeBodyMove()
	{
		//蛇身的移動
		for(int i=0;i<snakes.size();i++)
		{
			snakes.get(i).setLayoutX(positionX.get(i));
			snakes.get(i).setLayoutY(positionY.get(i));
		}
	}
	
	private void AddPythonLength()
	{
		positionX.add(0,snake.getLayoutX());
		positionY.add(0,snake.getLayoutY());
	}
	
	private void RemovePythonLength()
	{
		if(snakes.size()<positionX.size()-1)
		{
			positionX.remove(positionX.size()-1);
			positionY.remove(positionY.size()-1);
		}
	}
	
	KeyFrame snakeMoveFrame = new KeyFrame(snakeMoveTime, snakeMove);
	Timeline snakeTimeline = new Timeline(snakeMoveFrame);
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		startTime = System.currentTimeMillis();
		snakeTimeline.setCycleCount(Timeline.INDEFINITE);
		snakeTimeline.play();
		mainMap.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
            public void handle(KeyEvent event) {
				if(event.getCode()==KeyCode.SPACE)
				{
					delta = 10;
				}
			}
		});
	}
	
	@Override
	public void handle(KeyEvent e) {
		if(e.getCode()==KeyCode.ESCAPE)
		{
			if(a==0)
			{
				snakeTimeline.pause();
				Node root;
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("pauseMenu.fxml"));
					root = loader.load();
					PauseMenuController pmc = loader.getController();
					pmc.SetParent(map, root, snakeTimeline);
					map.getChildren().add(root);
					root.requestFocus();
					root.setLayoutX(300);
					root.setLayoutY(100);
					a=1;
					passTempTime = passTime;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		if(e.getCode()==KeyCode.UP&&!direction.equals("down")) {
			direction = "up";
		}
		if(e.getCode()==KeyCode.LEFT&&!direction.equals("right")) {
			direction = "left";
		}
		if(e.getCode()==KeyCode.DOWN&&!direction.equals("up")) {
			direction = "down";
		}
		if(e.getCode()==KeyCode.RIGHT&&!direction.equals("left")) {
			direction = "right";
		}
		
	}
}


