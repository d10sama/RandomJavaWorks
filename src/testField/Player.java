package testField;

//╟ввсохвъ

public class Player {
	
	boolean isWhite=true;
	public int[][] nodes_w;
	public int[][] nodes_b;
	int status=0;
	public int nodecount=225;
	int wClickedRow=-1,wClickedColumn=-1;
	int bClickedRow=-1,bClickedColumn=-1;
	
	public Player()
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
