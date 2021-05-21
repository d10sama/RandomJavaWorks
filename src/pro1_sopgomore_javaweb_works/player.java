package pro1_sopgomore_javaweb_works;

//╟ввсохвъ

public class Player {
	
	boolean isWhite=true;
	int[][] nodes_w;
	int[][] nodes_b;
	int status=0;
	int nodecount=225;
	int wClickedRow,wClickedColumn;
	int bClickedRow,bClickedColumn;
	
	Player()
	{
		nodes_w=new int[15][15];
		nodes_b=new int[15][15];
		for(int i=0;i<15;i++)
			for(int j=0;j<15;j++)
			{
				nodes_w[i][j]=0;
				nodes_b[i][j]=0;
			}
	}
	int status()
	{
		return status;
	}
	void refresh()
	{
		nodecount=225;
		for(int i=0;i<15;i++)
			for(int j=0;j<15;j++)
			{
				nodes_w[i][j]=0;
				nodes_b[i][j]=0;
			}
		isWhite=true;
	}
	void print(int mode)
	{
		if (mode==1)
		{
			System.out.println("-------------------------------------------------");
			for(int i=0;i<15;i++)
			{	for(int j=0;j<15;j++)
				{	
					System.out.printf("%2d,%2d,%d,%d   ", i,j,nodes_w[i][j],nodes_b[i][j]);
				}System.out.println("\t");
			}
		}else
		{
			
		}
		
	}
}
