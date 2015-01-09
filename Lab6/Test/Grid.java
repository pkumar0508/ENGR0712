import java.util.Random;
import java.util.HashSet;

public class Grid
{
	// variables
	private HashSet<Position> grid;
	private final int SIZE;
	private static final Random r = new Random();
	
	// constructors
	public Grid (int size)
	{
		SIZE = size;
		grid = new HashSet<>();
	}
	
	// accessors
	public boolean get (Position p)
	{ return grid.contains(p); }
	
	public int size ()
	{ return SIZE; }
	
	// method
	public void populate (double P)
	{
		int n = (int) (side() * side() * P);
		int i = 0;
		Position p;
		
		grid = new HashSet<>();
		while (i < n)
		{
			p = randomPosition();
			if (!get(p))
			{
				set(p);
				i++;
			}
		}
	}
	
	// method helpers
	private Position randomPosition ()
	{
		return new Position(r.nextInt(side()) - SIZE,
							r.nextInt(side()) - SIZE);
	}
	
	private int side ()
	{ return 2 * SIZE + 1; }
	
	private void set (Position p)
	{ set(p, true); }
	
	private void set (Position p, boolean value)
	{
		if (value)
			grid.add(p);
		else
			grid.remove(p);
	}
	
	// toString
	public String toString ()
	{
		String s = "";
		Position p;
		
		for (int i = -SIZE; i <= SIZE; i++)
		{
			for (int j = -SIZE; j <= SIZE; j++)
			{
				p = new Position(i, j);
				if (grid.contains(p))
					s += "*";
				else
					s += "-";
			}
			
			s += "\n";
		}
		
		return s;	
	}
}