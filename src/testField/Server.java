package testField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.stage.Stage;


public class Server extends Thread{
	public static void main(String[] args)
	{
		Player pler=new Player();
		new Server(pler).start();
		
		
	}
	
	//游戏数据部
	Player pler;
	/*
	 * GS==-1; draw
	 * GS== 0; white won
	 * GS== 1; black won
	 * GS== 2; continue
	 */
	int GameStatus=2;
	boolean is_coordinate_validate=false;
	int p1re=1,p2re=1;
	boolean WCoordinateValid=false,BCoordinateValid=false;
	
	
	//网络连接部
	ServerSocket serverSocket1=null;
	Socket soc1=null;
	private DataOutputStream snd1=null;
	private DataInputStream rec1=null;
	ServerSocket serverSocket2=null;
	Socket soc2=null;
	private DataOutputStream snd2=null;
	private DataInputStream rec2=null;

	ServerSocket serverSocket3=null;
	Socket soc3=null;
	private DataOutputStream snd3=null;
	private DataInputStream rec3=null;
	ServerSocket serverSocket4=null;
	Socket soc4=null;
	private DataOutputStream snd4=null;
	private DataInputStream rec4=null;
	
	
	Server(Player pler)
	{
		this.pler=pler;
	}
	@Override
	public void run() {
		connect();
		while(this.p1re==1&&this.p2re==1)
		{
			//刷新pler
			this.pler.refresh();
			//进行一局游戏
			OJ();
			//接收客户端是不是要再来一把
			try {
			p1re=rec1.readInt();
			p2re=rec2.readInt();}catch(Exception e) {System.out.println("接收重开消息失败");}
		}
	}
	void connect()
	{
		try {
			serverSocket1 = new ServerSocket(8000);
			soc1=serverSocket1.accept();
			System.out.println(soc1.getInetAddress().getHostAddress());
			//connect pipes from 01&p2
			this.snd1=new DataOutputStream(soc1.getOutputStream());
			this.rec1= new DataInputStream(soc1.getInputStream());
			
			//白监听黑socket
			serverSocket3 = new ServerSocket(8003);
			soc3=serverSocket3.accept();
			this.snd3=new DataOutputStream(soc3.getOutputStream());
			this.rec3=new DataInputStream(soc3.getInputStream());
			
			serverSocket2 = new ServerSocket(8001);
			soc2=serverSocket2.accept();
			System.out.println(soc2.getInetAddress().getHostAddress());
			//connect pipes from 01&p2
			this.snd2=new DataOutputStream(soc2.getOutputStream());
			this.rec2= new DataInputStream(soc2.getInputStream());
			
			//黑监听白socket
			serverSocket4 = new ServerSocket(8004);
			soc4=serverSocket4.accept();
			this.snd4=new DataOutputStream(soc4.getOutputStream());
			this.rec4=new DataInputStream(soc4.getInputStream());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void OJ()
	{
		while(this.GameStatus==2)
		{
			int mpr=-1,mpc=-1;
			this.WCoordinateValid=false;
			this.BCoordinateValid=false;
			while(!this.WCoordinateValid)
				{//玩家白
				
				try {
					//接收row
					mpr=rec1.readInt();
					//接收column
					mpc=rec1.readInt();
					System.out.println(
							("白")
							+" "+mpr+","+mpc);
				}
					catch(Exception e) {System.out.println("接收数据时出错");}
					
				//判断接收数据是否合法
				this.is_coordinate_validate=DataValidate(mpr,mpc);
					
				//返回数据是否承认
				try {
				if(this.is_coordinate_validate)
				{	
					snd1.writeInt(1);
					
					//修改pler数据，刷新数据并通知玩家2
					
					this.pler.nodes_w[mpr][mpc]=1;
					this.pler.nodecount--;
					
					//告知黑棋改棋子
					snd4.writeInt(1);
					snd4.writeInt(mpr);
					snd4.writeInt(mpc);
					
					sendOperation();
					this.WCoordinateValid=true;
				}
				else
				{
					snd4.writeInt(6);
					snd1.writeInt(0);
				}
				}catch(Exception e) 
				{
					System.out.println("服务器发送数据出错");
					//出错则跳跃到下次循环
					break;
				}
			}
			
			//玩家黑
			mpr=-1;
			mpc=-1;
			while(!this.BCoordinateValid)
			{	
				try {
					//接收row
					mpr=rec2.readInt();
					//接收column
					mpc=rec2.readInt();
					System.out.println(
							("黑")
							+" "+mpr+","+mpc);
				}
					catch(Exception e) {System.out.println("接收数据时出错");}
					
				//判断接收数据是否合法
				this.is_coordinate_validate=DataValidate(mpr,mpc);
					
				//返回数据是否承认
				try {
				if(this.is_coordinate_validate)
				{	
					snd2.writeInt(1);
					
					//修改pler数据，刷新数据并通知
					snd3.writeInt(1);
					snd3.writeInt(mpr);
					snd3.writeInt(mpc);
					this.pler.nodes_b[mpr][mpc]=1;
					this.pler.nodecount--;
					sendOperation();
					this.BCoordinateValid=true;
				}
				else
				{
					snd3.writeInt(6);
					snd2.writeInt(0);
				}
				}catch(Exception e) 
				{
					System.out.println("服务器发送数据出错");
					//出错则跳跃到下次循环
					break;
				}
			}
		}
	}
	void sendOperation()
	{
		try {
			//-1平局，0白赢，1黑赢，2什么也不发生
			this.GameStatus=Rules.winner(pler);
			switch(GameStatus)
			{
			case -1:
				snd3.writeInt(4);
				snd4.writeInt(4);
				break;
			case 0:
				snd3.writeInt(2);
				snd4.writeInt(3);
				break;
			case 1:
				snd4.writeInt(2);
				snd3.writeInt(3);
				break;
			case 2:
				//do nothing
				break;
			}
			//发送操作结果给双方监听器
		}catch(Exception e) {System.out.println("发送操作时出错");}
	}
	boolean DataValidate(int row,int col)
	{
		if(this.pler.nodes_w[row][col]==0&&this.pler.nodes_b[row][col]==0)
			return true;
		return false;
	}
}
