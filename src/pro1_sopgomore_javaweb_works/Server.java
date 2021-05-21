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
	//��Ϸ���Ӳ�
	int p1PosColumn=-1,p1PosRow=-1;
	int p2PosColumn=-1,p2PosRow=-1;
	Player pler;
	int GAME_STATUS=2;
	int isPlaceable=0;
	//�������Ӳ�
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
			
			UDPServer1=new DatagramSocket(8001);//8001�������1����
			UDPServer2=new DatagramSocket(8002);//8002�������2����
			UDPsnd1=new DatagramSocket();//�����1������
			UDPsnd2=new DatagramSocket();//�����2������
			
			//���1���ܷ������ݰ���ʼ��
			p1rec1=new DatagramPacket(buf,buf.length,addr,8001);
			p1snd1=new DatagramPacket(bufsnd,bufsnd.length,addr,8003);
			//���2���ܷ������ݰ���ʼ��
			p2rec1=new DatagramPacket(buf,buf.length,addr,8002);
			p2snd1=new DatagramPacket(bufsnd,bufsnd.length,addr,8004);
			
			while(GAME_STATUS==2)
			{
			//���Ӳ���
				while(this.isPlaceable==0)
				{
					//�������1����λ��
					UDPServer1.receive(p1rec1);
					this.p1PosRow=Methods.byteArrayToInt2(buf);
					UDPServer1.receive(p1rec1);
					this.p1PosColumn=Methods.byteArrayToInt2(buf);
					//����ܷ����
					this.isPlaceable=placeable(p1PosRow,p1PosColumn);
					//���ط��������ÿͻ��˸������̣�����ʲôҲ����
					bufsnd=Methods.intTobyte2(this.isPlaceable);
					UDPsnd1.send(p1snd1);
				}
				//�޸�pler������ֵ
				pler.nodes_w[this.p1PosRow][this.p1PosColumn]=1;

				//�ж��Ƿ�ʤ��
				GAME_STATUS=Rules.winner(pler);
				this.isPlaceable=0;
				if(GAME_STATUS!=2)
					break;
				
				while(this.isPlaceable==0)
				{
					//�������1����λ��
					UDPServer2.receive(p2rec1);
					this.p2PosRow=Methods.byteArrayToInt2(buf);
					UDPServer2.receive(p2rec1);
					this.p2PosColumn=Methods.byteArrayToInt2(buf);
					//����ܷ����
					this.isPlaceable=placeable(p2PosRow,p2PosColumn);
					//���ط��������ÿͻ��˸������̣�����ʲôҲ����
					bufsnd=Methods.intTobyte2(this.isPlaceable);
					UDPsnd2.send(p2snd1);
				}
				//�޸�pler������ֵ
				pler.nodes_b[this.p1PosRow][this.p1PosColumn]=1;
				
				//�ж��Ƿ�ʤ��
				GAME_STATUS=Rules.winner(pler);
				this.isPlaceable=0;
			}
			bufsnd=Methods.intTobyte2(this.GAME_STATUS);
			System.out.println("�Ծֽ��"+this.GAME_STATUS);
			UDPsnd1.send(p1snd1);
			UDPsnd2.send(p1snd1);
			UDPsnd1.close();
			UDPsnd2.close();
			//��������tcp����ע����
			//������ʹ��tcp�����ˣ���ʱ���Ű�
			//p1soc=TCPServer.accept();
			//p1rec=new DataInputStream(p1soc.getInputStream());
			//�շ�����ʾ��
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
	//�ܷ����player����
	int placeable(int r,int c)
	{
		if(pler.nodes_w[r][c]==0&&pler.nodes_b[r][c]==0)
			return 1;//�ɷ���
		return 0;//���ɷ���
	}
			
}
