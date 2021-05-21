package pro1_sopgomore_javaweb_works;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//玩家：黑
public class UDPClient2 implements Runnable
{
	public static void main(String[] args)
	{
		try {
			new Thread(new UDPClient(args,1)).start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//游戏数据部
	int GAME_STATUS=2;
	int player;//玩家，0是黑，1是白
	int mpr,mpc;//MyPosRow,MyPosColumn
	GameEnv ge;
	int CLICK_ALLOW;
	
	//服务器接口部
	String[] args;
	InetAddress add=null;
	
	DatagramSocket soc=null;//发送器
	DatagramSocket roc=null;//接收器
	
	DatagramPacket snd=null;//发送包
	DatagramPacket rec=null;//接收包
	
	
	byte[] buf=new byte[100];
	byte[] bufrec=new byte[100];
	
	UDPClient2(String[] args,int player) throws Exception
	{
		if(player==1||player==0)
		{
			this.player=player;
			this.args=args;
			ge=new GameEnv(args,player);
		}
		else
			throw new Exception(String.format("WRONG PLAYER ID:%d",player));
	}
	@Override
	public void run() {
		//e.run(this.args);
		try {
			add=InetAddress.getByName("localhost");
			//8001玩家1服务器接口，8003玩家1接收器，8002玩家2服务器接口，8004玩家2接收器
			this.soc=new DatagramSocket();
			this.roc=new DatagramSocket(8003+this.player);
			this.snd=new DatagramPacket(buf,buf.length,add,8001+this.player);
			this.rec=new DatagramPacket(bufrec,bufrec.length,add,8003+this.player);
			buf=Methods.intTobyte2(this.player);
			soc.send(snd);
			
			//this.roc=new DatagramSocket(8003);
			
			while(this.GAME_STATUS==2)
			{
				
				//允许点击行为
				//方块位置记录部分
				
				
				//buf转换区
					//buf=Methods.intTobyte2(this.GAME_STATUS);
				
				
				
				
				//buf发送器
				/*
				 11111111111111111
				       同时，无视棋盘点击行为
				 11111111111111111
				 */
				soc.send(snd);
				
				
				//bufrec接收器
				roc.receive(rec);
				
				
				//GAME_STATUS=Methods.byteArrayToInt2(buf);
				//System.out.println(GAME_STATUS);
			}
			//流关闭区
			//s.close();
			//soc.close();
		}catch(Exception e)
		{
			
		}
	}
}