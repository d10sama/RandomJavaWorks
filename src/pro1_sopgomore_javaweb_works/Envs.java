package pro1_sopgomore_javaweb_works;

import java.util.Collection;
import java.util.LinkedList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Envs extends Application{
	CFmid[] mids;
	CFside[] top;
	CFside[] left;
	CFside[] rig;
	CFside[] bottom;
	CFcorner ul,ur,dl,dr;
	player pler=new player();
	boolean quit=false;
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		GridPane cf=new GridPane();
		CourtField(cf);
		Scene scene = new Scene(cf,1000,850);
		cf.setOnMouseClicked(e->
		{
			judge(cf,scene);
		}
	);
		scene.setOnMouseClicked(e->
		{
			if(quit==true)
				primaryStage.close();
		});
		primaryStage.setScene(scene);
		primaryStage.setTitle("五子棋");
		primaryStage.show();
	}
	public static void main(String[] args) {
	    launch(args);
	}
	
	void judge(GridPane cf,Scene sc)
	{
		//本段问题！
		//平局后刷新太快，需要等待
		//平局时刻
		if(pler.status()==-1)
		{
			pler.refresh();
			
			cf.getChildren().removeAll(mids);
			cf.getChildren().removeAll(top);
			cf.getChildren().removeAll(left);
			cf.getChildren().removeAll(rig);
			cf.getChildren().removeAll(bottom);
			cf.getChildren().removeAll(ul,ur,dl,dr);
			won(cf,-1);
		}
		//黑色胜利
		else if(pler.status()==1)
		{
			pler.refresh();
			
			cf.getChildren().removeAll(mids);
			cf.getChildren().removeAll(top);
			cf.getChildren().removeAll(left);
			cf.getChildren().removeAll(rig);
			cf.getChildren().removeAll(bottom);
			cf.getChildren().removeAll(ul,ur,dl,dr);
			won(cf,1);
		}
		//白色胜利
		else if(pler.status()==0)
		{
			pler.refresh();
			
			cf.getChildren().removeAll(mids);
			cf.getChildren().removeAll(top);
			cf.getChildren().removeAll(left);
			cf.getChildren().removeAll(rig);
			cf.getChildren().removeAll(bottom);
			cf.getChildren().removeAll(ul,ur,dl,dr);
			won(cf,0);
		}
		//其他情况
		else
		{
		}
	}
	
	void won(GridPane cf,int status)
	{
		Button CON=new Button("Continue?");//continue?
		Button QUIT=new Button("Quit?");
		String winner="The winner is %s";
		Label l1=new Label();
		if(status==0)
			winner=String.format(winner, " White!!");
		if(status==1)
			winner=String.format(winner, " Black!!");
		if(status==0)
			winner=String.format(winner, " Nobody,你们俩平局");
		Stage tmp=new Stage();
		GridPane tmp3=new GridPane();
		Scene tmp2=new Scene(tmp3,1000,850);
		tmp.setScene(tmp2);
		tmp.setTitle("对局结果");
		tmp.show();
		l1.setText(winner);
		l1.setFont(new Font(30));
		tmp3.add(l1, 1, 1);
		tmp3.add(CON, 1, 2);
		tmp3.add(QUIT, 1, 3);
		CON.setOnMouseClicked(e->
			{
				tmp.close();
				CourtField(cf);
			}
		);
		QUIT.setOnMouseClicked(e->
			{
				
				try {
					this.stop();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					tmp.close();
				}
			}
		);
		
		
		
	}
	//环境构造
	void CourtField(GridPane cf)
	{
		int PosDetect=0;
		cf.setAlignment(Pos.TOP_LEFT);
		ul=new CFcorner(0);
		mids=new CFmid[169];
		top=new CFside[13];
		left=new CFside[13];
		rig=new CFside[13];
		bottom=new CFside[13];
		ur=new CFcorner(1);
		dl=new CFcorner(3);
		dr=new CFcorner(2);
		cf.add(ul,0,0);
		ul.event(pler,0,0);
		while(PosDetect<13)
		{	
			left[PosDetect]=new CFside(0);
			final int PD=PosDetect;
			left[PosDetect].event(pler,PD+1,0);
			cf.add(left[PosDetect],0,PosDetect+1);
			PosDetect++;
		}
		cf.add(dl,0,14);
		dl.event(pler,14,0);
		PosDetect=0;
		for(int i=0;i<13;i++)
			for(int j=0;j<15;j++)
			{
				final int I=i;
				final int J=j;
				switch(j)
				{
				case 0:{
						top[i]=new CFside(1);
						top[i].event(pler,J,I+1);
							cf.add(top[i], i+1, j);
						break;
						}
				case 14:{
						bottom[i]=new CFside(3);
						bottom[i].event(pler,J,I+1);
						cf.add(bottom[i], i+1, j);
						break;
						}
				default:
					final int PD=i*(j-1);
					mids[PD]=new CFmid();
					mids[PD].event(pler,J,I+1);
					cf.add(mids[PD], i+1, j);
					break;
				
				}
			}
		
		cf.add(ur, 14, 0);
		ur.event(pler,0,14);
		while(PosDetect<13)
		{
			final int PD=PosDetect;
			rig[PosDetect]=new CFside(2);
			rig[PosDetect].event(pler,PD+1,14);
			cf.add(rig[PosDetect], 14, PosDetect+1);
			PosDetect++;
		}
		cf.add(dr, 14, 14);
		dr.event(pler,14,14);
		
		
	}
}