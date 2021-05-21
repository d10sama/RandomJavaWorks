package pro1_sopgomore_javaweb_works;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

//玩家：黑
public class UDPClient implements Runnable
{
	public static void main(String[] args)
	{
		new Thread(new UDPClient(args,0)).start();
	}
	
	int player;//玩家，0是黑，1是白
	String[] args;
	InetAddress add=null;
	DatagramSocket soc=null;
	DatagramSocket roc=null;
	DatagramPacket snd=null;
	DatagramPacket rec=null;
	Envs e=null;
	
	byte[] buf=new byte[100];
	byte[] bufrec=new byte[100];
	
	UDPClient(String[] args,int player)
	{
		this.player=player;
		this.args=args;
		e=new Envs();
	}
	@Override
	public void run() {
		//e.run(this.args);
		try {
			add=InetAddress.getByName("localhost");
			this.soc=new DatagramSocket();
			this.roc=new DatagramSocket(8003);
			int input=0;
			Scanner s=new Scanner(System.in);
			while(input!=2)
			{
				System.out.println("next方式接收：");
				input=s.nextInt();
				buf=Methods.intTobyte2(input);
				this.snd=new DatagramPacket(buf,buf.length,add,8001);
				soc.send(snd);
				this.rec=new DatagramPacket(bufrec,bufrec.length,add,8003);
				roc.receive(rec);
				input=Methods.byteArrayToInt2(buf);
				System.out.println(input);
			}
			s.close();
			soc.close();
		}catch(Exception e)
		{
			
		}
	}
}