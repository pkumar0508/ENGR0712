public class ClusterGrid
{
	private final int N;
	private int count;
	private int[][] clusters;
	private final int DIGITS = 2;
	private Grid grid;
	
	public ClusterGrid (Grid g)
	{
		grid = g;
		N = g.size();
		count = 0;
		clusters = new int[N][N];
	}
	
	private int get (int x, int y)
	{ return clusters[x][y]; }
	
	private void set (int x, int y, int value)
	{ clusters[x][y] = value; }
	
	public int countClusters ()
	{
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				count = killBlob(i, j);
		
		return count;
	}
	
	private int killBlob (int i, int j)
	{
		if (grid.get(i, j))
		{
			count++;
			killBlob(i, j, count);
		}
		
		return count;
	}
	
	private void killBlob (int i, int j, int c)
	{
		if (i >= 0 && i < N && j >= 0 && j < N && grid.get(i, j))
		{
			grid.set(i, j, false);
			set(i, j, c);
			
			killBlob(i - 1, j, c);
			killBlob(i, j - 1, c);
			killBlob(i + 1, j, c);
			killBlob(i, j + 1, c);
		}
	}
	
	public String toString()
	{
		String s = new String();
		final String SEPARATOR = " ";
		
		for (int i = 0; i < N; i++)
		{
			s += number(i, 0);
			for (int j = 1; j < N; j++)
				s += SEPARATOR + number(i, j);
			s += "\n";
		}
		
		return s;
	}
	
	private String number (int i, int j)
	{
		int pow10 = 1;
		String s = "";
		final String FILLER = " ";
		
		for (int x = 1; x < DIGITS; x++)
		{
			pow10 *= 10;
			if (get(i, j) < pow10)
				s += FILLER;
		}
		s += get(i, j);
		
		return s;
	}
}