package pro1_sopgomore_javaweb_works;

public class player {
	boolean isWhite=true;
	char[][] nodes;
	int nodecount=300;
	player()
	{
		nodes=new char[20][15];
		for(int i=0;i<20;i++)
			for(int j=0;j<15;j++)
			{
				nodes[i][j]='*';
			}
	}
	void refresh()
	{
		nodecount=300;
		for(int i=0;i<20;i++)
			for(int j=0;j<15;j++)
			{
				nodes[i][j]='*';
			}
		isWhite=true;
	}
}
