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

//标签方块

public class CourtField extends Application
{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		VBox Field =new VBox(0);
		CFcorner cm1=new CFcorner(0);
		CFcorner cm2=new CFcorner(1);
		CFcorner cm3=new CFcorner(2);
		CFcorner cm4=new CFcorner(3);
		Field.getChildren().addAll(cm1,cm2,cm3,cm4);
		
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

//静态类
//若有全局方法及变量则写入其中
class Paint{
	
}

class CFmid extends Label{
	String path;
	String path_W;
	String path_B;
	Image i1;
	ImageView i1v;
	CFmid()
	{
		path=new File("lib/MidField.png").getAbsolutePath();
		path_W=new File("lib/MidField_W.png").getAbsolutePath();
		path_B=new File("lib/MidField_B.png").getAbsolutePath();
		try {
			i1 = new 
					Image(new FileInputStream(path));
			}catch(Exception e) {e.printStackTrace();}
			finally {
			i1v=new ImageView(i1);
			i1v.setPreserveRatio(true);
			this.setPrefSize(50, 50);
		    this.setGraphic(i1v);
			}
	}
	//0变白，1变黑
	void Turn(int i)
	{
		try {	
			if(i==0)
			{	
				i1=new Image(
						new FileInputStream(path_W));
				i1v=new ImageView(i1);
				i1v.setPreserveRatio(true);
				this.setPrefSize(50, 50);
			    this.setGraphic(i1v);
			}
			else
			{
				i1=new Image(
						new FileInputStream(path_B));
				i1v=new ImageView(i1);
				i1v.setPreserveRatio(true);
				this.setPrefSize(50, 50);
			    this.setGraphic(i1v);
			}
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
class CFside extends Label{
	String path;
	String path_W;
	String path_B;
	Image i1;
	ImageView i1v;
	int iRotated;
	CFside(int i)
	{
		path=new File("lib/SideField.png").getAbsolutePath();
		path_W=new File("lib/SideField_W.png").getAbsolutePath();
		path_B=new File("lib/SideField_B.png").getAbsolutePath();
		try {
			i1 = new 
					Image(new FileInputStream(path));
			}catch(Exception e) {e.printStackTrace();}
			finally {
			i1v=new ImageView(i1);
			i1v.setPreserveRatio(true);
			this.setPrefSize(50, 50);
			iRotated=i*90;
			i1v.setRotate(iRotated);
		    this.setGraphic(i1v);
			}
	}
	//0变白，1变黑
	void Turn(int i)
	{
		try {	
			if(i==0)
			{	
				i1=new Image(
						new FileInputStream(path_W));
				i1v=new ImageView(i1);
				i1v.setPreserveRatio(true);
				this.setPrefSize(50, 50);
				i1v.setRotate(iRotated);
			    this.setGraphic(i1v);
			}
			else
			{
				i1=new Image(
						new FileInputStream(path_B));
				i1v=new ImageView(i1);
				i1v.setPreserveRatio(true);
				this.setPrefSize(50, 50);
				i1v.setRotate(iRotated);
			    this.setGraphic(i1v);
			}
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
class CFcorner extends Label{
	String path;
	String path_W;
	String path_B;
	Image i1;
	ImageView i1v;
	int iRotated;
	CFcorner(int i)
	{
		path=new File("lib/CornerField.png").getAbsolutePath();
		path_W=new File("lib/CornerField_W.png").getAbsolutePath();
		path_B=new File("lib/CornerField_B.png").getAbsolutePath();
		try {
		i1 = new 
				Image(new FileInputStream(path));
		}catch(Exception e) {e.printStackTrace();}
		finally {
		i1v=new ImageView(i1);
		i1v.setPreserveRatio(true);
		iRotated=i*90;
		i1v.setRotate(iRotated);
		this.setPrefSize(50, 50);
	    this.setGraphic(i1v);
		}
	}
	//0变白，1变黑
	void Turn(int i)
	{
		try {	
			if(i==0)
			{	
				i1=new Image(
						new FileInputStream(path_W));
				i1v=new ImageView(i1);
				i1v.setPreserveRatio(true);
				i1v.setRotate(iRotated);
				this.setPrefSize(50, 50);
			    this.setGraphic(i1v);
			}
			else
			{
				i1=new Image(
						new FileInputStream(path_B));
				i1v=new ImageView(i1);
				i1v.setPreserveRatio(true);
				i1v.setRotate(iRotated);
				this.setPrefSize(50, 50);
			    this.setGraphic(i1v);
			}
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

}

