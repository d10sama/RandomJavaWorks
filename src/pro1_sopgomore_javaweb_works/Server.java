package pro1_sopgomore_javaweb_works;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javafx.scene.layout.GridPane;

public class Server {
	public static void main(String[] args)
	{
		OJ server=new OJ();
		server.start();
	}
}
class OJ extends Thread
{
	//游戏连接部
	int p1PosColumn=-1,p1PosRow=-1;
	int p2PosColumn=-1,p2PosRow=-1;
	Player pler;
	int GAME_STATUS=2;
	int isPlaceable=0;
	//网络连接部
	InetAddress addr=null;
	DatagramSocket UDPServer1=null;
	DatagramSocket UDPServer2=null;
	
	DatagramSocket UDPsnd1=null;
	DatagramSocket UDPsnd2=null;
	
	DatagramPacket p1rec1=null;
	DatagramPacket p1snd1=null;
	
	DatagramPacket p2rec1=null;
	DatagramPacket p2snd1=null;
	
	byte[] buf=new byte[100];
	byte[] bufsnd=new byte[100];
	
	OJ()
	{
		pler=new Player();
	}
	@Override
	public void run()
	{
		try {
			addr=InetAddress.getByName("localhost");
			
			UDPServer1=new DatagramSocket(8001);//8001接受玩家1数据
			UDPServer2=new DatagramSocket(8002);//8002接受玩家2数据
			UDPsnd1=new DatagramSocket();//给玩家1发数据
			UDPsnd2=new DatagramSocket();//给玩家2发数据
			
			//玩家1接受发送数据包初始化
			p1rec1=new DatagramPacket(buf,buf.length,addr,8001);
			p1snd1=new DatagramPacket(bufsnd,bufsnd.length,addr,8003);
			//玩家2接受发送数据包初始化
			p2rec1=new DatagramPacket(buf,buf.length,addr,8002);
			p2snd1=new DatagramPacket(bufsnd,bufsnd.length,addr,8004);
			
			while(GAME_STATUS==2)
			{
			//白子部分
				while(this.isPlaceable==0)
				{
					//接受玩家1落子位置
					UDPServer1.receive(p1rec1);
					this.p1PosRow=Methods.byteArrayToInt2(buf);
					UDPServer1.receive(p1rec1);
					this.p1PosColumn=Methods.byteArrayToInt2(buf);
					//检测能否放入
					this.isPlaceable=placeable(p1PosRow,p1PosColumn);
					//返回放入结果，让客户端更新棋盘，或者什么也不做
					bufsnd=Methods.intTobyte2(this.isPlaceable);
					UDPsnd1.send(p1snd1);
				}
				//修改pler格子数值
				pler.nodes_w[this.p1PosRow][this.p1PosColumn]=1;

				//判断是否胜利
				GAME_STATUS=Rules.winner(pler);
				this.isPlaceable=0;
				if(GAME_STATUS!=2)
					break;
				
				while(this.isPlaceable==0)
				{
					//接受玩家1落子位置
					UDPServer2.receive(p2rec1);
					this.p2PosRow=Methods.byteArrayToInt2(buf);
					UDPServer2.receive(p2rec1);
					this.p2PosColumn=Methods.byteArrayToInt2(buf);
					//检测能否放入
					this.isPlaceable=placeable(p2PosRow,p2PosColumn);
					//返回放入结果，让客户端更新棋盘，或者什么也不做
					bufsnd=Methods.intTobyte2(this.isPlaceable);
					UDPsnd2.send(p2snd1);
				}
				//修改pler格子数值
				pler.nodes_b[this.p1PosRow][this.p1PosColumn]=1;
				
				//判断是否胜利
				GAME_STATUS=Rules.winner(pler);
				this.isPlaceable=0;
			}
			bufsnd=Methods.intTobyte2(this.GAME_STATUS);
			System.out.println("对局结果"+this.GAME_STATUS);
			UDPsnd1.send(p1snd1);
			UDPsnd2.send(p1snd1);
			UDPsnd1.close();
			UDPsnd2.close();
			//下两行是tcp，先注释了
			//不打算使用tcp方法了，暂时留着吧
			//p1soc=TCPServer.accept();
			//p1rec=new DataInputStream(p1soc.getInputStream());
			//收发数据示例
			/*
			while(Condition)
			{
				UDPServer1.receive(p1rec1);
				p2PosColumn=Methods.byteArrayToInt2(buf);
				System.out.println(p2PosColumn);
				buf=Methods.intTobyte2(p2PosColumn);
				
				UDPsnd1.send(p1snd1);
			}*/
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	//能否放入player矩阵
	int placeable(int r,int c)
	{
		if(pler.nodes_w[r][c]==0&&pler.nodes_b[r][c]==0)
			return 1;//可放入
		return 0;//不可放入
	}
			
}
