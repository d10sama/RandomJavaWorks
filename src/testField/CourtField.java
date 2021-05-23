package testField;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//标签方块

class CFmid extends Label{
	String path;
	String path_W;
	String path_B;
	Image i1;
	ImageView i1v;
	boolean isLoadFail=false;
	int MyPosRow,MyPosColumn;
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
	CFmid(int mpr,int mpc)
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
	int MyPosRow,MyPosColumn;
	CFside(int i,int mpr,int mpc)
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
	int MyPosRow,MyPosColumn;
	CFcorner(int i,int mpr,int mpc)
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