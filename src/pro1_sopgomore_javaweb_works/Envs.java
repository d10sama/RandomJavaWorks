package pro1_sopgomore_javaweb_works;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		GridPane cf=new GridPane();
		CourtField(cf);
		//Rules(cf);
		Scene scene = new Scene(cf,1324,800);
		primaryStage.setScene(scene);
		primaryStage.setTitle("五子棋");
		primaryStage.show();
	}
	public static void main(String[] args) {
	    launch(args);
	}
	//环境构造
	void CourtField(GridPane cf)
	{
		int PosDetect=0;
		cf.setAlignment(Pos.TOP_CENTER);
		ul=new CFcorner(0);
		mids=new CFmid[234];
		top=new CFside[18];
		left=new CFside[13];
		rig=new CFside[13];
		bottom=new CFside[18];
		ur=new CFcorner(1);
		dl=new CFcorner(3);
		dr=new CFcorner(2);
		cf.add(ul,0,0);
		ul.event(pler,0,0);
		while(PosDetect<13)
		{	
			left[PosDetect]=new CFside(0);
			final int PD=PosDetect;
			left[PosDetect].event(pler,0,PD+1);
			cf.add(left[PosDetect],0,PosDetect+1);
			PosDetect++;
		}
		cf.add(dl,0,14);
		dl.event(pler,0,14);
		PosDetect=0;
		for(int i=0;i<18;i++)
			for(int j=0;j<15;j++)
			{
				final int I=i;
				final int J=j;
				switch(j)
				{
				case 0:{
						top[i]=new CFside(1);
						top[i].event(pler,I+1,J);
							cf.add(top[i], i+1, j);
						break;
						}
				case 14:{
						bottom[i]=new CFside(3);
						bottom[i].event(pler,I+1,J);
						cf.add(bottom[i], i+1, j);
						break;
						}
				default:
					final int PD=i*(j-1);
					mids[PD]=new CFmid();
					mids[PD].event(pler,I+1,J);
					cf.add(mids[PD], i+1, j);
					break;
				
				}
			}
		
		cf.add(ur, 19, 0);
		ur.event(pler,19,0);
		while(PosDetect<13)
		{
			final int PD=PosDetect;
			rig[PosDetect]=new CFside(2);
			rig[PosDetect].event(pler,19,PD+1);
			cf.add(rig[PosDetect], 19, PosDetect+1);
			PosDetect++;
		}
		cf.add(dr, 19, 14);
		dr.event(pler,19,14);
	}
	//规则构造
	void Rules(GridPane cf)
	{
		cf.setOnMouseClicked(e->
		{
			//5连续则（！isWhite)胜利
			judge(pler,cf);
			//pler.coutdown=0则平局
			draw(pler,cf);
		}
		);
	}
	//平局
	void draw(player pler,GridPane cf)
	{
		if(pler.nodecount==0)
			clr(pler,cf);
	}
	//4次遍历
	void judge(player pler,GridPane cf)
	{
		int PresentLen=0;
		int MaxLen=0;
	}
	void clr(player pler,GridPane cf)
	{
		pler.refresh();
		//消息弹窗以及重来还是退出
		
		
		CourtField(cf);
	}
}
/*
//竖着来
for(int i=0;i<20;i++)
{
	int j=0;
	while(j<15)
	{
		if(pler.nodes[i][j]==1)
		{
			PresentLen++;
		}else
		{
			MaxLen=PresentLen>MaxLen?PresentLen:MaxLen;
			PresentLen=0;
		}
		j++;
	}
}
if(MaxLen>4){clr(pler,cf);};
MaxLen=0;
PresentLen=0;
//横着来
for(int i=0;i<15;i++)
{
	int j=0;
	while(j<20)
	{
		if(pler.nodes[j][i]==1)
		{
			PresentLen++;
		}else
		{
			MaxLen=PresentLen>MaxLen?PresentLen:MaxLen;
			PresentLen=0;
		}
		j++;
	}
}
if(MaxLen>4){clr(pler,cf);};
MaxLen=0;
PresentLen=0;
//左下到右上
for(int i=5;i<15;i++)
{
		int pdi=i;
		int pdj=0;
		while(pdi>-1)
		{
			if(pler.nodes[pdj][pdi]==1)
			{
				PresentLen++;
			}else
			{
				MaxLen=PresentLen>MaxLen?PresentLen:MaxLen;
				PresentLen=0;
			}
			pdj+=1;
			pdi-=1;
		}
}
if(MaxLen>4){clr(pler,cf);};
MaxLen=0;
PresentLen=0;

for(int j=1;j<14;j++)
{
	int pdi=14;
	int pdj=j;
	while(pdj<20&&pdi>-1)
	{
		if(pler.nodes[pdj][pdi]==1)
		{
			PresentLen++;
		}else
		{
			MaxLen=PresentLen>MaxLen?PresentLen:MaxLen;
			PresentLen=0;
		}
		pdj+=1;
		pdi-=1;
	}
}
if(MaxLen>4){clr(pler,cf);};
MaxLen=0;
PresentLen=0;
////左上到右下
for(int j=14;j>-1;j++)
{
	int pdi=0;
	int pdj=j;
	while(pdi<15&&pdj<20)
	{
		if(pler.nodes[pdj][pdi]==1)
		{
			PresentLen++;
		}else
		{
			MaxLen=PresentLen>MaxLen?PresentLen:MaxLen;
			PresentLen=0;
		}
	}
	pdj++;
	pdi++;
}
if(MaxLen>4){clr(pler,cf);};
MaxLen=0;
PresentLen=0;

for(int i=1;i<15;i++)
{
	int pdi=i;
	int pdj=0;
	while(pdi<15&&pdj<20)
	{
		if(pler.nodes[pdj][pdi]==1)
		{
			PresentLen++;
		}else
		{
			MaxLen=PresentLen>MaxLen?PresentLen:MaxLen;
			PresentLen=0;
		}
	}
	pdj++;
	pdi++;
}

if(MaxLen>4){clr(pler,cf);};
MaxLen=0;
PresentLen=0;
 */ 
