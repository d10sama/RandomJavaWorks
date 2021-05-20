package pro1_sopgomore_javaweb_works;

import java.io.DataInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

}
class OnlineJudge 
{
	public static void main(String[] args)
	{
		OJ server=new OJ();
		server.start();
	}
}
class OJ extends Thread
{
	//游戏连接部
	
	//网络连接部
	InetAddress addr=null;
	ServerSocket TCPServer=null;
	Socket p1soc=null;
	DatagramSocket UDPServer=null;
	DatagramPacket p2rec1=null;
	DatagramPacket p2rec2=null;
	DataInputStream p1rec=null;
	
	byte[] buf=new byte[100];

	
	OJ()
	{
		//pler=new Player();
	}
	@Override
	public void run()
	{
		try {
			addr=InetAddress.getByName("localhost");
			TCPServer=new ServerSocket(8000);
			UDPServer=new DatagramSocket(8001);
			p2rec1=new DatagramPacket(buf,buf.length,addr,8001);
			p2rec2=new DatagramPacket(buf,buf.length,addr,8001);
			p1soc=TCPServer.accept();
			p1rec=new DataInputStream(p1soc.getInputStream());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
