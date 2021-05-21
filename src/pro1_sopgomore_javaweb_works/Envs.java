package pro1_sopgomore_javaweb_works;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Envs extends Application{

	public static void main(String[] args) {
		Application.launch(args);
	}
	public void run(String[] args)
	{
		launch(args);
	}
	
	CFmid[] mids;
	CFside[] top;
	CFside[] left;
	CFside[] rig;
	CFside[] bottom;
	CFcorner ul,ur,dl,dr;
	Player pler=new Player();
	boolean quit=false;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		GridPane cf=new GridPane();
		CourtField(cf);
		if(ul.isLoadFail)
		{
			clr(cf);
			Label test=new Label(ul.getText());
			test.setPrefSize(1000,200);
			cf.add(test, 1, 1);
		}
		Scene scene = new Scene(cf,1000,850);
		cf.setOnMouseClicked(e->
			{
				judge(cf,primaryStage);
			});
		scene.setOnMouseClicked(e->
		{
			if(quit==true)
				primaryStage.close();
		});
		primaryStage.setScene(scene);
		primaryStage.setTitle("五子棋");
		primaryStage.show();
	}
	//对局状态判断
	void judge(GridPane cf,Stage ps)
	{
		//本段问题！
		//平局后刷新太快，需要等待
		//平局时刻
		if(pler.status()==-1)
		{
			pler.refresh();

			won(cf,-1,ps);
		}
		//黑色胜利
		else if(pler.status()==1)
		{
			pler.refresh();

			won(cf,1,ps);
		}
		//白色胜利
		else if(pler.status()==0)
		{
			pler.refresh();
			
			won(cf,0,ps);
		}
		//其他情况
		else
		{
			
		}
	}
	//胜利后做什么，-1/0/1状态分离
	void won(GridPane cf,int status,Stage ps)
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
		GridPane.setMargin(l1, new Insets(0,0,0,115));
		GridPane tmp3=new GridPane();
		tmp3.setBackground(new Background(new BackgroundFill(Color.CHOCOLATE,null,null)));
		l1.setText(winner);
		l1.setPrefHeight(50);
		l1.setFont(new Font("SimHei",40));
		l1.setAlignment(Pos.TOP_LEFT);
		CON.setAlignment(Pos.CENTER);
		QUIT.setAlignment(Pos.CENTER);
		CON.setPrefSize(100,50);
		QUIT.setPrefSize(100,50);
		tmp3.add(l1, 1, 1);
		tmp3.add(CON, 1, 3);
		tmp3.add(QUIT, 2, 3);
		Scene tmp2=new Scene(tmp3,800,400);
		tmp.setScene(tmp2);
		tmp.setTitle("对局结果");
		tmp.show();
		
		CON.setOnMouseClicked(e->
			{
				tmp.close();
				clr(cf);
				CourtField(cf);
			}
		);
		QUIT.setOnMouseClicked(e->
			{

				clr(cf);
				try {
					ps.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					tmp.close();
				}
			}
		);
		
		
		
	}
	//清屏
	void clr(GridPane cf)
	{
		cf.getChildren().removeAll(mids);
		cf.getChildren().removeAll(top);
		cf.getChildren().removeAll(left);
		cf.getChildren().removeAll(rig);
		cf.getChildren().removeAll(bottom);
		cf.getChildren().removeAll(ul,ur,dl,dr);
	}
	
	//环境构造
	void CourtField(GridPane cf)
	{
		int PosDetect=0;
		cf.setAlignment(Pos.TOP_LEFT);
		ul=new CFcorner(0,0,0);
		mids=new CFmid[169];
		top=new CFside[13];
		left=new CFside[13];
		rig=new CFside[13];
		bottom=new CFside[13];
		ur=new CFcorner(1,0,14);
		dl=new CFcorner(3,14,0);
		dr=new CFcorner(2,14,14);
		cf.add(ul,0,0);
		ul.event(pler,0,0);
		while(PosDetect<13)
		{	
			left[PosDetect]=new CFside(0,(PosDetect+1),0);
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
						top[i]=new CFside(1,J,I+1);
						top[i].event(pler,J,I+1);
							cf.add(top[i], i+1, j);
						break;
						}
				case 14:{
						bottom[i]=new CFside(3,J,I+1);
						bottom[i].event(pler,J,I+1);
						cf.add(bottom[i], i+1, j);
						break;
						}
				default:
					final int PD=i*(j-1);
					mids[PD]=new CFmid(J,I+1);
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
			rig[PosDetect]=new CFside(2,PD+1,14);
			rig[PosDetect].event(pler,PD+1,14);
			cf.add(rig[PosDetect], 14, PosDetect+1);
			PosDetect++;
		}
		cf.add(dr, 14, 14);
		dr.event(pler,14,14);
		
		
	}
}