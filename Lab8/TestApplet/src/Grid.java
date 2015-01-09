import java.util.HashSet;
import java.util.Random;
import java.awt.Graphics;
import java.util.Collection;
import java.util.Iterator;

public class Grid
{
	// variables
	private static final Random r = new Random();
	private static boolean DEBUG = true;
	
	private HashSet<Position> s;
	
	// constructor
	public Grid (Collection<Position> c)
	{ s = new HashSet<>(c); }
	
	private void debug (String s)
	{
		if (DEBUG)
			System.out.print(s);
	}
	
	private boolean get (Position p)
	{ return s.contains(p); }
	
	// method
	public void nextGeneration ()
	{
		HashSet<Position> check = new HashSet<>(s),
				next = new HashSet<>(),
				temp = new HashSet<>();
		int count;
		boolean alive;
		Position p, q;
		
		for (Iterator i = s.iterator(); i.hasNext();)
		{
			p = (Position) i.next();
			check.addAll(p.neighbors());
		}
		
		for (Iterator j = check.iterator(); j.hasNext();)
		{
			p = (Position) j.next();
			count = 0;
			alive = get(p);
			
			temp.clear();
			temp.addAll(p.neighbors());
			for (Iterator k = temp.iterator(); k.hasNext();)
			{
				q = (Position) k.next();
				if (get(q))
					count++;
			}
			
			if (alive && (count == 2 || count == 3))
				next.add(p);
			
			if (!alive && count == 3)
				next.add(p);
		}
		
		s = next;
	}
	
	// graphics method
	public void paint (Graphics g, int size, int scale, int R)
	{
		Position p;
		
		g.clearRect(0, 0, size, size);
		
		for (Iterator i = s.iterator(); i.hasNext();)
		{
			p = (Position) i.next();
			
			g.fillRect(p.x * scale, p.y * scale, R, R);
		}
	}
}