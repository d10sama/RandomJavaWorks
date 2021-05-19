package pro1_sopgomore_javaweb_works;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
class Rules{
	static int result(player pler)
	{
		int isDraw=draw(pler);
		int isWon=winner(pler);
		System.out.println(isDraw+" "+isWon);
		if(isDraw==1)
			return -1;
		else
			return isWon;
	}
	
	//平局状况
	static int draw(player pler)
	{
		if(pler.nodecount==0)
			return 1;
		else 
			return 0;
	}
	//1黑赢，0白赢，2什么也不发生
	static int winner(player pler)
	{
		int count=0;
		
		int PresentLen_b=0;
		int MaxLen_b=0;
		int PresentLen_w=0;
		int MaxLen_w=0;
		//竖着来
		for(int column=0;column<15;column++)
		{	
			int row=0;
			PresentLen_w=0;
			PresentLen_b=0;
			while(row<15)
			{
				 
				if(pler.nodes_w[row][column]==1)
					PresentLen_w++;
				else
					MaxLen_w=MaxLen_w<PresentLen_w?PresentLen_w:MaxLen_w;
				if(pler.nodes_b[row][column]==1)
					PresentLen_b++;
				else
					MaxLen_b=MaxLen_b<PresentLen_b?PresentLen_b:MaxLen_b;
				row++;
			}
		}
		if(MaxLen_b>4)
			return 1;
		else if(MaxLen_w>4)
			return 0;
		//横着来
		for(int row=0;row<15;row++)
		{
			int column=0;
			PresentLen_w=0;
			PresentLen_b=0;
			while(column<15)
			{
				 
				if(pler.nodes_w[row][column]==1)
					PresentLen_w++;
				else
					MaxLen_w=MaxLen_w<PresentLen_w?PresentLen_w:MaxLen_w;
				if(pler.nodes_b[row][column]==1)
					PresentLen_b++;
				else
					MaxLen_b=MaxLen_b<PresentLen_b?PresentLen_b:MaxLen_b;
				column++;
			}
		}
		if(MaxLen_b>4)
			return 1;
		else if(MaxLen_w>4)
			return 0;
		//左下到右上
		for(int i=5;i<15;i++)
		{
			int row=i;
			int column=0;
			PresentLen_w=0;
			PresentLen_b=0;
			while(column<=i)
			{
				 
				if(pler.nodes_w[row][column]==1)
					PresentLen_w++;
				else
					MaxLen_w=MaxLen_w<PresentLen_w?PresentLen_w:MaxLen_w;
				if(pler.nodes_b[row][column]==1)
					PresentLen_b++;
				else
					MaxLen_b=MaxLen_b<PresentLen_b?PresentLen_b:MaxLen_b;
				column++;
				row--;
			}
		}
		if(MaxLen_b>4)
			return 1;
		else if(MaxLen_w>4)
			return 0;
		for(int precolumn=0;precolumn<=10;precolumn++)
		{
			int row=14;
			int column=precolumn;
			PresentLen_w=0;
			PresentLen_b=0;
			while(column<15&&row>-1)
			{
				 
				if(pler.nodes_w[row][column]==1)
					PresentLen_w++;
				else
					MaxLen_w=MaxLen_w<PresentLen_w?PresentLen_w:MaxLen_w;
				if(pler.nodes_b[row][column]==1)
					PresentLen_b++;
				else
					MaxLen_b=MaxLen_b<PresentLen_b?PresentLen_b:MaxLen_b;
				row--;
				column++;
			}
		}
		if(MaxLen_b>4)
			return 1;
		else if(MaxLen_w>4)
			return 0;
		//左上到右下
		for(int precolumn=10;precolumn>-1;precolumn--)
		{
			int row=0;
			int column=precolumn;
			PresentLen_w=0;
			PresentLen_b=0;
			while(column<15)
			{
				 
				if(pler.nodes_w[row][column]==1)
					PresentLen_w++;
				else
					MaxLen_w=MaxLen_w<PresentLen_w?PresentLen_w:MaxLen_w;
				if(pler.nodes_b[row][column]==1)
					PresentLen_b++;
				else
					MaxLen_b=MaxLen_b<PresentLen_b?PresentLen_b:MaxLen_b;
				column++;
				row++;
			}
		}
		if(MaxLen_b>4)
			return 1;
		else if(MaxLen_w>4)
			return 0;
		for(int preRow=0;preRow<=10;preRow++)
		{
			int row=preRow;
			int column=0;
			PresentLen_w=0;
			PresentLen_b=0;
			while(row<15)
			{
				 
				if(pler.nodes_w[row][column]==1)
					PresentLen_w++;
				else
					MaxLen_w=MaxLen_w<PresentLen_w?PresentLen_w:MaxLen_w;
				if(pler.nodes_b[row][column]==1)
					PresentLen_b++;
				else
					MaxLen_b=MaxLen_b<PresentLen_b?PresentLen_b:MaxLen_b;
				column++;
				row++;
			}
		}
		if(MaxLen_b>4)
			return 1;
		else if(MaxLen_w>4)
			return 0;
		return 2;
	}
}

class CFmid extends Label{
	String path;
	String path_W;
	String path_B;
	Image i1;
	ImageView i1v;

	void event(player pler,int i,int j)
	{
		this.setOnMouseClicked(e->{
			if(pler.isWhite&&pler.nodes_w[i][j]==0&&pler.nodes_b[i][j]==0)
			{
				pler.nodes_w[i][j]=1;
				pler.nodecount--;pler.print(2);
				this.Turn(0);
				pler.status=Rules.result(pler);
				pler.isWhite=false;
			}else if(!pler.isWhite&&pler.nodes_w[i][j]==0&&pler.nodes_b[i][j]==0)
			{
				pler.nodes_b[i][j]=1;
				pler.nodecount--;pler.print(2);
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
	void event(player pler,int i,int j)
	{
		this.setOnMouseClicked(e->{
			if(pler.isWhite&&pler.nodes_w[i][j]==0&&pler.nodes_b[i][j]==0)
			{
				pler.nodes_w[i][j]=1;
				pler.nodecount--;pler.print(2);
				this.Turn(0);
				pler.status=Rules.result(pler);
				pler.isWhite=false;
			}else if(!pler.isWhite&&pler.nodes_w[i][j]==0&&pler.nodes_b[i][j]==0)
			{
				pler.nodes_b[i][j]=1;
				pler.nodecount--;pler.print(2);
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
	void event(player pler,int i,int j)
	{
		this.setOnMouseClicked(e->{
			if(pler.isWhite&&pler.nodes_w[i][j]==0&&pler.nodes_b[i][j]==0)
			{
				pler.nodes_w[i][j]=1;
				
				pler.nodecount--;pler.print(2);
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

//	
//	
//	void clr(player pler,GridPane cf,int isWhite)
//	{
//		pler.refresh();
//		//消息弹窗以及重来还是退出
//		
//		
//		CourtField(cf);
//	}
