package app.pkumar0508.engr0712.lab3;

import java.util.HashSet;

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
	
	public int get (int x, int y)
	{ return clusters[x][y]; }
	
	public int count ()
	{ return count; }
	
	// method
	public void countClusters ()
	{
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				killBlob(i, j);
	}
	
	private void killBlob (int i, int j)
	{
		if (grid.get(i, j))
		{
			count++;
			killBlob(i, j, count);
		}
	}
	
	private void killBlob (int i, int j, int c)
	{
		if (i >= 0 && i < N && j >= 0 && j < N && grid.get(i, j))
		{
			grid.set(i, j, false);
			clusters[i][j] = c;
			
			killBlob(i - 1, j, c);
			killBlob(i, j - 1, c);
			killBlob(i + 1, j, c);
			killBlob(i, j + 1, c);
		}
	}
	
	public boolean hasPath ()
	{
		HashSet<Integer> s = new HashSet<>();
		boolean flag = false;
		int j = 0;
		
		for (int i = 0; i < N; i++)
			s.add(new Integer(clusters[i][0]));
		while (j < N && !flag)
			flag |= s.contains(new Integer(clusters[j][N]));
		
		return flag;
	}
}
