package pro1_sopgomore_javaweb_works;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//±êÇ©·½¿é

public class CourtField extends Application
{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		VBox Field =new VBox(0);
		CFmid cm1=new CFmid();
		cm1.setAlignment(Pos.CENTER);
		Field.getChildren().add(cm1);
		
		Scene scene = new Scene(Field,1024,768);
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestField");
		primaryStage.show();
	}
	public static void main(String[] args)
	{
		launch(args);
	}
}
class Paint{
	
}
class CFmid extends Label{
	String path;
	Image i1;
	ImageView i1v;
	CFmid() throws FileNotFoundException
	{
		path=new File("lib/MidField.png").getAbsolutePath();
		Image image = new 
				Image(new FileInputStream(path));
		
		this.setText("114514");
		this.setPrefSize(200, 200);
	    this.setGraphic(i1v);
	}
	void Turn(int i)
	{
	}
}
class CFside extends Label{
	
	CFside()
	{
		
	}
	void Turn(int i)
	{
		
	}
	void Rotate(int i)
	{
		switch(i)
		{
			case 1:
				this.Rotate(90);
				break;
			case 2:this.Rotate(180);
				break;
			case 3:this.Rotate(270);
				break;
		}
	}
}
class CFcorner extends Label{
	CFcorner()
	{
		this.setGraphic(new ImageView());
	}
	void Turn(int i)
	{
		if(i==0)
			this.setGraphic(new ImageView());
		else
			this.setGraphic(new ImageView());
	}
	void Rotate(int i)
	{
		switch(i)
		{
			case 1:
				this.Rotate(90);
				break;
			case 2:this.Rotate(180);
				break;
			case 3:this.Rotate(270);
				break;
		}
	}
}

