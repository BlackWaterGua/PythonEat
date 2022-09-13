package snake;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class LeaderboardController extends FinalResultController implements Initializable{
	
	@FXML
	Button Exit;
	
	@FXML
	Button Menu;
	
	@FXML
	GridPane form;
	
	FileReader reader = null;
	FileWriter writer = null;
	FileInputStream fis = null;
	BufferedReader br =null;
	
	
	ArrayList<String> data= new ArrayList<String>();
	ArrayList<String[]> dataDetail= new ArrayList<String[]>();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			writer = new FileWriter("./leaderboardb.txt", true);
			writer.write(score + " " + passTime/1000.000 + "\n");	
			writer.flush();
			writer.close();
			reader = new FileReader("./leaderboardb.txt");
			br = new BufferedReader(reader);
			while(br.ready())
			{
				String a =new String(br.readLine());
				data.add(a);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<data.size();i++)
		{
			String[] temp = data.get(i).split(" ");
			dataDetail.add(temp);
		}
		
		for(int i=0;i<data.size();i++) 
		{
			for(int x=0;x<data.size()-1;x++) 
			{
				if(Integer.parseInt(dataDetail.get(x)[0])<Integer.parseInt(dataDetail.get(x+1)[0]))
				{
					String[] temp = dataDetail.get(x);
					dataDetail.set(x, dataDetail.get(x+1));
					dataDetail.set(x+1, temp);
				}
			}
		}
		while(dataDetail.size()>12)
		{
			if(dataDetail.size()>12)
				dataDetail.remove(12);
		}
			
		for(int i=0;i<dataDetail.size();i++)
		{
			Label num = new Label(Integer.toString(i+1));
			num.setFont(Font.font("Calibri",25));
			num.setEffect(bloom);
			form.add(num, 0, i+1);
			if(i<9)
				form.setMargin(num, new Insets(30));
			if(i>=9)
				form.setMargin(num, new Insets(22));
			
			Label score = new Label(dataDetail.get(i)[0]);
			score.setFont(Font.font("Calibri",25));
			score.setEffect(bloom);
			form.add(score, 1, i+1);
			if(Integer.parseInt(dataDetail.get(i)[0])>=10)
				form.setMargin(score, new Insets(35));
			if(Integer.parseInt(dataDetail.get(i)[0])<10)
				form.setMargin(score, new Insets(43));
			
			Label time = new Label(dataDetail.get(i)[1]);
			time.setFont(Font.font("Calibri",25));
			time.setEffect(bloom);
			form.add(time, 2, i+1);
			double a = Double.parseDouble(dataDetail.get(i)[1]);
			double b = 100;
			if(a>=b)
				form.setMargin(time, new Insets(25));
			if(a<b)
				form.setMargin(time, new Insets(30));
		}
			
	}
	
	public void exit()
	{
		StartGame.currentStage.close();
	}
	public void menu()
	{
		StartGame.currentStage.setScene(StartGame.menuScene);
		score=0;
	}
}
