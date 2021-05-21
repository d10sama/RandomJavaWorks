package pro1_sopgomore_javaweb_works;

import java.io.DataInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

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
	//网络连接部
	InetAddress addr=null;
	ServerSocket TCPServer=null;
	Socket p1soc=null;
	DatagramSocket UDPServer=null;
	DatagramSocket UDPsnd=null;
	DatagramPacket p2rec1=null;
	DatagramPacket p2snd1=null;
	DataInputStream p1rec=null;
	
	byte[] buf=new byte[100];
	byte[] bufsnd=new byte[100];

	
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
			UDPsnd=new DatagramSocket();
			p2rec1=new DatagramPacket(buf,buf.length,addr,8001);
			p2snd1=new DatagramPacket(bufsnd,bufsnd.length,addr,8003);
			//下两行是tcp，先注释了
			//p1soc=TCPServer.accept();
			//p1rec=new DataInputStream(p1soc.getInputStream());
			
			while(p2PosColumn!=2)
			{
				UDPServer.receive(p2rec1);
				p2PosColumn=Methods.byteArrayToInt2(buf);
				System.out.println(p2PosColumn);
				buf=Methods.intTobyte2(p2PosColumn);
				
				UDPsnd.send(p2snd1);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
