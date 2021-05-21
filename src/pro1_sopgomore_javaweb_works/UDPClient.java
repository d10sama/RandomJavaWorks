package pro1_sopgomore_javaweb_works;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//玩家：白
public class UDPClient extends Application
{
	
	//游戏数据部
	int player=0;//玩家，0是黑，1是白
	int GAME_STATUS=2;
	int mpr,mpc;//MyPosRow,MyPosColumn
	int CLICK_ALLOW=1;//白子先下
	
	//游戏窗口组件部
	GridPane cf;
	CFmid[] mids;
	CFside[] top;
	CFside[] left;
	CFside[] rig;
	CFside[] bottom;
	CFcorner ul,ur,dl,dr;
	Label r,c;
	TextField row,column;
	Player pler=new Player();
	Button confirm;
	boolean quit=false;
		
	//服务器接口部
	String[] args;
	InetAddress add=null;
	
	DatagramSocket soc=null;//发送器
	DatagramSocket roc=null;//接收器
	
	DatagramPacket snd=null;//发送包
	DatagramPacket rec=null;//接收包
	
	
	byte[] buf=new byte[100];
	byte[] bufrec=new byte[100];
	
	
	public static void main(String args)
	{
		try {
			launch(args);}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			clientinitiate();
			windowinitiate(primaryStage);
			//while(this.GAME_STATUS==2)
			{
				
				//等待允许落子
				
				
				//方块位置记录部分
				
				
				//buf转换区
					//buf=Methods.intTobyte2(this.GAME_STATUS);
				
				
				//buf发送器
				/*
				 11111111111111111
				       同时，无视棋盘点击行为
				 11111111111111111
				 */
				//soc.send(snd);
				
				
				//bufrec接收器
				//roc.receive(rec);

			}
			//流关闭区
		}catch(Exception e)
		{
			
		}
	}
	
	void clientinitiate() throws Exception 
	{ 
		add=InetAddress.getByName("localhost");
		 //8001玩家1服务器接口，8003玩家1接收器，8002玩家2服务器接口，8004玩家2接收器
		//发送器 
		this.soc=new DatagramSocket(); 
		//接收器 
		this.roc=new DatagramSocket(8003+this.player); 
		//发送包
		this.snd=new DatagramPacket(buf,buf.length,add,8001+this.player); 
		//接收包
		this.rec=new DatagramPacket(bufrec,bufrec.length,add,8003+this.player);
	} 
	void windowinitiate(Stage ps) throws Exception 
	{ 
		cf=new GridPane(); 
		CourtField(cf);
		Scene scene = new Scene(cf,1000,850); 
		ps.setScene(scene);
		ps.setTitle("五子棋"); 
		ps.show(); 
	}
	//构建棋盘
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
		while(PosDetect<13)
		{	
			left[PosDetect]=new CFside(0,(PosDetect+1),0);
			cf.add(left[PosDetect],0,PosDetect+1);
			PosDetect++;
		}
		cf.add(dl,0,14);
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
							cf.add(top[i], i+1, j);
						break;
						}
				case 14:{
						bottom[i]=new CFside(3,J,I+1);
						cf.add(bottom[i], i+1, j);
						break;
						}
				default:
					final int PD=i*(j-1);
					mids[PD]=new CFmid(J,I+1);
					cf.add(mids[PD], i+1, j);
					break;
				
				}
			}
		
		cf.add(ur, 14, 0);
		while(PosDetect<13)
		{
			final int PD=PosDetect;
			rig[PosDetect]=new CFside(2,PD+1,14);
			cf.add(rig[PosDetect], 14, PosDetect+1);
			PosDetect++;
		}
		cf.add(dr, 14, 14);
		r=new Label("Row");
		c=new Label("Column");
		this.row=new TextField();
		this.column=new TextField();
		cf.add(r, 15, 0);
		cf.add(row, 15, 1);
		cf.add(c, 15, 2);
		cf.add(column, 15, 3);
		this.confirm=new Button("PUT");
		cf.add(this.confirm, 15, 4);
		this.confirm.setOnMouseClicked(e->
		{
			this.mpr=Integer.parseInt((row.getText()));
			this.mpc=Integer.parseInt((column.getText()));
			Turn();
		});
	}
	void Turn()
	{
		switch(mpr)
		{
		case 0:
			switch(mpc)
			{
			case 0:
				ul.Turn(this.player);
				break;
			case 14:
				ur.Turn(this.player);
				break;
			default:
				top[mpc].Turn(this.player);;
				break;
			}
			break;
		case 14:
			switch(mpc)
			{
			case 0:
				dl.Turn(this.player);
				break;
			case 14:
				dr.Turn(this.player);
				break;
			default:
				bottom[mpc].Turn(this.player);;
				break;
			}
			break;	
		default:
			switch(mpc)
			{
			case 0:
				left[mpr].Turn(player);
				break;
			case 14:
				rig[mpr].Turn(player);
				break;
			default:
				mids[(mpc-1)*13+mpr-1].Turn(player);
			}
			break;
		}
	}
	//对局状态判断
	void judge()
	{
		
	}
	//胜利后做什么，-1/0/1状态分离
	void won()
	{
		
	}
	//清屏
	void clr(GridPane cf)
	{
		
	}
	
}