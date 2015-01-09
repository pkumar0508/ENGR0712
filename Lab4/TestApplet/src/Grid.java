import java.awt.Graphics;
import java.util.Random;
import java.util.Hashtable;
import java.util.ArrayList;
import java.awt.Color;

public class Grid
{
	// variables
	private Hashtable<Position, Particle> grid;
	private int SCALE;
	private int SIZE;
	
	private static int BUFFER = 5;
	private static int R = 2;
	
	private static final Random random = new Random();
	private static final double CIRCLE = 8 * Math.atan(1.0);
	
	// constructors
	public Grid (int size)
	{
		SCALE = 1;
		SIZE = size;
		grid = new Hashtable<>();
	}
	
	public Grid (int size, int scale)
	{
		SCALE = scale;
		SIZE = size / scale;
		grid = new Hashtable<>();
	}
	
	// public methods
	public void put (Particle q, Graphics g)
	{
		Graphics G[] = { g };
		put(q, G);
	}
	
	public void put (Particle q, Graphics g[])
	{
		Position p = q.position();
		
		if (validPos(p))
		{
			grid.put(p, q);
			drawParticle(g, q);
		}
	}
	
	public void setR (int r)
	{ R = r; }
	
	public void setBuffer (int buffer)
	{ BUFFER = buffer; }
	
	public Particle get (Position p)
	{ return (Particle) grid.get(p); }
	
	public int size ()
	{ return SIZE; }
	
	// method overloads
	public void aggregate (int n, Graphics g[])
	{ aggregate (n, 1.0, g); }
	
	public void aggregate (int n, Graphics g)
	{
		Graphics[] G = { g };
		aggregate (n, 1.0, G);
	}
	
	public void aggregate (int n, double p, Graphics g)
	{
		Graphics[] G = { g };
		aggregate (n, p, G);
	}
	
	// method
	public void aggregate (int n, double prob, Graphics g[])
	{
		int r, max = 0;
		Position p;
		Particle q;
		
		for (int i = 0; i < n; i++)
		{
			r = optimize(max);
			if (r == size())				break;
			
			p = randomWalk(r, prob);
			
			if (Math.abs(p.x) > max)	max = Math.abs(p.x);
			if (Math.abs(p.y) > max)	max = Math.abs(p.y);
			
			q = new Particle(p, randomColor());
			put(q, g);
			drawParticle(g, q);
			debug(max, r, i, p, q);
		}
	}
	
	// method helpers
	protected Position randomWalk (int r, double prob)
	{
		Position p;
		boolean endState;
		
		p = randomPosition(r);
		endState = false;
		while (!endState)
		{
			while (!hasESN(p))
				nextPos(p, r);
			
			if (nextDouble() < prob)
				endState = true;
			else
				nextPos(p, r);
		}
		
		return p;
	}
	
	protected boolean hasESN (Position p)
	{
		ArrayList<Position> l = neighbors(p);
		boolean flag = false;
		Particle q;
		
		int i = 0;
		while (i < l.size() && !flag)
		{
			q = get((Position) l.get(i));
			
			if (q != null)
				flag = true;
			i++;
		}
		
		return flag;
	}
	
	protected void nextPos (Position p, int r)
	{
		ArrayList<Position> l = neighbors(p);
		Position temp;
		
		for (int i = l.size() - 1; i >= 0; i--)
		{
			temp = (Position) l.get(i);
			if (Math.abs(temp.x) > r ||
				Math.abs(temp.y) > r ||
				get(temp) != null)
				l.remove(i);
		}
			
		temp = (Position) l.get(nextInt(l.size()));
		p.x = temp.x;
		p.y = temp.y;
	}
	
	protected void debug (int max, int r, int i, Position p, Particle q)
	{ System.out.println(max + " " + r + " " + i + " " + p + " " + q); }
	
	protected int optimize (int max)
	{ return max + BUFFER; }
	
	protected Position map (Position p)
	{ return new Position(SIZE + p.x, SIZE - p.y); }
	
	protected ArrayList<Position> neighbors (Position p)
	{
		ArrayList<Position> l = new ArrayList<>();
		
		if (validPos(p.north()))	l.add(p.north());
		if (validPos(p.south()))	l.add(p.south());
		if (validPos(p.east()))		l.add(p.east());
		if (validPos(p.west()))		l.add(p.west());
		
		return l;
	}
	
	protected boolean validPos (Position p)
	{ return Math.abs(p.x) <= SIZE && Math.abs(p.y) <= SIZE; }
	
	protected void drawParticle (Graphics g[], Particle q)
	{
		for (int i = 0; i < g.length; i++)
		{
			Position p = map(q.position());
			g[i].setColor(q.color());
			g[i].fillOval(p.x * SCALE, p.y * SCALE, R, R);
		}
		
	}
	
	protected Position randomPosition (double r)
	{
		Position p = new Position();
		double theta = CIRCLE * nextDouble();
		
		p.x = (int) (r * Math.cos(theta));
		p.y = (int) (r * Math.sin(theta));
		
		return p;
	}
	
	protected Color randomColor ()
	{ return new Color(nextInt(256), nextInt(256), nextInt(256)); }
	
	protected double nextDouble ()
	{ return random.nextDouble(); }
	
	protected int nextInt(int i)
	{ return random.nextInt(i); }
}