package pro1_sopgomore_javaweb_works;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
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


class CFmid extends Label{
	String path;
	String path_W;
	String path_B;
	Image i1;
	ImageView i1v;
	boolean isLoadFail=false;
	void event(player pler,int i,int j)
	{
		this.setOnMouseClicked(e->{
			if(pler.isWhite&&pler.nodes_w[i][j]==0&&pler.nodes_b[i][j]==0)
			{
				pler.nodes_w[i][j]=1;
				pler.nodecount--;pler.print(1);
				this.Turn(0);
				pler.status=Rules.result(pler);
				pler.isWhite=false;
			}else if(!pler.isWhite&&pler.nodes_w[i][j]==0&&pler.nodes_b[i][j]==0)
			{
				pler.nodes_b[i][j]=1;
				pler.nodecount--;pler.print(1);
				this.Turn(1);
				pler.status=Rules.result(pler);
				pler.isWhite=true;
			}
		});
	}
	CFmid()
	{
		path=new File("lib/MidField.png").getAbsolutePath();
		path_W=new File("lib/MidField_W.png").getAbsolutePath();
		path_B=new File("lib/MidField_B.png").getAbsolutePath();
		try {
			i1 = new Image(new FileInputStream(path));
			i1v=new ImageView(i1);
			i1v.setPreserveRatio(true);
			this.setPrefSize(50, 50);
		    this.setGraphic(i1v);
			}catch(Exception e) {
				File tmp=new File("locationDetect");
				isLoadFail=true;
				if(!tmp.exists())
					try {
						tmp.createNewFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						this.setText("图像文件未找到且路径寻找失败！");
						this.setPrefSize(300, 50);
					}
				this.setText(String.format("请在%s同级文件夹中放入‘lib’文件夹",tmp.getAbsolutePath()));
				this.setPrefSize(300, 50);
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
	boolean isLoadFail=false;
	int iRotated;
	CFside(int i)
	{
		path=new File("lib/SideField.png").getAbsolutePath();
		path_W=new File("lib/SideField_W.png").getAbsolutePath();
		path_B=new File("lib/SideField_B.png").getAbsolutePath();
		try {
			i1 = new Image(new FileInputStream(path));
			i1v=new ImageView(i1);
			i1v.setPreserveRatio(true);
			this.setPrefSize(50, 50);
			iRotated=i*90;
			i1v.setRotate(iRotated);
		    this.setGraphic(i1v);
			}catch(Exception e) {
				File tmp=new File("locationDetect");
				isLoadFail=true;
				if(!tmp.exists())
					try {
						tmp.createNewFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						this.setText("图像文件未找到且路径寻找失败！");
						this.setPrefSize(300, 50);
					}
				this.setText(String.format("请在%s同级文件夹中放入‘lib’文件夹",tmp.getAbsolutePath()));
				this.setPrefSize(300, 50);
			}
	}
	void event(player pler,int i,int j)
	{
		this.setOnMouseClicked(e->{
			if(pler.isWhite&&pler.nodes_w[i][j]==0&&pler.nodes_b[i][j]==0)
			{
				pler.nodes_w[i][j]=1;
				pler.nodecount--;pler.print(1);
				this.Turn(0);
				pler.status=Rules.result(pler);
				pler.isWhite=false;
			}else if(!pler.isWhite&&pler.nodes_w[i][j]==0&&pler.nodes_b[i][j]==0)
			{
				pler.nodes_b[i][j]=1;
				pler.nodecount--;pler.print(1);
				this.Turn(1);
				pler.status=Rules.result(pler);
				pler.isWhite=true;
			}
		});
		
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
	boolean isLoadFail=false;
	int iRotated;
	CFcorner(int i)
	{
		path=new File("lib/CornerField.png").getAbsolutePath();
		path_W=new File("lib/CornerField_W.png").getAbsolutePath();
		path_B=new File("lib/CornerField_B.png").getAbsolutePath();
		try {
			i1 = new Image(new FileInputStream(path));
			i1v=new ImageView(i1);
			i1v.setPreserveRatio(true);
			iRotated=i*90;
			i1v.setRotate(iRotated);
			this.setPrefSize(50, 50);
		    this.setGraphic(i1v);
		}catch(Exception e) {
			File tmp=new File("locationDetect");
			isLoadFail=true;
			if(!tmp.exists())
				try {
					tmp.createNewFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					this.setText("图像文件未找到且路径寻找失败！");
					this.setPrefSize(300, 50);
				}
			this.setText(String.format("请在%s同级文件夹中放入‘lib’文件夹",tmp.getAbsolutePath()));
			this.setPrefSize(300, 50);
		}
	}
	void event(player pler,int i,int j)
	{
		this.setOnMouseClicked(e->{
			if(pler.isWhite&&pler.nodes_w[i][j]==0&&pler.nodes_b[i][j]==0)
			{
				pler.nodes_w[i][j]=1;
				
				pler.nodecount--;pler.print(1);
				this.Turn(0);
				pler.status=Rules.result(pler);
				pler.isWhite=false;
			}else if(!pler.isWhite&&pler.nodes_w[i][j]==0&&pler.nodes_b[i][j]==0)
			{
				pler.nodes_b[i][j]=1;
				pler.nodecount--;
				this.Turn(1);
				pler.status=Rules.result(pler);
				pler.isWhite=true;
			}
		});
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