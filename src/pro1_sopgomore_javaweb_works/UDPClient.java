package pro1_sopgomore_javaweb_works;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient implements Runnable
{
	int player;//��ң�0�Ǻڣ�1�ǰ�
	String[] args;
	InetAddress add=null;
	DatagramSocket soc=null;
	DatagramPacket snd=null;
	DatagramPacket rec=null;
	Envs e=null;
	
	public static void main(String[] args)
	{
		new Thread(new TCPClient(args,0)).start();
	}
	byte[] buf=new byte[100];
	
	UDPClient(String[] args,int player)
	{
		this.player=player;
		this.args=args;
		e=new Envs();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		e.run(this.args);
	}
}