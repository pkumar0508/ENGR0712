import java.util.Random;

public class Grid
{
	private boolean[][] grid;
	private final int N;
	private int count;
	
	public Grid (int n)
	{
		N = n;
		count = 0;
		clear();
	}
	
	public int getInt (int x, int y)
	{
		if(grid[x][y])
			return 1;
		return 0;
	}
	
	public boolean get (int x, int y)
	{ return grid[x][y]; }
	
	public int size ()
	{ return N; }
	
	public void clear ()
	{ grid = new boolean[N][N]; }
	
	public void set (int x, int y, boolean value)
	{ grid[x][y] = value; }
	
	public void populate (double p)
	{
		Random r = new Random();
		int n = (int) (N * N * p);
		int i = 0;
		int x, y;
		
		while (i < n)
		{
			x = r.nextInt(N);
			y = r.nextInt(N);
			if (!grid[x][y])
			{
				grid[x][y] = true;
				i++;
			}
		}
	}
}