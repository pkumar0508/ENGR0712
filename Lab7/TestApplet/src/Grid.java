import java.util.HashMap;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;

public class Grid
{
	private static final Random r = new Random();
	
	private static int R = 15;
	private static boolean DEBUG = true;
	
	private Graphics[] g = {};
	private final int SIZE;
	private final int SCALE;
	private HashMap map;
	private final Color[] COLORS;
	private final int[] ENERGIES;
	
	public Grid (int size, int scale, Color[] colors, int[] energies)
	{
		SCALE = scale;
		SIZE = size / scale;
		COLORS = colors;
		ENERGIES = energies;
		map = new HashMap();
	}
	
	// basic functions
	public void setR (int r)
	{ R = r; }
	
	private void debug (String s)
	{
		if (DEBUG)
			System.out.print(s);
	}
	
	private Position randomPos ()
	{ return new Position(r.nextInt(SIZE), r.nextInt(SIZE)); }
	
	private boolean validPos (Position p)
	{
		return p.x >= 0 && p.x < SIZE &&
			   p.y >= 0 && p.y < SIZE;
	}
	
	private boolean occupied (Position p)
	{ return map.containsKey(p); }
	
	private void add (Position p, int i)
	{
		map.put(p, new Integer(i));
		drawParticle(p);
	}
	
	public int get (Position p)
	{
		Integer i = (Integer) map.get(p);
		return i.intValue();
	}
	
	private int remove (Position p)
	{ 
		Integer i = (Integer) map.remove(p);
		return i.intValue();
	}
	
	// populate method
	public void populate (double[] P)
	{
		int n, i;
		Position p;
		
		for (int j = 0; j < P.length; j++)
		{
			n = (int) (SIZE * SIZE * P[j]);
			i = 0;
			
			while (i < n)
			{
				p = randomPos();
				
				if (!occupied(p))
				{
					add(p, j);
					i++;
				}
			}
		}
	}
	
	// method
	public void metropolis (int n)
	{
		Position p, q;
		double beta = 0.0;
		
		for (int t = 0; t < n; t++)
		{
			for (int i = 0; i < SIZE; i++)
				for (int j = 0; j < SIZE; j++)
				{
					p = new Position(i, j);
					q = randomPos();
					while (get(p) == get(q))
						q = randomPos();
					
					beta = optimize(t);
					attemptSwitch(p, q, beta);
				}
			
			debug(t + " " + beta + "\n");
		}
	}
	
	private double optimize (int x)
	{ return Math.pow(x, .8) / 100.0; }
	
	private void attemptSwitch (Position p, Position q, double beta)
	{
		int deltaE = deltaE(p, q);
		
		if (deltaE <= 0 || boltzmann(beta, deltaE))
			swap(p, q);
	}
	
	private int deltaE (Position p, Position q)
	{
		int Ei = 0, Ef = 0;
		int flag = get(q);
		
		Ei = energy(q, flag);
		Ef = energy(p, flag);
		
		return Ef - Ei;
	}
	
	public int energy (Position p, int i1)
	{
		int i2, n1 = -1, n2 = -1, index,
			energy = 0, size = COLORS.length;
		Position[] list = { p.north(), p.east(),
							p.south(), p.west() };
		
		for (int x = 0; x < list.length; x++)
			if (validPos(list[x]))
			{
				i2 = get(list[x]);
				index = i1 * size + i2;
				energy += ENERGIES[index];
			}
		
		return energy;
	}
	
	private boolean boltzmann (double beta, int deltaE)
	{ return r.nextDouble() < Math.exp(-beta * deltaE); }
	
	private void swap (Position p, Position q)
	{
		int i1 = remove(p),
			i2 = remove(q);
		
		add(p, i2);
		add(q, i1);
	}
	
	// graphic methods
	private void drawParticle (Position p)
	{
		for (int x = 0; x < g.length; x++)
		{
			g[x].setColor(COLORS[get(p)]);
			g[x].fillOval(p.x * SCALE, p.y * SCALE, R, R);
		}
	}
	
	public void setG (Graphics[] G)
	{ g = G; }
	
	// toString method
	public String toString ()
	{
		String s = "";
		Position p;
		
		for (int i = 0; i < SIZE; i++)
		{
			for (int j = 0; j < SIZE; j++)
			{
				p = new Position(i, j);
				s += get(p) + " ";
			}
			
			s += "\n";
		}
		
		return s;
	}
}