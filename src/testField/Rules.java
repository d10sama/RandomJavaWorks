package testField;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;


//静态类
//若有全局方法及变量则写入其中
public class Rules{
	static int result(Player pler)
	{
		int isDraw=draw(pler);
		int isWon=winner(pler);
		System.out.println(isDraw+" "+isWon);
		if(isDraw==1)
			return -1;
		else
			return isWon;
	}
	
	//平局状况
	static int draw(Player pler)
	{
		if(pler.nodecount<=0)
			return 1;
		else 
			return 0;
	}
	//-1平局，0白赢，1黑赢，2什么也不发生
	static int winner(Player pler)
	{
		int PresentLen_b=0;
		int MaxLen_b=0;
		int PresentLen_w=0;
		int MaxLen_w=0;
		if(draw(pler)==1)
			return -1;
		//竖着来
		for(int column=0;column<15;column++)
		{	
			int row=0;
			PresentLen_w=0;
			PresentLen_b=0;
			while(row<15)
			{
				 
				if(pler.nodes_w[row][column]==1)
					PresentLen_w++;
				else
					MaxLen_w=MaxLen_w<PresentLen_w?PresentLen_w:MaxLen_w;
				if(pler.nodes_b[row][column]==1)
					PresentLen_b++;
				else if(pler.nodes_w[row][column]==0)
				{	
					MaxLen_w=MaxLen_w<PresentLen_w?PresentLen_w:MaxLen_w;
					PresentLen_w=0;
				}
				else if(pler.nodes_b[row][column]==0)
				{	MaxLen_b=MaxLen_b<PresentLen_b?PresentLen_b:MaxLen_b;
					PresentLen_b=0;
				}
				row++;
			}
			MaxLen_w=MaxLen_w<PresentLen_w?PresentLen_w:MaxLen_w;
			MaxLen_b=MaxLen_b<PresentLen_b?PresentLen_b:MaxLen_b;
		}
		if(MaxLen_b>4)
			return 1;
		else if(MaxLen_w>4)
			return 0;
		//横着来
		for(int row=0;row<15;row++)
		{
			int column=0;
			PresentLen_w=0;
			PresentLen_b=0;
			while(column<15)
			{
				 
				if(pler.nodes_w[row][column]==1)
					PresentLen_w++;
				else
					MaxLen_w=MaxLen_w<PresentLen_w?PresentLen_w:MaxLen_w;
				if(pler.nodes_b[row][column]==1)
					PresentLen_b++;
				else if(pler.nodes_w[row][column]==0)
				{	MaxLen_w=MaxLen_w<PresentLen_w?PresentLen_w:MaxLen_w;
					PresentLen_w=0;
				}
				else if(pler.nodes_b[row][column]==0)
				{	MaxLen_b=MaxLen_b<PresentLen_b?PresentLen_b:MaxLen_b;
					PresentLen_b=0;
				}
				column++;
			}
			MaxLen_w=MaxLen_w<PresentLen_w?PresentLen_w:MaxLen_w;
			MaxLen_b=MaxLen_b<PresentLen_b?PresentLen_b:MaxLen_b;
		}
		if(MaxLen_b>4)
			return 1;
		else if(MaxLen_w>4)
			return 0;
		//左下到右上
		for(int i=5;i<15;i++)
		{
			int row=i;
			int column=0;
			PresentLen_w=0;
			PresentLen_b=0;
			while(column<=i)
			{
				 
				if(pler.nodes_w[row][column]==1)
					PresentLen_w++;
				else if(pler.nodes_w[row][column]==0)
				{	MaxLen_w=MaxLen_w<PresentLen_w?PresentLen_w:MaxLen_w;
				PresentLen_w=0;
				}
				if(pler.nodes_b[row][column]==1)
					PresentLen_b++;
				else if(pler.nodes_b[row][column]==0)
				{	MaxLen_b=MaxLen_b<PresentLen_b?PresentLen_b:MaxLen_b;
					PresentLen_b=0;
				}
				column++;
				row--;
			}
			MaxLen_w=MaxLen_w<PresentLen_w?PresentLen_w:MaxLen_w;
			MaxLen_b=MaxLen_b<PresentLen_b?PresentLen_b:MaxLen_b;
		}
		if(MaxLen_b>4)
			return 1;
		else if(MaxLen_w>4)
			return 0;
		for(int precolumn=0;precolumn<=10;precolumn++)
		{
			int row=14;
			int column=precolumn;
			PresentLen_w=0;
			PresentLen_b=0;
			while(column<15&&row>-1)
			{
				 
				if(pler.nodes_w[row][column]==1)
					PresentLen_w++;
				else if(pler.nodes_w[row][column]==0)
				{	MaxLen_w=MaxLen_w<PresentLen_w?PresentLen_w:MaxLen_w;
				PresentLen_w=0;
				}
				if(pler.nodes_b[row][column]==1)
					PresentLen_b++;
				else if(pler.nodes_b[row][column]==0)
				{	MaxLen_b=MaxLen_b<PresentLen_b?PresentLen_b:MaxLen_b;
				PresentLen_b=0;
				}
				row--;
				column++;
			}
			MaxLen_w=MaxLen_w<PresentLen_w?PresentLen_w:MaxLen_w;
			MaxLen_b=MaxLen_b<PresentLen_b?PresentLen_b:MaxLen_b;
		}
		if(MaxLen_b>4)
			return 1;
		else if(MaxLen_w>4)
			return 0;
		//左上到右下
		for(int precolumn=10;precolumn>-1;precolumn--)
		{
			int row=0;
			int column=precolumn;
			PresentLen_w=0;
			PresentLen_b=0;
			while(column<15)
			{
				 
				if(pler.nodes_w[row][column]==1)
					PresentLen_w++;
				else if(pler.nodes_w[row][column]==0)
				{	MaxLen_w=MaxLen_w<PresentLen_w?PresentLen_w:MaxLen_w;
				PresentLen_w=0;
				}
				if(pler.nodes_b[row][column]==1)
					PresentLen_b++;
				else if(pler.nodes_b[row][column]==0)
				{	MaxLen_b=MaxLen_b<PresentLen_b?PresentLen_b:MaxLen_b;
					PresentLen_b=0;
				}
				column++;
				row++;
			}
			MaxLen_w=MaxLen_w<PresentLen_w?PresentLen_w:MaxLen_w;
			MaxLen_b=MaxLen_b<PresentLen_b?PresentLen_b:MaxLen_b;
		}
		if(MaxLen_b>4)
			return 1;
		else if(MaxLen_w>4)
			return 0;
		for(int preRow=0;preRow<=10;preRow++)
		{
			int row=preRow;
			int column=0;
			PresentLen_w=0;
			PresentLen_b=0;
			while(row<15)
			{
				 
				if(pler.nodes_w[row][column]==1)
					PresentLen_w++;
				else if(pler.nodes_w[row][column]==0)
				{	MaxLen_w=MaxLen_w<PresentLen_w?PresentLen_w:MaxLen_w;
					PresentLen_w=0;
				}
				if(pler.nodes_b[row][column]==1)
					PresentLen_b++;
				else if(pler.nodes_b[row][column]==0)
				{	MaxLen_b=MaxLen_b<PresentLen_b?PresentLen_b:MaxLen_b;
					PresentLen_b=0;
				}
				column++;
				row++;
			}
			MaxLen_w=MaxLen_w<PresentLen_w?PresentLen_w:MaxLen_w;
			MaxLen_b=MaxLen_b<PresentLen_b?PresentLen_b:MaxLen_b;
		}
		if(MaxLen_b>4)
			return 1;
		else if(MaxLen_w>4)
			return 0;
		return 2;
	}
}
class Methods
{
	public static byte[] intTobyte2(int num)
    {
        byte[] result=null;
        ByteArrayOutputStream bos=new ByteArrayOutputStream(100);
        DataOutputStream dos=new DataOutputStream(bos);
        try {
            dos.writeInt(num);
            result=bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
	public static int byteArrayToInt2(byte[] arr)
    {
        int result=0;
        ByteArrayInputStream bis=new ByteArrayInputStream(arr);
        DataInputStream dis=new DataInputStream(bis);
        try {
            result= dis.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
