package testField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import testField.TCP1.Listener;

public class TCP2 extends Application{
	//游戏数据部
	Stage ps;
	private int mySerial=1;//我是白色
	GridPane cf;
	CFmid[] mids;
	CFside[] top;
	CFside[] left;
	CFside[] rig;
	CFside[] bottom;
	CFcorner ul,ur,dl,dr;
	Label r,c;
	TextField row,column;
	Button confirm,esc;
	int GameStatus=2;
	
	//网络连接部
	Socket socket;
	private DataInputStream fromServer;
	private DataOutputStream toServer;
	private String readline=null;//stream line receiver
	private int readint;// stream int receiver
	private int sendint;//stream int sender
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.ps=primaryStage;
		// TODO Auto-generated method stub
		connect();
		new Listener().start();
		courtfield(primaryStage);
		play(primaryStage);
	}
	
	void connect()
	{
		try {
			socket=new Socket("localhost",8000+this.mySerial);
			while(!socket.isConnected())
				socket=new Socket("localhost",8000+this.mySerial);
			// Create an input stream to receive data from the server
		    fromServer = new DataInputStream(socket.getInputStream());
		    // Create an output stream to send data to the server
		    toServer = new DataOutputStream(socket.getOutputStream());
		}catch(Exception e)
		{
			
		}
	}
	
	void courtfield(Stage ps)
	{
		this.cf=new GridPane();
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
			final int PD=PosDetect;
			cf.add(left[PosDetect],0,PosDetect+1);
			PosDetect++;
		}
		cf.add(dl,0,14);
		PosDetect=0;
		int midcount=0;
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
					mids[midcount]=new CFmid(J,I+1);
					cf.add(mids[midcount], i+1, j);
					midcount++;
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
		r=new Label("Row(0~14)");
		r.setFont(new Font("Bell MT",25));
		c=new Label("Column(0~14)");
		c.setFont(new Font("Bell MT",23));
		row=new TextField();
		column=new TextField();
		confirm=new Button("PUT!");
		esc=new Button("Leave");
		cf.add(r, 15, 0);
		cf.add(row, 15, 1);
		cf.add(c, 15, 2);
		cf.add(column, 15, 3);
		cf.add(confirm, 15, 4);
		cf.add(esc, 15, 5);
		cf.setBackground(new Background(new BackgroundFill(Color.DARKGOLDENROD,null,null)));
		Scene s=new Scene(cf,900,750);
		ps.setScene(s);
		ps.setTitle("五子棋 玩家："+(this.mySerial==0?"白":"黑"));
		ps.show();
	}
	
	void play(Stage ps)
	{
		esc.setOnMouseClicked(e->
		{
			Label tmpl=new Label("U Sure to Leave?");
			Button stay=new Button("STAY");
			Button leave=new Button("LEAVE");
			GridPane tmpp=new GridPane();
			Scene tmpc=new Scene(tmpp,200,200);
			Stage tmps=new Stage();
			tmps.setScene(tmpc);
			tmpp.add(tmpl, 0, 0);
			tmpp.add(stay, 0, 1);
			tmpp.add(leave, 1, 1);
			stay.setOnMouseClicked(e1->{
				tmps.close();
			});
			leave.setOnMouseClicked(e2->{
				
				//给服务器发离开消息
				//SendLeaveMessage();
				
				//同时关两个窗口
				tmps.close();
				ps.close();
			});
			tmps.show();
		});
		confirm.setOnMouseClicked(e->
		{
				int mpr=15,mpc=15;
				int OperationValid=0;
				mpr=Integer.parseInt(row.getText());
				mpc=Integer.parseInt(column.getText());
				if(mpr<0||mpr>14||mpc<0||mpc>14)
				{
					MessageStage("输入不在范围内哦");
				}else
				{
					//送出row
					sendint=mpr;
					try {toServer.writeInt(sendint);}catch (IOException e1){e1.printStackTrace();}
					//送出column
					sendint=mpc;
					try {toServer.writeInt(sendint);}catch (IOException e1){e1.printStackTrace();}
					
					//读取服务器发来的操作验证
					try {OperationValid=this.fromServer.readInt();} 
					catch (IOException e1) {
						System.out.println("验证操作时出错  "+new Date());
						MessageStage("验证操作时出错"+new Date());}
					//可以操作
					if(OperationValid==1)
					{
						Turn(mpr,mpc,1);
						
						//（极难！）禁用发操作功能
						
					}else
					{
						
					}
					
				}

		});
	}
	void MessageStage(String s)
	{
		Label tmpl=new Label(s);
		GridPane tmpp=new GridPane();
		Scene tmpc=new Scene(tmpp,100,50);
		Stage tmps=new Stage();
		tmps.setScene(tmpc);
		tmpp.add(tmpl, 0, 0);
		tmps.show();
	}
	void Turn(int mpr,int mpc,int b_w)
	{
		switch(mpr)
		{
		case 0:
			switch(mpc)
			{
			case 0:
				this.ul.Turn(b_w);
				break;
			case 14:
				this.ur.Turn(b_w);
				break;
			default:
				this.top[mpc].Turn(b_w);
				break;
			}
			break;
		case 14:
			switch(mpc)
			{
			case 0:
				this.dl.Turn(b_w);
				break;
			case 14:
				this.dr.Turn(b_w);
				break;
			default:
				this.bottom[mpc].Turn(b_w);
				break;
			}
			break;
			
		default:
			switch(mpc)
			{
			case 0:
				this.left[mpr].Turn(b_w);
				break;
			case 14:
				this.rig[mpr].Turn(b_w);
				break;
			default:
				this.mids[(mpc-1)*13+mpr-1].Turn(b_w);	
				break;
			}	
			break;
		}
	}
	void won(int i)
	{
		String s="The winner is ";
		Label outcome=new Label();
		outcome.setFont(new Font("Bell MT",25));
		GridPane fin=new GridPane();
		Button restart=new Button("Restart");
		Button leave=new Button("Leave");
		Scene loader=new Scene(fin,300,300);
		fin.add(outcome, 0, 0);
		fin.add(leave, 0, 1);
		fin.add(restart, 1, 1);
		Stage end=new Stage();
		end.setScene(loader);
		if(i==0)
		{
			outcome.setText(s+"WHITE!!!!");
		}
		else if(i==1)
		{
			outcome.setText(s+"BLACK!!!!");
		}
		else if(i==-1)
		{
			outcome.setText(s+"NOBODY!!!!");
		}
		end.setTitle("对局结果！");
		end.show();
		restart.setOnMouseClicked(e->
		{
			try {
				//给服务器发我要重来
				this.toServer.writeInt(1);
				//清屏
				clr();
				courtfield(this.ps);
				this.MessageStage("新的一局开始啦");
				end.close();
			} catch (IOException e1) {
				System.out.println("向服务器发送重开数据失败");
				this.MessageStage("向服务器发送重开数据失败");
			}
		});
		leave.setOnMouseClicked(e->
		{
			
			this.ps.close();
			end.close();
			try {
				this.stop();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
	void clr()
	{
		cf.getChildren().removeAll(mids);
		cf.getChildren().removeAll(top);
		cf.getChildren().removeAll(left);
		cf.getChildren().removeAll(rig);
		cf.getChildren().removeAll(bottom);
		cf.getChildren().removeAll(ul,ur,dl,dr);
		cf.getChildren().removeAll(r,c,row,column,confirm,esc);
	}
	class Listener extends Thread{
		@Override
		public void run() {
		try {
			Socket Listenersocket=new Socket("localhost",8004);
			while(!Listenersocket.isConnected())
				Listenersocket=new Socket("localhost",8004);
			DataInputStream fromServer2= new DataInputStream(Listenersocket.getInputStream());
			int ModifySignal=0;
			while(true)
			{
				ModifySignal=fromServer2.readInt();
				//服务器通知
				//信号1，对方落子
				if(ModifySignal==1)
				{
					int TurnRow,TurnColumn;
					TurnRow=fromServer2.readInt();
					TurnColumn=fromServer2.readInt();
					Platform.runLater(new Runnable(){
						@Override
						public void run() {
							Turn(TurnRow, TurnColumn,0);
						}
						
						});
					ModifySignal=0;
				}
				//信号2，我赢了
				else if(ModifySignal==2)
				{
					Platform.runLater(new Runnable(){
						@Override
						public void run() {
							won(1);
						}
						});
					ModifySignal=0;
				}
				//信号3，对方赢了
				else if(ModifySignal==3)
				{
					Platform.runLater(new Runnable(){
						@Override
						public void run() {
							won(0);
						}
						});
					ModifySignal=0;
				}
				//信号4，平局
				else if(ModifySignal==4)
				{
					Platform.runLater(new Runnable(){
						@Override
						public void run() {
							won(-1);
						}
						});
					ModifySignal=0;
				}
			}
			}catch(Exception e)
			{
				
			}
			
			
			
		}
	}
}
