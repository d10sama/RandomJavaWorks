package pro1_sopgomore_javaweb_works;

import java.io.DataInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

}
class OnlineJudge extends Thread
{

	InetAddress addr=null;
	
	ServerSocket TCPServer=null;
	Socket p1soc=null;
	DatagramSocket UDPServer=null;
	DatagramPacket p2rec1=null;
	DatagramPacket p2rec2=null;
	DataInputStream p1rec=null;
	OnlineJudge()
	{
		
	}
	@Override
	public void run()
	{
	}
	
}
