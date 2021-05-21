package pro1_sopgomore_javaweb_works;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;

class TCPClient implements Runnable
{
	int player;
	String[] args;
	InetAddress add=null;
	Socket soc=null;
	DataOutputStream snd=null;
	DataInputStream rec=null;
	Envs e=null;
	byte[] buf=new byte[100];
	public static void main(String[] args)
	{
		new Thread(new TCPClient(args,1)).start();
	}
	
	
	TCPClient(String[] args,int player)
	{
		e=new Envs();
		this.player=player;
		this.args=args;
	}
	@Override
	public void run() {
		//e.run(args);
		
	}
	
}