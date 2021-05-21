package pro1_sopgomore_javaweb_works;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//��ң���
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
	
	
	//��Ϸ���ݲ�
	int GAME_STATUS=2;
	int player;//��ң�0�Ǻڣ�1�ǰ�
	int mpr,mpc;//MyPosRow,MyPosColumn
	GameEnv ge;
	int CLICK_ALLOW;
	
	//�������ӿڲ�
	String[] args;
	InetAddress add=null;
	
	DatagramSocket soc=null;//������
	DatagramSocket roc=null;//������
	
	DatagramPacket snd=null;//���Ͱ�
	DatagramPacket rec=null;//���հ�
	
	
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
			//8001���1�������ӿڣ�8003���1��������8002���2�������ӿڣ�8004���2������
			this.soc=new DatagramSocket();
			this.roc=new DatagramSocket(8003+this.player);
			this.snd=new DatagramPacket(buf,buf.length,add,8001+this.player);
			this.rec=new DatagramPacket(bufrec,bufrec.length,add,8003+this.player);
			buf=Methods.intTobyte2(this.player);
			soc.send(snd);
			
			//this.roc=new DatagramSocket(8003);
			
			while(this.GAME_STATUS==2)
			{
				
				//��������Ϊ
				//����λ�ü�¼����
				
				
				//bufת����
					//buf=Methods.intTobyte2(this.GAME_STATUS);
				
				
				
				
				//buf������
				/*
				 11111111111111111
				       ͬʱ���������̵����Ϊ
				 11111111111111111
				 */
				soc.send(snd);
				
				
				//bufrec������
				roc.receive(rec);
				
				
				//GAME_STATUS=Methods.byteArrayToInt2(buf);
				//System.out.println(GAME_STATUS);
			}
			//���ر���
			//s.close();
			//soc.close();
		}catch(Exception e)
		{
			
		}
	}
}