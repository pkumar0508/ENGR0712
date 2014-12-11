import java.util.Random;

public class Grid
{
	private boolean[][] grid;
	final private int N;
	
	// constructor
	public Grid (int n)
	{
		N = n;
		clear();
	}
	
	// accessors
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
	
	// mutators
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
	
	// tester method
	public boolean hasPath ()
	{
		Grid c = new Grid(N);
		return hasPath(0, c);
	}
	
	private boolean hasPath (int x, Grid c)
	{
		if (x < N - 1)
			return hasPath(x, 0, c) || hasPath(x + 1, c);
		
		return hasPath(x, 0, c);
	}
	
	private boolean hasPath(int x, int y, Grid c)
	{
		if (x < 0 || y < 0 || y >= N || x >= N)
			return false;
		if (c.get(x, y))
			return false;
		if (!get(x, y))
			return false;
		if (y == N - 1)
			return true;
		
		c.set(x, y, true);
		
		return hasPath(x - 1, y, c) ||
			   hasPath(x + 1, y, c) ||
			   hasPath(x, y + 1, c) ||
			   hasPath(x, y - 1, c);
	}
	
	// only valid for small N
	/*
	public String toString()
	{
		String s = new String();
		final String SEPARATOR = " ";
		
		for (int i = 0; i < N; i++)
		{
			s += getInt(i, 0);
			for (int j = 1; j < N; j++)
				s += SEPARATOR + getInt(i, j);
			s += "\n";
		}
		
		return s;
	}
	*/
}